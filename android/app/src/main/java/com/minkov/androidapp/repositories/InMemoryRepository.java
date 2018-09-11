package com.minkov.androidapp.repositories;

import com.minkov.androidapp.repositories.base.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created to preserve battery while development
 * @param <T>
 */
public class InMemoryRepository<T> implements Repository<T> {

    private final List<T> mItems;

    public InMemoryRepository() {
        mItems = new ArrayList<>();
    }

    @Override
    public List<T> getAll() throws IOException {
        return mItems;
    }

    @Override
    public T add(T item) throws IOException {
        mItems.add(item);
        return item;
    }

    @Override
    public T getById(int id) throws IOException {
        return mItems.get(0);
    }
}
