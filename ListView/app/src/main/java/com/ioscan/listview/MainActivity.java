package com.ioscan.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private ListView listView;

    private ArrayAdapter<String> adapter;

    private String[] iller = {"istanbul","adana","izmir","sinop","bayburt","tokat","samsun","trabzon","sakarya","sivas"};

    private String[] aciklama = {"sa","as","sa","as","sa","as"};

    private int[] sehirResim = {R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,iller);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,DerailsActivity.class);
                intent.putExtra("sehir",iller[position].toString());
                intent.putExtra("aciklama",aciklama[position].toString());
                intent.putExtra("resim",sehirResim[position]);
                startActivity(intent);
            }
        });

    }
}