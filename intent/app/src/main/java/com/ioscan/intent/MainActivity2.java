package com.ioscan.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity
{

    private String kadi,sifre;

    TextView ad,sifrem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ad = findViewById(R.id.ad);
        sifrem = findViewById(R.id.sif);

        Intent gelenIntent = getIntent();

        ad.setText(gelenIntent.getStringExtra("kadi"));
        sifrem.setText(gelenIntent.getStringExtra("sifre"));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backIntent = new Intent(MainActivity2.this,MainActivity.class);
        finish();
        startActivity(backIntent);
    }
}