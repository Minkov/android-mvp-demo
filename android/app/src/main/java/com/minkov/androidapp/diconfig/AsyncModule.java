package com.minkov.androidapp.diconfig;

import com.minkov.androidapp.async.AsyncSchedulerProvider;
import com.minkov.androidapp.async.base.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AsyncModule {
    @Provides
    @Singleton
    public SchedulerProvider schedulerProvider() {
        return AsyncSchedulerProvider.getInstance();
    }
}
