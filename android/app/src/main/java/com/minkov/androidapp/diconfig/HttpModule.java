package com.minkov.androidapp.diconfig;

import com.minkov.androidapp.Constants;
import com.minkov.androidapp.http.HttpRequester;
import com.minkov.androidapp.http.OkHttpHttpRequester;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpModule {
    @Provides
    public HttpRequester httpRequester() {
        return new OkHttpHttpRequester();
    }

    @Provides
    @Named("baseServerUrl")
    public String baseServerUrl() {
        return Constants.BASE_SERVER_URL;
    }
}
