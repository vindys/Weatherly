package com.vindys.weatherly.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.moshi.Moshi;
import com.vindys.weatherly.data.model.Coordinates;
import com.vindys.weatherly.data.model.MainModel;
import com.vindys.weatherly.data.model.Sys;
import com.vindys.weatherly.data.model.WeatherModel;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class WeatherTypeConverter {

    private Moshi moshi = new Moshi.Builder().build();

    @TypeConverter
    public String coordinatesToJson(Coordinates coordinates) {
        return coordinates == null ? null : moshi.adapter(Coordinates.class).toJson(coordinates);
    }

    @TypeConverter
    public Coordinates coordinatesFromJson(String string) {
        Coordinates weather;
        try {
            weather = moshi.adapter(Coordinates.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return weather;
    }

    @TypeConverter
    public String sysToJson(Sys sys) {
        return sys == null ? null : moshi.adapter(Sys.class).toJson(sys);
    }

    @TypeConverter
    public Sys sysFromJson(String string) {
        Sys sys;
        try {
            sys = moshi.adapter(Sys.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sys;
    }

    @TypeConverter // note this annotation
    public String fromWeatherModelList(List<WeatherModel> weatherModels) {
        if (weatherModels == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<WeatherModel>>() {
        }.getType();
        String json = gson.toJson(weatherModels, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<WeatherModel> toWeatherModelsList(String weatherModelString) {
        if (weatherModelString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<WeatherModel>>() {
        }.getType();
        List<WeatherModel> weatherModels = gson.fromJson(weatherModelString, type);
        return weatherModels;
    }

    @TypeConverter
    public String MainModelToJson(MainModel mainModel) {
        return mainModel == null ? null : moshi.adapter(MainModel.class).toJson(mainModel);
    }

    @TypeConverter
    public MainModel mainModelFromJson(String string) {
        MainModel mainModel;
        try {
            mainModel = moshi.adapter(MainModel.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return mainModel;
    }
}
