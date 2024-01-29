package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.fetchmealRecord;
import com.example.hostelappnitj.ModelResponse.mealRecord;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.example.hostelappnitj.userAdapterMess;
import com.example.hostelappnitj.userAdapterMessRecord;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessRecordList_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<mealRecord> mealList;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_record_list);

        recyclerView = findViewById(R.id.recyleview);

        sharedPrefManager=new SharedPrefManager(MessRecordList_Activity.this);
//        rollNumber = sharedPrefManager.getUser().getRollNumber();
//        hostelName=sharedPrefManager.getHostelUser().getHostelName();
//        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();

        recyclerView.setLayoutManager(new LinearLayoutManager(MessRecordList_Activity.this));

        progressDialog = new ProgressDialog(MessRecordList_Activity.this);
        progressDialog.setTitle("Checking Records..");
        progressDialog.setMessage("Counting all diets\nHave patience.....");
        progressDialog.show();
        progressDialog.setCancelable(false);

        String hostelName1= "Boys Hostel 7";
        fetchmealRecord model = new fetchmealRecord(hostelName1);
        Call<fetchmealRecord> call = RetrofitClient.getInstance().getApi().getMessDietRecord(model);
        call.enqueue(new Callback<fetchmealRecord>() {
            @Override
            public void onResponse(Call<fetchmealRecord> call, Response<fetchmealRecord> response) {
                progressDialog.dismiss();

                if(response.isSuccessful()){
                    String msg = response.body().getMessage();
                    Toast.makeText(MessRecordList_Activity.this, msg, Toast.LENGTH_SHORT).show();
                    mealList = response.body().getMealList();
                    recyclerView.setAdapter(new userAdapterMessRecord(MessRecordList_Activity.this, mealList));
                }else{
                    Toast.makeText(MessRecordList_Activity.this, "Record Not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<fetchmealRecord> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MessRecordList_Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}