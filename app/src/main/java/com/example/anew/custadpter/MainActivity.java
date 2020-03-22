package com.example.anew.custadpter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int status= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(status== PackageManager.PERMISSION_GRANTED)
        {
            satya();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
        }
    }

    public void satya()
    {
        ListView lv=findViewById(R.id.lv);
        String s1="/storage/sdcard1/Pictures/";
        File f=new File(s1);
        if(!f.exists())
        {
            s1="/storage/emulated/pictures/";
            f=new File(s1);
        }
        File[] file=f.listFiles();
        lv.setAdapter(new MyAdapter(this));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            satya();
        }
        else
        {
            Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
        }
    }
}
