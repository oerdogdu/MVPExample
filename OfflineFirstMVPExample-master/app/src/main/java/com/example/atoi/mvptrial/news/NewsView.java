package com.example.atoi.mvptrial.news;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.atoi.mvptrial.data.source.DataRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by atoi on 5.02.2018.
 */

public class NewsView extends Activity implements NewsContract.View {

    @Inject NewsPresenter newsPresenter;
    @Inject
    DataRepository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((com.example.atoi.mvptrial.Application) getApplication())
                .getDataComponent()
                .inject(this);

        new NewsPresenter(repository);
    }

    @Override
    public void onResume() {
        super.onResume();
        newsPresenter.takeView(this);
        newsPresenter.loadNews();
    }

    @Override
    public void showNews(List news) {
        Toast.makeText(NewsView.this, news.size()+"", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }
}
