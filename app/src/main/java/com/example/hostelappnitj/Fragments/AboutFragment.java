package com.example.hostelappnitj.Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hostelappnitj.R;


public class AboutFragment extends Fragment {
//CardView cardView;
    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_about, container, false);
//        cardView = (CardView) view.findViewById(R.id.cardView3);
//        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
//        int mHeightScreen = displaymetrics.heightPixels;
////        cardView.setMinimumHeight((int)(0.5)*mHeightScreen);
        return view;
    }

}