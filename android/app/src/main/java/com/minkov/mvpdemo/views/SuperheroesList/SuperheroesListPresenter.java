package com.minkov.mvpdemo.views.SuperheroesList;

import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.repositories.base.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class SuperheroesListPresenter implements SuperheroesListContracts.Presenter {

    private final Repository<Superhero> mSuperheroesRepository;
    private SuperheroesListContracts.View mView;

    public SuperheroesListPresenter(Repository<Superhero> superheroesRepository) {
        mSuperheroesRepository = superheroesRepository;
    }

    @Override
    public void onSuperheroSelected(Superhero superhero) {
        mView.showDetails(superhero);
    }

    @Override
    public void applyFilter(String pattern) {
        mView.showLoading();
        pattern = pattern.toLowerCase();
        String finalPattern = pattern;
        mSuperheroesRepository.getAll(superheroes -> {
            List<Superhero> filteredSuperheroes = superheroes.stream()
                .filter(superhero -> superhero.getName().toLowerCase().contains(finalPattern))
                .collect(Collectors.toList());
            presentSuperheroesToView(filteredSuperheroes);
        });
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
        mSuperheroesRepository.getAll(this::presentSuperheroesToView);
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
