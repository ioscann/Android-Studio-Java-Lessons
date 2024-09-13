package com.ioscan.androidstudiodersler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kisiler kisi = new kisiler("iso","can");
        ArrayList<String> list = new ArrayList<>();
        list.add("iso");
        list.add("cab");

        boolean result = list.contains("iso");
        int index =list.indexOf("iso");
        int last = list.lastIndexOf("iso");

        if (result == true) {System.out.println(result);
                                System.out.println(index);
                                System.out.println(last);}

        System.out.println(list.get(0));

        for (String isim : list)
        {
            System.out.println(isim);
        }

        list.remove("cab");


        for (String isim : list)
        {
            System.out.println(isim);
        }

        HashMap<Integer,String> tablo = new HashMap<>();
        tablo.put(1,"iso");
        tablo.put(2,"ozan");
        tablo.put(3,"mert");

        for (int i =0;i<tablo.size();i++)
        {
            System.out.println(tablo.get(i));
        }

        for (Map.Entry tablom : tablo.entrySet())
        {
            System.out.println(tablom.getKey() + " " + tablom.getValue());
        }


    }
}