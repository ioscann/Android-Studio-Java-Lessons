package com.ioscan.isim_sehir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class normal_oyun extends AppCompatActivity
{
    private Button harfAl,tahminEt;
    private EditText tahmin;
    private TextView ipucu,kelime,toplam;
    private Random rndil,rndHarf;
    private int rndilNumber,rndHarfNumber;
    private String gelenil,ilBoyutu;
    private String kelimem = "";

    private int maxPuan,puan = 0;
    private String tahminim;
    private boolean myBool=false;
    private ArrayList<Character> ilHafrleri;
    private String[] iller = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya",
            "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın", "Balıkesir",
            "Bartın", "Batman", "Bayburt", "Bilecik", "Bingöl", "Bitlis",
            "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum",
            "Denizli", "Diyarbakır", "Düzce", "Elazığ", "Edirne", "Erzincan",
            "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari",
            "Hatay", "Iğdır", "Isparta", "İstanbul", "İzmir", "Kahramanmaraş",
            "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kırıkkale",
            "Kırklareli", "Kırşehir", "Kilis", "Kocaeli", "Konya", "Kütahya",
            "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş",
            "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Şanlıurfa", "Şırnak",
            "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uşak", "Van",
            "Yalova", "Yozgat", "Zonguldak"};


    private void harfal()
    {
        if (ilHafrleri.size() > 0)
        {
            rndHarf = new Random();
            rndHarfNumber = rndHarf.nextInt(ilHafrleri.size());
            String[] txtHarfler = kelime.getText().toString().split(" ");
            char[] gelenIlHarfler = gelenil.toCharArray();

            for (int i = 0; i < gelenil.length(); i++) {
                if (txtHarfler[i].equals("_") && gelenIlHarfler[i] == ilHafrleri.get(rndHarfNumber)) {
                    txtHarfler[i] = String.valueOf(ilHafrleri.get(rndHarfNumber));
                    kelimem = "";

                    for (int j = 0; j < gelenil.length(); j++) {
                        if (j == i)
                            kelimem += txtHarfler[j] + " ";
                        else if (j < gelenil.length() - 1)
                            kelimem += txtHarfler[j] + " ";
                        else
                            kelimem += txtHarfler[j];

                    }
                    maxPuan -=1;
                    break;
                }

            }

            kelime.setText(kelimem);
            ilHafrleri.remove(rndHarfNumber);

        }
    }

    private void soruGetir()
    {
        rndil = new Random();

        toplam.setText("Toplam Puan : " + puan);
        rndilNumber = rndil.nextInt(iller.length);
        gelenil = iller[rndilNumber];

        ilHafrleri = new ArrayList<>();

        for (char c : gelenil.toCharArray())
            ilHafrleri.add(c);

        ipucu.setText(gelenil.length() + " harfli ilimiz");

        kelimem = "";

        for (int i = 0; i< gelenil.length();i++ )
        {
            if (i < gelenil.length()) { kelimem += "_ "; }
            else if (i == gelenil.length() -1) { kelimem += "_";}
        }

        kelime.setText(kelimem.toString());

        tahmin.setText("");

        maxPuan = gelenil.length();

        if (myBool == false)
        {
            if (gelenil.length() > 5 && gelenil.length() <8) {harfal();}
            else if (gelenil.length() > 8) {harfal();harfal();}
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun);

        harfAl = findViewById(R.id.btnHarf);
        tahminEt = findViewById(R.id.btnTahmin);
        tahmin = findViewById(R.id.txtTahmin);
        ipucu = findViewById(R.id.ipucu);
        kelime = findViewById(R.id.txtKelime);
        toplam = findViewById(R.id.toplamPuan);

        soruGetir();

        tahminEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tahminim = tahmin.getText().toString();
                tahminim.toString().split(" ");
                String yeniTahminim = tahminim.toLowerCase();
                String yenigelenil = gelenil.toLowerCase();
                if (!TextUtils.isEmpty(tahminim))
                {
                    if (yeniTahminim.equals(yenigelenil)){ Toast.makeText(getApplicationContext(),"Doğru tahmin " + maxPuan + " puan kazandın!",Toast.LENGTH_SHORT).show(); puan = puan + maxPuan; soruGetir();myBool = false;}
                    else {Toast.makeText(getApplicationContext(),"Yanlış tahmin !",Toast.LENGTH_SHORT).show();tahmin.setText("");}
                }
                else {Toast.makeText(getApplicationContext(),"Tahmin kısmı boş bırakılamaz !",Toast.LENGTH_SHORT).show();}
            }
        });

        harfAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                harfal();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backIntent = new Intent(normal_oyun.this,MainActivity.class);
        finish();
        startActivity(backIntent);
    }
}