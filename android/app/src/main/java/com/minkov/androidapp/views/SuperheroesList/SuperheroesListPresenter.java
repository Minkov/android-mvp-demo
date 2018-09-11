package com.minkov.androidapp.views.SuperheroesList;

import com.minkov.androidapp.async.base.SchedulerProvider;
import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.services.base.SuperheroesService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class SuperheroesListPresenter
        implements SuperheroesListContracts.Presenter {

    private final SuperheroesService mSuperheroesService;
    private final SchedulerProvider mSchedulerProvider;
    private SuperheroesListContracts.View mView;

    @Inject
    public SuperheroesListPresenter(
            SuperheroesService superheroesService,
            SchedulerProvider schedulerProvider) {
        mSuperheroesService = superheroesService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    // same as // setView(SuperheroesListContracts.View view)
    public void subscribe(SuperheroesListContracts.View view) {
        mView = view;
    }

    @Override
    public void loadSuperheroes() {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Superhero>>) emitter -> {
                    List<Superhero> superheroes = mSuperheroesService.getAllSuperheroes();
                    emitter.onNext(superheroes);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(
                        this::presentSuperheroesToView,
                        error -> mView.showError(error)
                );
    }

    @Override
    public void filterSuperheroes(String pattern) {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Superhero>>) emitter -> {
                    List<Superhero> superheroes = mSuperheroesService.getFilteredSuperheroes(pattern);
                    emitter.onNext(superheroes);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(
                        this::presentSuperheroesToView,
                        error -> mView.showError(error)
                );
    }

    @Override
    public void selectSuperhero(Superhero superhero) {
        mView.showSuperheroDetails(superhero);
    }

    private void presentSuperheroesToView(List<Superhero> superheroes) {
        if (superheroes.isEmpty()) {
            mView.showEmptySuperheroesList();
        } else {
            mView.showSuperheroes(superheroes);
        }
    }
}
