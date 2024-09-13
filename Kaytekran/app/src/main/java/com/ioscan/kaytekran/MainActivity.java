package com.ioscan.kaytekran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    Button kayit;
    String adim,soyadim,sehrim,yasim;
    EditText ad,soyad,sehir,yas;
    CheckBox tenis,futbol,yuzme;
    RadioButton erkek,kadin;
    ArrayList<String> hobiler = new ArrayList<>();

    String cinsiyet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ad = findViewById(R.id.ad);
        soyad = findViewById(R.id.soyad);
        sehir = findViewById(R.id.sehir);
        yas = findViewById(R.id.yas);

        tenis = findViewById(R.id.tenis);
        futbol = findViewById(R.id.futbol);
        yuzme = findViewById(R.id.yuzme);

        kayit = findViewById(R.id.kayitol);

        erkek = findViewById(R.id.erkek);
        kadin = findViewById(R.id.kadin);

        erkek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (erkek.isChecked() == true) {cinsiyet = "Erkek"; kadin.setChecked(false);}
            }
        });

        kadin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (kadin.isChecked() == true) {cinsiyet = "Kadın"; erkek.setChecked(false);}
            }
        });


        futbol.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {hobiler.add(futbol.getText().toString());}
            else {hobiler.remove(futbol.getText().toString());}
        });

        tenis.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {hobiler.add(tenis.getText().toString());}
            else {hobiler.remove(tenis.getText().toString());}
        });

        yuzme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {hobiler.add(yuzme.getText().toString());}
            else {hobiler.remove(yuzme.getText().toString());}
        });

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"sa",Toast.LENGTH_SHORT).show();
                adim = ad.getText().toString();
                soyadim = soyad.getText().toString();
                yasim = yas.getText().toString();
                sehrim = sehir.getText().toString();

                if (!TextUtils.isEmpty(adim) && !TextUtils.isEmpty(soyadim) && !TextUtils.isEmpty(yasim) && !TextUtils.isEmpty(sehrim) && cinsiyet.isEmpty() == false)
                {
                    System.out.println("Adınız -> " + adim);
                    System.out.println("Soyadınız -> "+ soyadim);
                    System.out.println("Şehriniz -> "+ sehrim);
                    System.out.println("Yaşınız -> " + yasim);
                    System.out.println("Cinsiyetiniz -> " + cinsiyet);
                    if (hobiler.isEmpty() == false ) {System.out.println("Hobileriniz -> " + hobiler);};
                }
            }
        });


    }

}