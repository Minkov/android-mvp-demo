package com.minkov.mvpdemo.views.SuperheroesList;

import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.views.base.MvpContracts;

import java.util.List;

public class SuperheroesListContracts {
    public interface View extends MvpContracts.View<Presenter> {
        void showSuperheroes(List<Superhero> superheroes);
        void showLoading();
        void hideLoading();
        void showDetails(Superhero superhero);
        void showEmptySuperheroes();
        void setPresenter(Presenter presenter);
    }

    public interface Presenter extends MvpContracts.Presenter<View> {
        void subscribe(View view);
        void unsubscribe();
        void onSuperheroSelected(Superhero superhero);
        void applyFilter(String pattern);
    }
}
