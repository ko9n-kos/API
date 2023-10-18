package commonLogic;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

import static commonLogic.PropertyReader.getProperties;

public class Specification {
    public static RequestSpecification requestSpecification() throws IOException {
        String URL = getProperties("config.properties", "baseUrl");
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification expectedStatusCode(int expectedStatus) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatus)
                .build();

    }

    public static void expectedResponse(int expectedStatus) throws IOException {
        RestAssured.requestSpecification = requestSpecification();
        RestAssured.responseSpecification = expectedStatusCode(expectedStatus);
    }
}