package com.minkov.mvpdemo.repositories;

import com.minkov.mvpdemo.utils.Tasker;
import com.minkov.mvpdemo.http.base.HttpRequester;
import com.minkov.mvpdemo.repositories.base.Repository;
import com.minkov.mvpdemo.utils.base.JsonParser;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class HttpRepository<T> implements Repository<T> {
    private final String mBaseUrl;
    private final HttpRequester mHttpRequester;
    private final JsonParser<T> mJsonParser;

    public HttpRepository(String baseUrl, HttpRequester httpRequester, JsonParser<T> jsonParser) {
        mBaseUrl = baseUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }

    @Override
    public void getAll(Consumer<List<T>> onComplete) {
        Tasker.runInBackground(() -> {
            try {
                String body = mHttpRequester.get(mBaseUrl);
                List<T> entities = mJsonParser.parseToObjects(body);
                onComplete.accept(entities);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void add(T entity, Consumer<T> onComplete) {

    }

    @Override
    public void getById(int id, Consumer<T> onComplete) {

    }
}
