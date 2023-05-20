package com.example.hostelappnitj.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.RegisterationActivity;
import com.example.hostelappnitj.Acitvity.settingUserProfile;
import com.example.hostelappnitj.ModelResponse.HostelRegisterationResponse;
import com.example.hostelappnitj.ModelResponse.RegisterResponse;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
TextView username , rollNumber,roomNumber , branch,hostelName , email , phone;
ImageView imgProfile ;
FloatingActionButton btnChangeProfileImg ;
FloatingActionButton btnChangeProfile ;
LinearLayout roomLayout;
String stringEmail;
SharedPrefManager sharedPrefManager;
    String picturePath , id;
    RegisterResponse responseFromApi;
    ProgressDialog progressDialog;
    Uri selectedImage ;



    private static int RESULT_LOAD_IMAGE = 1;
    public static final String TAG = "Test";
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        username=view.findViewById(R.id.txt_username);
        rollNumber=view.findViewById(R.id.txt_rollNumber);
        roomNumber=view.findViewById(R.id.roomNumber);
        branch=view.findViewById(R.id.txt_Branch);
        hostelName=view.findViewById(R.id.hostelName);
        email=view.findViewById(R.id.txt_email);
        phone=view.findViewById(R.id.txt_phone);
        imgProfile=view.findViewById(R.id.profileImage);
        btnChangeProfileImg=view.findViewById(R.id.changeProfile);
        btnChangeProfile=view.findViewById(R.id.editProfile);
        roomLayout=view.findViewById(R.id.roomlinearLayout);
        sharedPrefManager= new SharedPrefManager(getActivity());
        email.setText(sharedPrefManager.getUser().getEmail());
        username.setText(sharedPrefManager.getUser().getUsername());
        rollNumber.setText(sharedPrefManager.getUser().getRollNumber());

        stringEmail = sharedPrefManager.getUser().getEmail();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Downloading...");
        progressDialog.setMessage("Loading Profile...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        if(sharedPrefManager.getUser().getPhone()==null){
            phone.setText("Update Profile");
        }else{
            phone.setText(sharedPrefManager.getUser().getPhone());
        }

        if(sharedPrefManager.getUser().getAddress()==null){
            phone.setText("Update Profile");
        }else{
            branch.setText(sharedPrefManager.getUser().getAddress());
        }

        if(sharedPrefManager.getAdmin().equals("Admin")){
            phone.setVisibility(View.INVISIBLE);
            btnChangeProfile.setVisibility(View.GONE);
            branch.setVisibility(View.GONE);
            rollNumber.setVisibility(View.GONE);
            email.setGravity(Gravity.CENTER);
        }

        roomLayout.setVisibility(View.INVISIBLE);


        String imageFromDatabase= sharedPrefManager.getUser().getAvatar();
////center crop is use to not the image to be streched when resized
        Picasso.get().load(imageFromDatabase).resize(550,550).centerCrop().into(imgProfile);




        btnChangeProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {

                    Intent galleryIntent = new Intent(   Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);   //to start an activity from the Fragment
                }else {
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

                }
            }
        });

        btnChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), settingUserProfile.class);
                intent.putExtra("name",username.getText().toString());
                intent.putExtra("phone",phone.getText().toString());
                intent.putExtra("branch",branch.getText().toString());
                startActivity(intent);
            }
        });


        loadDataRooms();

        return view ;
    }

    private void loadDataRooms() {
        HostelRegisterationResponse model = new HostelRegisterationResponse(stringEmail);
        Call<HostelRegisterationResponse> call = RetrofitClient.getInstance().getApi().getRoomsRuntime(model);
        call.enqueue(new Callback<HostelRegisterationResponse>() {
            @Override
            public void onResponse(Call<HostelRegisterationResponse> call, Response<HostelRegisterationResponse> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body().getMessage().equals("user 1 found")){
                        roomLayout.setVisibility(View.VISIBLE);
                        roomNumber.setText( response.body().getHostel().getRoomNumber());
                        hostelName.setText( response.body().getHostel().getHostelName());
                        phone.setText(response.body().getPhone());
                        branch.setText(response.body().getBranch());


                    }
                    else if(response.body().getMessage().equals("user 2 found")){
                        roomLayout.setVisibility(View.VISIBLE);
                        roomNumber.setText( response.body().getHostel().getRoomNumber());
                        hostelName.setText( response.body().getHostel().getHostelName());
                        phone.setText(response.body().getPhone());
                        branch.setText(response.body().getBranch());
                        progressDialog.dismiss();


                    }else{
                        roomLayout.setVisibility(View.INVISIBLE);
                        phone.setText(response.body().getPhone());
                        branch.setText(response.body().getBranch());
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<HostelRegisterationResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //    The onResume() get called always before the fragment is displayed. Even when the fragment is created for the first time .
    @Override
    public void onResume() {
        super.onResume();
        String imageFromDatabase= sharedPrefManager.getUser().getAvatar();
////center crop is use to not the image to be streched when resized
        Picasso.get().load(imageFromDatabase).resize(550,550).centerCrop().into(imgProfile);
        username.setText(sharedPrefManager.getUser().getUsername());
        rollNumber.setText(sharedPrefManager.getUser().getRollNumber());
        phone.setText(sharedPrefManager.getUser().getPhone());
        branch.setText(sharedPrefManager.getUser().getBranch());

        loadDataRooms();  // to load the rooms
    }


    //on activity result when browse file button is clicked then the activity will start from here
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // In fragment class callback
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMAGE && null!=data) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("UPLOADING...");
                progressDialog.setMessage("New Profile...");
                progressDialog.setCancelable(false);
                progressDialog.show();
//                METHOD 2 OF UPLOADING AND DISPLAYING IMAGE IN IMAGE VIEW Here Cursor are used
                selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
                //donot set the image here..set the image when it is stored in sharedPreference to avoid double reloading of image
//                profileImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                uploadImage();

            }
        } catch (Exception e) {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Something went wrong..", Toast.LENGTH_LONG).show();
        }
    }


    private void uploadImage(){
        File file = new File(picturePath);
        id = sharedPrefManager.getUser().get_id();
        //        uploading the data using Multipart-Form
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(),requestFile);

        Call<RegisterResponse> call = RetrofitClient.getInstance().getApi()
                .UploadProfile(id,body);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                responseFromApi= response.body();
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),"Image Uploaded Successfully",Toast.LENGTH_LONG).show();
                    //the image got deleted when we change the fragment so we will keep it permanent bu adding it into sharedPreference
                    //                      Update the avatar in the Shared Preference also
                    sharedPrefManager.SaveUser(responseFromApi.getUser());
                    String imageFromDatabase= sharedPrefManager.getUser().getAvatar();
                    Picasso.get().load(imageFromDatabase).resize(650,650).centerCrop().into(imgProfile);
                    progressDialog.dismiss();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(),"Connection Lost",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }


}