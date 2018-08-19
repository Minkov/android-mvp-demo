package com.minkov.mvpservicesdemofirebase.services.base;

import com.minkov.mvpservicesdemofirebase.models.Superhero;

import java.util.List;
import java.util.function.Consumer;

public interface SuperheroesService {
    void getFilteredSuperheroes(String pattern, Consumer<List<Superhero>> onResult);

    void getAll(Consumer<List<Superhero>> onResult);
}
