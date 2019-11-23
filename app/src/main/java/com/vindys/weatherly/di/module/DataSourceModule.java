package com.vindys.weatherly.di.module;

import com.vindys.weatherly.data.repository.WeatherRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataSourceModule {
    @Binds
    abstract WeatherRepository provideRepoImpl(WeatherRepository repo);
}
