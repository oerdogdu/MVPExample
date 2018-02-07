package com.example.atoi.mvptrial.data.source;

import com.example.atoi.mvptrial.data.News;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Atoi on 4.01.2018.
 */

public interface DataStore {
    Observable<List<News>> getNews();
}
