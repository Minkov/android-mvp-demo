package com.minkov.superheroesapi.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelsConfiguration {
    @Bean
    public Class<Superhero> provideSuperheroClass() {
        return Superhero.class;
    }
}
