package com.abdallahapps.coutoriesapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class CountryFlag {

    @SerializedName("flag_img")
    private String flag_img;

    public String getFlag_img() {
        return flag_img;
    }

    public void setFlag_img(String flag_img) {
        this.flag_img = flag_img;
    }
}
