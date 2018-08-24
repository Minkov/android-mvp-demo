package com.minkov.superheroesapi;

import com.minkov.superheroesapi.models.Superhero;
import com.minkov.superheroesapi.repositories.base.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SuperheroesApiApplication {

    @Autowired
    public SuperheroesApiApplication(GenericRepository<Superhero> superheroesRepository) {
        List<Superhero> initialSuperheroes = Arrays.asList(
            new Superhero("Batman", "Bruce Wayne"),
            new Superhero("Wonder woman", "Diana Prince"),
            new Superhero("The Flash", "Barry Alan")
        );
        initialSuperheroes
            .forEach(superheroesRepository::add);
    }

    public static void main(String[] args) {
        SpringApplication.run(SuperheroesApiApplication.class, args);
    }
}
