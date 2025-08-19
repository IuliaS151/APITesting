package org.example.steps;


import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SendRequest {
    public Response post(String path, Object body) {
        return given().body(body).when().post(path).then().extract().response();
    }


}
