package com.ioscan.odev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    WebView w;
    RatingBar r;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        r = findViewById(R.id.ratingBar);
        b = findViewById(R.id.button4);
        w = findViewById(R.id.sa);

        w.getSettings().setJavaScriptEnabled(true);
        w.loadUrl("https://www.youtube.com/@ioscannn");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float puan = r.getRating();
                Toast.makeText(getApplicationContext(),"Puanınız " + puan,Toast.LENGTH_SHORT).show();
            }
        });
    }
}