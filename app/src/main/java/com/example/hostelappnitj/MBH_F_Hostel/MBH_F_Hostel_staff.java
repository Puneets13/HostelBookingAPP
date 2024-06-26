package com.example.hostelappnitj.MBH_F_Hostel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.hostelappnitj.MBH_A_Hostel.MBH_A_Hostel_staff;
import com.example.hostelappnitj.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MBH_F_Hostel_staff extends AppCompatActivity {
    FloatingActionButton call1;
    FloatingActionButton call2;
    FloatingActionButton call3;
    FloatingActionButton call4;
    FloatingActionButton call5;
    ImageButton backbutton;
    private DialogInterface.OnClickListener dialogClickListener;
    private static final int REQUEST_PHONE_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_mbh_fhostel_staff);
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
                                callIntent.setData(Uri.parse("tel:"+"9779945241"));//change the number
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MBH_F_Hostel_staff.this);
                // on below line we are setting message for our dialog box.
                builder.setTitle("CALL TO STAFF");
                builder.setMessage("Are you sure you want to make a call : 9779945241 ?")
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
                                callIntent.setData(Uri.parse("tel:"+"7888952464"));//change the number
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MBH_F_Hostel_staff.this);
                // on below line we are setting message for our dialog box.
                builder.setTitle("CALL TO STAFF");
                builder.setMessage("Are you sure you want to make a call : 7888952464 ?")
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
                                callIntent.setData(Uri.parse("tel:"+"9113707652"));//change the number
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MBH_F_Hostel_staff.this);
                // on below line we are setting message for our dialog box.
                builder.setTitle("CALL TO STAFF");
                builder.setMessage("Are you sure you want to make a call : 9113707652 ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();
                ///////

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
                                callIntent.setData(Uri.parse("tel:"+"9779980361"));//change the number
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MBH_F_Hostel_staff.this);
                // on below line we are setting message for our dialog box.
                builder.setTitle("CALL TO STAFF");
                builder.setMessage("Are you sure you want to make a call :9779980361 ?")
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
                                callIntent.setData(Uri.parse("tel:"+"9501811427"));//change the number
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MBH_F_Hostel_staff.this);
                // on below line we are setting message for our dialog box.
                builder.setTitle("CALL TO STAFF");
                builder.setMessage("Are you sure you want to make a call :9501811427 ?")
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