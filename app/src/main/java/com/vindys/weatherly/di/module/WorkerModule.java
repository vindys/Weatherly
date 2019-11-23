package com.vindys.weatherly.di.module;

import com.vindys.weatherly.di.WorkerKey;
import com.vindys.weatherly.worker.ChildWorkerFactory;
import com.vindys.weatherly.worker.LoadWeatherWorker;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(LoadWeatherWorker.class)
    public abstract ChildWorkerFactory bindLoadWeatherWorker(LoadWeatherWorker.Factory factory);
}
