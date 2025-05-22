package com.ioscan.viewpager_tablayout;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ioscan.viewpager_tablayout.adapters.CustomViewPager;
import com.ioscan.viewpager_tablayout.fragments.HomeFragment;
import com.ioscan.viewpager_tablayout.fragments.MessagesFragment;
import com.ioscan.viewpager_tablayout.fragments.NotificationFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private CustomViewPager mAdapter;
    private TabLayout mTabLayout;

    private void init(){
        mViewPager = findViewById(R.id.mainViewPager);
        mTabLayout = findViewById(R.id.mainTabLayout);

        mAdapter = new CustomViewPager(getSupportFragmentManager());
        mAdapter.addFragment(new HomeFragment(),"Home");
        mAdapter.addFragment(new MessagesFragment(), "Messages");
        mAdapter.addFragment(new NotificationFragment(), "Notification");

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }
}