package com.ioscan.navigationview_kullanimi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView nav;

    private Toolbar tool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.navigate);
        tool = findViewById(R.id.tool);

        setSupportActionBar(tool);

        nav.setItemIconTintList(null);

        toggle = new ActionBarDrawerToggle(this,drawer,tool,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.bir) {
                    drawer.closeDrawer(GravityCompat.START);
                    Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawer.isOpen() == true) {drawer.closeDrawer(GravityCompat.START);}
        else {super.onBackPressed();}
    }


}