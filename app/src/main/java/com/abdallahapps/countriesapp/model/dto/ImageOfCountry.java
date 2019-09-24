package com.abdallahapps.countriesapp.model.dto;

import android.widget.ImageView;

import com.abdallahapps.countriesapp.R;
import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

public class ImageOfCountry {

    private String imageUrl;

    public ImageOfCountry (String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view , String url){
        Glide.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view);

    }

}
