package com.ioscan.smailcan_vurur_201104109;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    RadioButton r1,r2;
    Button register;
    public static String nameSurname;
    public static String password;
    public static String gender;

    public static String dateOfBirth;
    public static String birthPlace;
    public static String mail;
    String place;
    String[] adresler ={"Tokat","İstanbul","Erzincan","Erzurum"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayAdapter<String> adapter =  new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, adresler);
        Spinner s = findViewById(R.id.spinner);
        s.setAdapter(adapter);

        register = findViewById(R.id.button);
        t1 = findViewById(R.id.editTextText);
        t2 = findViewById(R.id.editTextText3);
        t3 = findViewById(R.id.editTextText2);
        t4 = findViewById(R.id.editTextText4);
        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
        r1.setChecked(true);
        gender = "Erkek";

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1.isChecked() == true) {gender = "Erkek"; r2.setChecked(false); }

            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r2.isChecked() == true) {gender = "Kadın"; r1.setChecked(false);}
            }
        });

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t1.getText().toString().isEmpty()==false && t2.getText().toString().isEmpty()==false && t3.getText().toString().isEmpty()==false && t4.getText().toString().isEmpty()==false && gender.isEmpty()==false )
                {
                    nameSurname = t1.getText().toString();
                    password = t3.getText().toString();
                    dateOfBirth = t2.getText().toString();
                    birthPlace = place;
                    mail = t4.getText().toString();
                    Toast.makeText(getApplicationContext(), "Kayıt olma işlemi başarılı ! İsterseniz üye girişi veya şifre değiştirme işlemi yapabilirsiniz", Toast.LENGTH_SHORT).show();
                }
                else { Toast.makeText(getApplicationContext(), "Hiçbir alan boş bırakılamaz !", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}