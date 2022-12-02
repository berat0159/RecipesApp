package com.example.yemektarifleriz.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Database;
import androidx.room.Room;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;


import com.example.yemektarifleriz.databinding.ActivityAddBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;
    SQLiteDatabase database;



    int ay;
    int yil;
    int gun;

    DatePickerDialog datePickerDialog;
    ActivityResultLauncher<Intent> resultLauncher;
    ActivityResultLauncher<String> permissionLauncher;
    Bitmap selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        database=this.openOrCreateDatabase("Remembrances",MODE_PRIVATE,null);
        binding.addDataText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Calendar calendar=Calendar.getInstance();
                    gun=calendar.get(Calendar.DAY_OF_MONTH);
                    yil=calendar.get(Calendar.YEAR);
                    ay=calendar.get(Calendar.MONTH);
                    datePickerDialog=new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            binding.addDataText.setText(i2+"/"+(i1+1)+"/"+i);
                        }
                    },yil,ay,gun);
                    datePickerDialog.show();

                }
            }
        });



        registerLauncher();
    }

    public void selectImage(View view ){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view,"Fotoğraf Yüklüyebilmeniz İçin İzin Vermeniz Gerekiyor", BaseTransientBottomBar.LENGTH_INDEFINITE).setAction("İzin Ver", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //request permission
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                });


            }else {
                //request permission
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }


        }else {
            //gallery
            Intent intentToGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            resultLauncher.launch(intentToGallery);

        }

    }

    public void save(View view){
        String name=binding.yournameText.getText().toString();
        String cookerName=binding.cookernameText.getText().toString();
        String date=binding.addDataText.getText().toString();




        Bitmap smallImage=makeSmallerImage(selectedImage,300);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] bytes=outputStream.toByteArray();

        try {

            database.execSQL("CREATE TABLE IF NOT EXISTS remembrance(id INTEGER PRIMARY KEY , mealname VARCHAR,cookername VARCHAR,date VARCHAR,image BLOB)");

            String sqlString="INSERT INTO remembrance (mealname,cookername,date,image) VALUES (? ,? ,? ,?)";
            SQLiteStatement sqLiteStatement=database.compileStatement(sqlString);
            sqLiteStatement.bindString(1,name);
            sqLiteStatement.bindString(2,cookerName);
            sqLiteStatement.bindString(3,date);
            sqLiteStatement.bindBlob(4,bytes);
            sqLiteStatement.execute();
        }catch (Exception e){
            e.printStackTrace();

        }
        Intent intent=new Intent(AddActivity.this, MainActivityRecyclerView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);





    }

    public Bitmap makeSmallerImage(Bitmap image,int maximumSize){
        int width=image.getWidth();
        int height=image.getHeight();

        float bitmapRatio=(float) (width/height);

        if(bitmapRatio>1){
            width=maximumSize;
            height=(int) (width/bitmapRatio);

        }else {
            height=maximumSize;
            width=(int) (height*bitmapRatio);
        }


        return image.createScaledBitmap(image,width,height,true);
    }



    private void registerLauncher(){
        resultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                  if(result.getResultCode()==RESULT_OK){
                      Intent intentFromResult=result.getData();
                      if (intentFromResult!=null){
                          Uri imageData=intentFromResult.getData();


                          try {
                              if(Build.VERSION.SDK_INT>=28){
                                  ImageDecoder.Source source=ImageDecoder.createSource(AddActivity.this.getContentResolver(),imageData);
                                  selectedImage= ImageDecoder.decodeBitmap(source);
                                  binding.addImageView.setImageBitmap(selectedImage);
                              }else {
                                  selectedImage=MediaStore.Images.Media.getBitmap(AddActivity.this.getContentResolver(),imageData);
                                  binding.addImageView.setImageBitmap(selectedImage);
                              }






                          }catch (Exception e){
                              e.printStackTrace();
                          }
                      }
                  }
            }
        });

        permissionLauncher=registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result){
                    //permission granted
                    Intent intentToGallery=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    resultLauncher.launch(intentToGallery);

                }else {
                    //permission denied
                    Toast.makeText(AddActivity.this,"İzin Gerekiyor",Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}