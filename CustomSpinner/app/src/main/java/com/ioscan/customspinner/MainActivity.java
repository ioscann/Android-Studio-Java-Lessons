package com.ioscan.customspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinner;
    private TextView flagName;
    private CustomSpinnerAdapter customSpinnerAdapter;
    private Flag mFlag;

    private void init()
    {
        mSpinner = findViewById(R.id.mainSpinner);
        flagName = findViewById(R.id.mainTextView);
        customSpinnerAdapter = new CustomSpinnerAdapter(Flag.getData(this), this);
        mSpinner.setAdapter(customSpinnerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mFlag = (Flag) parent.getItemAtPosition(position);
                flagName.setText(mFlag.getFlagName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mFlag = (Flag) parent.getItemAtPosition(0);
                flagName.setText(mFlag.getFlagName());
            }
        });

    }
}