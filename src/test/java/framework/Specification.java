package framework;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jsonplaceholder.pojo.PostData;
import org.testng.Assert;

import java.io.FileWriter;
import java.io.IOException;

import static framework.PropertyReader.getProperties;
import static io.restassured.RestAssured.given;
import static jsonplaceholder.pojo.UserData.gson;

public class Specification {
    public static void expectedResponse(int expectedStatus) throws IOException {
        String URL = getProperties("config.properties", "baseUrl");
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(expectedStatus)
                .build();
    }

    public static Response POST(String uri, PostData object) {
        return given()
                .body(object)
                .when()
                .post(uri);
    }

    public static Response GET(int status, String uri) throws IOException {
        expectedResponse(status);
        return given()
                .when()
                .get(uri);
    }
}