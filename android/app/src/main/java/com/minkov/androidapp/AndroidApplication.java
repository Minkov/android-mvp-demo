package com.minkov.androidapp;

import android.support.annotation.VisibleForTesting;

import com.minkov.androidapp.diconfig.DaggerAppComponent;
import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.repositories.base.Repository;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class AndroidApplication extends DaggerApplication {
    @Inject
    Repository<Superhero> superheroesRepository;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @VisibleForTesting
    public Repository<Superhero> getSuperheroesRepository() {
        return superheroesRepository;
    }
}
