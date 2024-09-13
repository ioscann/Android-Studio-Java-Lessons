package com.ioscan.firebasekullanimi;

import android.os.Bundle;
import android.view.SoundEffectConstants;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText loginMail,loginPassword,loginNewMail;
    private Button loginButton,updateButton,deleteButton;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore mStore;
    private DatabaseReference mReference;
    private DocumentReference mdReference;
    private HashMap<String, Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        loginMail = findViewById(R.id.loginMail);
        loginPassword = findViewById(R.id.loginPassword);
        loginNewMail = findViewById(R.id.loginNewMail);
        loginButton = findViewById(R.id.loginButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mStore = FirebaseFirestore.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!loginMail.getText().toString().isEmpty() && !loginPassword.getText().toString().isEmpty())
                {
                    mAuth.signInWithEmailAndPassword(loginMail.getText().toString(),loginPassword.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    mUser = mAuth.getCurrentUser();

                                    //assert mUser != null;
                                    if (mUser != null)
                                    {
                                        getDataFromFireStore(mUser.getUid());
                                        //getDataFromFirebaseDatabase(mUser.getUid());
                                    }

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!loginNewMail.getText().toString().isEmpty())
                {
                    mData = new HashMap<>();
                    mData.put("Mail",loginNewMail.getText().toString());

                    //updateDataFromFirebaseDB(mData,loginNewMail.getText().toString(),mUser.getUid());
                    updateDataFromFirestore(mData,loginNewMail.getText().toString(),mUser.getUid());
                }
                else {
                    Toast.makeText(LoginActivity.this, "Mail kısmı boş olamaz", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //deleteDataFromFirebaseDB(mUser.getUid());
                deleteDataFromFirestore(mUser.getUid());
            }
        });
    }

    private void getDataFromFirebaseDatabase(String uid)
    {
        mReference = FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid());
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());

                for (DataSnapshot snp : snapshot.getChildren()){
                    System.out.println(snp.getKey() + " = " + snp.getValue());
                }

                Toast.makeText(LoginActivity.this, "Giriş yapma başarılı :)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataFromFireStore(String uid)
    {
        mdReference = mStore.collection("Users").document(uid);

        mdReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            mData = (HashMap)documentSnapshot.getData();

                            for(Map.Entry data : mData.entrySet())
                            {
                                System.out.println(data.getKey() + " = " + data.getValue());
                            }

                            Toast.makeText(LoginActivity.this, "Giriş yapma başarılı :)", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e.getMessage());
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateDataFromFirebaseDB(HashMap<String,Object> hashMap,String newMail, String uid)
    {
        mReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        mReference.updateChildren(hashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Veri güncellemesi başarılı", Toast.LENGTH_SHORT).show();

                            System.out.println("Güncellenen veriler :");
                            getDataFromFirebaseDatabase(uid);
                        }
                    }
                });
    }

    private void updateDataFromFirestore(HashMap<String,Object> hashMap , String newMail, String uid)
    {
        mStore.collection("Users").document(uid)
                .update(hashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Günelleme işlemi başarılı :)", Toast.LENGTH_SHORT).show();
                            System.out.println("------------------ Güncellenen veriler ------------------");
                            getDataFromFireStore(uid);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void deleteDataFromFirebaseDB(String uid){

        mReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        mReference.removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Silme işlemi başarılı", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void deleteDataFromFirestore(String uid){

        mStore.collection("Users").document(uid)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Silme işlemi başarılı :)", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}