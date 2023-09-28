package com.example.hostelappnitj.Girls_Hostel_A;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.gestures.Settings;
import com.alexvasilkov.gestures.views.interfaces.GestureView;
import com.example.hostelappnitj.Acitvity.RoomConfirmer;
import com.example.hostelappnitj.AdminActivity.SearchStudent_AdminActivity;
import com.example.hostelappnitj.ModelResponse.PreRegisterResponse;
import com.example.hostelappnitj.ModelResponse.hostel;
import com.example.hostelappnitj.ModelResponse.person;
import com.example.hostelappnitj.ModelResponse.statusModel;
import com.example.hostelappnitj.ModelResponse.studentListModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.example.hostelappnitj.databinding.ActivityMghAFloor1Binding;
import com.example.hostelappnitj.databinding.ActivityMghAFloor2Binding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mgh_A_floor_1 extends AppCompatActivity {
    private ActivityMghAFloor1Binding binding;
    AppCompatButton room301;
    AppCompatButton btnBook3;
    GestureView gestureView;
    SharedPrefManager sharedPrefManager;
    String hostelName, username, rollNumber, email, branch;
    TextView display;
    List<hostel> hostelList;
    List<statusModel>hostelStatusList;
    AppCompatButton buttons[] = new AppCompatButton[19];
    ProgressDialog progressDialog ,  progressDialog1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityMghAFloor1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(view);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sharedPrefManager = new SharedPrefManager(mgh_A_floor_1.this);
        username = sharedPrefManager.getUser().getUsername();
        email = sharedPrefManager.getUser().getEmail();
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        branch = sharedPrefManager.getUser().getBranch();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Updating Seat Matrix..");
        progressDialog.show();
        progressDialog.setCancelable(false);
//        intent from MegaBoysB_activity
        Intent intent = getIntent();
        hostelName = intent.getStringExtra("hostelName");

        String userType = sharedPrefManager.getAdmin();

        if(userType.equals("Admin")){
            binding.btnRoomBook3.setVisibility(View.INVISIBLE);
        }

        gestureView = findViewById(R.id.gestureview);
        gestureView.getController().getSettings()
                .setMaxZoom(2f)
                .setMinZoom(0.9f)
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

        loadStatus(); //to load the color of rooms in matrix

//        TO PASS THE INTENT TO NEXT REGISTER ACTIVITY
        binding.btnRoomBook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mgh_A_floor_1.this, "Register here", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mgh_A_floor_1.this, RoomConfirmer.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("hostelName", hostelName);
                intent.putExtra("username", username);
                intent.putExtra("rollNumber", rollNumber);
                intent.putExtra("email", email);
                intent.putExtra("branch", branch);
                intent.putExtra("floor", "1");  // change this floor number also
                startActivity(intent);
            }
        });
        Handler handler= new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                loadStatus();
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable,2000);

        //To implement click listener on every button

        for (int i = 1; i < 19; i++) {

            String buttonID;
            String roomNumber;
            if (i > 0 && i < 10) {
                buttonID = "room1" + "0" + i;
                roomNumber = "10" + i;
            } else {
                buttonID = "room1" + i;
                roomNumber = "1" + i;

            }

            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (AppCompatButton) findViewById(resID);

            int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(Floor_1_SeatMatrix_A.this, buttonID, Toast.LENGTH_SHORT).show();

                    progressDialog1 = new ProgressDialog(mgh_A_floor_1.this);
                    progressDialog1.setTitle("Loading...");
                    progressDialog1.setMessage("Checking the occupancy...");
                    progressDialog1.show();
                    progressDialog1.setCancelable(false);

                    String hostelName = "Girls Hostel A";
                    studentListModel studentListModel = new studentListModel(roomNumber, hostelName);

                    Call<studentListModel> call = RetrofitClient.getInstance().getApi().studentListResponse(studentListModel);

                    call.enqueue(new Callback<com.example.hostelappnitj.ModelResponse.studentListModel>() {
                        @Override
                        public void onResponse(Call<com.example.hostelappnitj.ModelResponse.studentListModel> call, Response<com.example.hostelappnitj.ModelResponse.studentListModel> response) {
                            studentListModel responseFromAPI = response.body();
                            if (response.isSuccessful()) {
                                if (responseFromAPI.getMessage().equals("no user found")) {
                                    progressDialog1.dismiss();
                                    Toast.makeText(mgh_A_floor_1.this, "Room is Empty", Toast.LENGTH_SHORT).show();
                                }
                                if (responseFromAPI.getMessage().equals("single user found")) {
                                    Toast.makeText(mgh_A_floor_1.this, "single user exist", Toast.LENGTH_SHORT).show();
                                    person p1 = responseFromAPI.getPerson1();
                                    String email, phone, address, branch, rollNumber, fatherName, fatherPhone, avatar, userName;
                                    email = p1.getEmail();
                                    phone = p1.getPhone();
                                    address = p1.getAddress();
                                    branch = p1.getBranch();
                                    rollNumber = p1.getRollNumber();
                                    fatherName = p1.getFatherName();
                                    fatherPhone = p1.getFatherPhone();
                                    avatar = p1.getAvatar();
                                    userName = p1.getUsername();
                                    Intent intent = new Intent(mgh_A_floor_1.this, SearchStudent_AdminActivity.class);
                                    intent.putExtra("occupied", "1");
                                    intent.putExtra("userName", userName);
                                    intent.putExtra("email", email);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("address", address);
                                    intent.putExtra("branch", branch);
                                    intent.putExtra("rollNumber", rollNumber);
                                    intent.putExtra("fatherName", fatherName);
                                    intent.putExtra("fatherPhone", fatherPhone);
                                    intent.putExtra("avatar", avatar);
                                    intent.putExtra("roomNumber", roomNumber);
                                    progressDialog1.dismiss();

                                    startActivity(intent);

                                }
                                if (responseFromAPI.getMessage().equals("two user found")) {
                                    Toast.makeText(mgh_A_floor_1.this, "both user exist", Toast.LENGTH_SHORT).show();
                                    person p1 = responseFromAPI.getPerson1();
                                    person p2 = responseFromAPI.getPerson2();
                                    String email, phone, address, branch, rollNumber, fatherName, fatherPhone, avatar, userName;
                                    email = p1.getEmail();
                                    phone = p1.getPhone();
                                    address = p1.getAddress();
                                    branch = p1.getBranch();
                                    rollNumber = p1.getRollNumber();
                                    fatherName = p1.getFatherName();
                                    fatherPhone = p1.getFatherPhone();
                                    avatar = p1.getAvatar();
                                    userName = p1.getUsername();
                                    Intent intent = new Intent(mgh_A_floor_1.this, SearchStudent_AdminActivity.class);
                                    intent.putExtra("occupied", "2");
                                    intent.putExtra("userName1", userName);
                                    intent.putExtra("email1", email);
                                    intent.putExtra("phone1", phone);
                                    intent.putExtra("address1", address);
                                    intent.putExtra("branch1", branch);
                                    intent.putExtra("rollNumber1", rollNumber);
                                    intent.putExtra("fatherName1", fatherName);
                                    intent.putExtra("fatherPhone1", fatherPhone);
                                    intent.putExtra("avatar1", avatar);
                                    intent.putExtra("roomNumber", roomNumber);


                                    intent.putExtra("userName2", p2.getUsername());
                                    intent.putExtra("email2", p2.getEmail());
                                    intent.putExtra("phone2", p2.getPhone());
                                    intent.putExtra("address2", p2.getAddress());
                                    intent.putExtra("branch2", p2.getBranch());
                                    intent.putExtra("rollNumber2", p2.getRollNumber());
                                    intent.putExtra("fatherName2", p2.getFatherName());
                                    intent.putExtra("fatherPhone2", p2.getFatherPhone());
                                    intent.putExtra("avatar2", p2.getAvatar());
                                    progressDialog1.dismiss();

                                    startActivity(intent);

                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<com.example.hostelappnitj.ModelResponse.studentListModel> call, Throwable t) {
                            Toast.makeText(mgh_A_floor_1.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog1.dismiss();

                        }
                    });


                }
            });
        }
    }

    private void loadStatus() {
        //        API call for Status verification
        Call<PreRegisterResponse>call1 = RetrofitClient.getInstance().getApi().fetchAllHostelsStatus();
        call1.enqueue(new Callback<PreRegisterResponse>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<PreRegisterResponse> call, Response<PreRegisterResponse> response) {
                PreRegisterResponse responseFromAPI1 = response.body();
                if(response.isSuccessful()){
                    progressDialog.dismiss();  //if any error occurs then it need to be returned

                    hostelStatusList=responseFromAPI1.getHostelStatusList();
                    int n = hostelStatusList.size();
                    String status_received,room,hostel_name,nums;
                    String room2 = "";
                    for (int i =0 ; i<n;i++){
                        status_received=hostelStatusList.get(i).getStatus();
                        room=hostelStatusList.get(i).getRoomNumber();
                        hostel_name=hostelStatusList.get(i).getHostelName();
                        nums=hostelStatusList.get(i).getNums();

//                        room2=room+" ";

//                            if condition for evaluating the hostel name
                        //concept
//                        vacancy    status   output
//                           0        0         no
//                           1        0         light blue
//                           2        0         blue
//                           0        1       red
//                           1        1       red
//                           2        1       red
                        if(hostel_name.equals("Girls Hostel A")){
                            if(room!=null) {
                                if (status_received.equals("1")) {   //temporary blocked
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_temporarily_blocked);
                                    }catch (NullPointerException e){
                                        progressDialog.dismiss();  //if any error occurs then it need to be returned
                                        e.printStackTrace();
                                    }
                                }
                                if (status_received.equals("0")&& nums.equals("1")) {   //unBlock room num people =1
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_occupied_partially);
                                    }catch (NullPointerException e){
                                        progressDialog.dismiss();  //if any error occurs then it need to be returned
                                        e.printStackTrace();
                                    }
                                }
                                if (status_received.equals("0")&& nums.equals("2")) {   //unBlock room  , num people 2
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_occupied_full);
                                    }catch (NullPointerException e){
                                        progressDialog.dismiss();  //if any error occurs then it need to be returned
                                        e.printStackTrace();
                                    }
                                }
                                if (status_received.equals("0")&& nums.equals("0")) {   //unBlock room  , num people 2
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_gradient2);
                                    }catch (NullPointerException e){
                                        progressDialog.dismiss();  //if any error occurs then it need to be returned
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PreRegisterResponse> call, Throwable t) {
                progressDialog.dismiss();  //if any error occurs then it need to be returned
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadStatus(); //to load the color of rooms in matrix
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        this.recreate();
    }

}