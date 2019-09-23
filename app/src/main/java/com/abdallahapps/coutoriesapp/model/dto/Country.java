package com.abdallahapps.coutoriesapp.model.dto;

import android.widget.ImageView;

import com.abdallahapps.coutoriesapp.APP;
import com.abdallahapps.coutoriesapp.R;
import com.abdallahapps.coutoriesapp.ui.home.view.ContinentsRVAdapter;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import androidx.databinding.BindingAdapter;

public class Country {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("flag_img")
    private  String flag;



    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view , String imageUrl){
        Glide.with(view.getContext())
                .load( imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
