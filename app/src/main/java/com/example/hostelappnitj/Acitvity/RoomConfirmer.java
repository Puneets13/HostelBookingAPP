package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.AdminActivity.StudentList_Admin_HomeActivity;
import com.example.hostelappnitj.Hostels.Hostel_Rules_Activity;
import com.example.hostelappnitj.MBH_A_Hostel.Floor_6_SeatMatrix_A;
import com.example.hostelappnitj.ModelResponse.PreRegisterResponse;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomConfirmer extends AppCompatActivity {
EditText etroomNumber;
AppCompatButton proceed;
TextView txtHostelPolicy;
    String hostelName, username, rollNumber, email, branch,roomNumber,floor ;
    String allow="0";
    Switch switchAgree ;
    ProgressDialog progressDialog;
    private DialogInterface.OnClickListener dialogClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_room_confirmer);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        etroomNumber = findViewById(R.id.editRoom);
        proceed= findViewById(R.id.proceed);
    switchAgree = findViewById(R.id.switchAgree);
    txtHostelPolicy=findViewById(R.id.txthostel_policy);
        Intent intent = getIntent();
        hostelName = intent.getStringExtra("hostelName");
        username = intent.getStringExtra("username");
        rollNumber = intent.getStringExtra("rollNumber");
        email = intent.getStringExtra("email");
        branch = intent.getStringExtra("branch");

        floor = intent.getStringExtra("floor");  // intent from differet activity floor 1 , floor 2 like that


//here we need to add proced

//to handle the switch listener
        switchAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    allow="1";

                } else {
                    // The toggle is disabled
                    allow="0";
                }
            }
        });
        txtHostelPolicy.setPaintFlags(txtHostelPolicy.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txtHostelPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(RoomConfirmer.this, Hostel_Rules_Activity.class);
                startActivity(intent1);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                    if switch is true then only check other things
                roomNumber = etroomNumber.getText().toString();
                if(roomNumber.isEmpty()){
                    etroomNumber.setError("PLease enter your Room Number");
                    etroomNumber.requestFocus();
                    return;
                }
                int room_num_size=roomNumber.length();
                String room_frontstring=roomNumber.substring(0,1);

                int room_frontint=Integer.parseInt(room_frontstring);
                String room_substring = roomNumber.substring(Math.max(roomNumber.length() - 2, 0));
                int room_int = Integer.parseInt(room_substring);

                String room_substring1 = roomNumber.substring(Math.max(roomNumber.length() - 3, 0));
                if (!floor.equals(room_frontstring)) {
                    etroomNumber.requestFocus();
                    etroomNumber.setError("Enter number according floor");
                    return;
                }
                if (etroomNumber.getText().toString().isEmpty()) {
                    etroomNumber.requestFocus();
                    etroomNumber.setError("Please Enter Room Number");
                    return;
                }
//                set room number according to the hostels
               if ( hostelName.equals("Boys Hostel 7") || hostelName.equals("Boys Hostel 6") || hostelName.equals("Boys Hostel 3") || hostelName.equals("Boys Hostel 4") ){
                   if (room_int > 52 || room_int < 1||room_frontint<1||room_frontint>4||!(room_num_size==3)) {
                       etroomNumber.requestFocus();
                       etroomNumber.setError("Room number Out of box");
                       return;
                   }
                }
               else if(hostelName.equals("Boys Hostel 7E") && floor.equals("1")  ){
                   if (room_int > 31 || room_int < 1||room_frontint<1||room_frontint>4||!(room_num_size==3)) {
                       etroomNumber.requestFocus();
                       etroomNumber.setError("Room number Out of box");
                       return;
                   }
               }
               else if(hostelName.equals("Boys Hostel 7E") && !floor.equals("1")  ){
                   if (room_int > 34 || room_int < 1||room_frontint<1||room_frontint>4||!(room_num_size==3)) {
                       etroomNumber.requestFocus();
                       etroomNumber.setError("Room number Out of box");
                       return;
                   }
               }
               else if(hostelName.equals("Girls Hostel A") ){
                   if (room_int > 19 || room_int < 1||room_frontint<1||room_frontint>4||!(room_num_size==3)) {
                       etroomNumber.requestFocus();
                       etroomNumber.setError("Room number Out of box");
                       return;
                   }
               }
               else{
//                   FOR MEGA BOYS HOSTEL
                   if (room_int > 46 || room_int < 1||room_frontint<1||room_frontint>7||!(room_num_size==3)) {
                       etroomNumber.requestFocus();
                       etroomNumber.setError("Room number Out of box");
                       return;
                   }
               }

                if (allow.equals("0")){

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

                    AlertDialog.Builder builder = new AlertDialog.Builder(RoomConfirmer.this);
                    // on below line we are setting message for our dialog box.
                    builder.setMessage("Please agree with Hostel Policies..")
                            .setTitle("NITJ HOSTELS")
                            .setPositiveButton("OK", dialogClickListener)
                            .show();



                return ;
                }
                else if( hostelName.equals("Boys Hostel 7") || hostelName.equals("Boys Hostel 6") || hostelName.equals("Boys Hostel 3") || hostelName.equals("Boys Hostel 4") || hostelName.equals("Boys Hostel 7E")){


                        progressDialog = new ProgressDialog(RoomConfirmer.this);
                        progressDialog.setTitle("Hostel Booking");
                        progressDialog.setMessage("Opening Registration Form..");
                        progressDialog.show();
                        progressDialog.setCancelable(false);

                        PreRegisterResponse preRegisterResponse = new PreRegisterResponse(roomNumber, rollNumber, email, hostelName);
                        Call<PreRegisterResponse> call = RetrofitClient.getInstance().getApi().PreRegisterResponse_single(preRegisterResponse);
                        call.enqueue(new Callback<PreRegisterResponse>() {
                            @Override
                            public void onResponse(Call<PreRegisterResponse> call, Response<PreRegisterResponse> response) {
                                PreRegisterResponse responseFromAPI = response.body();
                                progressDialog.dismiss();
//                            Toast.makeText(RoomConfirmer.this, "response received", Toast.LENGTH_SHORT).show();
                                if (response.isSuccessful()) {

//                                Toast.makeText(RoomConfirmer.this, "response succesful", Toast.LENGTH_SHORT).show();
                                    if (responseFromAPI.getMessage().equals("go")) {
//                                    show the intent for Register room

                                        Intent intent = new Intent(RoomConfirmer.this, RegisterationActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("hostelName", hostelName);
                                        intent.putExtra("username", username);
                                        intent.putExtra("rollNumber", rollNumber);
                                        intent.putExtra("email", email);
                                        intent.putExtra("branch", branch);
                                        intent.putExtra("roomNum", roomNumber);
                                        startActivity(intent);
                                        finish();
                                    } else if (responseFromAPI.getMessage().equals("fully filled")) {

                                        progressDialog.dismiss();
//                        show the dialog box
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
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RoomConfirmer.this);
                                        // on below line we are setting message for our dialog box.
                                        builder.setMessage("Room Fully Occupied\nTry Another Room Number")
                                                .setTitle("NITJ HOSTELS")
                                                .setPositiveButton("OK", dialogClickListener)
                                                .show();

                                    } else if (responseFromAPI.getMessage().equals("booking in process")) {

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
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RoomConfirmer.this);
                                        // on below line we are setting message for our dialog box.
                                        builder.setMessage("This room is under Booking Process\nTry again Later..")
                                                .setTitle("NITJ HOSTELS")
                                                .setPositiveButton("OK", dialogClickListener)
                                                .show();


                                    } else if (responseFromAPI.getMessage().equals("user already registered")) {


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
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RoomConfirmer.this);
                                        // on below line we are setting message for our dialog box.
                                        builder.setMessage("You Have Already Registered\nOne Person can Book one room only..")
                                                .setTitle("NITJ HOSTELS")
                                                .setPositiveButton("OK", dialogClickListener)
                                                .show();

                                    } else {

                                        Toast.makeText(RoomConfirmer.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<PreRegisterResponse> call, Throwable t) {
                                Toast.makeText(RoomConfirmer.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                else {

                    progressDialog = new ProgressDialog(RoomConfirmer.this);
                    progressDialog.setTitle("Hostel Booking");
                    progressDialog.setMessage("Opening Registration Form..");
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                    PreRegisterResponse preRegisterResponse = new PreRegisterResponse(roomNumber, rollNumber, email, hostelName);
                    Call<PreRegisterResponse> call = RetrofitClient.getInstance().getApi().PreRegisterResponse(preRegisterResponse);
                    call.enqueue(new Callback<PreRegisterResponse>() {
                        @Override
                        public void onResponse(Call<PreRegisterResponse> call, Response<PreRegisterResponse> response) {
                            PreRegisterResponse responseFromAPI = response.body();
                            progressDialog.dismiss();
//                            Toast.makeText(RoomConfirmer.this, "response received", Toast.LENGTH_SHORT).show();
                            if (response.isSuccessful()) {

//                                Toast.makeText(RoomConfirmer.this, "response succesful", Toast.LENGTH_SHORT).show();
                                if (responseFromAPI.getMessage().equals("go")) {
//                                    show the intent for Register room

                                    Intent intent = new Intent(RoomConfirmer.this, RegisterationActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("hostelName", hostelName);
                                    intent.putExtra("username", username);
                                    intent.putExtra("rollNumber", rollNumber);
                                    intent.putExtra("email", email);
                                    intent.putExtra("branch", branch);
                                    intent.putExtra("roomNum", roomNumber);
                                    startActivity(intent);
                                    finish();
                                } else if (responseFromAPI.getMessage().equals("fully filled")) {

                                    progressDialog.dismiss();
//                        show the dialog box
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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RoomConfirmer.this);
                                    // on below line we are setting message for our dialog box.
                                    builder.setMessage("Room Fully Occupied\nTry Another Room Number")
                                            .setTitle("NITJ HOSTELS")
                                            .setPositiveButton("OK", dialogClickListener)
                                            .show();

                                } else if (responseFromAPI.getMessage().equals("booking in process")) {

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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RoomConfirmer.this);
                                    // on below line we are setting message for our dialog box.
                                    builder.setMessage("This room is under Booking Process\nTry again Later..")
                                            .setTitle("NITJ HOSTELS")
                                            .setPositiveButton("OK", dialogClickListener)
                                            .show();


                                } else if (responseFromAPI.getMessage().equals("user already registered")) {


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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RoomConfirmer.this);
                                    // on below line we are setting message for our dialog box.
                                    builder.setMessage("You Have Already Registered\nOne Person can Book one room only..")
                                            .setTitle("NITJ HOSTELS")
                                            .setPositiveButton("OK", dialogClickListener)
                                            .show();

                                } else {

                                    Toast.makeText(RoomConfirmer.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<PreRegisterResponse> call, Throwable t) {
                            Toast.makeText(RoomConfirmer.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }


//                Intent intent = new Intent(RoomConfirmer.this, RegisterationActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("hostelName", hostelName);
//                intent.putExtra("username", username);
//                intent.putExtra("rollNumber", rollNumber);
//                intent.putExtra("email", email);
//                intent.putExtra("branch", branch);
//                intent.putExtra("roomNum",roomNum);
//                startActivity(intent);
//                finish();

        }
        });
    }

    }
