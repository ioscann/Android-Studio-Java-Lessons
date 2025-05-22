package com.ioscan.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button getTimeButton;
    private TextView timeText;

    private Retrofit retrofit;
    private TimeApi timeApi;
    private String baseURL = "https://worldtimeapi.org/api/timezone/";
    private Call<TimeTurkey> timeTurkeyCall;
    private TimeTurkey timeTurkey;

    private void init()
    {
        getTimeButton = findViewById(R.id.mainGetTimeButton);
        timeText = findViewById(R.id.mainTimeText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        getTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRetrofitSettings();
            }
        });
    }

    private void setRetrofitSettings()
    {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        timeApi = retrofit.create(TimeApi.class);
        timeTurkeyCall = timeApi.getTime();

        timeTurkeyCall.enqueue(new Callback<TimeTurkey>() {
            @Override
            public void onResponse(Call<TimeTurkey> call, Response<TimeTurkey> response) {
                if (response.isSuccessful()){
                    timeTurkey = response.body();

                    if (timeTurkey != null)
                    {
                        timeText.setText(timeTurkey.getDateTime().split("T")[0]);
                    }
                }
            }

            @Override
            public void onFailure(Call<TimeTurkey> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
}