package com.vindys.weatherly.data.repository;


import android.os.AsyncTask;
import android.util.Log;

import com.vindys.weatherly.data.Resource;
import com.vindys.weatherly.data.Status;
import com.vindys.weatherly.data.local.dao.WeatherDao;
import com.vindys.weatherly.data.local.entity.ForecastResponse;
import com.vindys.weatherly.data.local.entity.WeatherResponse;
import com.vindys.weatherly.data.model.Location;
import com.vindys.weatherly.utils.AppExecutors;
import com.vindys.weatherly.utils.DBOperationListener;
import com.vindys.weatherly.utils.RateLimiter;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Observable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.ListenableWorker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vindys
 */

public class WeatherRepository{

    private static final String TAG = "WeatherRepository";

    private WeatherDao weatherDao;

    private LiveData<ForecastResponse> forecastObservable;

    public LiveData<ForecastResponse> getForecastObservable() {
        return forecastObservable;
    }

    @Inject
    public WeatherRepository(WeatherDao weatherDao, WeatherAppService weatherAppService) {
        this.weatherDao = weatherDao;

        forecastObservable = weatherDao.getForecast();
    }

    private void loadForecastFromDB() {
        weatherDao.getForecast();
    }

    public void updateForecast(ForecastResponse forecastResponse, DBOperationListener dbOperationListener) {
        UpdateDatabase updateDatabase = new UpdateDatabase();
        updateDatabase.setListener(dbOperationListener);
        updateDatabase.execute(forecastResponse);
    }

    public class UpdateDatabase extends AsyncTask<ForecastResponse, Void, Long> {
        WeakReference<DBOperationListener> listener;
        private void setListener(DBOperationListener listener){
            this.listener = new WeakReference<>(listener);
        }
        @Override
        protected Long doInBackground(ForecastResponse...a) {
            return weatherDao.updateData(a[0]);
        }

        @Override
        protected void onPostExecute(Long results) {
            //check if there are data in the db
            if(listener.get()!=null)
                listener.get().onFinishDBOperation(results);
        }
    }
}
