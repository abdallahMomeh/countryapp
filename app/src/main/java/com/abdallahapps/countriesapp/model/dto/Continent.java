package com.abdallahapps.countriesapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Continent {

    @SerializedName("id")
    private int id;

    @SerializedName("continent_name")
    private String name;

    @SerializedName("countries")
    private List<Country> countries;

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

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
