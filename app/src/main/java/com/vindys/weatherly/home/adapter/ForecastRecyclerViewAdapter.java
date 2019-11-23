package com.vindys.weatherly.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vindys.weatherly.R;
import com.vindys.weatherly.data.local.entity.WeatherResponse;
import com.vindys.weatherly.databinding.ListItemWeatherBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<ForecastRecyclerViewAdapter.WeatherDataViewHolder> {

    private List<WeatherResponse> weatherResponses;

    public void setWeatherResponses(List<WeatherResponse> weatherResponses) {
        this.weatherResponses = weatherResponses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                    int viewType) {
        ListItemWeatherBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_weather,
                        parent, false);

        return new WeatherDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherDataViewHolder holder, int position) {
        holder.binding.executePendingBindings();

        WeatherResponse forecastDay = weatherResponses.get(position);
        holder.binding.setMainModel(forecastDay);
        holder.itemView.setTag(forecastDay);
    }



    @Override
    public int getItemCount() {
        if (this.weatherResponses == null)
            this.weatherResponses = new ArrayList<>();
        return weatherResponses.size();
    }

    class WeatherDataViewHolder extends RecyclerView.ViewHolder {
        final ListItemWeatherBinding binding;

        WeatherDataViewHolder(ListItemWeatherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
