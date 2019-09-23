package com.abdallahapps.coutoriesapp.ui.home.view;

import android.os.Bundle;
import android.util.Log;

import com.abdallahapps.coutoriesapp.APP;
import com.abdallahapps.coutoriesapp.R;
import com.abdallahapps.coutoriesapp.model.dto.Continent;
import com.abdallahapps.coutoriesapp.model.dto.ContinentList;
import com.abdallahapps.coutoriesapp.ui.base.BaseActivity;
import com.abdallahapps.coutoriesapp.ui.home.viewModel.HomeVM;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeActivity extends BaseActivity {

    HomeVM homeVM;
    RecyclerView countriesRV;
    ContinentsRVAdapter  continentsRVAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        APP.context = this;
        initViews();
        homeVM = ViewModelProviders.of(this).get(HomeVM.class);

        homeVM.continentLiveData.observe(this , continent ->{
            continentsRVAdapter.addContinent(continent);
            Log.d("myTag","get continentLiveData");
        } );

    }


    void initViews(){

        countriesRV = findViewById(R.id.countriesRV);
        continentsRVAdapter = new ContinentsRVAdapter();
        GridLayoutManager manager = new GridLayoutManager(this,2);
        manager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {

                if (continentsRVAdapter.getCountries().get(position) instanceof ContinentList){
                    return 2;
                }else {
                    return 1;
                }

            }
        });

        countriesRV.setLayoutManager(manager);
        countriesRV.setAdapter(continentsRVAdapter);

    }
}

