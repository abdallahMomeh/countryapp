package com.abdallahapps.countriesapp.ui.detailsScreen.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.abdallahapps.countriesapp.R;
import com.abdallahapps.countriesapp.databinding.ImageCountryItemBinding;
import com.abdallahapps.countriesapp.model.dto.ImageOfCountry;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ImagesRVAdapter extends RecyclerView.Adapter<ImagesRVAdapter.ViewHolder> {

    List<String> imageUrl = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageCountryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                ,R.layout.image_country_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setImage(new ImageOfCountry(imageUrl.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageUrl.size();
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageCountryItemBinding binding;
        ImageView imageView;
        public ViewHolder(ImageCountryItemBinding imageCountryItemBinding) {
            super(imageCountryItemBinding.getRoot());
            this.binding = imageCountryItemBinding;
            imageView = itemView.findViewById(R.id.imageView);
        }

    }
}
