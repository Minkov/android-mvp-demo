package com.minkov.mvpservicesdemofirebase.views.SuperheroCreate;

import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.views.base.MvpContracts;

import java.util.List;

public class SuperheroCreateContracts {
    public interface View extends MvpContracts.View<Presenter> {
        void showLoading();

        void hideLoading();
    }

    public interface Presenter extends MvpContracts.Presenter<View> {
    }
}
