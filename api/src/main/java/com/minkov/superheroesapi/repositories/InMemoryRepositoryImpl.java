package com.minkov.superheroesapi.repositories;


import com.minkov.superheroesapi.models.base.ModelBase;
import com.minkov.superheroesapi.repositories.base.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryRepositoryImpl<T extends ModelBase> implements GenericRepository<T> {
    private final Class<T> klass;
    private List<T> items;

    @Autowired
    public InMemoryRepositoryImpl(Class<T> klass) {
        this.klass = klass;
        this.items = new ArrayList<>();
    }

    @Override
    public List<T> findAll() {
        return this.items;
    }

    @Override
    public T findById(int id) {
        return this.items.stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElse(null);
    }

    @Override
    public void add(T superhero) {
        int nextId = this.getNextId();
        superhero.setId(nextId);
        this.items.add(superhero);
    }

    private int getNextId() {
        int biggestId = this.items.stream()
            .mapToInt(ModelBase::getId)
            .max()
            .orElse(0);
        return biggestId + 1;
    }
}
