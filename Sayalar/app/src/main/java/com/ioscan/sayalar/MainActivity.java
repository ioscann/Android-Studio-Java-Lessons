package com.ioscan.sayalar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Runnable runable;
    Handler handler;
    Button basla;
    TextView saniye;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basla = findViewById(R.id.button);
        saniye = findViewById(R.id.textView);

        basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        count++;
                        saniye.setText(String.valueOf(count));

                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(getApplicationContext(), "Bitti", Toast.LENGTH_SHORT).show();
                    }
                }.start(); */

                handler = new Handler();
                runable = new Runnable() {
                    @Override
                    public void run() {
                        saniye.setText(String.valueOf(count));
                        count++;
                        handler.postDelayed(runable,1000);
                    }
                };

                handler.post(runable);

                handler.removeCallbacks(runable);
            }

        });


    }
}