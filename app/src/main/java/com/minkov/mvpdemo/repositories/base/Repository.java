package com.minkov.mvpdemo.repositories.base;

import java.util.List;
import java.util.function.Consumer;

public interface Repository<T> {
    void getAll(Consumer<List<T>> onComplete);

    void add(T entity, Consumer<T> onComplete);

    void getById(String id, Consumer<T> onComplete);
}
