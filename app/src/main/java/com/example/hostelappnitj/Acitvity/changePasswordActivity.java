package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.UpdateUserResponse;
import com.example.hostelappnitj.ModelResponse.resetPasswordModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changePasswordActivity extends AppCompatActivity {
    EditText etnewpassword;
    AppCompatButton btnChangePassword;
    ProgressDialog progressDialog;
    String email;
    String newPassword;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        btnChangePassword=findViewById(R.id.btnchangepassword);
        etnewpassword=findViewById(R.id.etnewPassword);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");


        btnChangePassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(changePasswordActivity.this);
                progressDialog.setTitle("UPDATING");
                progressDialog.setMessage("Updating Password...");
                progressDialog.show();
                progressDialog.setCancelable(false);

                newPassword = etnewpassword.getText().toString();

                if (newPassword.isEmpty()) {
                    etnewpassword.requestFocus();
                    Toast.makeText(changePasswordActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                if(!containsNumbers(newPassword)) {
                    Toast.makeText(changePasswordActivity.this, "Password must contain numbers", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }

                if(newPassword.length()<8){
                    etnewpassword.requestFocus();
                    Toast.makeText(changePasswordActivity.this, "Minimum length Required is 8", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                else {
//            TO FIND THE SPECIAL CHARACTER IN PASSWORD
                    int count=0;
                    for (int i = 0; i < newPassword.length(); i++) {

                        // Checking the character for not being a
                        // letter,digit or space
                        if (!Character.isDigit(newPassword.charAt(i)) && !Character.isLetter(newPassword.charAt(i))
                                && !Character.isWhitespace(newPassword.charAt(i))) {
                            // Incrementing the countr for spl
                            // characters by unity
                            count++;
                        }
                    }
                    // When there is no special character encountered
                    if (count == 0){
                        Toast.makeText(changePasswordActivity.this, "Password must contain special characters", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        return ;
                    }else{
//                loadingPB.setVisibility(View.VISIBLE);
                    resetPasswordModel model = new resetPasswordModel(email,newPassword);
                    Call<resetPasswordModel> call = RetrofitClient.getInstance().getApi().resetpassword(model);
                    call.enqueue(new Callback<resetPasswordModel>() {
                        @Override
                        public void onResponse(Call<resetPasswordModel> call, Response<resetPasswordModel> response) {
                            progressDialog.dismiss();

                            if(response.isSuccessful()){
                                if(response.body().getMessage().equals("user not registered")){
                                    Toast.makeText(changePasswordActivity.this, "User Haven't registered", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    return ;
                                }
                                if(response.body().getMessage().equals("Password updated successfully")){
                                    progressDialog.dismiss();
                                    Toast.makeText(changePasswordActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(changePasswordActivity.this , SignInActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<resetPasswordModel> call, Throwable t) {
                            Toast.makeText(changePasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    });

                    }
                }


            }


        });
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