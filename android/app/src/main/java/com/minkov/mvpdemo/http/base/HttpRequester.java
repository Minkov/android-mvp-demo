package com.minkov.mvpdemo.http.base;

import java.io.IOException;

public interface HttpRequester {
    String get(String url) throws IOException;
}
