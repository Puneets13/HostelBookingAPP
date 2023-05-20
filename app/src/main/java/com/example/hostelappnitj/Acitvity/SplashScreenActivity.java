package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hostelappnitj.R;
import com.google.android.material.bottomappbar.BottomAppBar;

public class SplashScreenActivity extends AppCompatActivity {
TextView textViewtop , textViewBtm;
    Animation topAnimantion,bottomAnimation,middleAnimation;
    ImageView imagelogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_splash_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textViewtop=findViewById(R.id.textViewtop);
        textViewBtm=findViewById(R.id.textView9);
        imagelogo=findViewById(R.id.imageLogo);

        topAnimantion = AnimationUtils.loadAnimation(this, R.anim.side_slide);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation= AnimationUtils.loadAnimation(this, R.anim.middle_anim);


        textViewtop.setAnimation(topAnimantion);
        textViewBtm.setAnimation(bottomAnimation);
        imagelogo.setAnimation(middleAnimation);
        Handler handler = new Handler();
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                startActivity( new Intent(SplashScreenActivity.this, SignInActivity.class));
                finish();
            }
        };
     handler.postDelayed(runnable,3000);
    }
}