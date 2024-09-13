package com.ioscan.tekrar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Presentation;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView nav;
    private Toolbar tool;

    private ListView list;

    private bolumler bolum;

    private ArrayAdapter<String> arrayAdapter;

    private String[] isimler = {"Sparişlerim","Bana Özel Fırsatlar","Kuponlarım","Oynadıkça kazan","Hepsipay","Beğendiklerim","Soru ve Taleplerim","Değerlendirmelerim"
            ,"Ayarlarım","Adreslerim","Ürün karşılaştırma","Uygulama i.in geri bildirim yayınlama","Müşteri hizmetleri"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.nav);
        tool = findViewById(R.id.tool);
        list = findViewById(R.id.list);

        setStatusBarGradiant(MainActivity.this);

        nav.setItemIconTintList(null); //navigotion barın varsayılan temasını sıfırlar

        //toggle = new ActionBarDrawerToggle(this,drawer,tool,R.string.open,R.string.close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState(); //navigationView in açılması için tuş ekler

        bolum = new bolumler(this,isimler);

        list.setAdapter(bolum);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.header_theme);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            //window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
}