package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.MBH_A_Hostel.Floor_6_SeatMatrix_A;
import com.example.hostelappnitj.ModelResponse.PreRegisterResponse;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomConfirmer extends AppCompatActivity {
EditText etroomNumber;
Button proceed;
    String hostelName, username, rollNumber, email, branch,roomNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_confirmer);
        etroomNumber = findViewById(R.id.editRoom);
        proceed= findViewById(R.id.proceed);

        Intent intent = getIntent();
        hostelName = intent.getStringExtra("hostelName");
        username = intent.getStringExtra("username");
        rollNumber = intent.getStringExtra("rollNumber");
        email = intent.getStringExtra("email");
        branch = intent.getStringExtra("branch");

//here we need to add proced

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomNumber=etroomNumber.getText().toString();
                String room_substring = roomNumber.substring(Math.max(roomNumber.length() - 2, 0));
                int room_int=Integer.parseInt(room_substring);
                if(etroomNumber.getText().toString().isEmpty()){
                    etroomNumber.requestFocus();
                    etroomNumber.setError("Enter Room Number");
                    return;
                }
                else if(room_int>=46 || room_int<=01){
                    etroomNumber.requestFocus();
                    etroomNumber.setError("Please enter Valid Room Number");
                    return;
                }
                else{

                    PreRegisterResponse preRegisterResponse = new PreRegisterResponse(roomNumber,rollNumber,email,hostelName);
                    Call<PreRegisterResponse> call = RetrofitClient.getInstance().getApi().PreRegisterResponse(preRegisterResponse);
                    call.enqueue(new Callback<PreRegisterResponse>() {
                        @Override
                        public void onResponse(Call<PreRegisterResponse> call, Response<PreRegisterResponse> response) {
                            PreRegisterResponse responseFromAPI = response.body();
//                            Toast.makeText(RoomConfirmer.this, "response received", Toast.LENGTH_SHORT).show();
                            if(response.isSuccessful()){
//                                Toast.makeText(RoomConfirmer.this, "response succesful", Toast.LENGTH_SHORT).show();
                                if(responseFromAPI.getMessage().equals("go")){
//                                    show the intent for Register room
                                    Toast.makeText(RoomConfirmer.this, "Gooo", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(RoomConfirmer.this, RegisterationActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("hostelName", hostelName);
                                    intent.putExtra("username", username);
                                    intent.putExtra("rollNumber", rollNumber);
                                    intent.putExtra("email", email);
                                    intent.putExtra("branch", branch);
                                    intent.putExtra("roomNum",roomNumber);
                                    startActivity(intent);
                                    finish();
                                }
                                else if(responseFromAPI.getMessage().equals("fully filled")){
                                    Toast.makeText(RoomConfirmer.this, "Room Fully Occupied", Toast.LENGTH_SHORT).show();
                                }
                                else if(responseFromAPI.getMessage().equals("booking in process")){
                                    Toast.makeText(RoomConfirmer.this, "Can't Book..Wait for While", Toast.LENGTH_SHORT).show();
                                }
                                else if(responseFromAPI.getMessage().equals("user already registered")){
                                    Toast.makeText(RoomConfirmer.this, "You Have Already Registered", Toast.LENGTH_SHORT).show();
                                }else{
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
