package com.minkov.mvpservicesdemofirebase.testutils;

import com.minkov.mvpservicesdemofirebase.models.Superhero;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SuperheroesGenerator {
    public static List<Superhero> getSuperheroes(String prefix) {
        return getSuperheroes(10, prefix);
    }

    public static List<Superhero> getSuperheroes(int count, String prefix) {
        return IntStream.range(0, count)
                .mapToObj(index -> SuperheroesGenerator.generateSuperhero(index, prefix))
                .collect(Collectors.toList());
    }

    private static Superhero generateSuperhero(int seed, String prefix) {
        String name = String.format("%s %d", prefix, seed);
        return new Superhero(name);
    }
}
