package com.example.hostelappnitj.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.DataModel;
import com.example.hostelappnitj.ModelResponse.fetchAllStudentList;
import com.example.hostelappnitj.ModelResponse.person;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.UserAdapter;
import com.example.hostelappnitj.UserAdapter1;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentByName extends AppCompatActivity {
    String studentName , hostelname;
    RecyclerView recyclerView;
    List<person> userList;
    ProgressDialog progressDialog ;
    AppCompatButton btndeleteUsers;
    private DialogInterface.OnClickListener dialogClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_by_name);

        Intent intent = getIntent();
        hostelname=intent.getStringExtra("hostelName");
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait for a while...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        btndeleteUsers = findViewById(R.id.btndelete);
        recyclerView=findViewById(R.id.recyleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentByName.this));

        fetchAllStudentList model = new fetchAllStudentList(hostelname);
        Call<fetchAllStudentList> call = RetrofitClient.getInstance().getApi().fetchAllStudents(model);
        call.enqueue(new Callback<fetchAllStudentList>() {
            @Override
            public void onResponse(Call<fetchAllStudentList> call, Response<fetchAllStudentList> response) {
                if(response.isSuccessful()){
                    if(response.body().getMessage().equals("has users")){
                        progressDialog.dismiss();
                        userList=response.body().getUserList();
                        recyclerView.setAdapter(new UserAdapter1(StudentByName.this, userList));
                    }

                        if (response.body().getMessage().equals("no user found")){
                            progressDialog.dismiss();
//                        show the dialog box
                            dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        // on below line we are setting a click listener
                                        // for our positive button
                                        case DialogInterface.BUTTON_POSITIVE:
                                            finish();
                                            dialog.dismiss();
                                            break;
                                    }
                                }
                            };

                            AlertDialog.Builder builder = new AlertDialog.Builder(StudentByName.this);
                            // on below line we are setting message for our dialog box.
                            builder.setMessage("Rooms are Vacant..")
                                    .setTitle("NITJ HOSTELS")
                                    .setPositiveButton("Yes", dialogClickListener)
                                    .show();
                        }
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(StudentByName.this, "Something went wrong..", Toast.LENGTH_LONG);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<fetchAllStudentList> call, Throwable t) {
                Toast.makeText(StudentByName.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        btndeleteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(StudentByName.this);
                progressDialog.setTitle("Deleting Database...");
                progressDialog.setMessage("Wait for a while...");
                progressDialog.show();
                progressDialog.setCancelable(false);

                DataModel model = new DataModel(hostelname);
                Call<DataModel> call = RetrofitClient.getInstance().getApi().deleteUsers(model);
                call.enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        Toast.makeText(StudentByName.this, "received", Toast.LENGTH_SHORT).show();
                        if(response.isSuccessful()){
                            if(response.body().getMessage().equals("success")){
                                progressDialog.dismiss();
                                Toast.makeText(StudentByName.this, "Students List deleted", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(StudentByName.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(StudentByName.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });


    }
}