package com.abdallahapps.coutoriesapp.ui.detailsScreen.viewModel;

import android.util.Log;

import com.abdallahapps.coutoriesapp.model.dto.Country;
import com.abdallahapps.coutoriesapp.model.dto.FlagDTO;
import com.abdallahapps.coutoriesapp.model.dto.ImagesDTO;
import com.abdallahapps.coutoriesapp.model.dto.InfoDTO;
import com.abdallahapps.coutoriesapp.model.source.network.ApiFactory;
import com.abdallahapps.coutoriesapp.model.source.network.ApiService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class DetailsCountryVM extends ViewModel {

    private int countryId;
    public LiveData<String> infoLiveData = new MediatorLiveData<>();
    public LiveData<String> flagLiveData = new MediatorLiveData<>();
    public LiveData<List<String>> imagesLiveData = new MediatorLiveData<>();

    public void getAllData(){

        ApiService apiService = ApiFactory.create();

        Observable.zip(apiService.getFlag(countryId), apiService.getInfo(countryId), apiService.getImages(countryId), new Function3<FlagDTO, InfoDTO, ImagesDTO, Object>() {

            @Override
            public Object apply(FlagDTO flagDTO, InfoDTO infoDTO, ImagesDTO imagesDTO) throws Exception {

                Country country = new Country();
                country.setFlag(flagDTO.getCountryFlag().getFlag_img());
                country.setInfo(infoDTO.getCountryInfo().getInfo());
                country.setImages(imagesDTO.getCountryImages().getImages());

                return country;
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {

            @Override
            public void accept(Object o) throws Exception {
                Log.d("myTag","accept");
                ((MediatorLiveData<String>)flagLiveData).setValue(((Country)o).getFlag());
                ((MediatorLiveData<String>)infoLiveData).setValue(((Country)o).getInfo());
                ((MediatorLiveData<List<String>>)imagesLiveData)
                        .setValue(((Country)o).getImages());
            }
        });
    }
    /*public LiveData<String> getFlag(){

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

*/
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
