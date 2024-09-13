package com.ioscan.firebasekullanimi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText registerUserName,registerMail,registerPassword;

    private FirebaseAuth mAuth; // VERİ TABANI KİŞİ DOĞRULAMASI
    private FirebaseUser mUser; // VERİ TABANI KİŞİ ERİŞİMİ
    private FirebaseFirestore mStore;
    private DatabaseReference mReference; // REAL TIME VERİ TABANI KULLANIMI
    private HashMap<String, Object> mData; // RTDB VERİ GÖNDERİM OBJESİ

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        registerButton = findViewById(R.id.registerButton);
        registerMail = findViewById(R.id.registerMail);
        registerUserName = findViewById(R.id.registerUserName);
        registerPassword = findViewById(R.id.registerPassword);

        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        mStore = FirebaseFirestore.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!registerMail.getText().toString().isEmpty() && !registerPassword.getText().toString().isEmpty() && !registerUserName.getText().toString().isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(registerMail.getText().toString(),registerPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {

                                        mUser = mAuth.getCurrentUser();

                                        if (!mUser.getUid().isEmpty())
                                        {
                                            System.out.println("User ID --> " + mUser.getUid());

                                            mData = new HashMap<>();

                                            mData.put("UserID",mUser.getUid());
                                            mData.put("UserName",registerUserName.getText().toString());
                                            mData.put("Mail",registerMail.getText().toString());
                                            mData.put("password",registerPassword.getText().toString());

                                            //registerRealTimeDatabase(mData,mUser.getUid());
                                            registerFireStoreDatabase(mData,mUser.getUid());
                                        }
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(MainActivity.this, "E posta ve şifre kısmı doldurulmalıdır !", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }

    private void registerRealTimeDatabase(HashMap<String,Object> hashMap, String uid)
    {
        mReference.child("Users").child(uid).setValue(hashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Kayıt başarılı :)", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void registerFireStoreDatabase(HashMap<String,Object> hashMap, String uid)
    {
        mStore.collection("Users").document(uid)
                .set(hashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Kayıt başarılı :)", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}