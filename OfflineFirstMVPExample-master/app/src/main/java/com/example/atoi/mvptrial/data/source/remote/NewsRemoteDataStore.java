package com.example.atoi.mvptrial.data.source.remote;

import com.example.atoi.mvptrial.data.News;
import com.example.atoi.mvptrial.data.source.DataStore;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Atoi on 8.01.2018.
 */

public class NewsRemoteDataStore implements DataStore {

    private NetworkService networkService;
    @Override
    public Observable<List<News>> getNews() {
        networkService = NetworkModule.getApiService();
        return networkService.getNews();
    }

}
