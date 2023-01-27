package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaRouter2;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.alexvasilkov.gestures.Settings;
import com.alexvasilkov.gestures.views.interfaces.GestureView;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.SharedPrefManager;

public class SeatmatrixMBHBoys extends AppCompatActivity {
AppCompatButton room301;
AppCompatButton btnBook3;
    GestureView gestureView;
    SharedPrefManager sharedPrefManager ;
    String hostelName , username , rollNumber,email, branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seatmatrix_mbhboys);
        room301=findViewById(R.id.room301);
        btnBook3=findViewById(R.id.btnRoomBook3);


        sharedPrefManager=new SharedPrefManager(SeatmatrixMBHBoys.this);
        username=sharedPrefManager.getUser().getUsername();
        email=sharedPrefManager.getUser().getEmail();
        rollNumber=sharedPrefManager.getUser().getRollNumber();
        branch=sharedPrefManager.getUser().getBranch();

//        intent from MegaBoysB_activity
        Intent intent = getIntent();
        hostelName = intent.getStringExtra("hostelName");
        room301.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeatmatrixMBHBoys.this, "Room 301", Toast.LENGTH_SHORT).show();
            }
        });

        gestureView=findViewById(R.id.gestureview);
        gestureView.getController().getSettings()
                .setMaxZoom(2f)
                .setDoubleTapZoom(-1f) // Falls back to max zoom level
                .setPanEnabled(true)
                .setZoomEnabled(true)
                .setDoubleTapEnabled(true)
                .setRotationEnabled(true)
                .setRestrictRotation(false)
                .setOverscrollDistance(0f, 0f)
                .setOverzoomFactor(2f)
                .setFillViewport(false)
                .setFitMethod(Settings.Fit.INSIDE)
                .setGravity(Gravity.CENTER);


        btnBook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeatmatrixMBHBoys.this, "Register here", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeatmatrixMBHBoys.this, RegisterationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("hostelName",hostelName);
                intent.putExtra("username",username);
                intent.putExtra("rollNumber",rollNumber);
                intent.putExtra("email",email);
                intent.putExtra("branch",branch);
                startActivity(intent);
            }
        });



    }

}