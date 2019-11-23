package com.vindys.weatherly.di.module;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.squareup.moshi.Moshi;
import com.vindys.weatherly.BuildConfig;
import com.vindys.weatherly.data.repository.WeatherAppService;
import com.vindys.weatherly.utils.worker.AndroidWorkerInjectionModule;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

@Module(includes = {AndroidWorkerInjectionModule.class})
public class AppModule {



    @Provides
    @Singleton
    static Moshi provideMoshi() {
        return new Moshi.Builder().build();
    }

    @Singleton
    @Provides
    @EverythingIsNonNull
    static WeatherAppService provideSampleAppService(Moshi moshi) {
        return new Retrofit.Builder()
                .baseUrl(WeatherAppService.HTTPS_API_WEATHER_URL)
                .client(
                        new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request();
                                HttpUrl url = request.url().newBuilder()
                                        .addQueryParameter("appid", BuildConfig.ApiKey)
                                        .addQueryParameter("units","metric").build();
                                request = request.newBuilder().url(url).build();
                                return chain.proceed(request);
                            }
                        }).build()
                )
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(WeatherAppService.class);
    }

    @Singleton
    @Provides
    public FusedLocationProviderClient provideFusedLocationProviderClient(Application context) {
        return LocationServices.getFusedLocationProviderClient(context);
    }


    @Provides
    @Singleton
    public LocationManager provideLocationManager(Application context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }



}
