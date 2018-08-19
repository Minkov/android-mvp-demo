package com.minkov.mvpdemo.utils;

import com.google.gson.Gson;
import com.minkov.mvpdemo.utils.base.JsonParser;

import java.util.Arrays;
import java.util.List;

public class GsonJsonParser<T> implements JsonParser<T> {
    private final Gson mGson;
    private final Class<T> mKlass;
    private final Class<T[]> mArrayKlass;

    public GsonJsonParser(Class<T> klass, Class<T[]> arrayKlass) {
        mKlass = klass;
        mArrayKlass = arrayKlass;
        mGson = new Gson();
    }

    @Override
    public T parseToObject(String json) {
        return mGson.fromJson(json, mKlass);
    }

    @Override
    public List<T> parseToObjects(String json) {
        return Arrays.asList(mGson.fromJson(json, mArrayKlass));
    }
}
