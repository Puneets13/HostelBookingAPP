package com.example.hostelappnitj.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hostelappnitj.MGH_Girls.MghSeatMatrixFloor6;
import com.example.hostelappnitj.ModelResponse.fetchStudentList;
import com.example.hostelappnitj.ModelResponse.person;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.UserAdapter;
import com.example.hostelappnitj.UserAdapter1;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentList_Admin_HomeActivity extends AppCompatActivity {
    String studentName ;
    RecyclerView recyclerView;
    List<person> userList;
    ProgressDialog progressDialog ;
    private DialogInterface.OnClickListener dialogClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_student_list_admin_home);


        Intent intent = getIntent();
        studentName = intent.getStringExtra("studentName");
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait for a while...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String hostelName =   "Mega Boys Hostel A";
        recyclerView=findViewById(R.id.recyleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentList_Admin_HomeActivity.this));

        fetchStudentList model = new fetchStudentList(studentName);

        Call<fetchStudentList> call = RetrofitClient.getInstance().getApi().fetchStudentListHome(model);


        call.enqueue(new Callback<fetchStudentList>() {
            @Override
            public void onResponse(Call<fetchStudentList> call, Response<fetchStudentList> response) {
                if(response.isSuccessful()){
                    if(response.body().getMessage().equals("has users")){
                        progressDialog.dismiss();
                        userList=response.body().getUserList();
                        recyclerView.setAdapter(new UserAdapter1(StudentList_Admin_HomeActivity.this, userList));
                    }
                    else if (response.body().getMessage().equals("no user found")){

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
                                        break;

                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(StudentList_Admin_HomeActivity.this);
                        // on below line we are setting message for our dialog box.
                        builder.setMessage("No User Found..")
                                .setTitle("NITJ HOSTELS")
                                .setPositiveButton("Yes", dialogClickListener)
                                .show();
                    }
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(StudentList_Admin_HomeActivity.this, "Something went wrong..", Toast.LENGTH_LONG);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<fetchStudentList> call, Throwable t) {
                Toast.makeText(StudentList_Admin_HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }
}