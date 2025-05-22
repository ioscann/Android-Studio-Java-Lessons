package com.ioscan.customspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends BaseAdapter {

    private ArrayList<Flag> mFlagList;
    private View view;
    private Context context;

    private ImageView flagImage;
    private TextView flagName;

    public CustomSpinnerAdapter(ArrayList<Flag> mFlagList, Context context) {
        this.mFlagList = mFlagList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mFlagList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFlagList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.spinner_item, null);

        flagImage = view.findViewById(R.id.spinner_item_img);
        flagName = view.findViewById(R.id.spinner_item_flag_name);

        flagImage.setImageBitmap(mFlagList.get(position).getFlagImage());
        flagName.setText(mFlagList.get(position).getFlagName());

        return view;
    }
}
