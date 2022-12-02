package com.example.yemektarifleriz.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.yemektarifleriz.adapter.RememBranceAdapter;
import com.example.yemektarifleriz.databinding.ActivityMainRecyclerViewBinding;
import com.example.yemektarifleriz.modul.RememBrance;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class MainActivityRecyclerView extends AppCompatActivity {
    private ActivityMainRecyclerViewBinding binding;
    ArrayList<RememBrance> arrayList;
    RememBranceAdapter rememBranceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainRecyclerViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        arrayList=new ArrayList<>();

        binding.AniRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        rememBranceAdapter=new RememBranceAdapter(arrayList);
        binding.AniRecyclerView.setAdapter(rememBranceAdapter);
        getData();


    }


    private void getData(){
        try {

            SQLiteDatabase database=this.openOrCreateDatabase("Remembrances",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery("SELECT * FROM remembrance",null);
            int idIx=cursor.getColumnIndex("id");
            int nameIx=cursor.getColumnIndex("mealname");
            int imageIx=cursor.getColumnIndex("image");

            while (cursor.moveToNext()){

                int id=cursor.getInt(idIx);
                String name=cursor.getString(nameIx);
                byte[] image=cursor.getBlob(imageIx);
                ByteArrayInputStream imageInput=new ByteArrayInputStream(image);
                Bitmap theImage= BitmapFactory.decodeStream(imageInput);
                RememBrance rememBrance=new RememBrance(id,name,theImage);
                arrayList.add(rememBrance);
            }
            rememBranceAdapter.notifyDataSetChanged();
            cursor.close();


        }catch (Exception e){
                e.printStackTrace();
        }


    }
}