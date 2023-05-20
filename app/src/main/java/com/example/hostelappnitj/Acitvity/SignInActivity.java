package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.MainActivity;
import com.example.hostelappnitj.ModelResponse.DataModel;
import com.example.hostelappnitj.ModelResponse.ForgetpassModel;
import com.example.hostelappnitj.ModelResponse.User;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    Integer attempts=0;
    TextView registerlink , forgetpass;
    EditText password,email;
    Button login;
    ProgressDialog progressDialog ;
    SharedPrefManager sharedPrefManager ; //to maintain the login-logout session
    String userType , inEmail ;
    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_sign_in);
        //        to lock the orientations of the screen
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        password = findViewById(R.id.etpassword);
        email = findViewById(R.id.etemail);
        login=findViewById(R.id.btnlogin);
        registerlink=findViewById(R.id.registerlink);
        forgetpass=findViewById(R.id.txtforgetPass);
        registerlink.setOnClickListener(SignInActivity.this);
        login.setOnClickListener(this);
        forgetpass.setOnClickListener(this);
        sharedPrefManager = new SharedPrefManager(this.getApplicationContext());  //pass the context of the application to sharedprefmanager

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                if(getAttempts()==2){
                    login.setText("Too many attempts");
                    login.setClickable(false);
                }
                else{
                    Loginuser();
                }

//                Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(intent);
                break;
            case R.id.registerlink:startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                break;
            case R.id.txtforgetPass:forgetPassword();


        }
    }

    private void forgetPassword() {

        LayoutInflater li = LayoutInflater.from(SignInActivity.this);
        View promptsView = li.inflate(R.layout.dialog_input, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignInActivity.this);



        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
        final AppCompatButton btnProcced = (AppCompatButton) promptsView.findViewById(R.id.btnprocced);
        final AppCompatButton btnCancel = (AppCompatButton) promptsView.findViewById(R.id.btnCancel);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        btnProcced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(SignInActivity.this);
                progressDialog.setTitle("NITJ HOSTELS");
                progressDialog.setMessage("Sending OTP\nCheck your Email..");
                progressDialog.show();
                progressDialog.setCancelable(false);
                String email = userInput.getText().toString();

                if(email.isEmpty()){
                    userInput.requestFocus();
                    userInput.setError("Please enter the Email");
                    progressDialog.dismiss();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    userInput.requestFocus();
                    userInput.setError("Please enter email correctly");
                    progressDialog.dismiss();

                }
                else{
                    ForgetpassModel model = new ForgetpassModel(email);
                    Call<ForgetpassModel> call = RetrofitClient.getInstance().getApi().forgetpassword(model);

                    call.enqueue(new Callback<ForgetpassModel>() {
                        @Override
                        public void onResponse(Call<ForgetpassModel> call, Response<ForgetpassModel> response) {
                            progressDialog.dismiss();
                            if(response.isSuccessful()){
                                if(response.body().getMessage().equals("user not registered")){
                                    Toast.makeText(SignInActivity.this, "You haven't registered yet", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    return ;
                                }
                                if(response.body().getMessage().equals("Verification otp email sent")){
                                    progressDialog.dismiss();
                                    Toast.makeText(SignInActivity.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignInActivity.this,ForgetOTPActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra("email",email);
                                    startActivity(intent);
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ForgetpassModel> call, Throwable t) {
                            Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    });


                }


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();


    }

    private void Loginuser() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("LogIn");
        progressDialog.setMessage("Signing in User..");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();

        inEmail = email.getText().toString();
        if(inEmail.equals("mbh.B@nitj.ac.in") || inEmail.equals("mbh.A@nitj.ac.in") ||inEmail.equals("mbh.F@nitj.ac.in")||inEmail.equals("mgh.@nitj.ac.in")){
            userType = "Admin";
        }
        else{
            userType = "Student";
        }

        if (useremail.isEmpty()) {
            email.requestFocus();
            email.setError("Please enter your email");
            progressDialog.dismiss();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
            email.requestFocus();
            email.setError("Please enter email correctly");
            progressDialog.dismiss();
            return;
        }
        if (userpassword.isEmpty()) {
            Toast.makeText(this, "Please enter your password ", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;
        }
//        Creting the request for POST
        DataModel model = new DataModel(useremail,userpassword);
        Call<DataModel> call= RetrofitClient
                .getInstance()
                .getApi()
                .login(model);

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel responseFromAPi = response.body();
                progressDialog.dismiss();
//                User user = responseFromAPi.getUser(); //we have assigned the user here
                if(response.isSuccessful()){
                    User user = responseFromAPi.getUser(); //we have assigned the user here

                    if(responseFromAPi.getMessage().equals("false") && responseFromAPi.getError().equals("Incorrect Password")){
                        password.setText("");
                        setAttempts(getAttempts()+1);
                        Toast.makeText(SignInActivity.this, "Incorrect Password\nPlease try again", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(responseFromAPi.getError().equals("user not found") && responseFromAPi.getMessage().equals("false")){
                        email.setText("");
                        password.setText("");
                        setAttempts(getAttempts()+1);
                        Toast.makeText(SignInActivity.this, useremail+" has not registered", Toast.LENGTH_SHORT).show();

                    }
                    else if(responseFromAPi.getMessage().equals("not verified")&& responseFromAPi.getError().equals("please register again")){
                        email.setText("");
                        password.setText("");
                        setAttempts(getAttempts()+1);

                        Toast.makeText(SignInActivity.this, useremail+" has not registered", Toast.LENGTH_SHORT).show();

                    }
                    else{
//                      when it is successfull then
//                        String responseString =  "\nUsername : " + user.getUsername()+"\n"+user.getEmail();

                        sharedPrefManager.SaveUser(responseFromAPi.getUser());  //this is used to save the user properties in the sharePrefManager
//sharedPrefManager.getHostelUser(res)
                        if(userType=="Student"){
                            sharedPrefManager.setAdmin("Student");
                        }
                        else{
                            sharedPrefManager.setAdmin("Admin");
                        }

                        sharedPrefManager.SaveHostelUser(responseFromAPi.getHostel());

                        Toast.makeText(SignInActivity.this, user.getEmail()+" Login successfully", Toast.LENGTH_SHORT).show();

//                       define type

                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//this is used to clear the previous stack of activities so when back button pressed then previous activites
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }else{
//                    when the response code status is 500  then this block will work
                    password.setText("");
                    email.setText("");
                    progressDialog.dismiss();
                    setAttempts(getAttempts()+1);
                    Toast.makeText(SignInActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    protected void onStart(){    //when  the application will start it will check if the user is logged in then it will automatically changes to HomeActivity
        super.onStart();
        if(sharedPrefManager.isloggedIn()){
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//this is used to clear the previous stack of activities so when back button pressed then previous activites
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


}