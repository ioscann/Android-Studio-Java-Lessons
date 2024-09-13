package com.ioscan.bottom_navigation_view_kullanimi;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import android.widget.Toast;




public class homeFragment extends Fragment
{

    Button buton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home,container,false);

        buton = rootView.findViewById(R.id.myButton);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "sa", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }


}