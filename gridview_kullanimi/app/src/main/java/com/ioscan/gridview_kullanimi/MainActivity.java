package com.ioscan.gridview_kullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity
{

    ArrayAdapter<Integer> adapter;
    private GridView grid;

    private Integer[] dizi = {1,2,3,4,5,6,7,8,9,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = findViewById(R.id.grid);

        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,dizi);

        grid.setAdapter(adapter);
    }
}