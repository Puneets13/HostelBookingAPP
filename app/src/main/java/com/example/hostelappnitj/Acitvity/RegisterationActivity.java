package com.example.hostelappnitj.Acitvity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.HostelRegisterationResponse;
import com.example.hostelappnitj.ModelResponse.RegisterResponse;
import com.example.hostelappnitj.ModelResponse.hostel;
import com.example.hostelappnitj.ModelResponse.hostel_ID_Response;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterationActivity extends AppCompatActivity {
    String hostelName, username, rollNumber, email, branch, hostel_id, year, gender,roomNum;
    TextView txtUsername, txtRollNumber, txtEmail, txtHostelname, txtBranch;
    EditText etRoomNumber, etFatherName, etFatherPhone, etStdPhone, etAddress;
    AppCompatButton btnBook_room, btnPayment;
    ProgressDialog progressDialog;
    RadioButton btn1, btn2, btn3, btn4;
    RadioGroup radioGroup, radioGroupGender;
    Spinner spinnerBranch;
SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        txtHostelname = findViewById(R.id.txtHostelname);
        txtEmail = findViewById(R.id.txtEmail);
        txtUsername = findViewById(R.id.txtUsername);
        txtRollNumber = findViewById(R.id.txtRollno);
        txtBranch = findViewById(R.id.txtBranch);
        etRoomNumber = findViewById(R.id.etRoomNumber);
        etFatherName = findViewById(R.id.etFathername);
        etFatherPhone = findViewById(R.id.etFatherPhone);
        etStdPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnBook_room = findViewById(R.id.btnBookRoom);
//        btnPayment = findViewById(R.id.btnPayment);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        //        intent from SeatMatrixMBH B activity
        Intent intent = getIntent();
        hostelName = intent.getStringExtra("hostelName");
        username = intent.getStringExtra("username");
        rollNumber = intent.getStringExtra("rollNumber");
        email = intent.getStringExtra("email");
        branch = intent.getStringExtra("branch");
        roomNum=intent.getStringExtra("roomNum");

        txtRollNumber.setText(rollNumber);
        txtEmail.setText(email);
        txtHostelname.setText(hostelName);
        txtUsername.setText(username);
        txtBranch.setText(branch);
        Toast.makeText(this, roomNum, Toast.LENGTH_SHORT).show();
        etRoomNumber.setText(roomNum);
sharedPrefManager=new SharedPrefManager(RegisterationActivity.this);

//        setting spinner for branch
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinnerBranch);
        dropdown.setPrompt("Branches");
        String[] items = new String[]{"Select.."
                , "Mechanical Engineering"
                , "Chemical Engineering",
                "Chemistry",
                "Civil Engineering",
                "Computer Science & Engg.",
                "Electrical Engineering",
                "Electronics & Comm. Engg.",
                "Humanities & Management",
                "Industrial & Production Engg.",
                "Information Technology",
                "Instrumentation & Control Engg.",
                "Mathematics",
                "Mechanical Engineering",
                "Physics",
                "Textile Technology"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.v("item", (String) parent.getItemAtPosition(position));
                if (position == 0) {
                    branch = null;
                } else {
                    branch = (String) parent.getItemAtPosition(position);
//                Toast.makeText(RegisterationActivity.this, branch, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // on below line we are getting radio button from our group.
                RadioButton radioButton = findViewById(checkedId);

                // on below line we are displaying a toast message.
                year = radioButton.getText().toString();
            }
        });

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // on below line we are getting radio button from our group.
                RadioButton radioButton = findViewById(checkedId);

                // on below line we are displaying a toast message.
//                Toast.makeText(RegisterationActivity.this, radioButton.getText()+" Gender", Toast.LENGTH_SHORT).show();
                gender = radioButton.getText().toString();

            }
        });
//        click listener on button
        btnBook_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(RegisterationActivity.this);
                progressDialog.setTitle("Hostel Booking");
                progressDialog.setMessage("Registering Room..");
                progressDialog.show();
                progressDialog.setCancelable(false);
                String roomNo, fatherName, fatherPhone, address, phone;
                roomNo = etRoomNumber.getText().toString();
                fatherName = etFatherName.getText().toString();
                fatherPhone = etFatherPhone.getText().toString();
                phone = etStdPhone.getText().toString();
                address = etAddress.getText().toString();
                String room_substring = roomNo.substring(Math.max(roomNo.length() - 2, 0));
                int room_int=Integer.parseInt(room_substring);
                if (roomNo.isEmpty()) {
                    etRoomNumber.requestFocus();
                    etRoomNumber.setError("Please enter required Room Number");
                    progressDialog.dismiss();
                    return;
                }
                if(room_int>=46 || room_int<=01){
                    etRoomNumber.requestFocus();
                    etRoomNumber.setError("Please enter Valid Room Number");
                    progressDialog.dismiss();
                    return;
                }
                if (fatherName.isEmpty()) {
                    etFatherName.requestFocus();
                    etFatherName.setError("Please enter your Father's Name");
                    progressDialog.dismiss();
                    return;
                }
                if (fatherPhone.isEmpty()) {
                    etFatherPhone.requestFocus();
                    etFatherPhone.setError("Please enter your Father's Phone");
                    progressDialog.dismiss();
                    return;
                }
                if (phone.isEmpty()) {
                    etStdPhone.requestFocus();
                    etStdPhone.setError("Please enter Student's Phone");
                    progressDialog.dismiss();
                    return;
                }
                if (address.isEmpty()) {
                    etAddress.requestFocus();
                    etAddress.setError("Please enter Address");
                    progressDialog.dismiss();
                    return;
                }
//
                //        uploading the data using Multipart-Form
//                                                File file = new File(picturePath);
//                                                RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"),file);
//                                                MultipartBody.Part body = MultipartBody.Part.createFormData("pdf", file.getName(),requestFile);

                HostelRegisterationResponse model = new HostelRegisterationResponse(username, email, rollNumber, roomNo, hostelName, phone, address, branch, fatherPhone, fatherName, year);
                Call<HostelRegisterationResponse> call = RetrofitClient.getInstance().getApi()
                        .updateHostelRecord(model);
                call.enqueue(new Callback<HostelRegisterationResponse>() {
                    @Override
                    public void onResponse(Call<HostelRegisterationResponse> call, Response<HostelRegisterationResponse> response) {
                        progressDialog.dismiss();
                        HostelRegisterationResponse responseFromApi = response.body();
                        if (response.isSuccessful()) {
                            if (responseFromApi.getMessage().equals("success")) {
                                progressDialog.dismiss();
                                sharedPrefManager.SaveHostelUser(responseFromApi.getHostel());
//                                etAddress.setText(sharedPrefManager.getHostelUser().getHostelName());
//                                etFatherName.setText(sharedPrefManager.getHostelUser().getRoomNumber());

                                //this is used to save the user properties in the sharePrefManager
                                Toast.makeText(RegisterationActivity.this, responseFromApi.getHostel().getRoomNumber(), Toast.LENGTH_SHORT).show();
//                                finish(); //to remove the current activity
                            } else if(responseFromApi.getMessage().equals("Room Not Available")){
                                Toast.makeText(RegisterationActivity.this, "Room Not Available..", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterationActivity.this, responseFromApi.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HostelRegisterationResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // In fragment class callback
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            // When an Image is picked
//            if (requestCode == SELECT_PDF && null!=data) {
////                progressDialog = new ProgressDialog(getActivity());
////                progressDialog.setTitle("UPLOADING...");
////                progressDialog.setMessage("New Profile...");
////                progressDialog.setCancelable(false);
////                progressDialog.show();
////                METHOD 2 OF UPLOADING AND DISPLAYING IMAGE IN IMAGE VIEW Here Cursor are used
//                selectedImage = data.getData();
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                Cursor cursor = RegisterationActivity.this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                cursor.moveToFirst();
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                picturePath = cursor.getString(columnIndex);
//                cursor.close();
//                //donot set the image here..set the image when it is stored in sharedPreference to avoid double reloading of image
////                profileImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//
//
//            }
//        } catch (Exception e) {
////            progressDialog.dismiss();
//            Toast.makeText(RegisterationActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        //PDF
//        if (resultCode == RESULT_OK) {
//            if (requestCode == SELECT_PDF) {
//                Uri selectedUri_PDF = data.getData();
//                SelectedPDF = getPDFPath(selectedUri_PDF);
////                file = new File(getPath(selectedUri_PDF));
//            }
//        }
//    }


//    public String getPath(Uri uri)
//    {
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
//        if (cursor == null) return null;
//        int column_index =             cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String s=cursor.getString(column_index);
//        cursor.close();
//        return s;
//    }
//    public String getPDFPath(Uri uri){
//
//        final String id = DocumentsContract.getDocumentId(uri);
//        final Uri contentUri = ContentUris.withAppendedId(Uri.parse(sPath), Long.valueOf(id));
//
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }
//    private void extractPDF() {
//        try {
//            // creating a string for
//            // storing our extracted text.
//            String extractedText = "";
//
//            // creating a variable for pdf reader
//            // and passing our PDF file in it.
//            PdfReader reader = new PdfReader(SelectedPDF);
//
//            // below line is for getting number
//            // of pages of PDF file.
//            int n = reader.getNumberOfPages();
//            Toast.makeText(this, n, Toast.LENGTH_SHORT).show();
//            // running a for loop to get the data from PDF
//            // we are storing that data inside our string.
//            for (int i = 0; i < n; i++) {
//                extractedText = extractedText + PdfTextExtractor.getTextFromPage(reader, i + 1).trim() + "\n";
//                // to extract the PDF content from the different pages
//            }
//
//            // after extracting all the data we are
//            // setting that string value to our text view.
//            etAddress.setText(extractedText);
//
//            // below line is used for closing reader.
//            reader.close();
//        } catch (Exception e) {
//            // for handling error while extracting the text file.
////            extractedTV.setText("Error found is : \n" + e);
//        }
//    }


////getting the id of the room from the ROOM NUMBER using the api
//                Call<hostel_ID_Response> call = RetrofitClient
//                        .getInstance().getApi().getHostelId(roomNo);
//                call.enqueue(new Callback<hostel_ID_Response>() {
//                    @Override
//                    public void onResponse(Call<hostel_ID_Response> call, Response<hostel_ID_Response> response) {
//
//                      hostel_ID_Response hostel_id_response = response.body();
//                        if(response.isSuccessful()){
//                            if(hostel_id_response.getMessage().equals("Found")){
//                                hostel_id=hostel_id_response.getHostel().get_id();
////                                etAddress.setText(hostel_id);
//                            }else{
//                                Toast.makeText(RegisterationActivity.this, "Invalid Room Number", Toast.LENGTH_SHORT).show();
////                                Snackbar.make(,"Invalid Room Number",Snackbar.LENGTH_LONG).show();
//                                etRoomNumber.setText("");
//                                progressDialog.dismiss();
//                                return;
//                            }
//
//                        }
//                        else{
//                            Toast.makeText(RegisterationActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
//                           progressDialog.dismiss();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<hostel_ID_Response> call, Throwable t) {
//                        Toast.makeText(RegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
////                to fetch the id from another api call ..so pausing for sometime so using HANDLER
//                Handler handler = new Handler();
//                Runnable runnable = new Runnable() {
//                    public void run() {
//                        HostelRegisterationResponse model = new HostelRegisterationResponse(username,email,rollNumber,phone,branch,address,fatherPhone,fatherName);
//                        Call<HostelRegisterationResponse> call1 = RetrofitClient
//                                .getInstance().getApi().updateHostelRecord(hostel_id,model);
//
//                        call1.enqueue(new Callback<HostelRegisterationResponse>() {
//                            @Override
//                            public void onResponse(Call<HostelRegisterationResponse> call, Response<HostelRegisterationResponse> response) {
//                                HostelRegisterationResponse hostelRegisterationResponse = response.body();
//                                if (response.isSuccessful()) {
//                                    if(hostelRegisterationResponse.getStatus().equals("Failed")){
//                                        progressDialog.dismiss();
//                                        etRoomNumber.setText("");
//                                        etAddress.setText("");
//                                        etFatherName.setText("");
//                                        etFatherPhone.setText("");
//                                        etStdPhone.setText("");
//                                        Toast.makeText(RegisterationActivity.this, "Student Already Booked the Room", Toast.LENGTH_SHORT).show();
//                                        return;
//                                    }
//                                    else if(hostelRegisterationResponse.getStatus().equals("Not available")){
//                                        progressDialog.dismiss();
//                                        etRoomNumber.setText("");
//                                        Toast.makeText(RegisterationActivity.this, "Room Not Available", Toast.LENGTH_SHORT).show();
//                                        return;
//                                    }
//                                    else{
//                                        progressDialog.dismiss();
//                                        Toast.makeText(RegisterationActivity.this, roomNo+" booked for "+ username, Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<HostelRegisterationResponse> call, Throwable t) {
//                                progressDialog.dismiss();
//                                Toast.makeText(RegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();    }
//                        });
//
//                    }
//                };
//                handler.postDelayed(runnable, 2000);
//
//            }
//        });
//
//
