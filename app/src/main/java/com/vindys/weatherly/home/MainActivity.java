package com.vindys.weatherly.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.vindys.weatherly.R;
import com.vindys.weatherly.data.Resource;
import com.vindys.weatherly.data.Status;
import com.vindys.weatherly.data.local.entity.ForecastResponse;
import com.vindys.weatherly.databinding.ActivityMainBinding;
import com.vindys.weatherly.home.adapter.ForecastRecyclerViewAdapter;
import com.vindys.weatherly.home.viewmodel.WeatherViewModel;
import com.vindys.weatherly.worker.LoadWeatherWorker;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity{
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_ASK_LOCATION_PERMISSION = 100;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    ActivityMainBinding activityMainBinding;
    private WeatherViewModel viewModel;
    private ForecastRecyclerViewAdapter forecastRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        initDataBinding();
        observeViewModel();
    }

    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(WeatherViewModel.class);
        checkLocationPermission();
        initializeRecyclerView(activityMainBinding);
        activityMainBinding.setWeatherViewModel(viewModel);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void observeViewModel(){
        viewModel.getForecastObservable().observe(this, new Observer<ForecastResponse>() {
                    @Override
                    public void onChanged(@Nullable ForecastResponse responseResource) {
                        if(responseResource != null &&responseResource.list != null) {
                            activityMainBinding.setForecast(responseResource);
                            forecastRecyclerViewAdapter.setWeatherResponses(responseResource.list);
                        }
                    }
                });
    }

    private void initializeRecyclerView(ActivityMainBinding sampleActivityBinding){
        forecastRecyclerViewAdapter = new ForecastRecyclerViewAdapter();
        sampleActivityBinding.weatherList.setLayoutManager(new LinearLayoutManager(this));
        sampleActivityBinding.weatherList.setAdapter(forecastRecyclerViewAdapter);
    }

    public void checkLocationPermission() {
        int hasWriteStoragePermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasWriteStoragePermission = getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ASK_LOCATION_PERMISSION);
                return;
            }
        } else {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_ASK_LOCATION_PERMISSION){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the
                // location-related task you need to do.
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                }
            } else {
                // permission denied
            }
        }
    }
}
