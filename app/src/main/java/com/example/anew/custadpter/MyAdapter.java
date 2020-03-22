package com.example.anew.custadpter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.nio.file.Files;
import java.util.zip.Inflater;

public class MyAdapter extends BaseAdapter
{
    File[] file;
    File f1;
    String path;
    MainActivity mainActivity;
    MyAdapter(MainActivity mainActivity)
    {
        path="/storage/sdcard1/Pictures";
        f1=new File(path);
        if(!f1.exists())
        {
            path="/storage/emulated/Pictures";
            f1=new File(path);
        }
        file=f1.listFiles();
        this.mainActivity=mainActivity;
    }

    @Override
    public int getCount() {
        return file.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lin=LayoutInflater.from(mainActivity);
        View v= lin.inflate(R.layout.omg,null);
        ImageView iv1=v.findViewById(R.id.iv1);
        ImageView iv2=v.findViewById(R.id.iv2);
        TextView tv1=v.findViewById(R.id.tv1);
        TextView tv2=v.findViewById(R.id.tv2);
        final File fp= file[position];
        tv1.setText(fp.getName());
        tv2.setText(String.valueOf(fp.length()));
        Bitmap bmp= BitmapFactory.decodeFile(fp.getPath());
        Bitmap bmp1= ThumbnailUtils.extractThumbnail(bmp,240,240);
        iv1.setImageBitmap(bmp1);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fp.delete();
                file=f1.listFiles();
                MyAdapter.this.notifyDataSetChanged();
            }
        });
        return v;
    }
}
