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

    public static String geServerUrl() {
//        return "http://192.168.43.154:8080/api/";
        return "http://192.168.2.109:8080/api";
    }
}
