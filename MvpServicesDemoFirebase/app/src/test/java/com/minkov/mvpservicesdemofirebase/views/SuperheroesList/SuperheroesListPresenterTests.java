package com.minkov.mvpservicesdemofirebase.views.SuperheroesList;

import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.services.base.SuperheroesService;
import com.minkov.mvpservicesdemofirebase.testutils.SuperheroesGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SuperheroesListPresenterTests {
    private SuperheroesService mockSuperheroesService;
    private SuperheroesListPresenter mPresenter;
    private SuperheroesListContracts.View mockView;


    private void mockServiceGetAll(List<Superhero> superheroes) {
        doAnswer((Answer<Void>) invocation -> {
            Consumer<List<Superhero>> callback = invocation.getArgument(0);
            callback.accept(superheroes);

            return null;
        })
                .when(mockSuperheroesService)
                .getAll(any(Consumer.class));
    }

    private void mockServiceGetFiltered(List<Superhero> filteredSuperheroes) {
        doAnswer((Answer<Void>) invocation -> {
            Consumer<List<Superhero>> callback = invocation.getArgument(1);
            callback.accept(filteredSuperheroes);

            return null;
        })
                .when(mockSuperheroesService)
                .getFilteredSuperheroes(any(String.class), any(Consumer.class));
    }

    @Before
    public void setupTest() {
        mockSuperheroesService = mock(SuperheroesService.class);
        mockView = mock(SuperheroesListContracts.View.class);
        mPresenter = new SuperheroesListPresenter(mockSuperheroesService);
    }

    @Test
    public void presenterSubscribe_whenSuperheroes_shouldCallViewShowSuperheroes() {
        List<Superhero> superheroes = SuperheroesGenerator.getSuperheroes(10, "Superhero ");
        mockServiceGetAll(superheroes);

        mPresenter.subscribe(mockView);
        verify(mockView).showSuperheroes(superheroes);
    }

    @Test
    public void presenterSubscribe_whenNoSuperheroes_shouldCallViewShowEmptySuperheroes() {
        mockServiceGetAll(Collections.emptyList());
        mPresenter.subscribe(mockView);
        verify(mockView).showEmptySuperheroes();
    }


    @Test
    public void presenterApplyFilter_whenThereIsMatch_shouldCallViewShowSuperheroesWithMatch() {
        List<Superhero> superheroes = SuperheroesGenerator.getSuperheroes(10, "Superhero ");
        mockServiceGetAll(Collections.emptyList());
        mockServiceGetFiltered(superheroes);

        mPresenter.subscribe(mockView);
        mPresenter.applyFilter("");
        verify(mockView).showSuperheroes(superheroes);
    }

    @Test
    public void presenterSubscribe_whenNoMatch_shouldCallViewShowEmptySuperheroes() {
        mockServiceGetAll(Collections.emptyList());
        mockServiceGetFiltered(Collections.emptyList());
        mPresenter.subscribe(mockView);
        mPresenter.applyFilter("");
        verify(mockView, times(2)).showEmptySuperheroes();
    }
}