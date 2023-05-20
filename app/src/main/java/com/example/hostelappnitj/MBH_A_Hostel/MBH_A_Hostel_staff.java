package com.example.hostelappnitj.MBH_A_Hostel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MBH_A_Hostel_staff extends AppCompatActivity {
TextView textView1 ;
public Drawable drawableRight;
private DialogInterface.OnClickListener dialogClickListener;
    private static final int REQUEST_PHONE_CALL = 1;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_mbh_ahostel_staff);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView1 = findViewById(R.id.textView1);

        textView1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (textView1.getRight() - textView1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        ////

                        if (ContextCompat.checkSelfPermission(MBH_A_Hostel_staff.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MBH_A_Hostel_staff.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                        }
                        dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    // on below line we are setting a click listener
                                    // for our positive button
                                    case DialogInterface.BUTTON_POSITIVE:
//                        Make the Call Action
//ask for runtime permisson
                                        Intent callIntent=new Intent(Intent.ACTION_CALL);
                                        callIntent.setData(Uri.parse("tel:"+"6283021307"));//change the number
                                     startActivity(callIntent);

                                        break;
                                    // on below line we are setting click listener
                                    // for our negative button.
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        // on below line we are dismissing our dialog box.
                                        dialog.dismiss();
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(MBH_A_Hostel_staff.this);
                        // on below line we are setting message for our dialog box.
                        builder.setTitle("EMERGENCY CALL");
                        builder.setMessage("Make a call to the Clerk?")
                                .setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener)
                                .show();
                        ///////
                        return true;
                    }
                }

                return false;
            }
        });


    }
}