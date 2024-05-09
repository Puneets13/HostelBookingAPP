package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hostelappnitj.MessFragments.ApplyLeaveActivity;
import com.example.hostelappnitj.ModelResponse.User;
import com.example.hostelappnitj.ModelResponse.fetchmealRecord;
import com.example.hostelappnitj.ModelResponse.mealRecord;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.example.hostelappnitj.UserAdapter;
import com.example.hostelappnitj.userAdapterMess;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dietRecordActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<mealRecord> mealList;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_record);
        recyclerView = findViewById(R.id.recyleview);

        sharedPrefManager=new SharedPrefManager(dietRecordActivity.this);
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();

//        hostelName = "Boys Hostel 7";
        recyclerView.setLayoutManager(new LinearLayoutManager(dietRecordActivity.this));


        progressDialog = new ProgressDialog(dietRecordActivity.this);
        progressDialog.setTitle("Checking Records");
        progressDialog.setMessage("Counting all diets\nHave patience.....");
        progressDialog.show();
        progressDialog.setCancelable(false);

        fetchmealRecord model = new fetchmealRecord(rollNumber,hostelName,roomNumber);
        String token = sharedPrefManager.getToken();
        Call<fetchmealRecord> call = RetrofitClient.getInstance().getApi().getMealRecord("Bearer " + token,model);
        call.enqueue(new Callback<fetchmealRecord>() {
            @Override
            public void onResponse(Call<fetchmealRecord> call, Response<fetchmealRecord> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                mealList = response.body().getMealList();
                    recyclerView.setAdapter(new userAdapterMess(dietRecordActivity.this, mealList));
                } else{
//                                check if token is not verified
                    if(response.code() == 500) {
                        // Unauthorized - Token is invalid or expired
                        // Redirect user to login screen or take appropriate action
                        AlertDialog.Builder builder = new AlertDialog.Builder(dietRecordActivity.this);
                        builder.setTitle("ALERT");
                        builder.setMessage("Your Session expired\nPlease login Again");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                return;
                            }
                        }).show();
                        // Redirect to login screen or logout user
                    } else {
                        // Handle other HTTP error codes
                        Toast.makeText(dietRecordActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<fetchmealRecord> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(dietRecordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}