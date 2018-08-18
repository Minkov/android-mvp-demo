package com.minkov.superheroesapi.repositories.base;

import com.minkov.superheroesapi.models.base.ModelBase;

import java.util.List;

public interface GenericRepository<T extends ModelBase> {
    List<T> findAll();

    T findById(int id);

    void add(T superhero);
}
