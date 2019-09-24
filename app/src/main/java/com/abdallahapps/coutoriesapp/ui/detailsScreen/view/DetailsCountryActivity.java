package com.abdallahapps.coutoriesapp.ui.detailsScreen.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdallahapps.coutoriesapp.APP;
import com.abdallahapps.coutoriesapp.R;
import com.abdallahapps.coutoriesapp.model.dto.Country;
import com.abdallahapps.coutoriesapp.ui.base.BaseActivity;
import com.abdallahapps.coutoriesapp.ui.detailsScreen.viewModel.DetailsCountryVM;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailsCountryActivity extends BaseActivity {

    DetailsCountryVM detailsCountryVM;
    Country country;
    TextView countryInfoTV;
    ImageView flag;
    RecyclerView imagesRV;
    Toolbar toolbar;
    ImagesRVAdapter imagesRVAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_country);
        APP.context = this;
        initViews();
        detailsCountryVM = ViewModelProviders.of(this).get(DetailsCountryVM.class);

        if (getIntent().getExtras()!=null){

            if (getIntent().getExtras().containsKey("country")){
                String json = getIntent().getExtras().getString("country");
                country = new Gson().fromJson(json,Country.class);

                getSupportActionBar().setTitle(country.getName());
                detailsCountryVM.setCountryId(country.getId());
            }
        }

        detailsCountryVM.getAllData();
        detailsCountryVM.flagLiveData.observe(this , flagUrl -> {
            Glide.with(this).load(flagUrl).placeholder(R.drawable.round_editable_bk).into(flag);
        });

        detailsCountryVM.infoLiveData.observe(this , info -> {
            countryInfoTV.setText(info);
        });

        detailsCountryVM.imagesLiveData.observe(this, imagesUrl -> {
            imagesRVAdapter.setImageUrl(imagesUrl);
        });

    }

    void  initViews(){
        countryInfoTV = findViewById(R.id.infoDetailsTV);
        flag = findViewById(R.id.countryFlag);
        imagesRV = findViewById(R.id.imagesRV);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imagesRVAdapter = new ImagesRVAdapter();
        imagesRV.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        imagesRV.setAdapter(imagesRVAdapter);
    }



}
