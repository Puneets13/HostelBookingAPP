package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.hostelappnitj.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                startActivity( new Intent(SplashScreenActivity.this, SignInActivity.class));
                finish();
            }
        };
     handler.postDelayed(runnable,2000);
    }
}