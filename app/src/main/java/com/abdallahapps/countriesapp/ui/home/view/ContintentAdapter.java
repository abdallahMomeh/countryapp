package com.abdallahapps.countriesapp.ui.home.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdallahapps.countriesapp.R;
import com.abdallahapps.countriesapp.databinding.ContintentItemBinding;
import com.abdallahapps.countriesapp.databinding.CountryItemBinding;
import com.abdallahapps.countriesapp.model.dto.Continent;
import com.abdallahapps.countriesapp.model.dto.ContinentList;
import com.abdallahapps.countriesapp.model.dto.Country;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


public class ContintentAdapter extends PagedListAdapter<Object, RecyclerView.ViewHolder> {

    public final int COUNTRY_ITEM = 1 ;
    public final int CONTINENT_ITEM = 2 ;

    private OnCountryClickedListener onCountryClickedListener;

    protected ContintentAdapter() {
        super(DIFF_CALLBACK);
    }



    //DiffUtil is used to find out whether two object in the list are same or not
    public static DiffUtil.ItemCallback<Object> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Object>() {
                @Override
                public boolean areItemsTheSame(@NonNull Object oldCoupon,
                                               @NonNull Object newCoupon) {
                    Log.d("myTag", "areItemsTheSame");
                    if (oldCoupon instanceof Country && newCoupon instanceof ContinentList)
                        return false;
                    else if (oldCoupon instanceof ContinentList && newCoupon instanceof Country)
                        return false;
                    else
                        return ((Country)oldCoupon).getId() == ((Country)newCoupon).getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Object oldCoupon,
                                                  @NonNull Object newCoupon) {
                    Log.d("myTag", "areContentsTheSame");
                    return  false; //oldCoupon.equals(newCoupon);
                }
            };


    @Override
    public int getItemViewType(int position) {
        Log.d("myTag", "getItemViewType");
        if (getItem(position) instanceof ContinentList){
            return CONTINENT_ITEM;
        }else {
            return COUNTRY_ITEM;
        }

    }


    @Nullable
    @Override
    protected Object getItem(int position) {
        return super.getItem(position);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == COUNTRY_ITEM ) {
            CountryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.country_item, parent, false);
            return new CountryViewHolder(binding);
        } else {
            ContintentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.contintent_item, parent, false);
            return new ContinentViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Log.d("myTag", "onBind");

        if ( holder instanceof ContinentViewHolder){
            ((ContinentViewHolder) holder).binding.setContinent((ContinentList) getItem(position));
        }else {
            ((CountryViewHolder)holder).binding.setCountry((Country) getItem(position));
        }

        holder.itemView.setOnClickListener(view -> {
            if (holder instanceof CountryViewHolder){

                onCountryClickedListener.onCountryClicked((Country)getItem(position));

            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void setOnCountryClickedListener(OnCountryClickedListener onCountryClickedListener) {
        this.onCountryClickedListener = onCountryClickedListener;
    }


    class  CountryViewHolder extends  RecyclerView.ViewHolder{

        CountryItemBinding binding;

        public CountryViewHolder(CountryItemBinding countryItemBinding) {
            super(countryItemBinding.getRoot());
            this.binding = countryItemBinding;
        }
    }

    class  ContinentViewHolder extends  RecyclerView.ViewHolder{

        ContintentItemBinding binding;

        public ContinentViewHolder(ContintentItemBinding contintentItemBinding) {
            super(contintentItemBinding.getRoot());
            this.binding = contintentItemBinding;
        }
    }

    interface OnCountryClickedListener{
        public void onCountryClicked(Country country);
    }
}
