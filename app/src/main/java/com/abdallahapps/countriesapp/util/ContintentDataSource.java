package com.abdallahapps.countriesapp.util;

import android.content.Context;
import android.util.Log;

import com.abdallahapps.countriesapp.model.dto.Continent;
import com.abdallahapps.countriesapp.model.dto.ContinentDTO;
import com.abdallahapps.countriesapp.model.dto.ContinentList;
import com.abdallahapps.countriesapp.model.dto.Country;
import com.abdallahapps.countriesapp.model.source.network.ApiFactory;
import com.abdallahapps.countriesapp.model.source.network.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContintentDataSource extends PageKeyedDataSource<Integer , Object> {

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params
            , @NonNull LoadInitialCallback<Integer, Object> callback) {

         ApiFactory.create().getContinentById(1)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe((continentDTO) -> {
                     callback.onResult( prepareObjectsList(continentDTO) ,null,2);
                 },throwable -> {
                     throwable.printStackTrace();
                 });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params
            , @NonNull LoadCallback<Integer, Object> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params
            , @NonNull LoadCallback<Integer, Object> callback) {

        if (params.key <= params.requestedLoadSize) {

            ApiFactory.create().getContinentById(params.key)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((continentDTO) -> {
                        callback.onResult( prepareObjectsList(continentDTO), (params.key + 1));
                    }, throwable -> {
                        throwable.printStackTrace();
                    });

        }

    }

    List<Object> prepareObjectsList(ContinentDTO continentDTO ){

        List<Object> objects = new ArrayList<>();
        ContinentList c = new ContinentList();
        c.setId(continentDTO.getContinent().getId());
        c.setName(continentDTO.getContinent().getName());
        objects.add(c);
        for (int i = 0; i < continentDTO.getContinent().getCountries().size(); i++) {
            objects.add(continentDTO.getContinent().getCountries().get(i));
        }

        return objects;
    }

}
