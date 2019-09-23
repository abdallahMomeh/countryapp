package com.abdallahapps.coutoriesapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class CountryInfo {

    @SerializedName("info")
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
