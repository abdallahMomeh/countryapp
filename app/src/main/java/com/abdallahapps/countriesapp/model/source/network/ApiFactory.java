package com.abdallahapps.countriesapp.model.source.network;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


import okhttp3.OkHttpClient;

public class ApiFactory {

    public static String BASE_URL = "http://167.99.34.17/api/";

    private static ApiService apiService;

    public static ApiService create() {

        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(get_HTTPClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            apiService = retrofit.create(ApiService.class);

        }
        return apiService;
    }


    private static OkHttpClient get_HTTPClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        //httpClient.addInterceptor(logging);  // <-- this is the important line!
        httpClient.interceptors().add(logging);
        return httpClient.build();
    }


}
