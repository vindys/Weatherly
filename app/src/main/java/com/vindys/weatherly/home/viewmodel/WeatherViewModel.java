package com.vindys.weatherly.home.viewmodel;

import android.util.Log;

import com.vindys.weatherly.data.Resource;
import com.vindys.weatherly.data.local.entity.ForecastResponse;
import com.vindys.weatherly.data.model.Location;
import com.vindys.weatherly.data.repository.WeatherRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {
    private static final String TAG = "WeatherViewModel";

    @Inject
    WeatherRepository weatherRepository;
    private LiveData<ForecastResponse> forecastObservable;


    @Inject
    WeatherViewModel( WeatherRepository weatherRepository) {
        super();

        this.weatherRepository = weatherRepository;
        forecastObservable = weatherRepository.getForecastObservable();

    }

    public LiveData<ForecastResponse> getForecastObservable() {
        return forecastObservable;
    }
}
