//package com.example.hostelappnitj.Acitvity;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.widget.Toast;
//
//import com.example.hostelappnitj.ModelResponse.fetchmealRecord;
//import com.example.hostelappnitj.ModelResponse.mealRecord;
//import com.example.hostelappnitj.R;
//import com.example.hostelappnitj.RetrofitClient;
//import com.example.hostelappnitj.SharedPrefManager;
//import com.example.hostelappnitj.userAdapterMess;
//import com.example.hostelappnitj.userAdapterMessRecord;
//
//import java.net.URISyntaxException;
//import java.util.List;
//
//import io.socket.client.IO;
//import io.socket.client.Socket;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//
//public class MessRecordList_Activity extends AppCompatActivity {
//    RecyclerView recyclerView;
//    List<mealRecord> mealList;
//    ProgressDialog progressDialog;
//    SharedPrefManager sharedPrefManager;
//    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;
//
//    private final Handler handler = new Handler(Looper.getMainLooper());
//
//    private final Runnable updateRunnable = new Runnable() {
//        @Override
//        public void run() {
//            // Call the method to fetch updated data from MongoDB and update the RecyclerView
//            fetchDataAndUpdateRecyclerView();
//
//            // Schedule the next update after a delay (e.g., every 2 seconds)
//            handler.postDelayed(this, 2000); // Adjust the delay as needed
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mess_record_list);
//        recyclerView = findViewById(R.id.recyleview);
//
//        sharedPrefManager=new SharedPrefManager(MessRecordList_Activity.this);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(MessRecordList_Activity.this);
//        layoutManager.setReverseLayout(true); // Reverse the layout to start from the top
//        layoutManager.setStackFromEnd(true);  // Stack items from the bottom
//        recyclerView.setLayoutManager(layoutManager);
//
////        recyclerView.setAdapter(new userAdapterMessRecord(MessRecordList_Activity.this, mealList));
//
//
////        recyclerView.setLayoutManager(new LinearLayoutManager(MessRecordList_Activity.this));
////        layoutManager.setReverseLayout(true); // Reverse the layout to start from the top
////        layoutManager.setStackFromEnd(true);  // Stack items from the bottom
//
//
//        progressDialog = new ProgressDialog(MessRecordList_Activity.this);
//        progressDialog.setTitle("Checking Records..");
//        progressDialog.setMessage("Counting all diets\nHave patience.....");
//        progressDialog.show();
//        progressDialog.setCancelable(false);
////         Start the initial update
//        handler.post(updateRunnable);
//
//
//    }
//    private void fetchDataAndUpdateRecyclerView() {
//        // Implement the logic to fetch updated data from MongoDB and update the RecyclerView
//        // You can use AsyncTask, Retrofit, or any other method to make network requests
//        // Update your RecyclerView adapter here
//        String hostelName1= "Boys Hostel 7";
//
//        fetchmealRecord model = new fetchmealRecord(hostelName1);
//        Call<fetchmealRecord> call = RetrofitClient.getInstance().getApi().getMessDietRecord(model);
//        call.enqueue(new Callback<fetchmealRecord>() {
//            @Override
//            public void onResponse(Call<fetchmealRecord> call, Response<fetchmealRecord> response) {
//                progressDialog.dismiss();
//
//                if(response.isSuccessful()){
//                    String msg = response.body().getMessage();
//                    Toast.makeText(MessRecordList_Activity.this, msg, Toast.LENGTH_SHORT).show();
//                    mealList = response.body().getMealList();
//                    recyclerView.setAdapter(new userAdapterMessRecord(MessRecordList_Activity.this, mealList));
//                }else{
//                    Toast.makeText(MessRecordList_Activity.this, "Record Not found", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<fetchmealRecord> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(MessRecordList_Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        // Stop the periodic updates when the activity is destroyed
//        handler.removeCallbacks(updateRunnable);
//        super.onDestroy();
//    }
//
//}




package com.example.hostelappnitj.Acitvity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.fetchmealRecord;
import com.example.hostelappnitj.ModelResponse.mealRecord;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.example.hostelappnitj.userAdapterMess;
import com.example.hostelappnitj.userAdapterMessRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    String hostelName1="Boys Hostel 7";
    String meal_received="";



    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            // Call the method to fetch updated data from MongoDB and update the RecyclerView
            fetchDataAndUpdateRecyclerView();

            // Schedule the next update after a delay (e.g., every 5 seconds)
            handler.postDelayed(this, 3000); // Adjust the delay as needed
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_record_list);

        recyclerView = findViewById(R.id.recyleview);

        sharedPrefManager=new SharedPrefManager(MessRecordList_Activity.this);
//        rollNumber = sharedPrefManager.getUser().getRollNumber();
//        hostelName1=sharedPrefManager.getHostelUser().getHostelName();
//        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();

        recyclerView.setLayoutManager(new LinearLayoutManager(MessRecordList_Activity.this));

        progressDialog = new ProgressDialog(MessRecordList_Activity.this);
        progressDialog.setTitle("Checking Records..");
        progressDialog.setMessage("Counting all diets\nHave patience.....");
        progressDialog.show();
        progressDialog.setCancelable(false);

        handler.post(updateRunnable);

    }


    private void fetchDataAndUpdateRecyclerView() {
        // Implement the logic to fetch updated data from MongoDB and update the RecyclerView
        // You can use AsyncTask, Retrofit, or any other method to make network requests
        // Update your RecyclerView adapter here

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = dateFormat.format(currentDate);

        // Extract year, month, and day
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        year = yearFormat.format(currentDate);

        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        month = monthFormat.format(currentDate);

        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        int day = Integer.parseInt(dayFormat.format(currentDate));

        // Format the time
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String formattedTime = timeFormat.format(currentDate);

        // Parse the formatted time to get hours and minutes
        int hour = Integer.parseInt(formattedTime.split(":")[0]);
        int minute = Integer.parseInt(formattedTime.split(":")[1]);
        mealType ="";
        // Check the time ranges and print appropriate messages
//                if (hour >= 7 && hour < 11) {  // 7:00 AM to 11:00 AM
////                    System.out.println("Good Morning!");
//                    mealType = "breakfast";
//                }
        if (hour >= 7 && hour < 11) {  // 7:00 AM to 11:00 AM
//                    System.out.println("Good Morning!");
            meal_received = "breakfast";
        }
        else if (hour >= 12 && hour < 15) {   // 12:00PM to 3:00PM
//                  System.out.println("Good Afternoon!");
            meal_received = "lunch";
        }
//                else if ((hour >= 19 && hour < 22 && minute >= 30) || (hour == 22 && minute <= 30)) { // 7:30 PM to 10:30 PM
////                    System.out.println("Good Evening!");
//                    mealType = "dinner";
//                }
        else if ((hour >= 0 && hour<2) ) { // 7:30 PM to 10:30 PM
//                    System.out.println("Good Evening!");
            meal_received = "dinner";
        }
        else {
//                    System.out.println("Hello!");
            Toast.makeText(this, "Visit in Mess Timings", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("ALERT");
            builder.setMessage("Mess Closed\nPlease visit in Meal Timings\nHappy to see you for next meal. "+mealType);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }

        fetchmealRecord model = new fetchmealRecord(hostelName1,meal_received);
        Call<fetchmealRecord> call = RetrofitClient.getInstance().getApi().getMessDietRecord(model);
        call.enqueue(new Callback<fetchmealRecord>() {
            @Override
            public void onResponse(Call<fetchmealRecord> call, Response<fetchmealRecord> response) {
                progressDialog.dismiss();

                if(response.isSuccessful()){
                    String msg = response.body().getMessage();
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





    @Override
    protected void onDestroy() {
        // Stop the periodic updates when the activity is destroyed
        handler.removeCallbacks(updateRunnable);
        super.onDestroy();
    }
}