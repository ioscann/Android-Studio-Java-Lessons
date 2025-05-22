package com.ioscan.bottom_sheet_fragment;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private Button mainButton;
    private BottomSheetFragmentExample bottomSheetFragmentExample;
    private RecyclerView mainRecycler;

    private void init()
    {
        mainButton = findViewById(R.id.mainButton);
        bottomSheetFragmentExample = new BottomSheetFragmentExample(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetFragmentExample.show(getSupportFragmentManager(), "Bottom Sheet Fragment");

                bottomSheetFragmentExample.setOnItemClickListener(new BottomSheetFragmentExample.OnClickListener() {
                    @Override
                    public void onClick() {
                        bottomSheetFragmentExample.dismiss();
                    }
                });
            }
        });

    }
}