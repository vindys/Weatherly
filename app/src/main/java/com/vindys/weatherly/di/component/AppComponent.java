package com.vindys.weatherly.di.component;



import android.app.Application;
import android.content.Context;

import com.vindys.weatherly.MainApplication;
import com.vindys.weatherly.di.module.AppModule;
import com.vindys.weatherly.di.module.DatabaseModule;
import com.vindys.weatherly.di.module.MainActivityModule;
import com.vindys.weatherly.di.module.ViewModelFactoryModule;
import com.vindys.weatherly.di.module.WorkerModule;
import com.vindys.weatherly.utils.worker.AndroidWorkerInjectionModule;
import com.vindys.weatherly.worker.DaggerWorkerFactory;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AndroidWorkerInjectionModule.class,
        WorkerModule.class,
        AppModule.class,
        MainActivityModule.class,
        DatabaseModule.class,
        ViewModelFactoryModule.class})
public interface AppComponent extends AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder context(Context context);
    }

    DaggerWorkerFactory factory();
}