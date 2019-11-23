package com.vindys.weatherly.data.model;

public class MainModel {

    private Float temp;

    private int pressure;

    private float humidity;

    private float temp_min;

    private float sea_level;

    private float grnd_level;

    public MainModel(Float temp, int pressure, float humidity, float temp_min, float sea_level, float grnd_level, float temp_max) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.sea_level = sea_level;
        this.grnd_level = grnd_level;
        this.temp_max = temp_max;
    }

    private float temp_max;

    public float getSea_level() {
        return sea_level;
    }

    public void setSea_level(float sea_level) {
        this.sea_level = sea_level;
    }

    public float getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(float grnd_level) {
        this.grnd_level = grnd_level;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public int getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }
}
