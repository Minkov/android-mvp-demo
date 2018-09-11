package com.minkov.androidapp.diconfig;

import android.app.Application;

import com.minkov.androidapp.AndroidApplication;
import com.minkov.androidapp.validators.base.Validator;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        AppModule.class,
        ParsersModule.class,
        HttpModule.class,
        RepositoriesModule.class,
        ServicesModule.class,
        ValidatorsModule.class,
        AsyncModule.class,
        ViewsModule.class
})
public interface AppComponent extends AndroidInjector<AndroidApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
