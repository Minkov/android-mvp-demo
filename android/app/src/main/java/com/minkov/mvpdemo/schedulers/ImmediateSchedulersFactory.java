package com.minkov.mvpdemo.schedulers;

import com.minkov.mvpdemo.schedulers.base.SchedulersFactory;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ImmediateSchedulersFactory implements SchedulersFactory {

    private static ImmediateSchedulersFactory mInstance;

    private ImmediateSchedulersFactory() {

    }

    public static ImmediateSchedulersFactory instance() {
        if (mInstance == null) {
            mInstance = new ImmediateSchedulersFactory();
        }

        return mInstance;
    }

    @Override
    public Scheduler background() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
