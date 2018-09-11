package com.minkov.androidapp.views.SuperheroesList;

import com.minkov.androidapp.models.Superhero;

import java.util.List;

public interface SuperheroesListContracts {
    interface View {
        void setPresenter(Presenter presenter);

        void showSuperheroes(List<Superhero> superheroes);

        void showEmptySuperheroesList();

        void showError(Throwable e);

        void showLoading();

        void hideLoading();

        void showSuperheroDetails(Superhero superhero);
    }

    interface Presenter {
        void subscribe(View view);

        void loadSuperheroes();

        void filterSuperheroes(String pattern);

        void selectSuperhero(Superhero superhero);
    }

    interface Navigator {
        void navigateWith(Superhero superhero);
    }
}
