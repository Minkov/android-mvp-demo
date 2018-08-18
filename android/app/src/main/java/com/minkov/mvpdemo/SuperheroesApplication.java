package com.minkov.mvpdemo;

import android.app.Application;

import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.repositories.FirebaseRepository;

public class SuperheroesApplication extends Application {

    private FirebaseRepository<Superhero> mSuperheroesRepository;

    public FirebaseRepository<Superhero> getSuperheroesRepository() {
        if (mSuperheroesRepository == null) {
            mSuperheroesRepository = new FirebaseRepository<Superhero>(Superhero.class);
        }

        return mSuperheroesRepository;
    }
}
