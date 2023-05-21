package com.example.hostelappnitj.Hostels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.example.hostelappnitj.R;

public class ExpulsionFromHostelRules extends AppCompatActivity {
    ImageButton backbutton;
    ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_expulsion_from_hostel_rules);
        backbutton=findViewById(R.id.backButton);

        scrollView=findViewById(R.id.scrollanim);


        scrollView.setVerticalFadingEdgeEnabled(true);
        scrollView.setFadingEdgeLength(100);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}