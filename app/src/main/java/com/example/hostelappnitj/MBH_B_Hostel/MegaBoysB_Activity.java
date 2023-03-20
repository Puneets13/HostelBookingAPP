package com.example.hostelappnitj.MBH_B_Hostel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hostelappnitj.Acitvity.SeatmatrixMBHBoys;
import com.example.hostelappnitj.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MegaBoysB_Activity extends AppCompatActivity {
    CardView hostelRegisteration;

     private CharSequence[] hostelFloors ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbhb_activity);
        hostelRegisteration=findViewById(R.id.hostelRegisteration);


        hostelFloors = new CharSequence[]{
                "GROUND FLOOR","FLOOR 1", "FLOOR 2","FLOOR 3","FLOOR 4","FLOOR 5","FLOOR 6"
        };

        hostelRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                dialog box to select floors
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MegaBoysB_Activity.this);
                builder.setTitle("Select Floor");
                builder.setIcon(R.drawable.ic_registration);
                builder.setSingleChoiceItems(hostelFloors, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 : // Ground floor
                                Intent intent0 = new Intent(MegaBoysB_Activity.this, Floor_Ground_SeatMatrix.class);
                                intent0.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent0);
                                break;
                            case 1 : //floor 1
                                Intent intent = new Intent(MegaBoysB_Activity.this, Floor_1_SeatMatrix.class);
                                intent.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent);
                                break;
                            case 2 : //floor 2
                                Intent intent2 = new Intent(MegaBoysB_Activity.this, Floor_2_SeatMatrix.class);
                                intent2.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent2);
                                break;
                            case 3 : //floor 3
                                Intent intent3 = new Intent(MegaBoysB_Activity.this, Floor_3_SeatMatrix.class);
                                intent3.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent3);
                                break;
                            case 4 : //floor 1
                                Intent intent4 = new Intent(MegaBoysB_Activity.this, Floor_4_SeatMatrix.class);
                                intent4.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent4);
                                break;
                            case 5 : //floor 2
                                Intent intent5 = new Intent(MegaBoysB_Activity.this, Floor_5_SeatMatrix.class);
                                intent5.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent5);
                                break;
                            case 6 : //floor 3
                                Intent intent6 = new Intent(MegaBoysB_Activity.this,Floor_6_SeatMatrix.class);
                                intent6.putExtra("hostelName","Mega Boys Hostel B");
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