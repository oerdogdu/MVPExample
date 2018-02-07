package com.example.atoi.mvptrial.di.modules;

import android.content.Context;

import com.example.atoi.mvptrial.Application;
import com.example.atoi.mvptrial.di.scopes.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atoi on 6.02.2018.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton @ForApplication
    public Context provideApplicationContext() {
        return application;
    }
}
