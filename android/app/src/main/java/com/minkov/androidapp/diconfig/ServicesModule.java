package com.minkov.androidapp.diconfig;

import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.repositories.base.Repository;
import com.minkov.androidapp.services.HttpSuperheroesService;
import com.minkov.androidapp.services.base.SuperheroesService;
import com.minkov.androidapp.validators.base.Validator;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    public SuperheroesService superheroesService(Repository<Superhero> repository, Validator<Superhero> validator) {
        return new HttpSuperheroesService(repository, validator);
    }
}
