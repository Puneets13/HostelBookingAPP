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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MBH_A_Hostel_staff extends AppCompatActivity {
    FloatingActionButton call1;
    FloatingActionButton call2;
    FloatingActionButton call3;
    FloatingActionButton call4;
    FloatingActionButton call5;
    ImageButton backbutton;


private DialogInterface.OnClickListener dialogClickListener;
    private static final int REQUEST_PHONE_CALL = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_mbh_ahostel_staff);
        call3= findViewById(R.id.call3);
        call2=findViewById(R.id.call2);
        call4=findViewById(R.id.call4);
        call1=findViewById(R.id.call1);
        call5=findViewById(R.id.call5);
        backbutton=findViewById(R.id.backButton);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

       call1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
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
                               callIntent.setData(Uri.parse("tel:"+"7837797418"));//change the number
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
               builder.setTitle("CALL TO STAFF");
               builder.setMessage("Are you sure you want to make a call : 7837797418?")
                       .setPositiveButton("Yes", dialogClickListener)
                       .setNegativeButton("No", dialogClickListener)
                       .show();
               ///////

           }
       });

        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                callIntent.setData(Uri.parse("tel:"+"7087946940"));//change the number
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
                builder.setTitle("CALL TO STAFF");
                builder.setMessage("Are you sure you want to make a call : 7087946940 ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();
                ///////

            }
        });
        call3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                callIntent.setData(Uri.parse("tel:"+"8437357489"));//change the number
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

                builder.setTitle("CALL TO STAFF");
                builder.setMessage("Are you sure you want to make a call : 8437357489?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();

            }
        });
        call4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                callIntent.setData(Uri.parse("tel:"+"7973257236"));//change the number
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
                builder.setTitle("CALL TO STAFF");
                builder.setMessage("Are you sure you want to make a call : 7973257236 ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();
                ///////

            }
        });
        call5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                callIntent.setData(Uri.parse("tel:"+"9988903662"));//change the number
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
                builder.setTitle("CALL TO STAFF");
                builder.setMessage("Are you sure you want to make a call : 9988903662 ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();
                ///////

            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}