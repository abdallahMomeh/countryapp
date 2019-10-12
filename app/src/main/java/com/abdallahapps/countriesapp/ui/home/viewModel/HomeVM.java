package com.abdallahapps.countriesapp.ui.home.viewModel;

import android.util.Log;

import com.abdallahapps.countriesapp.model.dto.Continent;
import com.abdallahapps.countriesapp.model.source.network.ApiFactory;
import com.abdallahapps.countriesapp.model.source.network.ApiService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeVM extends ViewModel {

    //public LiveData<List<Integer>> countiresIdsLiveData = new MediatorLiveData<>();

    private LiveData<Continent> continentLiveData = new MutableLiveData<>();

    public HomeVM(){
        getContinents();
    }

    public void getContinents(){

        ApiService apiService = ApiFactory.create();
        apiService.getContinents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( contintentIdsDTO  -> {

                    /*((MediatorLiveData<List<Integer>>)countiresIdsLiveData)
                            .setValue(contintentIdsDTO.getContinentsIds());*/
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
                    ((MutableLiveData<Continent>) getContinentLiveData()).setValue(continentDTO.getContinent());
                    Log.d("myTag" , "on success get continent");
                },throwable -> {
                   throwable.printStackTrace();
                });
    }


    public LiveData<Continent> getContinentLiveData() {
        return continentLiveData;
    }

}
