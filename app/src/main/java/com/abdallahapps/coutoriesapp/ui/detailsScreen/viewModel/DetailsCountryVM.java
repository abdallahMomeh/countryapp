package com.abdallahapps.coutoriesapp.ui.detailsScreen.viewModel;

import com.abdallahapps.coutoriesapp.model.source.network.ApiFactory;
import com.abdallahapps.coutoriesapp.model.source.network.ApiService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailsCountryVM extends ViewModel {

    private int countryId;
    LiveData<String> infoLiveData = new MediatorLiveData<>();
    LiveData<String> flagLiveData = new MediatorLiveData<>();
    LiveData<List<String>> imagesLiveData = new MediatorLiveData<>();

    public LiveData<String> getFlag(){

        ApiService apiService = ApiFactory.create();
        apiService.getFlag(countryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( flagDTO -> {
                    ((MediatorLiveData<String>)flagLiveData).setValue(flagDTO.getCountryFlag().getFlag_img());
                },throwable -> {
                    throwable.printStackTrace();
                });

        return flagLiveData;
    }

    public LiveData<String> getInfo(){

        ApiService apiService = ApiFactory.create();
        apiService.getInfo(countryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(infoDTO -> {
                    ((MediatorLiveData<String>)infoLiveData).setValue(infoDTO.getCountryInfo().getInfo());
                },throwable -> {
                    throwable.printStackTrace();
                });


        return infoLiveData;
    }

    public LiveData<List<String>> getImages(){

        ApiService apiService = ApiFactory.create();
        apiService.getImages(countryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( images -> {
                    ((MediatorLiveData<List<String>>)imagesLiveData)
                            .setValue(images.getCountryImages().getImages());
                },throwable -> {
                    throwable.printStackTrace();
                });

        return imagesLiveData;

    }


    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
