package com.vindys.weatherly.di.module;

import android.app.Application;
import android.content.Context;

import com.vindys.weatherly.MainApplication;
import com.vindys.weatherly.di.ViewModelKey;
import com.vindys.weatherly.di.scope.ActivityContext;
import com.vindys.weatherly.home.MainActivity;
import com.vindys.weatherly.home.viewmodel.WeatherViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainActivityModule {



    @ContributesAndroidInjector()
    abstract MainActivity providesMainActivity();

    @Binds
    @ActivityContext
    public abstract Context bindContext(Context mainActivity) ;


    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel.class)
    public abstract ViewModel bindMainViewModel(WeatherViewModel viewModel);
}
