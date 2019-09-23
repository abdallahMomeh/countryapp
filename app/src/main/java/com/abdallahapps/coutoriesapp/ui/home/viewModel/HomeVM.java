package com.abdallahapps.coutoriesapp.ui.home.viewModel;

import android.util.Log;

import com.abdallahapps.coutoriesapp.model.dto.Continent;
import com.abdallahapps.coutoriesapp.model.source.network.ApiFactory;
import com.abdallahapps.coutoriesapp.model.source.network.ApiService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeVM extends ViewModel {

    public LiveData<List<Integer>> countiresIdsLiveData = new MediatorLiveData<>();

    public LiveData<Continent> continentLiveData = new MediatorLiveData<>();

    public HomeVM(){
        getContinents();
    }

    public void getContinents(){

        ApiService apiService = ApiFactory.create();
        apiService.getContinents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( contintentIdsDTO  -> {

                    ((MediatorLiveData<List<Integer>>)countiresIdsLiveData)
                            .setValue(contintentIdsDTO.getContinentsIds());
                    Log.d("myTag","on sucess get continents "+contintentIdsDTO.getContinentsIds().size());

                    for (int i=0 ; i<contintentIdsDTO.getContinentsIds().size(); i++){
                        getContinent(contintentIdsDTO.getContinentsIds().get(i));
                    }

                },throwable -> {
                    throwable.printStackTrace();

                });
    }



    void getContinent(int continentId){

        ApiService apiService = ApiFactory.create();
        apiService.getContinentById(continentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(continentDTO -> {
                    ((MediatorLiveData<Continent>)continentLiveData).setValue(continentDTO.getContinent());
                    Log.d("myTag" , "on success get continent");
                },throwable -> {
                   throwable.printStackTrace();
                });
    }


}
