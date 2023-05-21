package com.example.hostelappnitj.Hostels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.example.hostelappnitj.R;

public class Hostel_Rules_Activity extends AppCompatActivity {
    ImageButton backbutton;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_hostel_rules);
        backbutton=findViewById(R.id.backButton);
        scrollView=findViewById(R.id.scrollanim);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        scrollView.setVerticalFadingEdgeEnabled(true);
        scrollView.setFadingEdgeLength(100);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}