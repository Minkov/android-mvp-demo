package com.minkov.mvpdemo.views.SuperheroesList;

import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.repositories.base.Repository;
import com.minkov.mvpdemo.testutils.SuperheroesGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PresenterTests {
    private Repository<Superhero> mockRepository = mock(Repository.class);
    private SuperheroesListPresenter mPresenter;

    private List<Superhero> mSuperheroes;
    private SuperheroesListContracts.View mView;

    @Before
    public void setupTest() {
        mSuperheroes = new ArrayList<>();

        doAnswer((Answer<Void>) invocation -> {
            Consumer<List<Superhero>> callback = invocation.getArgument(0);
            callback.accept(mSuperheroes);

            return null;
        }).when(mockRepository)
                .getAll(any(Consumer.class));

        mView = mock(SuperheroesListContracts.View.class);

        mPresenter = new SuperheroesListPresenter(mockRepository);
    }

    @Test
    public void presenterSubscribe_whenSuperheroes_shouldCallViewShowSuperheroesWithTheSameSuperheroes() {
        mSuperheroes.addAll(SuperheroesGenerator.getSuperheroes(10, "Superhero"));
        mPresenter.subscribe(mView);
        verify(mView).showSuperheroes(mSuperheroes);
    }

    @Test
    public void presenterSubscribe_whenNoSuperheroes_shouldCallViewShowEmptyList() {
        mSuperheroes.clear();
        mPresenter.subscribe(mView);
        verify(mView).showEmptySuperheroes();
    }

    @Test
    public void presenterApplyFilter_whenSuperheroesMatchPatternWithCasing_shouldCallViewShowSuperheroesWithTheMatchedSuperheroes() {
        List<Superhero> matchingSuperheroes = SuperheroesGenerator.getSuperheroes(10, "Filter");
        mSuperheroes.addAll(SuperheroesGenerator.getSuperheroes(10, "Superhero"));
        mSuperheroes.addAll(matchingSuperheroes);
        mPresenter.subscribe(mView);
        mPresenter.applyFilter("Filter");
        verify(mView).showSuperheroes(matchingSuperheroes);
    }

    @Test
    public void presenterApplyFilter_whenSuperheroesMatchPatternWithoutCasing_shouldCallViewShowSuperheroesWithTheMatchedSuperheroes() {
        List<Superhero> matchingSuperheroes = SuperheroesGenerator.getSuperheroes(10, "Filter");
        mSuperheroes.addAll(SuperheroesGenerator.getSuperheroes(10, "Superhero"));
        mSuperheroes.addAll(matchingSuperheroes);
        mPresenter.subscribe(mView);
        mPresenter.applyFilter("fIlTeR");
        verify(mView).showSuperheroes(matchingSuperheroes);
    }

    @Test
    public void presenterSubsribe_shouldCallViewShowLoadingThenHideLoading() {
        mPresenter.subscribe(mView);
        verify(mView).showLoading();
        verify(mView).hideLoading();
    }


    @Test
    public void presenterApplyFilter_shouldCallViewShowLoadingThenHideLoadingTwice() {
        mPresenter.subscribe(mView);
        mPresenter.applyFilter("filter");

        verify(mView, times(2)).showLoading();
        verify(mView, times(2)).hideLoading();
    }
}
