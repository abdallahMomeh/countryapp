package com.abdallahapps.countriesapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContintentIdsDTO {

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("continents_ids")
    private List<Integer> continentsIds;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getContinentsIds() {
        return continentsIds;
    }

    public void setContinentsIds(List<Integer> continentsIds) {
        this.continentsIds = continentsIds;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
