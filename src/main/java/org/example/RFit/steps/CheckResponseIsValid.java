package org.example.RFit.steps;

import retrofit2.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckResponseIsValid {

    public void statusIs(Response<?> resp, int expected) {
        assertEquals(resp.code(), expected, "HTTP статус отличается");
        assertTrue(resp.isSuccessful(), "Response не успешный");
    }
}
