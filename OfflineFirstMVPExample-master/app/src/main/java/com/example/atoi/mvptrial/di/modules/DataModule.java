package com.example.atoi.mvptrial.di.modules;

import android.content.Context;

import com.example.atoi.mvptrial.data.source.DataRepository;
import com.example.atoi.mvptrial.data.source.local.NewsLocalDataStore;
import com.example.atoi.mvptrial.data.source.remote.NewsRemoteDataStore;
import com.example.atoi.mvptrial.di.scopes.ForApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atoi on 5.02.2018.
 */

@Module
public class DataModule {

    @Provides
    @Inject
    @ForApplication
    NewsLocalDataStore getNewsLocalDataStore(Context context) {
        return new NewsLocalDataStore(context);
    }

    @Provides
    @Singleton
    NewsRemoteDataStore getNewsRemoteDataStore() {
        return new NewsRemoteDataStore();
    }

    @Provides
    @Singleton
    DataRepository getDataRepository(NewsLocalDataStore newsLocalDataStore, NewsRemoteDataStore newsRemoteDataStore) {
        return new DataRepository(newsLocalDataStore, newsRemoteDataStore);

    }
}
