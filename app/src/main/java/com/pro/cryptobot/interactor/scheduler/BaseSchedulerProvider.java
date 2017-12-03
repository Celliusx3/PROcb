package com.pro.cryptobot.interactor.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by coyanoh on 28/11/2017.
 */

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
