package com.minkov.androidapp.diconfig;

import com.minkov.androidapp.views.SuperheroesList.SuperheroesListContracts;
import com.minkov.androidapp.views.SuperheroesList.SuperheroesListFragment;
import com.minkov.androidapp.views.SuperheroesList.SuperheroesListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SuperheroesListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SuperheroesListFragment superheroesListFragment();

    @ActivityScoped
    @Binds
    abstract SuperheroesListContracts.Presenter superheroesListPresenter(SuperheroesListPresenter presenter);
}
