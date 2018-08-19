package com.minkov.mvpservicesdemofirebase.services;

import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.repositories.base.Repository;
import com.minkov.mvpservicesdemofirebase.services.base.SuperheroesService;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SuperheroesServiceImpl implements SuperheroesService {
    private final Repository<Superhero> mSuperheroesRepository;

    public SuperheroesServiceImpl(Repository<Superhero> superheroesRepository) {
        mSuperheroesRepository = superheroesRepository;
    }

    @Override
    public void getFilteredSuperheroes(String pattern, Consumer<List<Superhero>> onResult) {
        getAllSuperheroes(superheroes -> onResult.accept(
                superheroes.stream()
                        .filter(this.getFilterFunc(pattern))
                        .collect(Collectors.toList())));
    }

    private Predicate<? super Superhero> getFilterFunc(String pattern) {
        return x -> x.getName().toLowerCase().contains(pattern.toLowerCase());
    }

    @Override
    public void getAllSuperheroes(Consumer<List<Superhero>> onResult) {
        mSuperheroesRepository.getAll(onResult);
    }
}
