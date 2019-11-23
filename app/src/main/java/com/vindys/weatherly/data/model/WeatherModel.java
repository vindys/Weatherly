package com.vindys.weatherly.data.model;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

public class WeatherModel {

    private String id;

    private String main;

    private String description;

    private String icon;

    // code for loading image here
    @BindingAdapter({ "avatar" })
    public static void loadImage(AppCompatImageView imageView, String iconID) {
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load("http://openweathermap.org/img/wn/"+ iconID+"@2x.png")
                .into(imageView);
    }

    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
