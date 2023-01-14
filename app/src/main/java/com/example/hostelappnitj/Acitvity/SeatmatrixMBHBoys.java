package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hostelappnitj.R;

public class SeatmatrixMBHBoys extends AppCompatActivity {
AppCompatButton room1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seatmatrix_mbhboys);
        room1=findViewById(R.id.room100);
        room1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeatmatrixMBHBoys.this, "Room slant 1", Toast.LENGTH_SHORT).show();
            }
        });
    }
}