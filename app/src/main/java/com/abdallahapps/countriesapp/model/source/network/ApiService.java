package com.abdallahapps.countriesapp.model.source.network;

import com.abdallahapps.countriesapp.model.dto.ContinentDTO;
import com.abdallahapps.countriesapp.model.dto.ContintentIdsDTO;
import com.abdallahapps.countriesapp.model.dto.FlagDTO;
import com.abdallahapps.countriesapp.model.dto.ImagesDTO;
import com.abdallahapps.countriesapp.model.dto.InfoDTO;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @POST("test2.php")
    @FormUrlEncoded
    Observable<ResponseBody>  login (@Field("name") String name , @Field("email") String email);

    @GET("get_continents_ids")
    Observable<ContintentIdsDTO> getContinents();

    @GET("get_continent_byId")
    Observable<ContinentDTO> getContinentById(@Query("continent_id")int continentId);


    @GET("get_flag")
    Observable<FlagDTO> getFlag(@Query("country_id") int country_id);

    @GET("get_info")
    Observable<InfoDTO> getInfo(@Query("country_id") int country_id);


    @GET("get_images")
    Observable<ImagesDTO> getImages(@Query("country_id") int country_id);


}
