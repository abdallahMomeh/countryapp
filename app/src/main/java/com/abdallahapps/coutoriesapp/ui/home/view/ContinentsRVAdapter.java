package com.abdallahapps.coutoriesapp.ui.home.view;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdallahapps.coutoriesapp.APP;
import com.abdallahapps.coutoriesapp.R;
import com.abdallahapps.coutoriesapp.model.dto.Continent;
import com.abdallahapps.coutoriesapp.model.dto.ContinentList;
import com.abdallahapps.coutoriesapp.model.dto.Country;
import com.abdallahapps.coutoriesapp.ui.detailsScreen.view.DetailsCountryActivity;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContinentsRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int COUNTRY_ITEM = 1 ;
    public final int CONTINENT_ITEM = 2 ;

    private List<Object> countries = new ArrayList<>();

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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
            return new CountryViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contintent_item, parent, false);
            return new ContinentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if ( holder instanceof ContinentViewHolder ){

            ((ContinentViewHolder)holder).continentTV.setText(( ( ContinentList ) getCountries().get(position)).getName());

        }else {
            ((CountryViewHolder)holder).countryName.setText(( (Country ) getCountries().get(position)).getName());
            Glide.with(APP.context.getApplicationContext())
                    .load( ((Country) getCountries().get(position)).getFlag())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(((CountryViewHolder)holder).flag);
        }

        holder.itemView.setOnClickListener(view -> {
            if (holder instanceof CountryViewHolder){
                APP.context.startActivity(new Intent(APP.context, DetailsCountryActivity.class)
                        .putExtra("country",new Gson().toJson(((Country)countries.get(position)))));
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

    class  CountryViewHolder extends  RecyclerView.ViewHolder{

        ImageView flag;
        TextView countryName;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.flagIV);
            countryName = itemView.findViewById(R.id.countryTV);

        }
    }

    class  ContinentViewHolder extends  RecyclerView.ViewHolder{

        TextView continentTV;
        public ContinentViewHolder(@NonNull View itemView) {
            super(itemView);
            continentTV = itemView.findViewById(R.id.continentTV);
        }
    }

}
