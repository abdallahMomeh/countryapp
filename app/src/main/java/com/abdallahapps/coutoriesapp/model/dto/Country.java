package com.abdallahapps.coutoriesapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class Country {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("flag_img")
    private  String flag;







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
