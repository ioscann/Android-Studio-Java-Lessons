package com.ioscan.bottom_navigation_view_kullanimi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity
{

    private FrameLayout frame;
    private BottomNavigationView bottom;

    private homeFragment home;


    private void setFrame(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = findViewById(R.id.frameLayout);
        bottom = findViewById(R.id.bottom);

        home = new homeFragment();

        bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.search)
                {
                    setFrame(home);
                    Toast.makeText(MainActivity.this, "search", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.home)
                {

                    Toast.makeText(MainActivity.this,"home",Toast.LENGTH_SHORT).show();}
                else if (id == R.id.person)
                {
                    Toast.makeText(MainActivity.this, "person", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }
}