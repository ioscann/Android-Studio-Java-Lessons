package com.ioscan.tekrar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class bolumler extends ArrayAdapter<String>
{

    private String[] isimler = {};

    private Context context;

    private TextView kategori;

    public bolumler(Context context, String[] dizi)
    {
        super(context,R.layout.listview_theme,dizi);
        this.isimler = dizi;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_theme,null);

        if (view != null)
        {
            kategori = view.findViewById(R.id.kategori);
            kategori.setText(isimler[position]);
        }
        return view;
    }
}
