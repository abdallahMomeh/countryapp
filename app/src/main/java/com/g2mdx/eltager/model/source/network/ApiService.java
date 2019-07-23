package com.g2mdx.eltager.model.source.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {


    @POST("test2.php")
    @FormUrlEncoded
    Observable<ResponseBody>  login (@Field("name") String name , @Field("email") String email);


}
