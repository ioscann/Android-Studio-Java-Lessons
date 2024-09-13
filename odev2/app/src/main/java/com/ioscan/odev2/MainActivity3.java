package com.ioscan.odev2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    Button kayit;
    RadioButton erkek ,kadin;
    EditText ad,soyad,kadi,sifre;
    CheckBox onay;
    String gender = "Erkek";

    public static long control;

    private DataBase V;

    void clear()
    {
        ad.setText("");
        soyad.setText("");
        kadi.setText("");
        sifre.setText("");
        onay.setChecked(false);
        erkek.setChecked(true);
        gender = "Erkek";
    }

    public long kayitEt(String ad,String soyad, String kadi, String sifre)
    {
        SQLiteDatabase db = V.getWritableDatabase();
        ContentValues veriler = new ContentValues();
        veriler.put("ad",ad);
        veriler.put("soyad",soyad);
        veriler.put("kadi",kadi);
        veriler.put("sifre",sifre);

        long sa = db.insert("kullanicilar",null,veriler);
        return sa;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        V = new DataBase(getApplicationContext());

        kayit = findViewById(R.id.button3);
        erkek = findViewById(R.id.radioButton);
        kadin = findViewById(R.id.radioButton2);
        ad = findViewById(R.id.editTextText3);
        soyad = findViewById(R.id.editTextText4);
        kadi = findViewById(R.id.editTextText5);
        sifre = findViewById(R.id.editTextText6);
        onay = findViewById(R.id.checkBox);

        erkek.setChecked(true);

        registerForContextMenu(kayit);

        erkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Erkek";
                kadin.setChecked(false);
            }
        });

        kadin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Kadın";
                erkek.setChecked(false);
            }
        });

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adim = ad.getText().toString();
                String soyadim = soyad.getText().toString();
                String kadim = kadi.getText().toString();
                String sifrem = sifre.getText().toString();

                if (adim.isEmpty()==false && soyadim.isEmpty()==false && kadim.isEmpty()== false && sifrem.isEmpty() == false && gender.isEmpty() == false && onay.isChecked() == true)
                {
                    try
                    {
                        DataBase database = new DataBase(MainActivity3.this);
                        database.veriEkle(adim,soyadim,gender,kadim,sifrem);

                        if (control>-1)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                            builder.setTitle("Bilgi");
                            builder.setMessage("Kayıt başarılı !");
                            builder.show();
                        }
                    }
                    finally
                    {
                        V.close();
                    }
                }
                else {Toast.makeText(getApplicationContext(),"Hiçbir alan boş bırakılamaz !",Toast.LENGTH_SHORT).show();}
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Temizle");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle() == "Temizle") {
            clear();
        }
        else {
            return  false;
        }
        return true;
    }
}