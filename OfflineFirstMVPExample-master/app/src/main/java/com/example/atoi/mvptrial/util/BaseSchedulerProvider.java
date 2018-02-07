package com.example.atoi.mvptrial.util;

import io.reactivex.Scheduler;

/**
 * Created by Atoi on 4.01.2018.
 */

public interface BaseSchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
