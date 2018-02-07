package com.example.atoi.mvptrial.data.source.remote;

import com.example.atoi.mvptrial.data.News;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Atoi on 9.01.2018.
 */

public interface NetworkService {
    @GET("typicode/demo/posts")
    Observable<List<News>> getNews();
}
