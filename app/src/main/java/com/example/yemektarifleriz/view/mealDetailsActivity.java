package com.example.yemektarifleriz.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.yemektarifleriz.databinding.ActivityMealDetailsBinding;
import com.example.yemektarifleriz.modul.MealsData;

public class mealDetailsActivity extends AppCompatActivity {
    private ActivityMealDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent=getIntent();

        MealsData mealsData=(MealsData) intent.getSerializableExtra("meal");
        binding.mealsImage.setImageResource(mealsData.rvimage);
        binding.mealImageText1.setImageResource(mealsData.rvTextImage);

    }
}