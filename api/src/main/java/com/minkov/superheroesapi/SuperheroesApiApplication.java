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
            new Superhero("Batman", "Bruce Wayne", "https://upload.wikimedia.org/wikipedia/en/8/87/Batman_DC_Comics.png"),
            new Superhero("Wonder woman", "Diana Prince", "https://upload.wikimedia.org/wikipedia/en/thumb/9/93/Wonder_Woman.jpg/250px-Wonder_Woman.jpg"),
            new Superhero("The Flash", "Barry Alan", "https://upload.wikimedia.org/wikipedia/en/thumb/2/29/Barry_Allen_Flash_Vol_4_30.png/250px-Barry_Allen_Flash_Vol_4_30.png"),

            new Superhero("Batman", "Bruce Wayne", "https://upload.wikimedia.org/wikipedia/en/8/87/Batman_DC_Comics.png"),
            new Superhero("Wonder woman", "Diana Prince", "https://upload.wikimedia.org/wikipedia/en/thumb/9/93/Wonder_Woman.jpg/250px-Wonder_Woman.jpg"),
            new Superhero("The Flash", "Barry Alan", "https://upload.wikimedia.org/wikipedia/en/thumb/2/29/Barry_Allen_Flash_Vol_4_30.png/250px-Barry_Allen_Flash_Vol_4_30.png"),
            new Superhero("Batman", "Bruce Wayne", "https://upload.wikimedia.org/wikipedia/en/8/87/Batman_DC_Comics.png"),
            new Superhero("Wonder woman", "Diana Prince", "https://upload.wikimedia.org/wikipedia/en/thumb/9/93/Wonder_Woman.jpg/250px-Wonder_Woman.jpg"),
            new Superhero("The Flash", "Barry Alan", "https://upload.wikimedia.org/wikipedia/en/thumb/2/29/Barry_Allen_Flash_Vol_4_30.png/250px-Barry_Allen_Flash_Vol_4_30.png"),
            new Superhero("Batman", "Bruce Wayne", "https://upload.wikimedia.org/wikipedia/en/8/87/Batman_DC_Comics.png"),
            new Superhero("Wonder woman", "Diana Prince", "https://upload.wikimedia.org/wikipedia/en/thumb/9/93/Wonder_Woman.jpg/250px-Wonder_Woman.jpg"),
            new Superhero("The Flash", "Barry Alan", "https://upload.wikimedia.org/wikipedia/en/thumb/2/29/Barry_Allen_Flash_Vol_4_30.png/250px-Barry_Allen_Flash_Vol_4_30.png")
        );

        initialSuperheroes
            .forEach(superheroesRepository::add);
    }

    public static void main(String[] args) {
        SpringApplication.run(SuperheroesApiApplication.class, args);
    }
}
