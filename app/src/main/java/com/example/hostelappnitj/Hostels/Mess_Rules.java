package com.example.hostelappnitj.Hostels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hostelappnitj.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Mess_Rules extends AppCompatActivity {
    ImageButton backbutton;
    TextView textView4 , textView5;
    CircleImageView imageView8, imageView9 ;
    Animation topAnimantion,bottomAnimation,middleAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_mess_rules);
        backbutton=findViewById(R.id.backButton);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);

        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        middleAnimation= AnimationUtils.loadAnimation(this, R.anim.middle_anim);
        topAnimantion=AnimationUtils.loadAnimation(this,R.anim.right_to_left_anim);


        imageView8.setAnimation(topAnimantion);
        textView4.setAnimation(middleAnimation);


        imageView9.setAnimation(topAnimantion);
        textView5.setAnimation(middleAnimation);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}