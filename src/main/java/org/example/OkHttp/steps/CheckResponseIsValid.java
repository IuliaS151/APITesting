package org.example.OkHttp.steps;

import okhttp3.Response;

import static org.testng.Assert.assertEquals;

public class CheckResponseIsValid {
    public void statusIs(Response r, int expected) {
        assertEquals(r.code(), expected, "HTTP status");
    }
}
