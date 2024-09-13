package com.ioscan.custom_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class meyveAdapter extends ArrayAdapter<String> {

    public ImageView image;

    public TextView meyveAd, kalori;
    private String[] meyveler;
    private int[] kaloriler;
    private int[] resimler;

    private Context context;

    public meyveAdapter(String[] meyveler, int[] kaloriler, int[] resilmler, Context context) {
        super(context, R.layout.meyve_item, meyveler);
        this.meyveler = meyveler;
        this.kaloriler = kaloriler;
        this.resimler = resilmler;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.meyve_item, null);

        if (view != null) {
            meyveAd = view.findViewById(R.id.meyveAd);
            kalori = view.findViewById(R.id.meyveKalori);
            image = view.findViewById(R.id.image);

            meyveAd.setText(meyveler[position]);
            kalori.setText(String.valueOf(kaloriler[position]));
            image.setImageResource(resimler[position]);
        }

        return view;
    }
}
