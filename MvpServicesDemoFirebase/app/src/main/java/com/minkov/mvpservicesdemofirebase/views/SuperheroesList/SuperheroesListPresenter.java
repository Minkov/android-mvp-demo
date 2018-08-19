package com.minkov.mvpservicesdemofirebase.views.SuperheroesList;

import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.services.FirebaseSuperheroesService;
import com.minkov.mvpservicesdemofirebase.services.base.SuperheroesService;

import java.util.List;
import java.util.stream.Collectors;

public class SuperheroesListPresenter implements SuperheroesListContracts.Presenter {
    private final SuperheroesService mSuperheroesService;
    private SuperheroesListContracts.View mView;

    public SuperheroesListPresenter(SuperheroesService superheroesService) {
        mSuperheroesService = superheroesService;
    }

    @Override
    public void applyFilter(String pattern) {
        mView.showLoading();
        mSuperheroesService.getFilteredSuperheroes(
                pattern,
                this::presentSuperheroesToView
        );
    }

    @Override
    public void subscribe(SuperheroesListContracts.View view) {
        mView = view;
        loadSuperheroes();
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    private void loadSuperheroes() {
        mView.showLoading();
        mSuperheroesService.getAll(this::presentSuperheroesToView);

    }

    private void presentSuperheroesToView(List<Superhero> superheroes) {
        if (superheroes.isEmpty()) {
            mView.showEmptySuperheroes();
        } else {
            mView.showSuperheroes(superheroes);
        }

        mView.hideLoading();
    }
}