package com.abdallahapps.countriesapp.util;

import android.content.Context;
import android.util.Log;

import com.abdallahapps.countriesapp.model.dto.Continent;
import com.abdallahapps.countriesapp.model.dto.Country;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class ContinentDatasourceFactrory extends DataSource.Factory<Integer, Object> {

    private ContintentDataSource contintentDataSource;

    @Override
    public DataSource<Integer, Object> create() {
        Log.d("myTag","create data source");
        if(contintentDataSource == null){
            contintentDataSource = new ContintentDataSource();
        }
        return contintentDataSource;
    }

}
