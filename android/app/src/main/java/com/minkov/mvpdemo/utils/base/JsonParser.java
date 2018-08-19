package com.minkov.mvpdemo.utils.base;

import java.util.List;

public interface JsonParser<T> {
    T parseToObject(String json);

    List<T> parseToObjects(String json);
}
