package com.minkov.androidapp.diconfig;

import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.validators.SuperheroValidator;
import com.minkov.androidapp.validators.base.Validator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ValidatorsModule {
    @Provides
    @Singleton
    public Validator<Superhero> superheroValidator() {
        return new SuperheroValidator();
    }
}
