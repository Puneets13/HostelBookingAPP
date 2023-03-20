package com.example.hostelappnitj.MGH_Girls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hostelappnitj.Acitvity.SeatmatrixMBHBoys;
import com.example.hostelappnitj.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MegaGirlsActivity extends AppCompatActivity {

    CardView hostelRegisteration;
    private CharSequence[] hostelFloors ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mega_girls);
        hostelRegisteration=findViewById(R.id.hostelRegisteration);

        hostelFloors = new CharSequence[]{
                "GROUND FLOOR","FLOOR 1", "FLOOR 2","FLOOR 3","FLOOR 4","FLOOR 5","FLOOR 6"
        };


        hostelRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                dialog box to select floors
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MegaGirlsActivity.this);
                builder.setTitle("Select Floor");
                builder.setIcon(R.drawable.ic_registration);
                builder.setSingleChoiceItems(hostelFloors, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 : // Ground floor
                                Intent intent0 = new Intent(MegaGirlsActivity.this, mgh_seatmatrix_ground.class);
                                intent0.putExtra("hostelName","Mega Girls Hostel0");
                                startActivity(intent0);
                                break;
                            case 1 : //floor 1
                                Intent intent = new Intent(MegaGirlsActivity.this, mgh_seatmatrix_floor1.class);
                                intent.putExtra("hostelName","Mega Girls Hostel1");
                                startActivity(intent);
                                break;
                            case 2 : //floor 2
                                Intent intent2 = new Intent(MegaGirlsActivity.this,mgh_seatmatrix_floor2.class);
                                intent2.putExtra("hostelName","Mega Girls Hostel2");
                                startActivity(intent2);
                                break;
                            case 3 : //floor 3
                                Intent intent3 = new Intent(MegaGirlsActivity.this, mgh_seatmatrix_floor3.class);
                                intent3.putExtra("hostelName","Mega Girls Hostel3");
                                startActivity(intent3);
                                break;
                            case 4 : //floor 1
                                Intent intent4 = new Intent(MegaGirlsActivity.this, mghSeatmarixFloor4.class);
                                intent4.putExtra("hostelName","Mega Girls Hostel");
                                startActivity(intent4);
                                break;
                            case 5 : //floor 2
                                Intent intent5 = new Intent(MegaGirlsActivity.this, MghSeatmatrixFloor5.class);
                                intent5.putExtra("hostelName","Mega Girls Hostel");
                                startActivity(intent5);
                                break;
                            case 6 : //floor 3
                                Intent intent6 = new Intent(MegaGirlsActivity.this, MghSeatMatrixFloor6.class);
                                intent6.putExtra("hostelName","Mega Girls Hostel");
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