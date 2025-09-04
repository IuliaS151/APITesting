package org.example.RA.support;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.containsString;

public class Specifications {

    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(url)
                .addQueryParam("key", System.getenv("API_KEY"))
                .addQueryParam("token", System.getenv("API_TOKEN"))
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpec200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();
    }

    public static ResponseSpecification responseSpec404() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectHeader("Content-Type", containsString("text/plain"))
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = null;
    }
}
