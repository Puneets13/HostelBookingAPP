package com.example.hostelappnitj.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelappnitj.Boys_Hostel_7.Bh_7_Floor_1;
import com.example.hostelappnitj.Boys_Hostel_7.Bh_7_Floor_2;
import com.example.hostelappnitj.Boys_Hostel_7.Bh_7_Floor_3;
import com.example.hostelappnitj.Boys_Hostel_7.Bh_7_Floor_Ground;
import com.example.hostelappnitj.Boys_Hostel_7E.Bh_7E_Floor_1;
import com.example.hostelappnitj.Boys_Hostel_7E.Bh_7E_Floor_2;
import com.example.hostelappnitj.Boys_Hostel_7E.Bh_7E_Floor_3;
import com.example.hostelappnitj.Boys_Hostel_7E.Bh_7E_Floor_Ground;
import com.example.hostelappnitj.ModelResponse.person;
import com.example.hostelappnitj.ModelResponse.studentListModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bh7E_AdminActivity extends AppCompatActivity {
    EditText etRoomNumber , etStudentName;
    AppCompatButton btnProccedRoomNumber,btnProccedStudentName;
    CardView hostelList , hostelPlan;
    ProgressDialog progressDialog ;
    private CharSequence[] hostelFloors ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_bh7_e_admin);

        etRoomNumber = findViewById(R.id.editRoom);
        etStudentName = findViewById(R.id.etStudentName);
        btnProccedRoomNumber=findViewById(R.id.btnproceedRoom);
        btnProccedStudentName=findViewById(R.id.btnproceedStudent);
        hostelList=findViewById(R.id.hostelList);
        hostelPlan=findViewById(R.id.HostelRoomPlan);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait for a while...");


        hostelFloors = new CharSequence[]{
                "GROUND FLOOR","FLOOR 1", "FLOOR 2","FLOOR 3"
        };


        hostelList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hosteName = "Boys Hostel 7E";

                Intent intent = new Intent(Bh7E_AdminActivity.this,StudentByName.class);
                intent.putExtra("hostelName",hosteName);
                startActivity(intent);
            }
        });


        hostelPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                dialog box to select floors
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Bh7E_AdminActivity.this);
                builder.setTitle("Select Floor");
                builder.setIcon(R.drawable.ic_registration);
                builder.setSingleChoiceItems(hostelFloors, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // Ground floor
                                Intent intent0 = new Intent(Bh7E_AdminActivity.this, Bh_7E_Floor_Ground.class);
                                intent0.putExtra("hostelName", "Boys Hostel 7E");
                                startActivity(intent0);
                                break;
                            case 1: //floor 1
                                Intent intent = new Intent(Bh7E_AdminActivity.this, Bh_7E_Floor_1.class);
                                intent.putExtra("hostelName", "Boys Hostel 7E");
                                startActivity(intent);
                                break;
                            case 2: //floor 2
                                Intent intent2 = new Intent(Bh7E_AdminActivity.this, Bh_7E_Floor_2.class);
                                intent2.putExtra("hostelName", "Boys Hostel 7E");
                                startActivity(intent2);
                                break;
                            case 3: //floor 3
                                Intent intent3 = new Intent(Bh7E_AdminActivity.this,  Bh_7E_Floor_3.class);
                                intent3.putExtra("hostelName", "Boys Hostel 7E");
                                startActivity(intent3);
                                break;
                        }
                    }
                });
                builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog,null));
                builder.show();

            }
        });

        btnProccedRoomNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomNumber = etRoomNumber.getText().toString();
                if(roomNumber.isEmpty()){
                    etRoomNumber.requestFocus();
                    etRoomNumber.setError("Enter Room Number");
                    return;
                }
                else{
                    etRoomNumber.setText("");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    String hostelName =   "Boys Hostel 7E";
                    studentListModel studentListModel = new studentListModel(roomNumber,hostelName);

                    Call<studentListModel> call = RetrofitClient.getInstance().getApi().studentListResponse(studentListModel);

                    call.enqueue(new Callback<com.example.hostelappnitj.ModelResponse.studentListModel>() {
                        @Override
                        public void onResponse(Call<com.example.hostelappnitj.ModelResponse.studentListModel> call, Response<com.example.hostelappnitj.ModelResponse.studentListModel> response) {
                            studentListModel responseFromAPI = response.body();
                            if(response.isSuccessful()){
                                if(responseFromAPI.getMessage().equals("no user found")){
                                    progressDialog.dismiss();
                                    Toast.makeText(Bh7E_AdminActivity.this, "Room is Empty", Toast.LENGTH_SHORT).show();
                                }
                                if (responseFromAPI.getMessage().equals("user found")){
                                    Toast.makeText(Bh7E_AdminActivity.this, "single user exist", Toast.LENGTH_SHORT).show();
                                    person p1 = responseFromAPI.getPerson1();
                                    String email,phone,address,branch,rollNumber,fatherName,fatherPhone,avatar,userName;
                                    email = p1.getEmail();
                                    phone= p1.getPhone();
                                    address= p1.getAddress();
                                    branch=p1.getBranch();
                                    rollNumber= p1.getRollNumber();
                                    fatherName= p1.getFatherName();
                                    fatherPhone=p1.getFatherPhone();
                                    avatar= p1.getAvatar();
                                    userName=p1.getUsername();
                                    Intent intent = new Intent(Bh7E_AdminActivity.this,SearchStudent_AdminActivity.class);
                                    intent.putExtra("occupied","1");
                                    intent.putExtra("userName",userName);
                                    intent.putExtra("email",email);
                                    intent.putExtra("phone",phone);
                                    intent.putExtra("address",address);
                                    intent.putExtra("branch",branch);
                                    intent.putExtra("rollNumber",rollNumber);
                                    intent.putExtra("fatherName",fatherName);
                                    intent.putExtra("fatherPhone",fatherPhone);
                                    intent.putExtra("avatar",avatar);
                                    intent.putExtra("roomNumber",roomNumber);
                                    progressDialog.dismiss();

                                    startActivity(intent);

                                }
                                if (responseFromAPI.getMessage().equals("two user found")){
                                    Toast.makeText(Bh7E_AdminActivity.this, "both user exist", Toast.LENGTH_SHORT).show();
                                    person p1 = responseFromAPI.getPerson1();
                                    person p2 = responseFromAPI.getPerson2();
                                    String email,phone,address,branch,rollNumber,fatherName,fatherPhone,avatar,userName;
                                    email = p1.getEmail();
                                    phone= p1.getPhone();
                                    address= p1.getAddress();
                                    branch=p1.getBranch();
                                    rollNumber= p1.getRollNumber();
                                    fatherName= p1.getFatherName();
                                    fatherPhone=p1.getFatherPhone();
                                    avatar= p1.getAvatar();
                                    userName=p1.getUsername();
                                    Intent intent = new Intent(Bh7E_AdminActivity.this,SearchStudent_AdminActivity.class);
                                    intent.putExtra("occupied","2");
                                    intent.putExtra("userName1",userName);
                                    intent.putExtra("email1",email);
                                    intent.putExtra("phone1",phone);
                                    intent.putExtra("address1",address);
                                    intent.putExtra("branch1",branch);
                                    intent.putExtra("rollNumber1",rollNumber);
                                    intent.putExtra("fatherName1",fatherName);
                                    intent.putExtra("fatherPhone1",fatherPhone);
                                    intent.putExtra("avatar1",avatar);
                                    intent.putExtra("roomNumber",roomNumber);


                                    intent.putExtra("userName2",p2.getUsername());
                                    intent.putExtra("email2",p2.getEmail());
                                    intent.putExtra("phone2",p2.getPhone());
                                    intent.putExtra("address2",p2.getAddress());
                                    intent.putExtra("branch2",p2.getBranch());
                                    intent.putExtra("rollNumber2",p2.getRollNumber());
                                    intent.putExtra("fatherName2",p2.getFatherName());
                                    intent.putExtra("fatherPhone2",p2.getFatherPhone());
                                    intent.putExtra("avatar2",p2.getAvatar());
                                    progressDialog.dismiss();

                                    startActivity(intent);

                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<com.example.hostelappnitj.ModelResponse.studentListModel> call, Throwable t) {
                            Toast.makeText(Bh7E_AdminActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    });
                }
            }
        });

        btnProccedStudentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName1 = etStudentName.getText().toString();
                String studentName2 = studentName1.toUpperCase();
                String studentName =  studentName2.trim(); //to reomve extra space at front and back if present

                if(studentName.isEmpty()){
                    etStudentName.requestFocus();
                    etStudentName.setError("Enter Student Name");
                    return;
                }else{
                    etStudentName.setText("");
                    Intent intent = new Intent(Bh7E_AdminActivity.this,StudentList_AdminActivity.class);
                    intent.putExtra("studentName",studentName);
                    intent.putExtra("hostelName","Boys Hostel 7E");
                    etStudentName.setText("");
                    startActivity(intent);
                }
            }
        });



    }
}