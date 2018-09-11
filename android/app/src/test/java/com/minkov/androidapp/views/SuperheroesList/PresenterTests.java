package com.minkov.androidapp.views.SuperheroesList;

import com.minkov.androidapp.async.SyncSchedulerProvider;
import com.minkov.androidapp.async.base.SchedulerProvider;
import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.services.base.SuperheroesService;
import com.minkov.androidapp.utils.SuperheroesGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.internal.schedulers.ImmediateThinScheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PresenterTests {
    @Mock
    SuperheroesService mockService;

    @Mock
    SuperheroesListContracts.View mockView;

    private SchedulerProvider schedulerProvider = new SyncSchedulerProvider();

    private ArrayList<Superhero> mSuperheroes;

    private SuperheroesListPresenter mPresenter;

    @Before
    public void setupTest() throws Exception {
        mSuperheroes = new ArrayList<>();
        when(mockService.getAllSuperheroes())
                .thenReturn(mSuperheroes);

        when(mockService.getFilteredSuperheroes(anyString()))
                .thenReturn(mSuperheroes);

        mPresenter = new SuperheroesListPresenter(mockService, schedulerProvider);
        mPresenter.subscribe(mockView);
    }

    @Test
    public void presenterLoadSuperheroes_whenSuperheroes_expectViewShowSuperheroes() {
        mSuperheroes.addAll(SuperheroesGenerator.generateSuperheroesList(10));
        mPresenter.loadSuperheroes();
        verify(mockView).showSuperheroes(mSuperheroes);
    }

    @Test
    public void presenterLoadSuperheroes_whenNoSuperheroes_expectViewShowEmptySuperheroes() throws Exception {
        List<Superhero> superheroList = Collections.emptyList();
        mPresenter.loadSuperheroes();

        verify(mockView).showEmptySuperheroesList();
    }

    @Test
    public void presenterLoadSuperheroes_whenException_expectShowError() throws Exception {
        Exception e = new Exception();

        when(mockService.getAllSuperheroes())
                .thenThrow(e);

        mPresenter.loadSuperheroes();
        verify(mockView).showError(e);
    }


    @Test
    public void presenterFilterSuperheroes_whenMatchedSuperheroes_expectViewShowSuperheroes() {
        mSuperheroes.addAll(SuperheroesGenerator.generateSuperheroesList(10));
        mPresenter.filterSuperheroes("");
        verify(mockView).showSuperheroes(mSuperheroes);
    }

    @Test
    public void presenterFilterSuperheroes_whenNoMatchedSuperheroes_expectViewShowEmptySuperheroes() throws Exception {
        List<Superhero> superheroList = Collections.emptyList();
        mPresenter.filterSuperheroes("");

        verify(mockView).showEmptySuperheroesList();
    }

    @Test
    public void presenterFilterSuperheroes_whenException_expectShowError() throws Exception {
        Exception e = new Exception();

        when(mockService.getFilteredSuperheroes(""))
                .thenThrow(e);

        mPresenter.filterSuperheroes("");

        verify(mockView).showError(e);
    }

}
