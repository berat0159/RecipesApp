package com.example.yemektarifleriz.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.yemektarifleriz.databinding.ActivityAddBinding;
import com.example.yemektarifleriz.databinding.ActivityUserDetailsBinding;

import java.util.ArrayList;

public class UserDetails extends AppCompatActivity {
    private ActivityUserDetailsBinding binding;
    SQLiteDatabase database;
    int rememId;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Cursor cursor;
        Intent intent=getIntent();
        rememId=intent.getIntExtra("remem",0);
        database=this.openOrCreateDatabase("Remembrances",MODE_PRIVATE,null);
        try {

             cursor=database.rawQuery("SELECT * FROM remembrance WHERE  id=?",new String[]{String.valueOf(rememId)});

                 int mealnameIx=cursor.getColumnIndex("mealname");
                 int cookernameIx=cursor.getColumnIndex("cookername");
                 int dateIx=cursor.getColumnIndex("date");
                 int imageIx=cursor.getColumnIndex("image");

                 while (cursor.moveToNext()){

                     String name=cursor.getString(mealnameIx);
                     String cookername=cursor.getString(cookernameIx);
                     String date=cursor.getString(dateIx);

                     binding.userNameText.setText(name);
                     binding.userCookText.setText(cookername);
                     binding.userDataText.setText(date);

                     byte[] bytes=cursor.getBlob(imageIx);
                     Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                     binding.userImage.setImageBitmap(bitmap);
                 }
                 cursor.close();





        }catch (Exception e){
            e.printStackTrace();
        }




    }


}