package com.example.hostelappnitj.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hostelappnitj.Hostels.Hostel_Rules_Activity;
import com.example.hostelappnitj.Hostels.Mess_Rules;
import com.example.hostelappnitj.MBH_A_Hostel.MegaBoysA_Activity;
import com.example.hostelappnitj.MBH_B_Hostel.MegaBoysB_Activity;
import com.example.hostelappnitj.MBH_F_Hostel.MegaBoysF_Activity;
import com.example.hostelappnitj.MGH_Girls.MegaGirlsActivity;
import com.example.hostelappnitj.MainActivity;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
//import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
//import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class homeFragment extends Fragment {
    ImageView imageViewHostels ;
    AppCompatButton btnmghB,btnmghA,btnmghF,btnBoysH4,btnBoysH5,btnBoysH6,btnBoysH7,btnGirlsMega , btnhostelPolicy, btnMesslRule ;
   ExtendedFloatingActionButton floatingActionButton_call;
    private DialogInterface.OnClickListener dialogClickListener;
    private static final int REQUEST_PHONE_CALL = 1;
    SharedPrefManager sharedPrefManager;
    String genderRestriction;
    CoordinatorLayout coordinatorLayout;
    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        to lock the orientations of the screen
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imageViewHostels=view.findViewById(R.id.imageView_hostels);
        btnmghB=view.findViewById(R.id.btnMghB);
        btnmghA=view.findViewById(R.id.btnMghA);
        btnmghF=view.findViewById(R.id.btnMghF);
        btnhostelPolicy=view.findViewById(R.id.HostelPolicy);
        btnMesslRule=view.findViewById(R.id.Mess_rule);
        btnGirlsMega=view.findViewById(R.id.btnMegaGirls);
        floatingActionButton_call=view.findViewById(R.id.floatingActionButton_Call);
        coordinatorLayout=view.findViewById(R.id.homeFragmentLayout);


        sharedPrefManager=new SharedPrefManager(getActivity());
        genderRestriction=sharedPrefManager.getGender();

//        for showing the images autoMatically
//        int[] imageArray = { R.drawable.img_2, R.drawable.ic_9,R.drawable.ic_6,R.drawable.img_4, R.drawable.img_5};
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


        btnhostelPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Hostel_Rules_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        btnMesslRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Mess_Rules.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        floatingActionButton_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
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
//
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

                if( !isNetworkAvailable()){
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // on below line we are setting message for our dialog box.
                    builder.setTitle("NETWORK REQUIRED");
                    builder.setMessage("Make sure you have an active Internet connection..")
                            .setPositiveButton("Okay", dialogClickListener)
                            .show();

                }
                else{
                    Toast.makeText(getActivity(), "MBH B", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MegaBoysB_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });
        btnmghA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !isNetworkAvailable()){
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // on below line we are setting message for our dialog box.
                    builder.setTitle("NETWORK REQUIRED");
                    builder.setMessage("Make sure you have an active Internet connection")
                            .setPositiveButton("Okay", dialogClickListener)
                            .show();

                }
                else {
                    Toast.makeText(getActivity(), "MBH A", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MegaBoysA_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
        btnmghF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !isNetworkAvailable()){
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // on below line we are setting message for our dialog box.
                    builder.setTitle("NETWORK REQUIRED");
                    builder.setMessage("Make sure you have an active Internet connection")
                            .setPositiveButton("Okay", dialogClickListener)
                            .show();

                }
                else {
                    Toast.makeText(getActivity(), "MBH F", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MegaBoysF_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

        btnGirlsMega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !isNetworkAvailable()){
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // on below line we are setting message for our dialog box.
                    builder.setTitle("NETWORK REQUIRED");
                    builder.setMessage("Make sure you have active Internet connection")
                            .setPositiveButton("Okay", dialogClickListener)
                            .show();

                }
                else {
                    Toast.makeText(getActivity(), "MEGA GIRLS", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MegaGirlsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });


        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}

