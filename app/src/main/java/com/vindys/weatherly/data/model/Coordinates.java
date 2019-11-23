package com.vindys.weatherly.data.model;

public class Coordinates {
    private float lon;
    private float lat;
    private String name;

    public Coordinates(float lon, float lat, String name) {
        this.lon = lon;
        this.lat = lat;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
