package com.vindys.weatherly.data.local.entity;


import com.google.gson.annotations.SerializedName;
import com.vindys.weatherly.data.model.City;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "forecast_response")
public class ForecastResponse {
    private int id;
    @SerializedName("list")
    public List<WeatherResponse> list = null;
    @PrimaryKey
    @NonNull
    private City city;
    private int cod;

    /*public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    @ColumnInfo(name = "created_at")
    private Long createdTime;*/


    public ForecastResponse(int id, List<WeatherResponse> list,@NonNull City city, int cod) {
        this.id = id;
        this.list = list;
        this.city = city;
        this.cod = cod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<WeatherResponse> getList() {
        return list;
    }

    public void setList(List<WeatherResponse> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
