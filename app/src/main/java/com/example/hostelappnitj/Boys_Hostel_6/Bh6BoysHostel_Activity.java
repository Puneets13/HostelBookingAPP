package com.example.hostelappnitj.Boys_Hostel_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.hostelappnitj.Boys_Hostel_7.Bh7BoysHostel_Activity;
import com.example.hostelappnitj.Boys_Hostel_7.Bh_7_Floor_2;
import com.example.hostelappnitj.Hostels.ExpulsionFromHostelRules;
import com.example.hostelappnitj.Hostels.Hostel_Rules_Activity;
import com.example.hostelappnitj.Hostels.Mess_Rules;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_1_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_3_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_Ground_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.MBH_Hostel_Staff;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Bh6BoysHostel_Activity extends AppCompatActivity {
    CardView hostelRegisteration,hostelRules,messRules,expulsionFromHostel,hostelStaff;
    SharedPrefManager sharedPrefManager;
    ImageButton backbutton;
    private CharSequence[] hostelFloors ;
    String genderRestriction;
    private DialogInterface.OnClickListener dialogClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_bh6_boys_hostel);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        hostelRegisteration=findViewById(R.id.hostelRegisteration);
        hostelRules=findViewById(R.id.hostelrules);
        messRules=findViewById(R.id.messRules);
        expulsionFromHostel=findViewById(R.id.expulsionFromHostel);
        hostelStaff=findViewById(R.id.hostelStaff);
        backbutton=findViewById(R.id.backButton);

        sharedPrefManager=new SharedPrefManager(Bh6BoysHostel_Activity.this);
        genderRestriction=sharedPrefManager.getGender();

        hostelFloors = new CharSequence[]{
                "GROUND FLOOR","FLOOR 1", "FLOOR 2","FLOOR 3"
        };


        hostelStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bh6BoysHostel_Activity.this, MBH_Hostel_Staff.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        expulsionFromHostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bh6BoysHostel_Activity.this, ExpulsionFromHostelRules.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        messRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bh6BoysHostel_Activity.this, Mess_Rules.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        hostelRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bh6BoysHostel_Activity.this, Hostel_Rules_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        hostelRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (genderRestriction.equals("female")) {

                    dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                // on below line we are setting a click listener
                                // for our positive button
                                case DialogInterface.BUTTON_POSITIVE:
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(Bh6BoysHostel_Activity.this);
                    // on below line we are setting message for our dialog box.
                    builder.setTitle("ACCESS DENIED");
                    builder.setMessage("Sorry\nYou can't access this")
                            .setPositiveButton("Okay", dialogClickListener)
                            .show();

                } else {

//                dialog box to select floors
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Bh6BoysHostel_Activity.this);
                    builder.setTitle("Select Floor");
                    builder.setIcon(R.drawable.ic_registration);
                    builder.setSingleChoiceItems(hostelFloors, 0, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0: // Ground floor
                                    Intent intent0 = new Intent(Bh6BoysHostel_Activity.this, Bh_6_Floor_Ground.class);
                                    intent0.putExtra("hostelName", "Boys Hostel 6");
                                    startActivity(intent0);
                                    break;
                                case 1: //floor 1
                                    Intent intent = new Intent(Bh6BoysHostel_Activity.this, Bh_6_Floor_1.class);
                                    intent.putExtra("hostelName", "Boys Hostel 6");
                                    startActivity(intent);
                                    break;
                                case 2: //floor 2
                                    Intent intent2 = new Intent(Bh6BoysHostel_Activity.this, Bh_6_Floor_2.class);
                                    intent2.putExtra("hostelName", "Boys Hostel 6");
                                    startActivity(intent2);
                                    break;
                                case 3: //floor 3
                                    Intent intent3 = new Intent(Bh6BoysHostel_Activity.this, Bh_6_Floor_3.class);
                                    intent3.putExtra("hostelName", "Boys Hostel 6");
                                    startActivity(intent3);
                                    break;
                            }
                        }
                    });
                    builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog, null));
                    builder.show();
                }
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