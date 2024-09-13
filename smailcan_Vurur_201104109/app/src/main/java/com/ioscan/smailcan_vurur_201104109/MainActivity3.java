package com.ioscan.smailcan_vurur_201104109;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    Button sign;
    TextView kadi,sifre;

    String name = MainActivity2.nameSurname;
    String pass = MainActivity2.password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sign = findViewById(R.id.button5);
        kadi = findViewById(R.id.editTextText5);
        sifre = findViewById(R.id.editTextText6);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kullanici = kadi.getText().toString();
                String sifrem = sifre.getText().toString();
                String username = "admin";
                String password = "123";

                if (kullanici.equals(username) && sifrem.equals(password)) {
                    Toast.makeText(getApplicationContext(), "Giriş başarılı", Toast.LENGTH_LONG).show();
                }
                else if (kullanici.equals(name) && sifrem.equals(pass)){
                    Toast.makeText(getApplicationContext(), "Giriş başarılı", Toast.LENGTH_LONG).show();
                }
                else {Toast.makeText(getApplicationContext(), "Giriş başarısız", Toast.LENGTH_LONG).show();}
            }
        });

    }
}