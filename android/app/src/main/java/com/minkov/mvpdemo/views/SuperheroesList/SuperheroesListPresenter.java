package com.minkov.mvpdemo.views.SuperheroesList;

import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.repositories.base.RxRepository;
import com.minkov.mvpdemo.schedulers.base.SchedulersFactory;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.disposables.Disposable;

public class SuperheroesListPresenter implements SuperheroesListContracts.Presenter {

    private final RxRepository<Superhero> mSuperheroesRepository;
    private final SchedulersFactory mSchedulersFactory;
    private SuperheroesListContracts.View mView;

    public SuperheroesListPresenter(RxRepository<Superhero> superheroesRepository, SchedulersFactory schedulersFactory) {
        mSuperheroesRepository = superheroesRepository;
        mSchedulersFactory = schedulersFactory;
    }

    @Override
    public void onSuperheroSelected(Superhero superhero) {
        mView.showDetails(superhero);
    }

    @Override
    public void applyFilter(String pattern) {
        mView.showLoading();
        String finalPattern = pattern.toLowerCase();

        Disposable disposable = mSuperheroesRepository.getAll()
                .subscribeOn(mSchedulersFactory.background())
                .observeOn(mSchedulersFactory.ui())
                .map(superheroes -> superheroes.stream()
                        .filter(superhero -> superhero.getName().toLowerCase().contains(finalPattern))
                        .collect(Collectors.toList())
                )
                .subscribe(this::presentSuperheroesToView);
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
        Disposable disposable = mSuperheroesRepository.getAll()
                .subscribeOn(mSchedulersFactory.background())
                .observeOn(mSchedulersFactory.ui())
                .subscribe(this::presentSuperheroesToView);
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
