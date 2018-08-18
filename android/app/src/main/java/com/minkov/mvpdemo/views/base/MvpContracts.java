package com.minkov.mvpdemo.views.base;

public class MvpContracts {
    public interface View<T extends Presenter> {
        void setPresenter(T presenter);
    }

    public interface Presenter<T extends View> {
        void subscribe(T view);

        void unsubscribe();
    }
}
