package com.ioscan.custom_gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity
{

    private GridView grid;
    private int[] dizi = {1,2,3,4,5,6,7,8,9,10};

    private String[] hayvan = {"ayı","kuş","dana","kedi","köpek","böcek","sinek","yılan","gergedan","zürafa"};

    private hayvanlar hayvanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hayvanAdapter = new hayvanlar(dizi,hayvan,this);

        grid = findViewById(R.id.grid);

        grid.setAdapter(hayvanAdapter);

    }
}