package com.example.hostelappnitj.Fragments;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.RegisterationActivity;
import com.example.hostelappnitj.Acitvity.SignUpActivity;
import com.example.hostelappnitj.Hostels.BoysH4Activity;
import com.example.hostelappnitj.Hostels.BoysH5Activity;
import com.example.hostelappnitj.Hostels.BoysH6Activity;
import com.example.hostelappnitj.Hostels.BoysH7Activity;
import com.example.hostelappnitj.Hostels.GirlsH1Activity;
import com.example.hostelappnitj.Hostels.GirlsH2Activity;
import com.example.hostelappnitj.Hostels.MegaBoysA_Activity;
import com.example.hostelappnitj.Hostels.MegaBoysB_Activity;
import com.example.hostelappnitj.Hostels.MegaBoysF_Activity;
import com.example.hostelappnitj.Hostels.MegaGirlsActivity;
import com.example.hostelappnitj.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class homeFragment extends Fragment {
ImageView imageViewHostels ;
ExtendedFloatingActionButton registerBtn;
    AppCompatButton btnmghB,btnmghA,btnmghF,btnBoysH4,btnBoysH5,btnBoysH6,btnBoysH7,btnGirlsMega , btnGirlsH1,btnGirlsH2 ;


    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        to lock the orientations of the screen
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imageViewHostels=view.findViewById(R.id.imageView_hostels);
        registerBtn=view.findViewById(R.id.registerBtn);
        btnmghB=view.findViewById(R.id.btnMghB);
        btnmghA=view.findViewById(R.id.btnMghA);
        btnmghF=view.findViewById(R.id.btnMghF);
        btnBoysH4=view.findViewById(R.id.btnHostel4);
        btnBoysH5=view.findViewById(R.id.btnHostel5);
        btnBoysH6=view.findViewById(R.id.btnHostel6);
        btnBoysH7=view.findViewById(R.id.btnHostel7);
        btnGirlsMega=view.findViewById(R.id.btnMegaGirls);
        btnGirlsH1=view.findViewById(R.id.btnGrlHostel1);
        btnGirlsH2=view.findViewById(R.id.btnGrlHostel2);


//        for showing the images autoMatically
        int[] imageArray = { R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5};
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
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        btnmghB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "MBH B", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MegaBoysB_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnmghA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "MBH A", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MegaBoysA_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnmghF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "MBH F", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MegaBoysF_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnBoysH4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "H4 BOYS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), BoysH4Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnBoysH5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "H5 BOYS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), BoysH5Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnBoysH6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "H6 BOYS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), BoysH6Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnBoysH7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "H7 BOYS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), BoysH7Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnGirlsMega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "MEGA GIRLS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MegaGirlsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnGirlsH1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "H1 GIRLS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), GirlsH1Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnGirlsH2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "H2 GIRLS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), GirlsH2Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        return view;
    }

}