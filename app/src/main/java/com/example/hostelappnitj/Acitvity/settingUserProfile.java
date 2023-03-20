package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
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
        setContentView(R.layout.activity_setting_user_profile);
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
        etBranch.setText(branch);
        etPhone.setText(phone);
        etUsername.setText(name);


    btncreateprofile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username , phone , address , branch ;
            username=etUsername.getText().toString();
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
                    User user = responseFromApi.getUser();
                    if(response.isSuccessful()){

                        etUsername.setText("");
                        etPhone.setText("");
                        etAddress.setText("");
                        etBranch.setText("");
    //    when it is successfull then
    sharedPrefManager.SaveUser(responseFromApi.getUser());  //this is used to save the user properties in the sharePrefManager
    Toast.makeText(settingUserProfile.this, "Profile updated", Toast.LENGTH_SHORT).show();
                        onBackPressed(); //to close the activity
                    }
                }
                @Override
                public void onFailure(Call<CreateProfileResponse> call, Throwable t) {
                    Toast.makeText(settingUserProfile.this, "SomeThing went wrong..", Toast.LENGTH_SHORT).show();
                }
            });
        }
    });

    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // In fragment class callback
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            // When an Image is picked
//            if (requestCode == RESULT_LOAD_IMAGE && null!=data) {
//                progressDialog = new ProgressDialog(getApplicationContext());
//                progressDialog.setTitle("UPLOADING...");
//                progressDialog.setMessage("New Profile...");
//                progressDialog.setCancelable(false);
//                progressDialog.show();
////                METHOD 2 OF UPLOADING AND DISPLAYING IMAGE IN IMAGE VIEW Here Cursor are used
//                selectedImage = data.getData();
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                cursor.moveToFirst();
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                picturePath = cursor.getString(columnIndex);
//                cursor.close();
//                //donot set the image here..set the image when it is stored in sharedPreference to avoid double reloading of image
////                profileImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//
//                uploadImage();
//
//            }
//        } catch (Exception e) {
//            progressDialog.dismiss();
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
//        }
//    }

//    private void uploadImage(){
//        File file = new File(picturePath);
//        id = sharedPrefManager.getUser().get_id();
//        Toast.makeText(this,"id: "+id,Toast.LENGTH_LONG).show();
//
//        //        uploading the data using Multipart-Form
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(),requestFile);
//
//        Call<RegisterResponse> call = RetrofitClient.getInstance().getApi()
//                .UploadProfile(id,body);
//
//        call.enqueue(new Callback<RegisterResponse>() {
//            @Override
//            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
//                responseFromApi= response.body();
//                if(response.isSuccessful()){
//                    Toast.makeText(settingUserProfile.this,"Image Uploaded Successfully",Toast.LENGTH_LONG).show();
//                    //the image got deleted when we change the fragment so we will keep it permanent bu adding it into sharedPreference
//                    //                      Update the avatar in the Shared Preference also
//                    sharedPrefManager.SaveUser(responseFromApi.getUser());
//                    String imageFromDatabase= sharedPrefManager.getUser().getAvatar();
//                    Picasso.get().load(imageFromDatabase).resize(650,650).centerCrop().into(profileImage);
//                    progressDialog.dismiss();
//                }else{
//                    progressDialog.dismiss();
//                    Toast.makeText(settingUserProfile.this,"Connection Lost",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RegisterResponse> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(settingUserProfile.this,t.getMessage(),Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//    }


}