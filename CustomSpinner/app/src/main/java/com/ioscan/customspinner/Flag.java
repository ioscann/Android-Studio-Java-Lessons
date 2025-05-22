package com.ioscan.customspinner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Flag {
    private Bitmap flagImage;
    private String flagName;

    private Flag(){

    }
    public Flag(Bitmap flagImage, String flagName) {
        this.flagImage = flagImage;
        this.flagName = flagName;
    }

    public Bitmap getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(Bitmap flagImage) {
        this.flagImage = flagImage;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public static ArrayList<Flag> getData(Context context)
    {
        Integer[] flagList = {
                R.drawable.flag1,
                R.drawable.flag2,
                R.drawable.flag3,
                R.drawable.flag4,
                R.drawable.flag5
        };

        String[] flagNameList = {
                "Türkiye",
                "Amerika",
                "Japonya",
                "Hindistan",
                "Çin"
        };

        ArrayList<Flag> flagArrayList = new ArrayList<>();

        Flag mFlag;
        Bitmap bitmap;

        for (int i = 0; i < flagNameList.length; i++ )
        {
            bitmap = BitmapFactory.decodeResource(context.getResources(), flagList[i]);
            mFlag = new Flag(bitmap, flagNameList[i]);

            flagArrayList.add(mFlag);
        }

        return flagArrayList;
    }
}
