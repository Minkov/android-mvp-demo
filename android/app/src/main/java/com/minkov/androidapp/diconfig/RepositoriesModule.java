package com.minkov.androidapp.diconfig;

import com.minkov.androidapp.Constants;
import com.minkov.androidapp.http.HttpRequester;
import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.parsers.base.JsonParser;
import com.minkov.androidapp.repositories.HttpRepository;
import com.minkov.androidapp.repositories.InMemoryRepository;
import com.minkov.androidapp.repositories.base.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {
    @Provides
    @Singleton
    public Repository<Superhero> superheroRepository() {
        Repository<Superhero> repository = new InMemoryRepository<>();
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

        initialSuperheroes.forEach(s -> {
            try {
                repository.add(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return repository;
    }

//    @Provides
//    @Singleton
//    public Repository<Superhero> superheroRepository(
//            @Named("baseServerUrl") String baseServerUrl,
//            HttpRequester httpRequester,
//            JsonParser<Superhero> jsonParser
//    ) {
//        String url = baseServerUrl + "/superheros";
//        return new HttpRepository<>(url, httpRequester, jsonParser);
//    }

//    @Provides
//    public Repository<Power> superheroRepository() {
//        return new InMemoryRepository<>();
//    }
}
