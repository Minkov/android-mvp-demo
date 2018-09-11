package com.minkov.androidapp.diconfig;

import com.minkov.androidapp.views.SuperheroCreate.SuperheroCreatePresenter;
import com.minkov.androidapp.views.SuperheroCreate.SuperheroCreateContracts;
import com.minkov.androidapp.views.SuperheroCreate.SuperheroCreateFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SuperheroCreateModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SuperheroCreateFragment superheroCreateFragment();

    @ActivityScoped
    @Binds
    abstract SuperheroCreateContracts.Presenter superheroCreatePresenter(SuperheroCreatePresenter presenter);
}

