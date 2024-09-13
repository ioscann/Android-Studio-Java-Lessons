package com.ioscan.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.PrivateKey;

public class DerailsActivity extends AppCompatActivity
{

    private ImageView image;
    private TextView sehir,aciklama;

    private Intent intent;

    String sAciklama,sIsim;

    int resim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derails);

        image = findViewById(R.id.image);
        sehir = findViewById(R.id.sehirismi);
        aciklama = findViewById(R.id.aciklama);

        intent = getIntent();

        sAciklama = getIntent().getStringExtra("aciklama");
        sIsim = getIntent().getStringExtra("sehir");
        resim = getIntent().getIntExtra("resim",-1);

        image.setImageResource(resim);
        aciklama.setText(sAciklama);
        sehir.setText(sIsim);
    }
}