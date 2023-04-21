package com.example.hostelappnitj.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hostelappnitj.MBH_A_Hostel.Floor_1_SeatMatrix_A;
import com.example.hostelappnitj.MBH_A_Hostel.Floor_2_SeatMatrix_A;
import com.example.hostelappnitj.MBH_A_Hostel.Floor_3_SeatMatrix_A;
import com.example.hostelappnitj.MBH_A_Hostel.Floor_4_SeatMatrix_A;
import com.example.hostelappnitj.MBH_A_Hostel.Floor_5_SeatMatrix_A;
import com.example.hostelappnitj.MBH_A_Hostel.Floor_6_SeatMatrix_A;
import com.example.hostelappnitj.MBH_A_Hostel.Floor_Ground_SeatMatrix_A;
import com.example.hostelappnitj.MBH_A_Hostel.MegaBoysA_Activity;
import com.example.hostelappnitj.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MBHA_AdminActivity extends AppCompatActivity {
EditText etRoomNumber , etStudentName;
AppCompatButton btnProccedRollNumber,btnProccedStudentName;
CardView hostelList , hostelPlan;
    private CharSequence[] hostelFloors ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbha_admin);
        etRoomNumber = findViewById(R.id.editRoom);
        etStudentName = findViewById(R.id.etStudentName);
        btnProccedRollNumber=findViewById(R.id.btnproceedRoom);
        btnProccedStudentName=findViewById(R.id.btnproceedStudent);
        hostelList=findViewById(R.id.hostelList);
        hostelPlan=findViewById(R.id.HostelRoomPlan);


        hostelFloors = new CharSequence[]{
                "GROUND FLOOR","FLOOR 1", "FLOOR 2","FLOOR 3","FLOOR 4","FLOOR 5","FLOOR 6"
        };


        hostelPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                dialog box to select floors
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MBHA_AdminActivity.this);
                builder.setTitle("Select Floor");
                builder.setIcon(R.drawable.ic_registration);
                builder.setSingleChoiceItems(hostelFloors, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 : // Ground floor
                                Intent intent0 = new Intent(MBHA_AdminActivity.this, Floor_Ground_SeatMatrix_A.class);
                                intent0.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent0);
                                break;
                            case 1 : //floor 1
                                Intent intent = new Intent(MBHA_AdminActivity.this, Floor_1_SeatMatrix_A.class);
                                intent.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent);
                                break;
                            case 2 : //floor 2
                                Intent intent2 = new Intent(MBHA_AdminActivity.this, Floor_2_SeatMatrix_A.class);
                                intent2.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent2);
                                break;
                            case 3 : //floor 3
                                Intent intent3 = new Intent(MBHA_AdminActivity.this, Floor_3_SeatMatrix_A.class);
                                intent3.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent3);
                                break;
                            case 4 : //floor 1
                                Intent intent4 = new Intent(MBHA_AdminActivity.this, Floor_4_SeatMatrix_A.class);
                                intent4.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent4);
                                break;
                            case 5 : //floor 2
                                Intent intent5 = new Intent(MBHA_AdminActivity.this, Floor_5_SeatMatrix_A.class);
                                intent5.putExtra("hostelName","Mega Boys Hostel B");
                                startActivity(intent5);
                                break;
                            case 6 : //floor 3
                                Intent intent6 = new Intent(MBHA_AdminActivity.this, Floor_6_SeatMatrix_A.class);
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