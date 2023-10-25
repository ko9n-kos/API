package jsonplaceholder.requestsTest;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import jsonplaceholder.pojo.PostData;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static framework.PropertyReader.getProperties;
import static framework.Specification.*;
import static org.hamcrest.Matchers.*;
import static jsonplaceholder.pojo.PostData.checkIdSorting;

public class PostsTest {
    Faker faker = new Faker();

    @Test
    public void getAllPosts() throws IOException {
        List<PostData> posts = GET(200, getProperties("config.properties", "postsRequest"))
                .then().assertThat().contentType(ContentType.JSON)
                .extract().body().jsonPath().getList("$", PostData.class);
        checkIdSorting(posts);
    }

    @Test
    public void getPostSuccess() throws IOException {
        GET(200, getProperties("config.properties", "postsRequest") + 99)
                .then().body("userId", equalTo(10),
                        "id", equalTo(99),
                        "title", notNullValue(),
                        "body", notNullValue());
    }

    @Test
    public void getPostFailure() throws IOException {
        GET(404, getProperties("config.properties", "postsRequest") + 150)
                .then().body("isEmpty()", is(true));
    }

    @Test
    public void createPost() throws IOException {
        expectedResponse(201);
        PostData postToSend = new PostData(1, 101, faker.dog().name(), faker.dog().name());
        POST(getProperties("config.properties", "postsRequest"), postToSend)
                .then().body("userId", equalTo(postToSend.getUserId()),
                        "title", equalTo(postToSend.getTitle()),
                        "body", equalTo(postToSend.getBody()))
                .body("$", hasKey("id"));
    }
}