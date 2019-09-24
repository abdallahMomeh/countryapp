package com.abdallahapps.countriesapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class FlagDTO {

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("country_flag")
    private CountryFlag countryFlag ;

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

    public CountryFlag getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(CountryFlag countryFlag) {
        this.countryFlag = countryFlag;
    }



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
}



