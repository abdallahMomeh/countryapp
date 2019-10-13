package com.abdallahapps.countriesapp.ui.home.view;

import android.content.Intent;
import android.os.Bundle;

import com.abdallahapps.countriesapp.R;
import com.abdallahapps.countriesapp.model.dto.ContinentList;
import com.abdallahapps.countriesapp.ui.base.BaseActivity;
import com.abdallahapps.countriesapp.ui.detailsScreen.view.DetailsCountryActivity;
import com.abdallahapps.countriesapp.ui.home.viewModel.HomeVM;
import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeActivity extends BaseActivity {


    HomeVM homeVM2;
    RecyclerView countriesRV;
    ContintentAdapter contintentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        homeVM2 = ViewModelProviders.of(this).get(HomeVM.class);
        homeVM2.contintentIds.observe(this , continentsIds -> {
            homeVM2.initPaging(continentsIds);
            observeContinents();
        });


    }

    void observeContinents(){
        homeVM2.countryList.observe(this,continents -> {
            contintentAdapter.submitList(continents);
        });
    }

    void initViews(){

       countriesRV = findViewById(R.id.countriesRV);
       contintentAdapter = new ContintentAdapter();
       contintentAdapter.setOnCountryClickedListener( country -> {

           startActivity(new Intent(this, DetailsCountryActivity.class)
                   .putExtra("country",new Gson().toJson(country)));

       });

        GridLayoutManager manager = new GridLayoutManager(this,2);
        manager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {

                if (contintentAdapter.getItem(position) instanceof ContinentList){
                    return 2;
                }else {
                    return 1;
                }

            }
        });

        countriesRV.setLayoutManager(manager);
        countriesRV.setAdapter(contintentAdapter);

    }
}

