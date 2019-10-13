package com.abdallahapps.countriesapp.ui.home.viewModel;

import android.app.Application;
import android.util.Log;

import com.abdallahapps.countriesapp.model.dto.Continent;
import com.abdallahapps.countriesapp.model.source.network.ApiFactory;
import com.abdallahapps.countriesapp.model.source.network.ApiService;
import com.abdallahapps.countriesapp.util.ContinentDatasourceFactrory;
import com.abdallahapps.countriesapp.util.ContintentDataSource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeVM extends ViewModel {


    public LiveData<List<Integer>> contintentIds = new MutableLiveData<>();

    //PagedList controls data loading using data source
    public LiveData<PagedList<Object>> countryList ;

    public HomeVM(){
       getContinents();

    }


    public void getContinents(){

        ApiService apiService = ApiFactory.create();
        apiService.getContinents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( contintentIdsDTO  -> {

                    ((MutableLiveData)contintentIds).setValue(contintentIdsDTO.getContinentsIds());

                },throwable -> {
                    throwable.printStackTrace();

                });
    }

    public void initPaging( List<Integer> continentsIds ){

        //instantiate CouponsDataSourceFactory
        ContinentDatasourceFactrory factory = new ContinentDatasourceFactrory();


        //create PagedList Config
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                //.setInitialLoadSizeHint(1)
                .setPageSize(continentsIds.size()).build();

        //create LiveData object using LivePagedListBuilder which takes
        //data source factory and page config as params
        countryList = new LivePagedListBuilder<>(factory, config).build();
    }

   /* //factory for creating view model,
    // required because we need to pass Application to view model object
    public static class CouponViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private Application mApplication;
        public CouponViewModelFactory(Application application) {
            mApplication = application;
        }
        @Override
        public <T extends ViewModel> T create(Class<T> viewModel) {
            return (T) new HomeVM(mApplication);
        }
    }*/


}
