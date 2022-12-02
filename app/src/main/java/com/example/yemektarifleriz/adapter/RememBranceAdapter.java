package com.example.yemektarifleriz.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemektarifleriz.view.UserDetails;
import com.example.yemektarifleriz.databinding.RememrecyclerrowBinding;
import com.example.yemektarifleriz.modul.RememBrance;

import java.util.ArrayList;

public class RememBranceAdapter extends RecyclerView.Adapter<RememBranceAdapter.RememBranceHolder> {
    ArrayList<RememBrance> arrayList;
    public RememBranceAdapter(ArrayList<RememBrance> arrayList){
        this.arrayList=arrayList;

    }
    @NonNull
    @Override
    public RememBranceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RememrecyclerrowBinding rememrecyclerrowBinding=RememrecyclerrowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RememBranceHolder(rememrecyclerrowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RememBranceHolder holder, int position) {
        holder.binding.text.setText(arrayList.get(position).name);
        holder.binding.RecyclerViewImage.setImageBitmap(arrayList.get(position).image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), UserDetails.class);
                intent.putExtra("remem",arrayList.get(holder.getAdapterPosition()).id);
                holder.itemView.getContext().startActivity(intent);

            }
        });
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RememBranceHolder extends RecyclerView.ViewHolder{
        private RememrecyclerrowBinding binding;
        public RememBranceHolder(RememrecyclerrowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }

    }
}
