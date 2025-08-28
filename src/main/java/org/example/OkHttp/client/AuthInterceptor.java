package org.example.OkHttp.client;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final String key;
    private final String token;

    public AuthInterceptor(String key, String token) {
        this.key = key;
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws java.io.IOException {
        HttpUrl url = chain.request().url().newBuilder()
                .addQueryParameter("key", key)
                .addQueryParameter("token", token)
                .build();

        Request request = chain.request().newBuilder().url(url).build();

        return chain.proceed(request);
    }
}
