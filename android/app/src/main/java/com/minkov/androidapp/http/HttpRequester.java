package com.minkov.androidapp.http;

import java.io.IOException;
import java.util.function.Consumer;

public interface HttpRequester {
    String get(String url) throws IOException;

    String post(String url, String body) throws IOException;
}
