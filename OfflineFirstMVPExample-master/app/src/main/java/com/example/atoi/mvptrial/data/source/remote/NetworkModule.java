package com.example.atoi.mvptrial.data.source.remote;

import com.example.atoi.mvptrial.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Atoi on 9.01.2018.
 */

public class NetworkModule {

    private static Retrofit retrofit;
    private static NetworkService networkService;

    public static NetworkService getApiService() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
         networkService =  retrofit.create(NetworkService.class);
         return networkService;
    }

}
