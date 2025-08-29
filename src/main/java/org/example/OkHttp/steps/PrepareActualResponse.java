package org.example.OkHttp.steps;

import com.google.gson.Gson;
import org.example.OkHttp.models.BoardResponse;
import okhttp3.Response;

import java.io.IOException;

public class PrepareActualResponse {
    private final Gson gson = new Gson();

    public BoardResponse asBoard(Response r) throws IOException {
        if (r.body() == null) {
            throw new IllegalStateException("Response body is null");
        }
        String json = r.body().string();
        return gson.fromJson(json, BoardResponse.class);
    }
}
