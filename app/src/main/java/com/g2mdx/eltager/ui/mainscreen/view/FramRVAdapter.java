package com.g2mdx.eltager.ui.mainscreen.view;

import android.view.View;
import android.view.ViewGroup;

import com.g2mdx.eltager.model.dto.Farm;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FramRVAdapter extends RecyclerView.Adapter<FramRVAdapter.ViewHolder> {

    private List<Farm> farms = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return getFarms().size();
    }

    public void addFarms(List<Farm> farmList){
        getFarms().addAll(farmList);
        notifyDataSetChanged();
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
