package com.minkov.androidapp.diconfig;


import com.minkov.androidapp.views.SuperheroCreate.SuperheroCreateActivity;
import com.minkov.androidapp.views.SuperheroDetails.SuperheroDetailsActivity;
import com.minkov.androidapp.views.SuperheroesList.SuperheroesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
            modules = SuperheroesListModule.class
    )
    abstract SuperheroesListActivity superheroesListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = SuperheroDetailsModule.class
    )
    abstract SuperheroDetailsActivity superheroDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = SuperheroCreateModule.class
    )
    abstract SuperheroCreateActivity superheroCreateActivity();
}