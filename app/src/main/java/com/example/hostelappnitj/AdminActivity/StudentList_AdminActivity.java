package com.example.hostelappnitj.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hostelappnitj.MGH_Girls.MghSeatMatrixFloor6;
import com.example.hostelappnitj.ModelResponse.User;
import com.example.hostelappnitj.ModelResponse.fetchStudentList;
import com.example.hostelappnitj.ModelResponse.person;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StudentList_AdminActivity extends AppCompatActivity {
String studentName , hostelname;
    RecyclerView recyclerView;
    List<person> userList;
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_admin);

        Intent intent = getIntent();
        studentName = intent.getStringExtra("studentName");
        hostelname=intent.getStringExtra("hostelName");
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait for a while...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String hostelName =   "Mega Boys Hostel A";
        recyclerView=findViewById(R.id.recyleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentList_AdminActivity.this));

fetchStudentList model = new fetchStudentList(studentName,hostelname);

        Call<fetchStudentList> call = RetrofitClient.getInstance().getApi().fetchStudentList(model);
        call.enqueue(new Callback<fetchStudentList>() {
            @Override
            public void onResponse(Call<fetchStudentList> call, Response<fetchStudentList> response) {
                if(response.isSuccessful()){
                    if(response.body().getMessage().equals("has users")){
                        progressDialog.dismiss();
                        userList=response.body().getUserList();
                        recyclerView.setAdapter(new UserAdapter(StudentList_AdminActivity.this, userList));
                    }
                    if (response.body().getMessage().equals("no user found")){
                        Toast.makeText(StudentList_AdminActivity.this, "No Such User Exist..", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        finish();
                    }
                }
                    else {
                    progressDialog.dismiss();
                    Toast.makeText(StudentList_AdminActivity.this, "Something went wrong..", Toast.LENGTH_LONG);
                    finish();
                    }

            }

            @Override
            public void onFailure(Call<fetchStudentList> call, Throwable t) {
                Toast.makeText(StudentList_AdminActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}