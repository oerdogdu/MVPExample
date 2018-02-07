package com.example.atoi.mvptrial.data.source;

import android.util.Log;

import com.example.atoi.mvptrial.data.News;
import com.example.atoi.mvptrial.data.source.local.NewsLocalDataStore;
import com.example.atoi.mvptrial.data.source.remote.NewsRemoteDataStore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Atoi on 4.01.2018.
 */

public class DataRepository implements DataStore {

    boolean mCacheIsDirty = true;

    Map<String, News> mCachedNews;


    private NewsLocalDataStore mNewsLocalDataStore;
    private NewsRemoteDataStore mNewsRemoteDataStore;

    public DataRepository(NewsLocalDataStore mNewsLocalDataStore, NewsRemoteDataStore mNewsRemoteDataStore) {
        this.mNewsLocalDataStore = mNewsLocalDataStore;
        this.mNewsRemoteDataStore = mNewsRemoteDataStore;
    }

    @Override
    public Observable<List<News>> getNews() {
        if (mCachedNews != null && !mCacheIsDirty) {
            Log.d("cached", "cached");
            return Observable.fromIterable(mCachedNews.values()).toList().toObservable();
        }
        else if(mCachedNews == null) {
            mCachedNews = new LinkedHashMap<>();
        }
        Observable<List<News>> remoteNews = getAndSaveRemoteNews();

        if (mCacheIsDirty) {
            return remoteNews;
        } else {
            Observable<List<News>> localNews = getAndCacheLocalNews();
            return Observable.concat(localNews, remoteNews)
                    .filter(tasks -> !tasks.isEmpty())
                    .firstOrError()
                    .toObservable();
        }
    }

    private Observable<List<News>> getAndCacheLocalNews() {
        return mNewsLocalDataStore.getNews()
                .flatMap(tasks -> Observable.fromIterable(tasks)
                        .doOnNext(task -> mCachedNews.put(task.getId(), task))
                        .toList()
                        .toObservable());
    }

    private Observable<List<News>> getAndSaveRemoteNews() {
        return mNewsRemoteDataStore
                .getNews()
                .flatMap(allNews -> Observable.fromIterable(allNews).doOnNext(news -> {
                    mNewsLocalDataStore.saveNews(news);
                    mCachedNews.put(news.getId(), news);
                }).toList().toObservable())
                .doOnComplete(() -> mCacheIsDirty = false);
    }
}
