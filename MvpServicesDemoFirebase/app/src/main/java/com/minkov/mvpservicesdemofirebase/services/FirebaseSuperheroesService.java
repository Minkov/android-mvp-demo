package com.minkov.mvpservicesdemofirebase.services;

import com.google.firebase.firestore.FirebaseFirestore;
import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.services.base.SuperheroesService;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FirebaseSuperheroesService implements SuperheroesService {
    private final FirebaseFirestore mDb;
    private Predicate<? super Superhero> filterFunc;

    public FirebaseSuperheroesService() {
        mDb = FirebaseFirestore.getInstance();

    }

    @Override
    public void getFilteredSuperheroes(String pattern, Consumer<List<Superhero>> onResult) {
        String patternToLower = pattern.toLowerCase();
        getAll(superheroes -> onResult.accept(
                superheroes.stream()
                        .filter(superhero -> superhero.getName().toLowerCase().contains(patternToLower))
                        .collect(Collectors.toList())));
    }

    private Predicate<? super Superhero> getFilterFunc(String pattern) {
        return x -> x.getName().toLowerCase().contains(pattern.toLowerCase());
    }

    @Override
    public void getAll(Consumer<List<Superhero>> onResult) {
        mDb.collection("superheros")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Superhero> superheroes = task.getResult().toObjects(Superhero.class);
                        onResult.accept(superheroes);
                    }
                });
    }
}
