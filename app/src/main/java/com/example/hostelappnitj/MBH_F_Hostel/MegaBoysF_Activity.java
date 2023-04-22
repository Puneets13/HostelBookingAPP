package com.example.hostelappnitj.MBH_F_Hostel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hostelappnitj.Hostels.ExpulsionFromHostelRules;
import com.example.hostelappnitj.Hostels.Hostel_Rules_Activity;
import com.example.hostelappnitj.Hostels.Mess_Rules;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_1_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_2_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_3_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_4_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_5_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_6_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_Ground_SeatMatrix;
import com.example.hostelappnitj.MBH_B_Hostel.MBH_Hostel_Staff;
import com.example.hostelappnitj.MBH_B_Hostel.MegaBoysB_Activity;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MegaBoysF_Activity extends AppCompatActivity {
    CardView hostelRegisteration,hostelRules,messRules,expulsionFromHostel,hostelStaff;
    SharedPrefManager sharedPrefManager;

    private CharSequence[] hostelFloors ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbhf_activity);

        hostelRegisteration=findViewById(R.id.hostelRegisteration);
        hostelRules=findViewById(R.id.hostelrules);
        messRules=findViewById(R.id.messRules);
        expulsionFromHostel=findViewById(R.id.expulsionFromHostel);
        hostelStaff=findViewById(R.id.hostelStaff);
        sharedPrefManager=new SharedPrefManager(MegaBoysF_Activity.this);


        hostelFloors = new CharSequence[]{
                "GROUND FLOOR","FLOOR 1", "FLOOR 2","FLOOR 3","FLOOR 4","FLOOR 5","FLOOR 6"
        };

        hostelStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MegaBoysF_Activity.this, MBH_F_Hostel_staff.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        expulsionFromHostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MegaBoysF_Activity.this, ExpulsionFromHostelRules.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        messRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MegaBoysF_Activity.this, Mess_Rules.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        hostelRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MegaBoysF_Activity.this, Hostel_Rules_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        hostelRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                dialog box to select floors
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MegaBoysF_Activity.this);
                builder.setTitle("Select Floor");
                builder.setIcon(R.drawable.ic_registration);
                builder.setSingleChoiceItems(hostelFloors, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 : // Ground floor
                                Intent intent0 = new Intent(MegaBoysF_Activity.this, mbhfFloorGround.class);
                                intent0.putExtra("hostelName","Mega Boys Hostel F");
                                startActivity(intent0);
                                break;
                            case 1 : //floor 1
                                Intent intent = new Intent(MegaBoysF_Activity.this, mbhfFloor1.class);
                                intent.putExtra("hostelName","Mega Boys Hostel F");
                                startActivity(intent);
                                break;
                            case 2 : //floor 2
                                Intent intent2 = new Intent(MegaBoysF_Activity.this, mbhfFloor2.class);
                                intent2.putExtra("hostelName","Mega Boys Hostel F");
                                startActivity(intent2);
                                break;
                            case 3 : //floor 3
                                Intent intent3 = new Intent(MegaBoysF_Activity.this, mbhfFloor3.class);
                                intent3.putExtra("hostelName","Mega Boys Hostel F");
                                startActivity(intent3);
                                break;
                            case 4 : //floor 1
                                Intent intent4 = new Intent(MegaBoysF_Activity.this, mbhfFloor4.class);
                                intent4.putExtra("hostelName","Mega Boys Hostel F");
                                startActivity(intent4);
                                break;
                            case 5 : //floor 2
                                Intent intent5 = new Intent(MegaBoysF_Activity.this, mbhfFloor5.class);
                                intent5.putExtra("hostelName","Mega Boys Hostel F");
                                startActivity(intent5);
                                break;
                            case 6 : //floor 3
                                Intent intent6 = new Intent(MegaBoysF_Activity.this, mbhfFloor6.class);
                                intent6.putExtra("hostelName","Mega Boys Hostel F");
                                startActivity(intent6);
                                break;
                        }
                    }
                });
                builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog,null));
                builder.show();

            }
        });
    }
}