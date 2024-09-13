package com.ioscan.progresbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private ToggleButton tog;
    private TextView txt;
    private SeekBar sik;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // indeterminateTint renk değiştirir

        img = findViewById(R.id.imagev);
        tog = findViewById(R.id.toggle);
        sik = findViewById(R.id.seek);
        txt = findViewById(R.id.sayac);

        sik.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String sayac = String.valueOf(progress);
                txt.setText(sayac);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {img.setVisibility(View.VISIBLE);}
                else {img.setVisibility(View.INVISIBLE);};
            }
        });
    }
}