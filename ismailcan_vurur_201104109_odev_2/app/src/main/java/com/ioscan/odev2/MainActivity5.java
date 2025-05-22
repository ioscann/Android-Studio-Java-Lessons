package com.ioscan.odev2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity5 extends AppCompatActivity {

    ListView list;
    Button b;
    EditText e;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        list = findViewById(R.id.listview);
        b = findViewById(R.id.button6);
        e = findViewById(R.id.editTextText7);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e.getText().toString().isEmpty() == false)
                {
                    DataBase veritabanı=new DataBase(MainActivity5.this);
                    List<String> vVeriler =veritabanı.veriListele(e.getText().toString());
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity5.this,android.R.layout.activity_list_item,android.R.id.text1,vVeriler);
                    list.setAdapter(adapter);
                }
            }
        });
    }
}