package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.MainActivity;
import com.example.hostelappnitj.ModelResponse.DataModel;
import com.example.hostelappnitj.ModelResponse.User;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    TextView loginlink ;
    EditText name, email, password,rollNumber;
    Button register;
    ProgressDialog progressDialog ;   //this will give the background box also
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        getSupportActionBar().hide(); //to hide the action bar shown at the top of the app
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        rollNumber=findViewById(R.id.etRollno);
        register = findViewById(R.id.btnSignUp);
        loginlink = findViewById(R.id.loginlink);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateUser();
            }
        });
        loginlink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btnSignUp:
//                Toast.makeText(this, "button", Toast.LENGTH_SHORT).show();
//                CreateUser();
//             break;
            case R.id.loginlink:
                switchonlogin();
                break;
        }
    }

    private void CreateUser() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Registering user");
        progressDialog.setMessage("Sending OTP to entered Email..");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String rollno = rollNumber.getText().toString();
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();

        if (rollno.isEmpty()) {
            rollNumber.requestFocus();
            rollNumber.setError("Please enter your Roll Number");
            progressDialog.dismiss();  //if any error occurs then it need to be returned
            return;
        }
        if (useremail.isEmpty()) {
            email.requestFocus();
            email.setError("Please enter your email");
            progressDialog.dismiss();

            return;
        }
        if (!useremail.contains("@nitj.ac.in")) {
            email.requestFocus();
            email.setError("Please enter your College email");
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
        else if(!containsNumbers(userpassword)) {
            Toast.makeText(this, "Password must contain numbers", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }


        else if(userpassword.length() < 8){
            Toast.makeText(this, "Minimum length Required is 8 ", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;
        }
        else {
//            TO FIND THE SPECIAL CHARACTER IN PASSWORD
            int count=0;
            for (int i = 0; i < userpassword.length(); i++) {

                // Checking the character for not being a
                // letter,digit or space
                if (!Character.isDigit(userpassword.charAt(i)) && !Character.isLetter(userpassword.charAt(i))
                        && !Character.isWhitespace(userpassword.charAt(i))) {
                    // Incrementing the countr for spl
                    // characters by unity
                    count++;
                }
            }
            // When there is no special character encountered
            if (count == 0){
                Toast.makeText(this, "Password must contain special characters", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return ;
            }
        }
//    HERE THE OBJECT OF RETROFIT IS BEING MADE (that was made seprately in the video)
//    connect laptop wwith the Hostpot of the phone  and on wifi clicking change it to private discoverable mode
//    MAKE SURE TO CHANGE THE DISCOVERABLE DEVICE (PRIVATE MODE) IN WIFI SETTING OF PARTICULAR NETWORK
//    use the current ip address and start the server before and the set the ip address in the mongodb and
//    use CMD ipconfig to get the ip address and then set the WIFI LAN ADAPTER Wifi IPV$ address
//    use "http://IP_ADDRESS:PORT_NUMBER/"
//
//        CREATE THE INSTANCE OF THE DATA-MODAL
//        DATA MODAL WILL CONTAIN THE INFORMATION TO BE SEND FROM THE CLIENT SIDE IN THE POST REQUEST
        DataModel modal = new DataModel(rollno, useremail, userpassword);
        Call<DataModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .register(modal);
//ENQUEUE THE REQUEST AND USE callback<DataModal>()
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                progressDialog.dismiss();

//                receive the response from the API DATA MODAL OBJECT
                DataModel responseFromAPi = response.body();
                if(response.isSuccessful()){
//                  this will run when status (200)
                    User user = responseFromAPi.getUser();  // now the user will contain the response user info (username,email,password)
                    if(responseFromAPi.getMessage().equals("false") && responseFromAPi.getError().equals("EMAIL already exists")){
                        email.setText("");
                        Toast.makeText(SignUpActivity.this, "Email already Registered\nTry different one", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        //Call intent to transefer to new login activity

                        Intent intent = new Intent(SignUpActivity.this, OTPActivity.class);
                        intent.putExtra("email",useremail);
//                        //this is used to clear the previous stack of activities so when back button pressed then previous activites
                        startActivity(intent);
                        finish();

                    }

                }else{
//                  this block will execute for status (500) when error is thrown
                    Toast.makeText(SignUpActivity.this, "Something went wrong\nTry again later", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                rollNumber.setText(t.getMessage());
            }
        });

    }

    private void switchonlogin() {
        Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
        startActivity(intent);
    }

    public static boolean containsNumbers(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        for (int i = 0; i < string.length(); ++i) {
            if (Character.isDigit(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
