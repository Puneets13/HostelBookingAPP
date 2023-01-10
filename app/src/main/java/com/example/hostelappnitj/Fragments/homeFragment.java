package com.example.hostelappnitj.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.RegisterationActivity;
import com.example.hostelappnitj.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class homeFragment extends Fragment {
ImageView imageViewHostels ;
FloatingActionButton registerBtn ;

    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imageViewHostels=view.findViewById(R.id.imageView_hostels);
        registerBtn=view.findViewById(R.id.registerBtn);
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
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Register here", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), RegisterationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        return view;
    }

}