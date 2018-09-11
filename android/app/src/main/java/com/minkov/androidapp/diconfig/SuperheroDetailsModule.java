package com.minkov.androidapp.diconfig;

import com.minkov.androidapp.views.SuperheroDetails.SuperheroDetailsContracts;
import com.minkov.androidapp.views.SuperheroDetails.SuperheroDetailsFragment;
import com.minkov.androidapp.views.SuperheroDetails.SuperheroDetailsPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class SuperheroDetailsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SuperheroDetailsFragment superheroDetailsFragment();

    @ActivityScoped
    @Binds
    abstract SuperheroDetailsContracts.Presenter superheroesListPresenter(SuperheroDetailsPresenter presenter);
}