package com.example.yemektarifleriz.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.yemektarifleriz.R;
import com.example.yemektarifleriz.adapter.MealAdapter;
import com.example.yemektarifleriz.databinding.ActivityMainBinding;
import com.example.yemektarifleriz.modul.MealsData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<MealsData> mealsDataArrayList;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        MealsData pizza=new MealsData(R.drawable.pizza,R.drawable.pizza1,"Evde Pizza");
        MealsData kadinbudu=new MealsData(R.drawable.kadinbudu,R.drawable.kadinbudu1,"Kadın Budu");
        MealsData pogaca=new MealsData(R.drawable.pogaca,R.drawable.pogaca1,"Pastane Poğaçası");
        MealsData sultankebabi=new MealsData(R.drawable.sultankebabi,R.drawable.sultankebabi1,"Tavuklu Sultan Kebabı");
        MealsData tavuksote=new MealsData(R.drawable.tavuksote,R.drawable.tavuksote1,"Tavuk Sote");
        MealsData sutlac=new MealsData(R.drawable.sutlac,R.drawable.sutlac1,"Fırında Sütlaç");
        MealsData islakkek=new MealsData(R.drawable.islakkek,R.drawable.islakek1,"İslak Kek");
        MealsData profiterol=new MealsData(R.drawable.profiterol,R.drawable.profiterol1,"Profiterol");
        MealsData elmali=new MealsData(R.drawable.elmalikurabiye,R.drawable.elmalikurabiye1,"Elmalı Kurabiye");
        MealsData brownie=new MealsData(R.drawable.brownie,R.drawable.browniekurabiye1,"Brownie Kurabiye");


        mealsDataArrayList=new ArrayList<>();

        mealsDataArrayList.add(brownie);
        mealsDataArrayList.add(sutlac);
        mealsDataArrayList.add(elmali);
        mealsDataArrayList.add(islakkek);
        mealsDataArrayList.add(profiterol);
        mealsDataArrayList.add(sultankebabi);
        mealsDataArrayList.add(tavuksote);
        mealsDataArrayList.add(pizza);
        mealsDataArrayList.add(kadinbudu);
        mealsDataArrayList.add(pogaca);

        binding.YemekRecyclerView.setHasFixedSize(true);


        binding.YemekRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        MealAdapter mealAdapter=new MealAdapter(mealsDataArrayList);

        binding.YemekRecyclerView.setAdapter(mealAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menumeal,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.anıekle){
            Intent intent=new Intent(this, AddActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.anılarım){
            Intent intent=new Intent(this, MainActivityRecyclerView.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


}