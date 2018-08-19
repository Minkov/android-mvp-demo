package com.minkov.mvpdemo.http;

import com.minkov.mvpdemo.http.base.HttpRequester;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class OkHttpRequester implements HttpRequester {
    private final OkHttpClient mClient;

    public OkHttpRequester() {
        mClient = new OkHttpClient();
    }

    @Override
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }
}
