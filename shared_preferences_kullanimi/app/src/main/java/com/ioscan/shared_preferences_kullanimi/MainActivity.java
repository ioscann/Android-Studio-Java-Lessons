package com.ioscan.shared_preferences_kullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private String username,userpasword,getUsername,getUserpasword;

    private boolean checked;

    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;
    private Button giris;

    private EditText kadi,sifre;

    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        giris = findViewById(R.id.giris);
        kadi = findViewById(R.id.kadi);
        sifre = findViewById(R.id.sifre);
        check = findViewById(R.id.check);

        preferences = this.getSharedPreferences("com.ioscan.shared_preferences_kullanimi", Context.MODE_PRIVATE);
        getUsername = preferences.getString("username",null);
        getUserpasword = preferences.getString("userpassword",null);
        checked = preferences.getBoolean("checked",false);

        if (checked == true)
        {
            if (!TextUtils.isEmpty(getUsername)) {kadi.setText(getUsername);}
            if (!TextUtils.isEmpty(getUserpasword)) {sifre.setText(getUserpasword);}
            check.setChecked(true);
        }
        else { check.setChecked(false);}

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = kadi.getText().toString();
                userpasword = sifre.getText().toString();

                if (username.isEmpty() == false && userpasword.isEmpty() == false)
                {
                    if (check.isChecked() == true)
                    {
                        editor = preferences.edit();
                        editor.putString("username",username);
                        editor.putString("userpassword",userpasword);
                        editor.putBoolean("checked",true);
                        editor.apply();

                        Toast.makeText(MainActivity.this, "Kullanıcı kaydı başarılı !", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        editor = preferences.edit();
                        editor.putString("username",username);
                        editor.putString("userpassword",userpasword);
                        editor.putBoolean("checked",false);
                        editor.apply();

                        Toast.makeText(MainActivity.this, "Kullanıcı kaydı başarılı !", Toast.LENGTH_SHORT).show();;
                    }

                }
                else {
                    Toast.makeText(MainActivity.this, "Hiçbir alan boş bırakılamaz !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}