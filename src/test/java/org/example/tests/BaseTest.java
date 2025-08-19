package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

public class BaseTest {

    protected static RequestSpecification SPEC;

    @BeforeClass //(beforClass vs beforeTest)
    public void baseSetup() {
        if (SPEC == null) {
            SPEC = new RequestSpecBuilder()
                    .setBaseUri("https://api.trello.com/1")
                    .setContentType(ContentType.JSON)
                    .addQueryParam("key", System.getenv("TRELLO_KEY"))
                    .addQueryParam("token", System.getenv("TRELLO_TOKEN"))
                    .build();
        }
        RestAssured.requestSpecification = SPEC;
    }

    // Пример общей базовой валидации
    protected void assertStatus(int actual, int expected) {
        assertEquals(actual, expected, "Unexpected HTTP status");
    }
}
