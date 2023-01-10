package com.example.hostelappnitj.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hostelappnitj.R;


public class homeFragment extends Fragment {
ImageView imageViewHostels ;


    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imageViewHostels=view.findViewById(R.id.imageView_hostels);

//        for showing the images autoMatically
        int[] imageArray = { R.drawable.img_1,R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5,R.drawable.img_6};
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                imageViewHostels.setImageResource(imageArray[i]);
                i++;
                if (i > imageArray.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
        return view;
    }
}