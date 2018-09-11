package com.minkov.androidapp.diconfig;

import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.parsers.GsonJsonParser;
import com.minkov.androidapp.parsers.base.JsonParser;

import dagger.Module;
import dagger.Provides;

@Module
public class ParsersModule {
    @Provides
    public JsonParser<Superhero> superheroJsonParser() {
        return new GsonJsonParser<>(Superhero.class, Superhero[].class);
    }
}
