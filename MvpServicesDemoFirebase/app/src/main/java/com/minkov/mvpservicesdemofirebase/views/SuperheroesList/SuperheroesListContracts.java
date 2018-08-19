package com.minkov.mvpservicesdemofirebase.views.SuperheroesList;

import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.views.base.MvpContracts;

import java.util.List;

public class SuperheroesListContracts {
    public interface View extends MvpContracts.View<Presenter> {
        void showSuperheroes(List<Superhero> superheroes);

        void showLoading();

        void hideLoading();

        void showEmptySuperheroes();
    }

    public interface Presenter extends MvpContracts.Presenter<View> {
        void applyFilter(String pattern);
    }
}
