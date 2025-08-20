package org.example.steps;


import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SendRequest {
    public Response post(String path, Object body) {
        return given()
                .body(body)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    public Response post(String path, Map<String, ?> queryParams) {
        return given()
                .queryParams(queryParams)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    public Response get(String path) {
        return given()
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public Response get(String path, Map<String, ?> queryParams) {
        return given()
                .queryParams(queryParams)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public Response put(String path, Map<String, ?> queryParams) {
        return given()
                .queryParams(queryParams)
                .when()
                .put(path)
                .then()
                .extract()
                .response();
    }

    public Response delete(String path) {
        return given()
                .when()
                .delete(path)
                .then()
                .extract()
                .response();
    }

    public Response delete(String path, Map<String, ?> queryParams) {
        return given()
                .queryParams(queryParams)
                .when()
                .delete(path)
                .then()
                .extract()
                .response();
    }
}
