package com.abdallahapps.countriesapp.ui.detailsScreen.viewModel;

import android.util.Log;

import com.abdallahapps.countriesapp.model.dto.Country;
import com.abdallahapps.countriesapp.model.dto.FlagDTO;
import com.abdallahapps.countriesapp.model.dto.ImagesDTO;
import com.abdallahapps.countriesapp.model.dto.InfoDTO;
import com.abdallahapps.countriesapp.model.source.network.ApiFactory;
import com.abdallahapps.countriesapp.model.source.network.ApiService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class DetailsCountryVM extends ViewModel {

    private int countryId;
    private LiveData<Country> countryLiveData  = new MutableLiveData<>();

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

                ((MutableLiveData<Country>) getCountryLiveData()).setValue(((Country)o));

            }
        });
    }



    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public LiveData<Country> getCountryLiveData() {
        return countryLiveData;
    }


}
