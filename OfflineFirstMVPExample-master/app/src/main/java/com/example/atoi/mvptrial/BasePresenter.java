package com.example.atoi.mvptrial;

/**
 * Created by Atoi on 3.01.2018.
 */

public interface BasePresenter<T extends BaseView> {

    void subscribe();

    void unsubscribe();
}
