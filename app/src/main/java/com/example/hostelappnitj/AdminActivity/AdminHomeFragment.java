package com.example.hostelappnitj.AdminActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hostelappnitj.Hostels.Hostel_Rules_Activity;
import com.example.hostelappnitj.Hostels.Mess_Rules;
import com.example.hostelappnitj.MBH_A_Hostel.MegaBoysA_Activity;
import com.example.hostelappnitj.MBH_B_Hostel.MegaBoysB_Activity;
import com.example.hostelappnitj.MBH_F_Hostel.MegaBoysF_Activity;
import com.example.hostelappnitj.MGH_Girls.MegaGirlsActivity;
import com.example.hostelappnitj.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AdminHomeFragment extends Fragment {

    ImageView imageViewHostels ;
    AppCompatButton btnmghB,btnmghA,btnmghF,btnGirlsMega , btnhostelPolicy, btnMesslRule,btnGetStudentList,btnStudentList ;
    AppCompatButton btnBh7 , btnBh6 , btnBh4 , btnBh3 ;
    ExtendedFloatingActionButton floatingActionButton_call;
    EditText etStudentName ;
    private DialogInterface.OnClickListener dialogClickListener;
    private static final int REQUEST_PHONE_CALL = 1;

    public AdminHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        imageViewHostels=view.findViewById(R.id.imageView_hostels);
        btnmghB=view.findViewById(R.id.btnMghB);
        btnmghA=view.findViewById(R.id.btnMghA);
        btnmghF=view.findViewById(R.id.btnMghF);
        btnBh3=view.findViewById(R.id.btnBh3);
        btnBh4=view.findViewById(R.id.btnBh4);
        btnBh6=view.findViewById(R.id.btnBh6);
        btnBh7=view.findViewById(R.id.btnBh7);

//        btnhostelPolicy=view.findViewById(R.id.HostelPolicy);
//        btnMesslRule=view.findViewById(R.id.Mess_rule);
        btnGirlsMega=view.findViewById(R.id.btnMegaGirls);
        btnStudentList=view.findViewById(R.id.btnStudentList);
        etStudentName=view.findViewById(R.id.etStudentName);
        floatingActionButton_call=view.findViewById(R.id.floatingActionButton_Call);


//        for showing the images autoMatically
//        int[] imageArray = { R.drawable.img_2, R.drawable.img_4, R.drawable.img_5};
        int[] imageArray = { R.drawable.img_h1,R.drawable.img_h3,R.drawable.img_h4, R.drawable.img_h5,R.drawable.img_h6,R.drawable.img_h7 };

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                imageViewHostels.setImageResource(imageArray[i]);
                i++;
                if (i > imageArray.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);


////
//        btnGetStudentList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Student List....", Toast.LENGTH_SHORT).show();
//            }
//        });


            btnStudentList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String studentName1 = etStudentName.getText().toString();
                    String studentName2 = studentName1.toUpperCase();
                    String studentName =  studentName2.trim(); //to reomve extra space at front and back if present

                    if(studentName.isEmpty()){
                       etStudentName.requestFocus();
                       etStudentName.setError("Please enter Name");
                       return;
                   }else{
                       Intent intent = new Intent(getActivity(),StudentList_Admin_HomeActivity.class);
                       intent.putExtra("studentName",studentName);
                       etStudentName.setText("");
                       startActivity(intent);
                   }
                }
            });


        floatingActionButton_call.setOnClickListener(new View.OnClickListener() {
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
                                callIntent.setData(Uri.parse("tel:"+"8360699022"));//change the number
                                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                                }
                                else
                                {
                                    startActivity(callIntent);
                                }

                                break;
                            // on below line we are setting click listener
                            // for our negative button.
                            case DialogInterface.BUTTON_NEGATIVE:
                                // on below line we are dismissing our dialog box.
                                dialog.dismiss();
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // on below line we are setting message for our dialog box.
                builder.setTitle("EMERGENCY CALL");
                builder.setMessage("Make a call to Main Gate Security Guard ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();

            }
        });

        btnmghB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "MBH B", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MBHB_AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnmghA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "MBH A", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MBHA_AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnmghF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "MBH F", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MBHF_AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnBh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "BH 3", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Bh3_AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnBh4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "BH 4", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Bh4_AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnBh6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "BH 6", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Bh6_AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnBh7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "BH 7", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Bh7_AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        btnGirlsMega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "MEGA GIRLS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MGH_AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        return view;
    }
}