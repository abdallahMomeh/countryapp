package com.abdallahapps.countriesapp.ui.home.view;

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
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ContinentsRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int COUNTRY_ITEM = 1 ;
    public final int CONTINENT_ITEM = 2 ;

    private List<Object> countries = new ArrayList<>();
    private OnCountryClickedListener onCountryClickedListener;


    @Override
    public int getItemViewType(int position) {
        if (getCountries().get(position) instanceof Country){
            return COUNTRY_ITEM;
        }else {
            return CONTINENT_ITEM;
        }
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

        if ( holder instanceof ContinentViewHolder ){
            ((ContinentViewHolder) holder).binding.setContinent(( ContinentList ) getCountries().get(position));
        }else {
            ((CountryViewHolder)holder).binding.setCountry(( (Country ) getCountries().get(position)));
        }

        holder.itemView.setOnClickListener(view -> {
            if (holder instanceof CountryViewHolder){
                onCountryClickedListener.onCountryClicked((Country)countries.get(position));
                /*APP.context.startActivity(new Intent(APP.context, DetailsCountryActivity.class)
                        .putExtra("country",new Gson().toJson(((Country)countries.get(position)))));*/
            }
        });
    }



    @Override
    public int getItemCount() {
        return getCountries().size();
    }

    public void addContinent(Continent continent){

        ContinentList c = new ContinentList();
        c.setId(continent.getId());
        c.setName(continent.getName());
        getCountries().add(c);

        for (int i=0; i<continent.getCountries().size(); i++){
            getCountries().add(continent.getCountries().get(i));
        }
        notifyDataSetChanged();

    }

    public List<Object> getCountries() {
        return countries;
    }

    public void setCountries(List<Object> countries) {
        this.countries = countries;
    }

    public OnCountryClickedListener getOnCountryClickedListener() {
        return onCountryClickedListener;
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
