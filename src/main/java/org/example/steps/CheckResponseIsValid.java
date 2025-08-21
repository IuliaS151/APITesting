package org.example.steps;

import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckResponseIsValid {

    public void statusIs(Response r, int expected) {
        assertEquals(r.statusCode(), expected, "HTTP status");
    }

    public void isJson(Response r){
        assertThat(r.contentType().contains("application/json"));
    }
}
