package org.example.RA.steps;

import io.restassured.response.Response;

public class PrepareActualResponse {

    public <T> T asPojo(Response resp, Class<T> clazz) {
        return resp.as(clazz);
    }

    public String getString(Response resp, String jsonPath) {
        return resp.jsonPath().getString(jsonPath);
    }

    public Integer getInt(Response resp, String jsonPath) {
        return resp.jsonPath().getInt(jsonPath);
    }

    public String asString(Response resp) {
        return resp.asString();
    }
}
