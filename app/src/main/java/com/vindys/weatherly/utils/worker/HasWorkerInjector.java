package com.vindys.weatherly.utils.worker;

import androidx.work.Worker;

import dagger.android.AndroidInjector;

public interface HasWorkerInjector {
    AndroidInjector<Worker> workerInjector();
}
