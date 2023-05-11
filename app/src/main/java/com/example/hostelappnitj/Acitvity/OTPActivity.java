package com.example.hostelappnitj.Acitvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.OTP_model;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.databinding.ActivityOtpactivityBinding;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {
    ActivityOtpactivityBinding binding;
    String email ;
    ProgressDialog progressDialog ;   //this will give the background box also
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled

        binding = ActivityOtpactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        binding.phoneLabel.setText(email);

        binding.SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(OTPActivity.this);
                progressDialog.setTitle("OTP Submitted");
                progressDialog.setMessage("Verifying OTP...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                if (!binding.otpView1.getText().toString().trim().isEmpty() && !binding.otpView2.getText().toString().trim().isEmpty() && !binding.otpView3.getText().toString().trim().isEmpty() && !binding.otpView4.getText().toString().trim().isEmpty() ) {
//to get the string numbers enter by user
                    String code_by_user = binding.otpView1.getText().toString() +
                            binding.otpView2.getText().toString() +
                            binding.otpView3.getText().toString() +
                            binding.otpView4.getText().toString() ;

                            if(code_by_user==null){
                                Toast.makeText(OTPActivity.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
//API integrate
                    OTP_model model = new OTP_model(email , code_by_user);
                    Call<OTP_model>call = RetrofitClient.getInstance().getApi().veriftyOTP(model);

                    call.enqueue(new Callback<OTP_model>() {
                        @Override
                        public void onResponse(Call<OTP_model> call, Response<OTP_model> response) {
//                            Toast.makeText(OTPActivity.this, "Entered response", Toast.LENGTH_SHORT).show();
                            OTP_model responseFromAPI = response.body();
                            if(response.isSuccessful()){
                                if(responseFromAPI.getMessage().equals("User Already Exist")){
                                    Toast.makeText(OTPActivity.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();

                                }
                                else if (responseFromAPI.getMessage().equals("Code expired")){
                                    progressDialog.dismiss();
                                    Toast.makeText(OTPActivity.this, "Code Expires..", Toast.LENGTH_SHORT).show();
                                    binding.otpView1.setText("");
                                    binding.otpView2.setText("");
                                    binding.otpView3.setText("");
                                    binding.otpView4.setText("");

                                }
                                else if (responseFromAPI.getMessage().equals("Invalid code")){
                                    progressDialog.dismiss();
                                    Toast.makeText(OTPActivity.this, "Invalid Code..\nResend Code", Toast.LENGTH_SHORT).show();
                                    binding.otpView1.setText("");
                                    binding.otpView2.setText("");
                                    binding.otpView3.setText("");
                                    binding.otpView4.setText("");
                                }
                                else if(responseFromAPI.getMessage().equals("user email verified successfully")){
                                    progressDialog.dismiss();
                                    Toast.makeText(OTPActivity.this, "User Verified", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(OTPActivity.this,SignInActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent1);
                                }
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(OTPActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<OTP_model> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(OTPActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(OTPActivity.this, "Please Enter Complete OTP", Toast.LENGTH_SHORT).show();

                }


            }
        });


        numberOTPMove(); //to move the cursore

        binding.resendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.otpView1.setText("");
                binding.otpView2.setText("");
                binding.otpView3.setText("");
                binding.otpView4.setText("");
                Toast.makeText(OTPActivity.this, "OTP Resent", Toast.LENGTH_SHORT).show();
                OTP_model model2 = new OTP_model(email);
Call<OTP_model>call = RetrofitClient.getInstance().getApi().resendOTP(model2);
call.enqueue(new Callback<OTP_model>() {
    @Override
    public void onResponse(Call<OTP_model> call, Response<OTP_model> response) {
        OTP_model responseFromAPI = response.body();
        if(response.isSuccessful()){
            if(responseFromAPI.getMessage().equals("User Already Exist")){
                Toast.makeText(OTPActivity.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
            else if (responseFromAPI.getMessage().equals("Code expired")){
                progressDialog.dismiss();
                Toast.makeText(OTPActivity.this, "Code Expires..", Toast.LENGTH_SHORT).show();
                binding.otpView1.setText("");
                binding.otpView2.setText("");
                binding.otpView3.setText("");
                binding.otpView4.setText("");

            }
            else if (responseFromAPI.getMessage().equals("Invalid code")){
                progressDialog.dismiss();
                Toast.makeText(OTPActivity.this, "Invalid Code..\nResend Code", Toast.LENGTH_SHORT).show();
                binding.otpView1.setText("");
                binding.otpView2.setText("");
                binding.otpView3.setText("");
                binding.otpView4.setText("");
            }
            else if(responseFromAPI.getMessage().equals("user email verified successfully")){
                progressDialog.dismiss();
                Toast.makeText(OTPActivity.this, "User Verified", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(OTPActivity.this,SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }
        }
    }

    @Override
    public void onFailure(Call<OTP_model> call, Throwable t) {

    }
});


            }
        });

    }

    private void numberOTPMove() {

//        add this to all the edit text to move this cursor //expand to see the code
        binding.otpView1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//request focus is used to move the cursor form one edit text to another
                if (!s.toString().trim().isEmpty()) {
                    binding.otpView2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otpView2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//request focus is used to move the cursor form one edit text to another
                if (!s.toString().trim().isEmpty()) {
                    binding.otpView3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otpView3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//request focus is used to move the cursor form one edit text to another
                if (!s.toString().trim().isEmpty()) {
                    binding.otpView4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}