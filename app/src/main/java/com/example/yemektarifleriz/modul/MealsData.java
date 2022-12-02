package com.example.yemektarifleriz.modul;

import java.io.Serializable;

public class MealsData implements Serializable {

    public int rvimage;
    public int rvTextImage;
    public String rvname;
    public MealsData(int rvimage,int rvTextImage,String rvname){
        this.rvimage=rvimage;
        this.rvTextImage=rvTextImage;
        this.rvname=rvname;
    }



}
