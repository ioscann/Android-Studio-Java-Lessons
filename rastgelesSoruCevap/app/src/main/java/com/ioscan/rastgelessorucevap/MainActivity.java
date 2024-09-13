package com.ioscan.rastgelessorucevap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.GetChars;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    private ToggleButton topla,cikar,carp,bol;
    private Button soru,tahmin;
    private EditText deger;
    private ArrayList islemTurleri = new ArrayList();

    TextView islem;

    Random rndIslem,rndSayi;

    int s1,s2,sonuc;

    int rndIslemNumber,rndSayiNumber;
    private String sorum,tahminim;

    private String islemTuruVeSoru()
    {
        if (islemTurleri.size() > 0)
        {
            sorum = " ";
            s1 = randomSayiGetir();
            sorum += String.valueOf(s1);
            sorum += " ";

            String tur = islemTurleri.get(rndIslemNumber).toString();
            sorum += tur;

            s2 = randomSayiGetir();
            sorum += " ";
            sorum += String.valueOf(s2);

            switch (tur)
            {
                case "+":
                    sonuc = s1 + s2;
                    break;
                case "-":
                    sonuc = s1-s2;
                    break;
                case "*":
                    sonuc = s1*s2;
                    break;  
                case "/":
                    sonuc = s1/s2;
                    break;

                default:
                    break;
            }
        }
        return sorum;
    }

    private int randomSayiGetir()
    {
       rndSayiNumber = rndSayi.nextInt(10)+1;

       return rndSayiNumber;
    }

    private void tahminKontrol()
    {
        tahminim = deger.getText().toString();

        if (!TextUtils.isEmpty(tahminim))
        {
            if (tahminim.matches(String.valueOf(sonuc))) {
                Toast.makeText(getApplicationContext(), "Doru", Toast.LENGTH_SHORT).show();
                islem.setText(islemTuruVeSoru());
                deger.setText("");
            }
            else {Toast.makeText(getApplicationContext(),"Yannış",Toast.LENGTH_SHORT).show(); deger.setText("");}
        }
        else{Toast.makeText(getApplicationContext(),"Boş bırakma",Toast.LENGTH_SHORT).show();};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rndIslem = new Random();
        rndSayi = new Random();

        topla = findViewById(R.id.togTop);
        cikar = findViewById(R.id.togCik);
        carp = findViewById(R.id.togCarp);
        bol = findViewById(R.id.togBol);
        islem = findViewById(R.id.islem);

        soru = findViewById(R.id.soru);
        tahmin = findViewById(R.id.tahmin);

        deger = findViewById(R.id.deger);

        topla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {islemTurleri.add("+");}
                else {islemTurleri.remove("+");}
            }
        });

        cikar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {islemTurleri.add("-");}
                else {islemTurleri.remove("-");}
            }
        });

        carp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {islemTurleri.add("*");}
                else {islemTurleri.remove("*");}
            }
        });

        bol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {islemTurleri.add("/");}
                else {islemTurleri.remove("/");}
            }
        });

        soru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (islemTurleri.size() > 0)
                {
                    islem.setText(islemTuruVeSoru());
                }
                else {Toast.makeText(getApplicationContext(),"İşlem türü şeç",Toast.LENGTH_SHORT).show();}
            }
        });

        tahmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tahminKontrol();
            }
        });


    }
}