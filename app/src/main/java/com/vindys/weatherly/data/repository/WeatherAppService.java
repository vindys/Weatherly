package com.vindys.weatherly.data.repository;

import com.vindys.weatherly.data.local.entity.ForecastResponse;
import com.vindys.weatherly.data.local.entity.WeatherResponse;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface WeatherAppService {
    String HTTPS_API_WEATHER_URL = "http://api.openweathermap.org/data/2.5/";

    /*@GET("weather")
    LiveData<ApiResponse<WeatherResponse>> getWeather(@Query("lat") double lat,
                                                      @Query("lon") double lon);*/

    @GET("forecast")
    Call<ForecastResponse> getForecast(@Query("lat") double lat,
                                                        @Query("lon") double lon);
}
