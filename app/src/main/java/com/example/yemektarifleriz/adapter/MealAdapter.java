package com.example.yemektarifleriz.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemektarifleriz.databinding.MainreyclerrowBinding;
import com.example.yemektarifleriz.modul.MealsData;
import com.example.yemektarifleriz.view.mealDetailsActivity;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealHolder> {

    private ArrayList<MealsData> mealsDataArrayList;
    public MealAdapter(ArrayList<MealsData> mealsDataArrayList){
        this.mealsDataArrayList=mealsDataArrayList;
    }

    @NonNull
    @Override
    public MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainreyclerrowBinding mainreyclerrowBinding=MainreyclerrowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MealHolder(mainreyclerrowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MealHolder holder, int position) {
        holder.binding.mainRecyclerImageView.setImageResource(mealsDataArrayList.get(position).rvimage);
        holder.binding.mainRecyclerViewName.setText(mealsDataArrayList.get(position).rvname);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), mealDetailsActivity.class);
                intent.putExtra("meal",mealsDataArrayList.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mealsDataArrayList.size();
    }

    public class MealHolder extends RecyclerView.ViewHolder{

        private MainreyclerrowBinding binding;
        public MealHolder(MainreyclerrowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
