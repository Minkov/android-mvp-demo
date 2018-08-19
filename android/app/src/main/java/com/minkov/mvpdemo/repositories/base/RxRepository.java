package com.minkov.mvpdemo.repositories.base;

import android.annotation.SuppressLint;

import com.minkov.mvpdemo.repositories.base.Repository;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.Flowable;

public interface RxRepository<T> extends Repository<T> {

    @Override
    default void getAll(Consumer<List<T>> action) {
        getAll().subscribe(action::accept);
    }


    @Override
    default void add(T entity, Consumer<T> onComplete) {
        add(entity).subscribe(onComplete::accept);
    }

    @Override
    default void getById(int id, Consumer<T> onComplete) {
        getById(id).subscribe(onComplete::accept);
    }

    Flowable<List<T>> getAll();

    Flowable<T> getById(int id);

    Flowable<T> add(T entity);

}
