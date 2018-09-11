package com.minkov.androidapp.views.SuperheroCreate;

import com.minkov.androidapp.async.base.SchedulerProvider;
import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.services.base.SuperheroesService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SuperheroCreatePresenter implements SuperheroCreateContracts.Presenter {

    private final SuperheroesService mSuperheroesService;
    private final SchedulerProvider mSchedulerProvider;
    private SuperheroCreateContracts.View mView;

    @Inject
    public SuperheroCreatePresenter(
            SuperheroesService superheroesService,
            SchedulerProvider schedulerProvider) {
        mSuperheroesService = superheroesService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(SuperheroCreateContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void save(Superhero superhero) {
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Superhero>) emitter -> {
                    Superhero createdSuperhero = mSuperheroesService.createSuperhero(superhero);
                    emitter.onNext(createdSuperhero);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnEach(x -> mView.hideLoading())
                .doOnError(mView::showError)
                .subscribe(s -> mView.navigateToHome());
    }
}
