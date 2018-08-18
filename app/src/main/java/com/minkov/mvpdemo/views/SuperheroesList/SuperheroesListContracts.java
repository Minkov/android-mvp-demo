package com.minkov.mvpdemo.views.SuperheroesList;

import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.views.base.MvpContracts;

import java.util.List;

public class SuperheroesListContracts {
    public interface View extends MvpContracts.View<Presenter> {
        void showSuperheroes(List<Superhero> superheroes);

        void showLoading();

        void hideLoading();

        void showSuperhero(Superhero superhero);

        void showEmptySuperheroes();
    }

    public interface Presenter extends MvpContracts.Presenter<View> {
        void onSuperheroSelected(Superhero superhero);

        void applyFilter(String pattern);
    }
}
