package com.minkov.superheroesapi.services.base;

import com.minkov.superheroesapi.models.Superhero;

import java.util.List;

public interface SuperheroesService {
    List<Superhero> getAllSuperheroes();

    Superhero findSuperheroById(int id);

    Superhero create(Superhero superhero);
}
