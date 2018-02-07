package com.example.atoi.mvptrial.news;

import com.example.atoi.mvptrial.BasePresenter;
import com.example.atoi.mvptrial.BaseView;

import java.util.List;

/**
 * Created by Atoi on 10.01.2018.
 */

public class NewsContract {

    public interface View extends BaseView {
        void showNews(List news);

        void showError(String message);

        void showComplete();
    }

    public interface Presenter extends BasePresenter {
        void loadNews();

        void takeView(NewsContract.View view);
    }

}
