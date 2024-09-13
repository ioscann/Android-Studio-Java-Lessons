package com.ioscan.isim_sehir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class sureli_oyun extends AppCompatActivity
{

    private Button harfAl,tahminEt;
    private EditText tahmin;
    private TextView ipucu,kelime,toplam,surem;
    private Random rndil,rndHarf;
    private int rndilNumber,rndHarfNumber;
    private String gelenil;
    private String kelimem = "";
    private Handler handler;
    private Runnable runnable;
    private int maxPuan,puan = 0;
    private String tahminim;
    private int saat = 0,dakika=0,saniye=0,sefer = 1;
    private int count=0;
    private boolean control = false, myBool=false;
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

    private void sayacBaslat()
    {
        handler = new Handler();
        runnable = new Runnable()
        {
            @Override
            public void run() {

                if (count == 0) {surem.setText("0.00");}
                else if (count % 60 != 0 && count < 10)
                {
                    saniye ++;
                    surem.setText(String.valueOf(dakika) + "." + "0" + String.valueOf(saniye));
                }
                else if (count % 60 != 0 && count >= 10 && count <= 59)
                {
                    saniye ++;
                    surem.setText(String.valueOf(dakika) + "." + String.valueOf(saniye));
                }
                else if (count % 60 >= 0 && count >= 60)
                {
                    if (count % 60 == 0 && count > 60) {sefer++;}

                    dakika = count / 60;
                    saniye = count - 60*sefer;

                    if (saniye < 10) {surem.setText(String.valueOf(dakika) + ".0" + String.valueOf(saniye));  }
                    else {surem.setText(String.valueOf(dakika) + "." + String.valueOf(saniye)); }
                }
                else if (count % 60 >= 0 && count >= 3600)
                {
                    if (count % 60 == 0 && count > 3600) {sefer++;}

                    saat = count / 3600;
                    dakika = count / 60;
                    saniye = count - 60*sefer;

                    if (saniye < 10) {surem.setText(String.valueOf(saat) + "." + String.valueOf(dakika) + ".0" + String.valueOf(saniye));  }
                    else {surem.setText(String.valueOf(saat) + "." +String.valueOf(dakika) + "." + String.valueOf(saniye)); }
                }

                handler.postDelayed(runnable,1000);

                if (dakika== 3)
                {
                    handler.removeCallbacks(runnable);
                    AlertDialog.Builder builder = new AlertDialog.Builder(sureli_oyun.this);
                    builder.setTitle("Süre bitti !");
                    builder.setMessage("Tekrar oynamak ister misin ?");
                    builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            count=0;
                            saat=0;
                            dakika=0;
                            saniye=0;
                            sayacBaslat();
                            soruGetir();
                        }
                    });
                    builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Intent backIntent = new Intent(sureli_oyun.this,MainActivity.class);
                            startActivity(backIntent);
                        }
                    });
                    builder.show();

                }

                count++;

            }
        };
        handler.post(runnable);
    }


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
                        else if (j < gelenil.length()-1)
                            kelimem += txtHarfler[j] + " ";
                        else
                            kelimem += txtHarfler[j];
                    }

                    maxPuan -=1;
                    break;
                }

            }

            ilHafrleri.remove(rndHarfNumber);
            kelime.setText(kelimem);
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

        int sayi = gelenil.length();

        if (myBool == false)
        {
            if (sayi >= 6 &&  sayi <=8) {harfal(); harfal();myBool = true;}
            else if (sayi >= 9) {harfal(); harfal(); harfal(); myBool = true;}
        }

        tahmin.setText("");

        maxPuan = gelenil.length();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sureli_oyun);

        harfAl = findViewById(R.id.btnHarf2);
        tahminEt = findViewById(R.id.btnTahmin2);
        tahmin = findViewById(R.id.txtTahmin2);
        ipucu = findViewById(R.id.ipucu2);
        kelime = findViewById(R.id.txtKelime2);
        toplam = findViewById(R.id.toplamPuan2);
        surem = findViewById(R.id.sure);

        sayacBaslat();
        soruGetir();

        tahminEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tahminim = tahmin.getText().toString();
                String yeniTahminim = tahminim.toLowerCase();
                String yenigelenil = gelenil.toLowerCase();
                if (!TextUtils.isEmpty(tahminim))
                {
                    if (yeniTahminim.equals(yenigelenil)){ gelenil = "";Toast.makeText(getApplicationContext(),"Doğru tahmin " + maxPuan + " puan kazandın!",Toast.LENGTH_SHORT).show(); puan = puan + maxPuan; ;myBool = false; soruGetir();}
                    else {Toast.makeText(getApplicationContext(),"Yanlış tahmin !",Toast.LENGTH_SHORT).show(); tahmin.setText("");}
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
        handler.removeCallbacks(runnable);
        Intent backIntent = new Intent(sureli_oyun.this,MainActivity.class);
        finish();
        startActivity(backIntent);
    }
}