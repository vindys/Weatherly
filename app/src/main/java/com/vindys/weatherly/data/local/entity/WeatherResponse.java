package com.vindys.weatherly.data.local.entity;


import com.vindys.weatherly.data.model.Coordinates;
import com.vindys.weatherly.data.model.MainModel;
import com.vindys.weatherly.data.model.Sys;
import com.vindys.weatherly.data.model.WeatherModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "weather_response")
public class WeatherResponse {
    @PrimaryKey
    private int id;
    private Coordinates coord;
    private MainModel main;
    private List<WeatherModel> weather;
    private Sys sys;
    private int timezone;
    private String name;
    private int cod;
    private int visibility;
    private long dt;
    private long currentTime;

    public WeatherResponse(int id, Coordinates coord, MainModel main, List<WeatherModel> weather,
                           Sys sys, int timezone, String name, int cod, int visibility, long dt, long currentTime) {
        this.id = id;
        this.coord = coord;
        this.main = main;
        this.weather = weather;
        this.sys = sys;
        this.timezone = timezone;
        this.name = name;
        this.cod = cod;
        this.visibility = visibility;
        this.dt = dt;
        this.currentTime = currentTime;
        if(currentTime == 0){
            setCurrentTime( new Date().getTime());
        }
    }

    public String getCreatedOn() {
        DateFormat dateTimeFormatter = new SimpleDateFormat("E dd MM hh:mm a");
        Date date = new Date(TimeUnit.SECONDS.toMillis(dt ));
        return dateTimeFormatter.format(date);
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public MainModel getMain() {
        return main;
    }

    public void setMain(MainModel main) {
        this.main = main;
    }

    public List<WeatherModel> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherModel> weather) {
        this.weather = weather;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }
}
