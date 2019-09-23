package com.abdallahapps.coutoriesapp.model.source.network;

import com.abdallahapps.coutoriesapp.model.source.preferences.SharedManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    public static String BASE_URL = "http://167.99.34.17/api/";

    public static ApiService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(get_HTTPClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create( ApiService.class);
    }



    private static OkHttpClient get_HTTPClient() {
        final Map<String, String> headers = new HashMap<>();

       /* String token = "";

        try {
            token = SharedManager.newInstance().getCashValue(Constants.Token);

        } catch (Exception e) {
        }
*/
        /*if (!TextUtils.isEmpty(token) && token != null) {
            String t = "Bearer "+token;
            t = t.replace("\"", "");             /// remove quots

            headers.put("Authorization", t  );
            // headers.put(Constants.NotificationMessage, NotificationToken);
        }*/
       /* String lang = SharedManager.newInstance().getCashValue(Constants.LANG);
        if (lang == null || TextUtils.isEmpty(lang) || lang.equalsIgnoreCase(Constants.ENGLISH)) {
            headers.put("Accept-Language", "en");
        } else {
            headers.put("Accept-Language", "ar");
        }*/
        headers.put("Accept", "application/json");

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(14L, TimeUnit.SECONDS)
                .writeTimeout(14L, TimeUnit.SECONDS)
                .readTimeout(14L, TimeUnit.SECONDS);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers

                Request.Builder requestBuilder = original.newBuilder(); // <-- this is the important line



              /*  for (Map.Entry<String, String> pairs : headers.entrySet()) {
                    if (pairs.getValue() != null) {
                        requestBuilder.header(pairs.getKey(), pairs.getValue());
                    }
                }*/

                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();

                Response response = chain.proceed(request);;
                if (response.code() == 307) {
                    request = request.newBuilder()
                            .url(response.header("Location"))
                            .build();
                    response = chain.proceed(request);

                }

                return response;

            }
        };

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.interceptors().add(interceptor);
        httpClient.interceptors().add(logging);
        //httpClient.interceptors().add(new RedirectInterceptor());
        // httpClient.setFollowRedirects(false);
        return httpClient.build();
    }


    private static class RedirectInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(chain.request());
            if (response.code() == 307) {
                request = request.newBuilder()
                        .url(response.header("Location"))
                        .build();
                response = chain.proceed(request);

            }
            return response;
        }
    }


}
