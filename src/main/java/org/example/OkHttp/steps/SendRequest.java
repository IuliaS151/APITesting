package org.example.OkHttp.steps;

import com.google.gson.Gson;
import okhttp3.*;
import org.example.OkHttp.client.TrelloClient;
import org.example.OkHttp.models.BoardResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class SendRequest {
    private final OkHttpClient http = TrelloClient.api();
    private final Gson gson = new Gson();
    private static final MediaType EMPTY = null;

    public Response createBoard(String name, Map<String, String> optional) throws IOException {
        if (optional == null) optional = Collections.emptyMap();

        HttpUrl.Builder urlBuilder = TrelloClient.url("/boards", optional).newBuilder();

        urlBuilder.addQueryParameter("name", name);

        RequestBody empty = RequestBody.create(new byte[0], EMPTY);
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .post(empty)
                .build();

        return http.newCall(request).execute();
    }

    public Response getBoard(String id) throws IOException {
        Request request = new Request.Builder()
                .url(TrelloClient.url("/boards" + id , null))
                .get()
                .build();

        return http.newCall(request).execute();
    }

    public Response updateBoard(String id, Map<String, String> params) throws IOException {
        RequestBody empty = RequestBody.create(new byte[0], EMPTY);
        Request request = new Request.Builder()
                .url(TrelloClient.url("/boards" + id, params))
                .put(empty)
                .build();

        return http.newCall(request).execute();
    }

    public Response deleteBoard(String id) throws IOException {
        Request request = new Request.Builder()
                .url(TrelloClient.url("/boards" + id, null))
                .delete()
                .build();

        return http.newCall(request).execute();
    }

    public BoardResponse asBoard(Response r) throws IOException {
        String json = r.body() != null ? r.body().string() : null;
        return json == null || json.isEmpty() ? null : gson.fromJson(json, BoardResponse.class);
    }
}
