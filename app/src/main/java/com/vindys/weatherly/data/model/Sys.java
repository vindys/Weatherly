package com.vindys.weatherly.data.model;

import com.squareup.moshi.Json;

public class Sys {
    private int id;
    private int type;
    @Json(name = "country")
    private String countryCode;
    private long sunrise;
    private long sunset;
    private int message;

    public Sys(int id, int type, String countryCode, long sunrise, long sunset, int message) {
        this.id = id;
        this.type = type;
        this.countryCode = countryCode;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
