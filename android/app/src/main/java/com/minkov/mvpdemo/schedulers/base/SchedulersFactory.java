package com.minkov.mvpdemo.schedulers.base;

import io.reactivex.Scheduler;

public interface SchedulersFactory {
    Scheduler background();

    Scheduler ui();
}
