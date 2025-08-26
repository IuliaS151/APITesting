package org.example.RFit.steps;

import retrofit2.Response;

public class PrepareActualResponse {
    public <T> T asPojo(Response<T> resp) {     return  resp.body();    }
}
