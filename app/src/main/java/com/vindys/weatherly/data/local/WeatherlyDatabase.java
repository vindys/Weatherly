package com.vindys.weatherly.data.local;

import com.vindys.weatherly.data.local.dao.WeatherDao;
import com.vindys.weatherly.data.local.entity.ForecastResponse;
import com.vindys.weatherly.data.local.entity.WeatherResponse;
import com.vindys.weatherly.data.model.Location;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {WeatherResponse.class, ForecastResponse.class, Location.class},version = 1,exportSchema = false)
@TypeConverters({WeatherTypeConverter.class,ForecastTypeConverter.class})
public abstract class WeatherlyDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
