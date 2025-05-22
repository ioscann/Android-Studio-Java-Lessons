package com.ioscan.simplegallery.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ioscan.simplegallery.R;
import com.ioscan.simplegallery.adapters.ImagePagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ImagePagerAdapter imagePagerAdapter;

    private Integer[] imageList = {R.drawable.minyon1, R.drawable.minyon2, R.drawable.minyon3};

    private void init()
    {
        mViewPager = findViewById(R.id.mainViewPager);

        imagePagerAdapter = new ImagePagerAdapter(imageList, MainActivity.this);
        mViewPager.setAdapter(imagePagerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }
}