package com.vindys.weatherly.worker;

import android.content.Context;
import android.util.Log;

import com.squareup.inject.assisted.AssistedInject;
import com.vindys.weatherly.data.model.CollectionUtils;

import java.lang.reflect.Constructor;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;

@Singleton
public class DaggerWorkerFactory extends WorkerFactory {
    private final Map<Class<? extends ListenableWorker>, Provider<ChildWorkerFactory>> creators;

    @Inject
    public DaggerWorkerFactory(Map<Class<? extends ListenableWorker>, Provider<ChildWorkerFactory>> creators) {
        this.creators = creators;
    }


    @Nullable
    @Override
    public ListenableWorker createWorker(
            @NonNull Context appContext,
            @NonNull String workerClassName,
            @NonNull WorkerParameters workerParameters) {
        Provider<ChildWorkerFactory> factoryProvider = CollectionUtils.getWorkerFactoryProviderByKey(creators, workerClassName);
        return factoryProvider.get().create(appContext, workerParameters);

    }
}
