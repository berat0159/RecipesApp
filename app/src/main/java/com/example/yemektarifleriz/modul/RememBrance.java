package com.example.yemektarifleriz.modul;

import android.graphics.Bitmap;

import androidx.room.Entity;

import java.sql.Blob;

public class RememBrance {


    public int id;
    public String name;
    public Bitmap image;

    public RememBrance( int id,String name,Bitmap image) {
        this.id = id;
        this.name=name;
        this.image=image;
    }
}
