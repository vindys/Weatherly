package com.vindys.weatherly.data.local.dao;

import android.content.Context;

import com.vindys.weatherly.data.local.WeatherlyDatabase;
import com.vindys.weatherly.data.local.entity.ForecastResponse;
import com.vindys.weatherly.data.local.entity.WeatherResponse;
import com.vindys.weatherly.data.model.City;
import com.vindys.weatherly.data.model.Coordinates;
import com.vindys.weatherly.data.model.MainModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.*;

public class WeatherDaoTest {

    private WeatherlyDatabase database;
    private WeatherDao weatherDao;
    private long forecastID = 0;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, WeatherlyDatabase.class).build();
        weatherDao = database.weatherDao();

        database.weatherDao().saveForecast(new ForecastResponse(1,null,new City(1,"Bangalore",
                new Coordinates(20.0f,4.5f,"BangaloreID"),"India"),200));
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }


}