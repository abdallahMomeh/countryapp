package com.abdallahapps.countriesapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesDTO {

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("country_images")
    private CountryImages countryImages ;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CountryImages getCountryImages() {
        return countryImages;
    }

    public void setCountryImages(CountryImages countryImages) {
        this.countryImages = countryImages;
    }


    public class CountryImages {

        @SerializedName("images")
        private List<String> images;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }


}
