package com.minkov.androidapp.views.SuperheroDetails;

import com.minkov.androidapp.models.Superhero;

public interface SuperheroDetailsContracts {
    interface View {
        void showSuperhero(Superhero superhero);

        void setPresenter(Presenter presenter);

        void showError(Throwable e);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void subscribe(View view);

        void loadSuperhero();

        void setSuperheroId(int id);
    }
}
