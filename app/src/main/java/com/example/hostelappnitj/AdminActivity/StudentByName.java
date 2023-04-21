package com.example.hostelappnitj.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
                            Toast.makeText(StudentByName.this, "Rooms are Vacant..", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
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

    }
}