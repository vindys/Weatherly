package com.vindys.weatherly.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.moshi.Moshi;
import com.vindys.weatherly.data.local.entity.WeatherResponse;
import com.vindys.weatherly.data.model.City;
import com.vindys.weatherly.data.model.Coordinates;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.room.TypeConverter;

public class ForecastTypeConverter {
    private Moshi moshi;

    @Inject
    public ForecastTypeConverter(Moshi moshi) {
        this.moshi = moshi;
    }

    public ForecastTypeConverter() {
        moshi = new Moshi.Builder().build();
    }

    @TypeConverter
    public String cityToJson(City city) {
        return city == null ? null : moshi.adapter(City.class).toJson(city);
    }

    @TypeConverter
    public City cityFromJson(String string) {
        City city;
        try {
            city = moshi.adapter(City.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return city;
    }

    @TypeConverter // note this annotation
    public String fromWeatherResponseList(List<WeatherResponse> weatherResponses) {
        if (weatherResponses == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<WeatherResponse>>() {
        }.getType();
        String json = gson.toJson(weatherResponses, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<WeatherResponse> toWeatherResponsesList(String weatherResponseString) {
        if (weatherResponseString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<WeatherResponse>>() {
        }.getType();
        List<WeatherResponse> weatherResponses = gson.fromJson(weatherResponseString, type);
        return weatherResponses;
    }

}
