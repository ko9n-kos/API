package jsonplaceholder.requestsTest;

import io.restassured.http.ContentType;
import jsonplaceholder.pojo.UserData;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static framework.PropertyReader.getProperties;
import static framework.Specification.GET;
import static jsonplaceholder.pojo.UserData.checkUser;
import static jsonplaceholder.pojo.UserData.checkUserFromList;

public class UsersTest {
    @Test
    public void getAllUsers() throws IOException {
        List<UserData> users = GET(200, getProperties("config.properties", "usersRequest"))
                .then().assertThat().contentType(ContentType.JSON)
                .extract().body().jsonPath().getList("$", UserData.class);
        checkUserFromList(users);
    }

    @Test
    public void getUser5() throws IOException {
        UserData user = GET(200, getProperties("config.properties", "usersRequest") + 5)
                .then().extract().body().as(UserData.class);
        checkUser(user);
    }
}