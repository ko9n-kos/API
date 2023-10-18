package requestsTest;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import pojo.PostData;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.UserData;

import java.io.IOException;
import java.util.List;

import static commonLogic.DataGen.getRandomText;
import static commonLogic.DataGen.randomNumWith1;
import static commonLogic.Specification.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static pojo.UserData.getExpectedUser5;


public class RestTest {
    Gson gson = new Gson();

    @Test
    public void getAllPosts() throws IOException {
        expectedResponse(200);
        List<PostData> posts = given()
                .when()
                .get(PostData.postsRequest)
                .then().log().all()
                .assertThat().contentType(ContentType.JSON)
                .extract().body().jsonPath().getList("$", PostData.class);
        List<Integer> ids = posts.stream().map(PostData::getId).toList();
        List<Integer> sortedIds = ids.stream().sorted().toList();
        Assert.assertEquals(ids, sortedIds);
    }

    @Test
    public void getPostSuccess() throws IOException {
        expectedResponse(200);
        given()
                .when()
                .get(PostData.postsRequest + 99)
                .then().log().all()
                .body("userId", equalTo(10),
                        "id", equalTo(99),
                        "title", notNullValue(),
                        "body", notNullValue());
    }

    @Test
    public void getPostFailure() throws IOException {
        expectedResponse(404);
        given()
                .when()
                .get(PostData.postsRequest + 150)
                .then().log().all()
                .body("isEmpty()", is(true));
    }

    @Test
    public void createPost() throws IOException {
        expectedResponse(201);
        PostData postToSend = new PostData(randomNumWith1(), randomNumWith1(), getRandomText(10), getRandomText(10));
        given()
                .body(postToSend)
                .when()
                .post(PostData.postsRequest)
                .then().log().all()
                .body("userId", equalTo(postToSend.getUserId()),
                        "title", equalTo(postToSend.getTitle()),
                        "body", equalTo(postToSend.getBody()))
                .body("$", hasKey("id"));
    }

    @Test
    public void getAllUsers() throws IOException {
        expectedResponse(200);
        List<UserData> users = given()
                .when()
                .get(UserData.usersRequest)
                .then().log().all()
                .assertThat().contentType(ContentType.JSON)
                .extract().body().jsonPath().getList("$", UserData.class);
        UserData userWithId5 = users.stream().filter(x -> x.getId().equals(5)).findFirst().orElse(null);
        Assert.assertEquals(gson.toJson(userWithId5), getExpectedUser5());
    }

    @Test
    public void getUser5() throws IOException {
        expectedResponse(200);
        UserData user = given()
                .when()
                .get(UserData.usersRequest + 5)
                .then().log().all()
                .extract().body().as(UserData.class);
        String actualUser = gson.toJson(user);
        Assert.assertEquals(actualUser, getExpectedUser5());
    }
}
