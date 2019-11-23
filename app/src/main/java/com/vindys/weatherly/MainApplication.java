package com.vindys.weatherly;

import com.vindys.weatherly.di.component.AppComponent;
import com.vindys.weatherly.di.component.DaggerAppComponent;
import com.vindys.weatherly.utils.worker.HasWorkerInjector;
import com.vindys.weatherly.worker.DaggerWorkerFactory;
import com.vindys.weatherly.worker.LoadWeatherWorker;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerApplication;

public class MainApplication extends DaggerApplication implements HasWorkerInjector {

    @Inject
    DispatchingAndroidInjector<Worker> workerDispatchingAndroidInjector;

    @Inject
    DaggerWorkerFactory daggerWorkerFactory;

    public static final String LOAD_FORECAST_WORKER_UNIQUE = "LoadForecastWorkerUnique";

    @Override
    public void onCreate() {
        super.onCreate();

        Configuration configuration = new Configuration.Builder()
                .setWorkerFactory(daggerWorkerFactory)
                .build();
        WorkManager.initialize(getApplicationContext(), configuration);
        loadWorker();
    }

    private void loadWorker(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build();
        PeriodicWorkRequest fetchWeather =
                new PeriodicWorkRequest.Builder(LoadWeatherWorker.class,
                        150,TimeUnit.MINUTES
                        /*,30,TimeUnit.MINUTES*/)
                        .setConstraints(constraints)
                        .build();
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                LOAD_FORECAST_WORKER_UNIQUE,
                ExistingPeriodicWorkPolicy.REPLACE,
                fetchWeather);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).context(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public AndroidInjector<Worker> workerInjector() {
        return workerDispatchingAndroidInjector;
    }
}
