package com.vindys.weatherly.di.module;

import android.app.Application;
import android.content.Context;

import com.vindys.weatherly.MainApplication;
import com.vindys.weatherly.data.local.WeatherlyDatabase;
import com.vindys.weatherly.data.local.dao.WeatherDao;
import com.vindys.weatherly.di.scope.ApplicationContext;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * @author tobennaezike
 * @since 20/03/19
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    WeatherlyDatabase provideAppDatabase(@NonNull Context context) {
        return Room.databaseBuilder(context, WeatherlyDatabase.class, "weather_db")
                .build();
    }

    @Provides
    @Singleton
    static WeatherDao provideWeatherResponseDao(@NonNull WeatherlyDatabase appDatabase) {
        return appDatabase.weatherDao();
    }
}
