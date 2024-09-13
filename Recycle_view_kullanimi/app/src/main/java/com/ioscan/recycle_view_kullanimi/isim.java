package com.ioscan.recycle_view_kullanimi;

import android.content.Context;

import java.util.ArrayList;

public class isim
{
    int dizi;
    String isimler;

    Context context;

    public isim()
    {

    }

    public isim(int dizi, String isimler, Context context)
    {
        this.dizi = dizi;
        this.isimler = isimler;
        this.context = context;
    }


    public int getDizi() {
        return dizi;
    }

    public void setDizi(int dizi) {
        this.dizi = dizi;
    }

    public void setIsimler(String isimler) {
        this.isimler = isimler;
    }

    public String getIsimler() {
        return isimler;
    }

    static public ArrayList<isim> setData()
    {
        ArrayList<isim> isimList = new ArrayList<isim>();
        String[] isimler = {"ali","ayşe","mehmet","rıza","iso"};
        int[] dizi = {1,2,3,4,5};

        isim isim = new isim();


        for (int i=0;i<isimler.length;i++)
        {
            isim.setIsimler(isimler[i]);
            isim.setDizi(dizi[i]);

            isimList.add(isim);
        }

        return isimList;
    }
}
