package com.ioscan.custom_gridview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.security.PrivateKey;

public class hayvanlar extends ArrayAdapter
{
    private TextView id,hayvanadi;
    private Context context;

    private int[] dizi = {};

    private String[] hayvan = {};

    public hayvanlar(int[] dizi,String[] hayvan,Context context)
    {
        super(context,R.layout.gridxml,hayvan);
        this.dizi = dizi;
        this.hayvan = hayvan;
        this.context = context;
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridxml,null);

        if (view != null)
        {
            hayvanadi = view.findViewById(R.id.ad);
            id = view.findViewById(R.id.aydi);

            hayvanadi.setText(hayvan[position]);
            id.setText(String.valueOf(dizi[position]));
        }
        return view;
    }
}
