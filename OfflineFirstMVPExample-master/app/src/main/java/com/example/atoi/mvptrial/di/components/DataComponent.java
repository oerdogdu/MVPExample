package com.example.atoi.mvptrial.di.components;

import com.example.atoi.mvptrial.Application;
import com.example.atoi.mvptrial.di.modules.AppModule;
import com.example.atoi.mvptrial.di.modules.DataModule;
import com.example.atoi.mvptrial.news.NewsView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by atoi on 6.02.2018.
 */
@Singleton
@Component(modules = {AppModule.class, DataModule.class}) public interface DataComponent {
    void inject(NewsView newsView);
    void inject(Application application);
}
