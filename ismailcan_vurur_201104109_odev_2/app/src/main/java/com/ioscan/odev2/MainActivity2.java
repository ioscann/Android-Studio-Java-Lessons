package com.ioscan.odev2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.Toast;

import kotlin.jvm.internal.Intrinsics;

public class MainActivity2 extends AppCompatActivity {
    Button giris,kayit,sorgu;
    EditText kadi,sifre;

    public static Boolean kontrol = false;

    private String[] sutunlar={"kadi","sifre"};
    private DataBase V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        V = new DataBase(getApplicationContext());

        giris = findViewById(R.id.button);
        kayit = findViewById(R.id.button2);
        kadi = findViewById(R.id.editTextText);
        sifre = findViewById(R.id.editTextText2);
        sorgu = findViewById(R.id.button5);

        sorgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
                startActivity(intent);
            }
        });

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
            }
        });

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kadim = kadi.getText().toString();
                String sifrem = sifre.getText().toString();

                if (kadim.isEmpty() == false && sifrem.isEmpty() == false)
                {
                    DataBase database = new DataBase(MainActivity2.this);

                    database.veriBul(kadim,sifrem);

                    if (kontrol == true)
                    {
                        kontrol = false;
                        Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Giriş başarısız!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}