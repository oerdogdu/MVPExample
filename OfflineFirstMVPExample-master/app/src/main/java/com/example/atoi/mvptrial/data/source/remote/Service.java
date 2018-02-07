package com.example.atoi.mvptrial.data.source.remote;

import com.example.atoi.mvptrial.data.News;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Atoi on 9.01.2018.
 */

public class Service {

    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public void getCityList(final GetNewsCallback getNewsCallback) {
        networkService.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(io.reactivex.Observable.empty())
                .scan(new ArrayList<News>(), (list, news) -> {
                    list.addAll(news);
                    return list;
                })
                .subscribe(list -> getNewsCallback.onSuccess(list));
    }


        public interface GetNewsCallback{
        void onSuccess(ArrayList<News> newsResponse);

        void onError();
    }

}
