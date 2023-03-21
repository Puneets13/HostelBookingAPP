package com.example.hostelappnitj.MBH_F_Hostel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.gestures.Settings;
import com.alexvasilkov.gestures.views.interfaces.GestureView;
import com.example.hostelappnitj.Acitvity.RegisterationActivity;
import com.example.hostelappnitj.MBH_B_Hostel.Floor_1_SeatMatrix;
import com.example.hostelappnitj.ModelResponse.HostelRegisterationResponse;
import com.example.hostelappnitj.ModelResponse.hostel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.example.hostelappnitj.databinding.ActivityFloor1SeatMatrixBinding;
import com.example.hostelappnitj.databinding.ActivityMbhfActivityBinding;
import com.example.hostelappnitj.databinding.ActivityMbhfFloor1Binding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mbhfFloor1 extends AppCompatActivity {

    private ActivityMbhfFloor1Binding binding;
    AppCompatButton room301;
    AppCompatButton btnBook3;
    GestureView gestureView;
    SharedPrefManager sharedPrefManager;
    String hostelName, username, rollNumber, email, branch;
    TextView display;
    List<hostel> hostelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMbhfFloor1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager = new SharedPrefManager(mbhfFloor1.this);
        username = sharedPrefManager.getUser().getUsername();
        email = sharedPrefManager.getUser().getEmail();
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        branch = sharedPrefManager.getUser().getBranch();

//        intent from MegaBoysB_activity
        Intent intent = getIntent();
        hostelName = intent.getStringExtra("hostelName");


        gestureView = findViewById(R.id.gestureview);
        gestureView.getController().getSettings()
                .setMaxZoom(2f)
                .setDoubleTapZoom(-1f) // Falls back to max zoom level
                .setPanEnabled(true)
                .setZoomEnabled(true)
                .setDoubleTapEnabled(true)
                .setRotationEnabled(true)
                .setRestrictRotation(false)
                .setOverscrollDistance(0f, 0f)
                .setOverzoomFactor(2f)
                .setFillViewport(false)
                .setFitMethod(Settings.Fit.INSIDE)
                .setGravity(Gravity.CENTER);

        loadRooms(); //to load the color of rooms in matrix

//        TO PASS THE INTENT TO NEXT REGISTER ACTIVITY
        binding.btnRoomBook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mbhfFloor1.this, "Register here", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mbhfFloor1.this, RegisterationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("hostelName", hostelName);
                intent.putExtra("username", username);
                intent.putExtra("rollNumber", rollNumber);
                intent.putExtra("email", email);
                intent.putExtra("branch", branch);
                startActivity(intent);
            }
        });
    }

    public void loadRooms(){
        Call<HostelRegisterationResponse> call = RetrofitClient.getInstance().getApi().fetchAllHostels();
        call.enqueue(new Callback<HostelRegisterationResponse>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<HostelRegisterationResponse> call, Response<HostelRegisterationResponse> response) {
                HostelRegisterationResponse responseFromAPI= response.body();
                if(response.isSuccessful()){
                    Toast.makeText(mbhfFloor1.this, "Hostels Updated", Toast.LENGTH_SHORT).show();
                    hostelList=  responseFromAPI.getHostelList();
                    int n = hostelList.size();


//                            List<String>usernames = new ArrayList<>();
                    List<String>roomNumberFullyFilled = new ArrayList<>();
                    String rollNumber1 , rollNumber2,room , room2 = "";



                    for (int i =0 ; i<n;i++){
                        rollNumber1 = hostelList.get(i).getRollNumber1();
                        rollNumber2 = hostelList.get(i).getRollNumber2();
                        room = hostelList.get(i).getRoomNumber();
                        room2=room+" ";

//                            if condition for evaluating the hostel name
                        String hostel_name = hostelList.get(i).getHostelName();

                        if(hostel_name.equals("Mega Boys Hostel B")){
                            if(room!=null) {
                                if (rollNumber1 != null && rollNumber2 != null) {
//                                    both ARE present
//                            roomNumberFullyFilled.add(room);
//                            Toast.makeText(SeatmatrixMBHBoys.this, room+" fully filled", Toast.LENGTH_SHORT).show();
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_occupied_full);
                                    }catch (NullPointerException e){
                                        e.printStackTrace();
                                    }

//                            b.setTextColor(R.color.white);
                                } else if (rollNumber1 != null && rollNumber2 == null) {
//                            student 1 is present only partially filled
//                            roomNumber.add(room);
//                            Toast.makeText(SeatmatrixMBHBoys.this, room+" partially filled", Toast.LENGTH_SHORT).show();
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_occupied_partially);
                                    }catch (NullPointerException e){
                                        e.printStackTrace();
                                    }
                                }
                            }



                        }
                    }
//                    binding.display.setText("total "+n);
//                            hosteltxt.setText("names : "+usernames);

                }else{
                    Toast.makeText(mbhfFloor1.this, "Something went Wrong..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HostelRegisterationResponse> call, Throwable t) {
                Toast.makeText(mbhfFloor1.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadRooms();
    }
}