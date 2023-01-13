package com.example.hostelappnitj.Hostels;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hostelappnitj.Acitvity.SeatmatrixMBHBoys;
import com.example.hostelappnitj.R;

public class MegaBoysB_Activity extends AppCompatActivity {
    Button roomsMatrix ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbhb_activity);
        roomsMatrix=findViewById(R.id.roomsMBGBoys);
        roomsMatrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MegaBoysB_Activity.this, SeatmatrixMBHBoys.class);
                startActivity(intent);
            }
        });
    }

}