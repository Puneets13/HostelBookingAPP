package com.example.hostelappnitj.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hostelappnitj.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class HelpFragment extends Fragment {

ExtendedFloatingActionButton btnKiran , btnPuneet;
    public HelpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_help, container, false);
       btnKiran = view.findViewById(R.id.emailKiran);
       btnPuneet=view.findViewById(R.id.emailPuneet);

       btnKiran.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent email = new Intent(Intent.ACTION_SEND);
               email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "heykiranthakur@gmail.com"});
               email.putExtra(Intent.EXTRA_SUBJECT, "Email support for your query");
//need this to prompts email client only
               email.setType("message/rfc822");

               startActivity(Intent.createChooser(email, "Choose an Email client :"));
           }
       });

        btnPuneet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "sidhupuneet115@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Email support for your query");
//need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
       return view;
    }
}