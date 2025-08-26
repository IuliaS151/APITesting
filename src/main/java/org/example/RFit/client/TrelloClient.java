package org.example.RFit.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.example.RFit.endpoints.TrelloService;

public class TrelloClient {
    private static TrelloService api; // Singletone

    private static String val(String secret) {
        String v = System.getProperty(secret);
        if (v == null || v.isEmpty()) v = System.getenv(secret);
        if (v == null || v.isEmpty())
            throw new IllegalStateException(secret + " is not set: " + secret);

        return v;
    }

    public static TrelloService api() {
        if (api != null) return api;

        String key = val("API_KEY");
        String token = val("API_TOKEN");

        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY); //логируем запросы/ответы

        OkHttpClient http = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(key, token))
                .addInterceptor(log)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.trello.com/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(http)
                .build();

        api = retrofit.create(TrelloService.class);

        return api;
    }

    private TrelloClient() {} // Приватный конструктор
}
