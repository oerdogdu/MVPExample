package com.example.atoi.mvptrial.news;

import com.example.atoi.mvptrial.data.News;
import com.example.atoi.mvptrial.data.source.DataRepository;
import com.example.atoi.mvptrial.util.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Created by Atoi on 10.01.2018.
 */
public class NewsPresenter implements NewsContract.Presenter {

    private final DataRepository dataRepository;
    private NewsContract.View view;
    private SchedulerProvider schedulerProvider;


    @Inject public NewsPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        schedulerProvider = SchedulerProvider.getInstance();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadNews() {
        dataRepository.getNews()
                .flatMap(Observable::fromIterable)
                .toList()
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(news ->
                {
                    processNews(news);
                });
    }

    @Override
    public void takeView(NewsContract.View view) {
        this.view = view;

    }

    public void processNews(@NonNull List<News> newsList) {
        view.showNews(newsList);
    }
}
