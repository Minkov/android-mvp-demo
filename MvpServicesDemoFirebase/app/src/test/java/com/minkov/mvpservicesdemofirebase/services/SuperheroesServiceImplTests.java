package com.minkov.mvpservicesdemofirebase.services;

import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.repositories.base.Repository;
import com.minkov.mvpservicesdemofirebase.testutils.SuperheroesGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@RunWith(MockitoJUnitRunner.class)
public class SuperheroesServiceImplTests {

    @Mock
    private Repository<Superhero> mockRepository;

    private SuperheroesServiceImpl mService;

    private void mockRepositoryGetAll(List<Superhero> superheroes) {
        doAnswer((Answer<Void>) invocation -> {
            Consumer<List<Superhero>> callback = invocation.getArgument(0);
            callback.accept(superheroes);

            return null;
        })
                .when(mockRepository)
                .getAll(any());
    }

    @Before
    public void setupTest() {
        mService = new SuperheroesServiceImpl(mockRepository);
    }

    @Test
    public void superheroesServiceImplGetAllSuperheroes_whenThereAreSuperheroes_shouldReturnListOfSuperheroes() {
        List<Superhero> superheroes = SuperheroesGenerator.getSuperheroes(10, "Superhero ");
        mockRepositoryGetAll(superheroes);
        mService.getAllSuperheroes(actualSuperheroes ->
                assertThat(actualSuperheroes, is(superheroes))
        );
    }

    @Test
    public void superheroesServiceImplGetAllSuperheroes_whenNoSuperheroes_shouldReturnEmptyList() {
        mockRepositoryGetAll(Collections.emptyList());
        mService.getAllSuperheroes(actualSuperheroes ->
                assertThat(actualSuperheroes, is(Collections.EMPTY_LIST))
        );
    }


    @Test
    public void superheroesServiceImplGetFilteredSuperheroes_whenThereAreMatches_shouldReturnMatched() {
        List<Superhero> superheroes = SuperheroesGenerator.getSuperheroes(10, "Superhero ");
        List<Superhero> matchedSuperheroes = SuperheroesGenerator.getSuperheroes(10, "Matched ");
        superheroes.addAll(matchedSuperheroes);
        mockRepositoryGetAll(superheroes);
        mService.getFilteredSuperheroes("Matched", actualSuperheroes ->
                assertThat(actualSuperheroes, is(matchedSuperheroes))
        );
    }

    @Test
    public void superheroesServiceImplGetFilteredSuperheroes_whenThereAreMatchesWithDifferentCasing_shouldReturnMatched() {
        List<Superhero> superheroes = SuperheroesGenerator.getSuperheroes(10, "Superhero ");
        List<Superhero> matchedSuperheroes = SuperheroesGenerator.getSuperheroes(10, "Matched ");
        superheroes.addAll(matchedSuperheroes);
        mockRepositoryGetAll(superheroes);
        mService.getFilteredSuperheroes("mAtcHed", actualSuperheroes ->
                assertThat(actualSuperheroes, is(matchedSuperheroes))
        );
    }

    @Test
    public void superheroesServiceImplGetAllSuperheroes_whenNoMatches_shouldReturnEmptyList() {
        mockRepositoryGetAll(SuperheroesGenerator.getSuperheroes(10, "Superhero "));
        mService.getFilteredSuperheroes("NO MATCH", actualSuperheroes ->
                assertThat(actualSuperheroes, is(Collections.EMPTY_LIST))
        );
    }
}
