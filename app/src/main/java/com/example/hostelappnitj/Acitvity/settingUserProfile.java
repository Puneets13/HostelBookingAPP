package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelappnitj.Fragments.ProfileFragment;
import com.example.hostelappnitj.MainActivity;
import com.example.hostelappnitj.ModelResponse.CreateProfileResponse;
import com.example.hostelappnitj.ModelResponse.DataModel;
import com.example.hostelappnitj.ModelResponse.RegisterResponse;
import com.example.hostelappnitj.ModelResponse.User;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class settingUserProfile extends AppCompatActivity {
EditText etUsername , etPhone , etAddress , etBranch;
//CircleImageView profileImage ;
FloatingActionButton btnChangeProfileImg ;
Button btncreateprofile ;
    SharedPrefManager sharedPrefManager;
    String picturePath , id;
    RegisterResponse responseFromApi;
    ProgressDialog progressDialog;
    Uri selectedImage ;
    private static int RESULT_LOAD_IMAGE = 1;
    public static final String TAG = "Test";
    String branch,name,roll,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  //To make the NIGHT MODE disabled
        setContentView(R.layout.activity_setting_user_profile);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        etUsername=findViewById(R.id.etusername);
        etPhone=findViewById(R.id.etPhone);
        etAddress=findViewById(R.id.etAddress);
        etBranch=findViewById(R.id.etBranch);
        btncreateprofile=findViewById(R.id.btnCreateProfile);

//        btnChangeProfileImg=findViewById(R.id.changeProfile);
        sharedPrefManager= new SharedPrefManager(this.getApplicationContext());
//        String imageFromDatabase= sharedPrefManager.getUser().getAvatar();
//////center crop is use to not the image to be streched when resized
//        Picasso.get().load(imageFromDatabase).resize(550,550).centerCrop().into(profileImage);


//        btnChangeProfileImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(ContextCompat.checkSelfPermission(settingUserProfile.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
//
//                    Intent galleryIntent = new Intent(   Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);   //to start an activity from the Fragment
//                }else {
//                    ActivityCompat.requestPermissions(settingUserProfile.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//
//                }
//            }
//        });
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
        branch=intent.getStringExtra("branch");
        phone=intent.getStringExtra("phone");
//        etBranch.setText(branch);
//        etPhone.setText(phone);
//        etUsername.setText(name);


    btncreateprofile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username , phone , address , branch ,username1;
            username1=etUsername.getText().toString();
            username=username1.toUpperCase();
            username.trim(); //to reomve extra space at front and back if present

            phone=etPhone.getText().toString();
            address=etAddress.getText().toString();
            branch=etBranch.getText().toString();
            if (username.isEmpty()) {
                etUsername.requestFocus();
                etUsername.setError("Please enter your Username");
                return;
            }
            if (branch.isEmpty()) {
                etBranch.requestFocus();
                etBranch.setError("Please enter your Branch");
                return;
            }

            if (phone.isEmpty()) {
                etPhone.requestFocus();
                etPhone.setError("Please enter your Phone Number");
                return;
            }
            if (address.isEmpty()) {
                etAddress.requestFocus();
                etAddress.setError("Please enter your Address");
                return;
            }
            progressDialog = new ProgressDialog(settingUserProfile.this);
            progressDialog.setTitle("UPLOADING...");
            progressDialog.setMessage("New Profile...");
            progressDialog.setCancelable(false);
            progressDialog.show();
//                    Creting the request for POST
            String _id = "id";
                  _id=sharedPrefManager.getUser().get_id();
            CreateProfileResponse model = new CreateProfileResponse(username,phone,address,branch);
            Call<CreateProfileResponse> call= RetrofitClient
                    .getInstance()
                    .getApi()
                    .createUserProfile(_id,model);

            call.enqueue(new Callback<CreateProfileResponse>() {
                @Override
                public void onResponse(Call<CreateProfileResponse> call, Response<CreateProfileResponse> response) {
                    CreateProfileResponse responseFromApi = response.body();
                    progressDialog.dismiss();
                    User user = responseFromApi.getUser();
                    if(response.isSuccessful()){

                        etUsername.setText("");
                        etPhone.setText("");
                        etAddress.setText("");
                        etBranch.setText("");
                        progressDialog.dismiss();

                        //    when it is successfull then
     sharedPrefManager.SaveUser(responseFromApi.getUser());  //this is used to save the user properties in the sharePrefManager
    Toast.makeText(settingUserProfile.this, "Profile updated", Toast.LENGTH_SHORT).show();
                        onBackPressed(); //to close the activity
                    }
                }
                @Override
                public void onFailure(Call<CreateProfileResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(settingUserProfile.this, "SomeThing went wrong..", Toast.LENGTH_SHORT).show();
                }
            });
        }
    });

    }
}