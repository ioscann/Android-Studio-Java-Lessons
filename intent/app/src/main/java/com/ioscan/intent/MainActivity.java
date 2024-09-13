package com.ioscan.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

    Button giris;
    EditText kadim,sifrem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        giris = findViewById(R.id.giris);
        kadim = findViewById(R.id.kadi);
        sifrem = findViewById(R.id.sifrem);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (!TextUtils.isEmpty(kadim.getText().toString()) && !TextUtils.isEmpty(sifrem.getText().toString()))
                {
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    intent.putExtra("kadi",kadim.getText().toString());
                    intent.putExtra("sifre",sifrem.getText().toString());
                    startActivity(intent);

                }
            }
        });
    }
}