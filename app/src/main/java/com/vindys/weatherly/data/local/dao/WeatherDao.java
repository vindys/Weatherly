package com.vindys.weatherly.data.local.dao;

import com.vindys.weatherly.data.local.entity.ForecastResponse;
import com.vindys.weatherly.data.local.entity.WeatherResponse;
import com.vindys.weatherly.data.model.Location;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

/**
 * Created by Vindys.
 */
@Dao
public abstract class WeatherDao {

    @Query("SELECT * FROM weather_response")
    abstract LiveData<WeatherResponse> loadWeather();

    @Query("SELECT * FROM forecast_response")
    abstract public LiveData<ForecastResponse> getForecast();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract public void saveWeather(WeatherResponse weatherEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract public long saveForecast(ForecastResponse forecastResponse);

    @Query("SELECT * FROM weather_response WHERE id=:id")
    abstract LiveData<WeatherResponse> getWeather(int id);

    @Query("Select count(id) from forecast_response")
    abstract Integer getForecastCount();

    @Delete
    abstract public void deleteWeather(WeatherResponse... items);

    @Query("Delete from weather_response")
    abstract public void deleteAllWeatherResponse();

    @Query("Delete from forecast_response")
    abstract public void deleteAllForecastResponse();

    @Transaction
    public long updateData(ForecastResponse forecastResponse) {
        deleteAllForecastResponse();
        saveForecast(forecastResponse);
        return getForecastCount();
    }

}
