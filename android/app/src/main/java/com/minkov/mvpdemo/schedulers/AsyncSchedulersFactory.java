package com.minkov.mvpdemo.schedulers;

import com.minkov.mvpdemo.schedulers.base.SchedulersFactory;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AsyncSchedulersFactory implements SchedulersFactory {
    private static AsyncSchedulersFactory mInstance;

    private AsyncSchedulersFactory() {

    }

    public static AsyncSchedulersFactory instance() {
        if (mInstance == null) {
            mInstance = new AsyncSchedulersFactory();
        }

        return mInstance;
    }

    @Override
    public Scheduler background() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
