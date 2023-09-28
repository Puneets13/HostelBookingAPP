package com.example.hostelappnitj.Girls_Hostel_B;

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

import com.example.hostelappnitj.Girls_Hostel_A.GirlsHostel_mgh_A_Activity;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_1;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_10;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_11;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_2;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_3;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_4;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_5;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_6;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_7;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_8;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_9;
import com.example.hostelappnitj.Girls_Hostel_A.mgh_A_floor_Ground;
import com.example.hostelappnitj.Hostels.ExpulsionFromHostelRules;
import com.example.hostelappnitj.Hostels.Hostel_Rules_Activity;
import com.example.hostelappnitj.Hostels.Mess_Rules;
import com.example.hostelappnitj.MGH_Girls.Mega_girls_hostelStaff;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class GirlsHostel_mgh_B_Activity extends AppCompatActivity {

    CardView hostelRegisteration;
    CardView hostelStaff;
    CardView hostelRules;
    CardView messRules;
    CardView expulsionRules;
    ImageButton backbutton;
    SharedPrefManager sharedPrefManager;
    private CharSequence[] hostelFloors ;
    String genderRestriction;
    private DialogInterface.OnClickListener dialogClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_girls_hostel_mgh_bactivity);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        hostelRegisteration=findViewById(R.id.hostelRegisteration);
        hostelStaff=findViewById(R.id.hostelStaff);
        hostelRules=findViewById(R.id.hostelrules);
        messRules=findViewById(R.id.messRules);
        expulsionRules=findViewById(R.id.expulsionFromHostel);
        backbutton=findViewById(R.id.backButton);


        sharedPrefManager=new SharedPrefManager(GirlsHostel_mgh_B_Activity.this);
        genderRestriction=sharedPrefManager.getGender();

        hostelFloors = new CharSequence[]{
                "GROUND FLOOR","FLOOR 1", "FLOOR 2","FLOOR 3","FLOOR 4","FLOOR 5","FLOOR 6","FLOOR 7","FLOOR 8","FLOOR 9","FLOOR 10","FLOOR 11"
        };
        hostelRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GirlsHostel_mgh_B_Activity.this, Hostel_Rules_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        messRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GirlsHostel_mgh_B_Activity.this, Mess_Rules.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        hostelStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GirlsHostel_mgh_B_Activity.this, Mega_girls_hostelStaff.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        expulsionRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GirlsHostel_mgh_B_Activity.this, ExpulsionFromHostelRules.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        hostelRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (genderRestriction.equals("male")) {

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

                    AlertDialog.Builder builder = new AlertDialog.Builder(GirlsHostel_mgh_B_Activity.this);
                    // on below line we are setting message for our dialog box.
                    builder.setTitle("ACCESS DENIED");
                    builder.setMessage("Sorry\nYou can't access this")
                            .setPositiveButton("Okay", dialogClickListener)
                            .show();

                }
                else{

//                dialog box to select floors
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GirlsHostel_mgh_B_Activity.this);
                    builder.setTitle("Select Floor");
                    builder.setIcon(R.drawable.ic_registration);
                    builder.setSingleChoiceItems(hostelFloors, 0, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0: // Ground floor
                                    Intent intent0 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_Ground.class);
                                    intent0.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent0);
                                    break;
                                case 1: //floor 1
                                    Intent intent1 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_1.class);
                                    intent1.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent1);
                                    break;
                                case 2: //floor 2
                                    Intent intent2 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_2.class);
                                    intent2.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent2);
                                    break;
                                case 3: //floor 3
                                    Intent intent3 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_3.class);
                                    intent3.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent3);
                                    break;
                                case 4: //floor 1
                                    Intent intent4 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_4.class);
                                    intent4.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent4);
                                    break;
                                case 5: //floor 2
                                    Intent intent5 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_5.class);
                                    intent5.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent5);
                                    break;
                                case 6: //floor 3
                                    Intent intent6 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_6.class);
                                    intent6.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent6);
                                    break;
                                case 7: //floor 3
                                    Intent intent7 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_7.class);
                                    intent7.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent7);
                                    break;
                                case 8: //floor 3
                                    Intent intent8 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_8.class);
                                    intent8.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent8);
                                    break;
                                case 9: //floor 3
                                    Intent intent9 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_9.class);
                                    intent9.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent9);
                                    break;
                                case 10: //floor 3
                                    Intent intent10 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_10.class);
                                    intent10.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent10);
                                    break;
                                case 11: //floor 3
                                    Intent intent11 = new Intent(GirlsHostel_mgh_B_Activity.this, mgh_A_floor_11.class);
                                    intent11.putExtra("hostelName", "Girls Hostel B");
                                    startActivity(intent11);
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