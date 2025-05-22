package com.ioscan.smailcan_vurur_201104109;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

import org.w3c.dom.Text;

public class MainActivity4 extends AppCompatActivity {

    Button button;
    TextView t1, t2, t3, t4, t5, t6, t7, t8;
    EditText mail;
    ;

    String name = MainActivity2.nameSurname;
    String dateOfBirth = MainActivity2.dateOfBirth;
    String gender = MainActivity2.gender;
    String place = MainActivity2.birthPlace;
    String myMail = MainActivity2.mail;

    void setDefault()
    {
        t1.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        t5.setVisibility(View.INVISIBLE);
        t7.setVisibility(View.INVISIBLE);

        t2.setText(" ");
        t4.setText(" ");
        t6.setText(" ");
        t8.setText(" ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        button = findViewById(R.id.button6);
        mail = findViewById(R.id.editTextText7);
        t1 = findViewById(R.id.textView14);
        t2 = findViewById(R.id.textView15);
        t3 = findViewById(R.id.textView16);
        t4 = findViewById(R.id.textView17);
        t5 = findViewById(R.id.textView18);
        t6 = findViewById(R.id.textView19);
        t7 = findViewById(R.id.textView20);
        t8 = findViewById(R.id.textView21);

        setDefault();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mail.getText().toString().isEmpty()) {
                    if (mail.getText().toString().equals(myMail))
                    {
                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{mail.getText().toString()});
                        email.putExtra(Intent.EXTRA_SUBJECT, "Üye giriş bilgisi");
                        email.putExtra(Intent.EXTRA_TEXT, "Yeni Şifreniz: 1234");
                        email.setType("message/rfc822");
                        startActivity(Intent.createChooser(email, "Bir uygulama seçiniz."));

                        t1.setVisibility(View.VISIBLE);
                        t2.setText(name);
                        t3.setVisibility(View.VISIBLE);
                        t4.setText(dateOfBirth);
                        t7.setVisibility(View.VISIBLE);
                        t8.setText(gender);
                        t5.setVisibility(View.VISIBLE);
                        t6.setText(place);
                    }
                    else {Toast.makeText(getApplicationContext(), "Sisteme kayıt olurken kullandığınız maili girmeniz gerek !", Toast.LENGTH_SHORT).show();setDefault();}
                }
                else {Toast.makeText(getApplicationContext(), "Mail kısmı boş bırakılamaz !", Toast.LENGTH_SHORT).show();setDefault();}
            }
        });
    }
}