package com.minkov.mvpdemo.repositories;

import com.minkov.mvpdemo.http.base.HttpRequester;
import com.minkov.mvpdemo.repositories.base.RxRepository;
import com.minkov.mvpdemo.utils.base.JsonParser;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class HttpRxRepository<T> implements RxRepository<T> {
    private final String mApiUrl;
    private final HttpRequester mHttpRequester;
    private final JsonParser<T> mJsonParser;

    public HttpRxRepository(String apiUrl, HttpRequester httpRequester, JsonParser<T> jsonParser) {
        mApiUrl = apiUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }

    @Override
    public Flowable<List<T>> getAll() {
        return Flowable.create(emitter -> {
            try {
                String body = mHttpRequester.get(mApiUrl);
                List<T> entities = mJsonParser.parseToObjects(body);
                emitter.onNext(entities);
            } catch (IOException ex) {
                emitter.onError(ex);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<T> getById(int id) {
        return null;
    }

    @Override
    public Flowable<T> add(T entity) {
        return null;
    }
}
