package org.example.OkHttp.client;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.Map;

public class TrelloClient {

    private static OkHttpClient client;
    private static final String BASE = "https://api.trello.com/1";

    private static String val(String secret) {
        String v = System.getProperty(secret);
        if (v == null || v.isEmpty()) v = System.getenv(secret);
        if (v == null || v.isEmpty())
            throw new IllegalStateException(secret + " is not set: " + secret);

        return v;
    }

    public static OkHttpClient api() {
        if (client != null) return client;

        String key = val("API_KEY");
        String token = val("API_TOKEN");

        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY); //логируем запросы/ответы

        client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(key, token))
                .addInterceptor(log)
                .build();

        return client;
    }

    public static HttpUrl url(String path, Map<String, String> query) {
        HttpUrl.Builder b = HttpUrl.parse(BASE).newBuilder();
        for (String s : path.split("/")) if (!s.isEmpty()) b.addPathSegment(s);
        if (query != null) for (Map.Entry<String,String> e : query.entrySet())
            if (e.getValue() != null) b.addQueryParameter(e.getKey(), e.getValue());

        return b.build();
    }
    private TrelloClient() {}
}
