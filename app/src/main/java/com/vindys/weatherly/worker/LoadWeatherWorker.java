package com.vindys.weatherly.worker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.util.concurrent.ListenableFuture;
import com.vindys.weatherly.data.local.entity.ForecastResponse;
import com.vindys.weatherly.data.repository.WeatherAppService;
import com.vindys.weatherly.data.repository.WeatherRepository;
import com.vindys.weatherly.utils.DBOperationListener;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.futures.SettableFuture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadWeatherWorker extends ListenableWorker{

    private static final String TAG = "LoadWeatherWorker";

    private WeatherRepository weatherRepository;
    private WeatherAppService weatherAppService;
    private SettableFuture<Result> mFuture;

    private LoadWeatherWorker(Context context,
                              WorkerParameters params,
                              WeatherRepository weatherRepository,
                              WeatherAppService weatherAppService) {
        super(context, params);
        this.weatherRepository = weatherRepository;
        this.weatherAppService = weatherAppService;
    }

    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        mFuture = SettableFuture.create();

        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        LocationCallback mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
            }
        };

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //        onLocationSuccess(location);
                if (location == null) {
                    Log.d(TAG, "Location is null");
                    mFuture.set(Result.failure());
                } else {
                    weatherAppService.getForecast(location.getLatitude(),location.getLongitude()).enqueue(
                            new Callback<ForecastResponse>() {
                                @Override
                                public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                                    if (!response.isSuccessful()) {
                                        // error case
                                        switch (response.code()) {
                                            case 404:
                                                break;
                                            case 500:
                                                break;
                                            default:
                                                break;
                                        }
                                        mFuture.set(Result.failure()) ;
                                    } else {
                                        weatherRepository.updateForecast(response.body(), new DBOperationListener() {
                                            @Override
                                            public void onFinishDBOperation(Long result) {
                                                if(result ==1)
                                                    mFuture.set(Result.success());
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onFailure(Call<ForecastResponse> call, Throwable t) {
                                    mFuture.set(Result.failure());
                                }
                            }
                    );

                }




            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mFuture.set(Result.failure());
            }
        });
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        return mFuture;
    }

    private void onLocationSuccess(Location location){


    }

    private void getForecastFromServer(Response<ForecastResponse> response){

        //   result = Result.failure();
    }

    private void addForecastToDB(ForecastResponse forecastResponse) {


    }

    public static class Factory implements ChildWorkerFactory {

        private final Provider<WeatherRepository> modelProvider;

        private final Provider<WeatherAppService> weatherAppServiceProvider;

        @Inject
        public Factory(Provider<WeatherRepository> modelProvider,
                       Provider<WeatherAppService> weatherAppServiceProvider) {
            this.modelProvider = modelProvider;
            this.weatherAppServiceProvider = weatherAppServiceProvider;
        }

        @Override
        public ListenableWorker create(Context context, WorkerParameters workerParameters) {
            return new LoadWeatherWorker(context,
                    workerParameters,
                    modelProvider.get(),
                    weatherAppServiceProvider.get());
        }
    }
}
