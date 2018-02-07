package com.example.atoi.mvptrial;

import com.example.atoi.mvptrial.di.components.DaggerDataComponent;
import com.example.atoi.mvptrial.di.components.DataComponent;
import com.example.atoi.mvptrial.di.modules.AppModule;
import com.example.atoi.mvptrial.di.modules.DataModule;

/**
 * Created by atoi on 6.02.2018.
 */

public class Application extends android.app.Application {
   private DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        dataComponent = DaggerDataComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}

