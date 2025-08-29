package org.example.RA.steps;


import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SendRequest {

    public Response post(String path, Map<String, String> queryParams) {
        return given()
                .queryParams(queryParams)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    // нет смысла делать мап, гет-у нужен тольео id
    public Response get(String path, String id) {
        return given()
                .pathParams("id", id)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public Response put(String path, String id, Map<String, String> queryParams) {
        return given()
                .pathParams("id", id)
                .queryParams(queryParams)
                .when()
                .put(path)
                .then()
                .extract()
                .response();
    }

    // также как гет
    public Response delete(String path, String id) {
        return given()
                .pathParams("id", id)
                .when()
                .delete(path)
                .then()
                .extract()
                .response();
    }
}
