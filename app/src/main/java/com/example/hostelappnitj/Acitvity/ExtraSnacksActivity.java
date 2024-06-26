package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.ItemListAdapter;
import com.example.hostelappnitj.MainActivity;
import com.example.hostelappnitj.ModelResponse.DailyScannerModel;
import com.example.hostelappnitj.ModelResponse.constantsModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
//import com.example.hostelappnitj.item_list_adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtraSnacksActivity extends AppCompatActivity {

    TextToSpeech textToSpeech ;

    private Map<String, Integer> itemMap;

    AppCompatButton btnConfirmPayment;
    String rollNumber , hostelName , roomNumber;
    String email ,  month , year , mealType , formattedDate;
    int amount=0;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;
    TextView txtHostelname;
    private ListView listView;
    private ItemListAdapter adapter;
    String formattedTime1 ;
    List<String> items_list ,  checkedItems , item ;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_snacks);
//        etExtraAmount = findViewById(R.id.etExtrasAmount)
        btnConfirmPayment = findViewById(R.id.btnExtrasPayment);
        txtHostelname = findViewById(R.id.txtHostelname);


        // Initialize RecyclerView and layout manager
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        DateFormat dateFormat2 = new SimpleDateFormat("hh.mm aa");
        String dateString2 = dateFormat2.format(new Date()).toString();

        sharedPrefManager=new SharedPrefManager(ExtraSnacksActivity.this);
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();
        txtHostelname.setText(hostelName);
        itemMap = new HashMap<>();
        items_list = new ArrayList<>();
        checkedItems = new ArrayList<>();
        item = new ArrayList<>();

//        GETTING THE LIST FROM MONGODB
        constantsModel model = new constantsModel(hostelName);

//        StringBuilder item_string = new StringBuilder();
//        Toast.makeText(this, model.getHostelName()+"", Toast.LENGTH_SHORT).show();


        progressDialog = new ProgressDialog(ExtraSnacksActivity.this);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Loading items..");
        progressDialog.show();
        progressDialog.setCancelable(false);

        String token = sharedPrefManager.getToken();
        Call<constantsModel> call = RetrofitClient.getInstance().getApi().getMessItemsList("Bearer " + token,model);
            call.enqueue(new Callback<constantsModel>() {
                @Override
                public void onResponse(Call<constantsModel> call, Response<constantsModel> response) {
                    if(response.isSuccessful()){
                        if(response.body().getMessage().equals("success")){
                            progressDialog.dismiss();
                            itemMap = response.body().getItem();
                            for (Map.Entry<String, Integer> entry : itemMap.entrySet()) {
                               String item_price =  entry.getKey() + ": " + entry.getValue();
                               items_list.add(item_price);

                            }
                            Collections.sort(items_list);
                            adapter = new ItemListAdapter(ExtraSnacksActivity.this, items_list);
                            recyclerView.setAdapter(adapter);

                        }  else{
//                                check if token is not verified
                            if(response.code() == 500) {
                                // Unauthorized - Token is invalid or expired
                                // Redirect user to login screen or take appropriate action
                                AlertDialog.Builder builder = new AlertDialog.Builder(ExtraSnacksActivity.this);
                                builder.setTitle("ALERT");
                                builder.setMessage("Your Session expired\nPlease login Again");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        Intent intent = new Intent(ExtraSnacksActivity.this, SignInActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                        return;
                                    }
                                }).show();
                                // Redirect to login screen or logout user
                            } else {
                                // Handle other HTTP error codes
                                Toast.makeText(ExtraSnacksActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<constantsModel> call, Throwable t) {
                    Toast.makeText(ExtraSnacksActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        //        text To speech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                // Check the time ranges and set the meal type accordingly
                if (hour >= 7 && hour < 10) {  // 7:00 AM to 10:00 AM
                    mealType = "breakfast";
                }
                else if (hour == 10 && minute <= 30) { // 10:00 AM to 10:30 AM
                    mealType = "breakfast";
                }
                else if (hour > 12 && hour < 15) { // 12:00 PM to 15:00 PM
                    mealType = "lunch";
                }
                else if (hour >= 17 && hour < 18) { // 5:00 PM to 6:00 PM
                    mealType = "snacks";
                }
                else if (hour == 18 && minute <= 30) { // 6:00 PM to 6:30 PM
                    mealType = "snacks";
                }
                else if (hour >= 19 && hour < 22) { // 7:00 PM to 10:00 PM
                    mealType = "dinner";
                }
                else if (hour == 22 && minute <= 15) { // 10:00 PM to 10:15 PM
                    mealType = "dinner";
                }
                else {
//                    System.out.println("Hello!");
                    Toast.makeText(ExtraSnacksActivity.this, "Visit in Mess Timings", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(ExtraSnacksActivity.this);
                    builder.setTitle("ALERT");
                    builder.setMessage("Mess Closed\nPlease visit in Meal Timings\nHappy to see you for next meal. "+mealType);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    }).show();
                }


//                mealType = "dinner";
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");

                // Get the current date and time
                Date currentDate1 = new Date();

                // Format the current time using SimpleDateFormat
                formattedTime1 = dateFormat1.format(currentDate1);

                checkedItems = adapter.getCheckedItems();  // getting this from adapter
                item = checkedItems;
                if(checkedItems.size()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ExtraSnacksActivity.this);
                    builder.setTitle("ALERT");
                    builder.setMessage("You haven't selected any items\nPlease add items to move forward\n");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            return;
                        }
                    }).show();
                }
                else{


                    progressDialog = new ProgressDialog(ExtraSnacksActivity.this);
                    progressDialog.setTitle("Processing");
                    progressDialog.setMessage("Transaction in progress..");
                    progressDialog.show();
                    progressDialog.setCancelable(false);


                    //                TO SUM UP THE AMOUNT STUDENT SELECTED
                    if (itemMap != null) { // Check if itemMap is not null before accessing its values
                        for(String item_got : checkedItems){
                            String[] parts = item_got.split(":");
                            String itemName = parts[0].trim(); // Trim any leading or trailing spaces

                            Integer itemValue = itemMap.get(itemName);
                            if (itemValue != null) { // Check if itemValue is not null before accessing its intValue
                                amount += itemValue.intValue();
                            }
                        }
                    }


//                    CONVERT DATE TO UTC FORMAT FOR THIS TO MATCH TIME ZONE
                    // Get the current date and time
                    Date currentDate3 = new Date();
                    // Create a date formatter for UTC time zone
                    SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    // Format the date as a string in UTC
                    String utcDateTimeString = dateFormat3.format(currentDate3);

                    DailyScannerModel model = new DailyScannerModel(rollNumber,roomNumber,hostelName,month,year,mealType,utcDateTimeString,formattedTime1,item,amount);

//                    DailyScannerModel model = new DailyScannerModel(rollNumber,roomNumber,hostelName,month,year,mealType,formattedDate,formattedTime1,item,amount);
                  //adding AUTH JWT
                    String token = sharedPrefManager.getToken();
                    Call<DailyScannerModel> call = RetrofitClient.getInstance().getApi().getExtraMeal("Bearer " + token,model);
                    call.enqueue(new Callback<DailyScannerModel>() {
                        @Override
                        public void onResponse(Call<DailyScannerModel> call, Response<DailyScannerModel> response) {
                            progressDialog.dismiss();
                            DailyScannerModel responseFromAPI = response.body();
                            if(response.isSuccessful()){
                                if(responseFromAPI.getMessage().equals("you have consumed extras")){

//                                    String speak = amount +"coins deducted.   +Enjoy your meal";
                                    String speak = "Enjoy your meal";

                                    textToSpeech.setSpeechRate(1);
                                    textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                                    Intent intent = new Intent(ExtraSnacksActivity.this, successScanActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();

                                }else if(responseFromAPI.getMessage().equals("you are on leave")){
                                    Toast.makeText(ExtraSnacksActivity.this, "You are on leave", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(ExtraSnacksActivity.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
//                                check if token is not verified
                                if(response.code() == 500) {
                                    // Unauthorized - Token is invalid or expired
                                    // Redirect user to login screen or take appropriate action
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ExtraSnacksActivity.this);
                                    builder.setTitle("ALERT");
                                    builder.setMessage("Your Session expired\nPlease login Again");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            Intent intent = new Intent(ExtraSnacksActivity.this, SignInActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                            return;
                                        }
                                    }).show();
                                    // Redirect to login screen or logout user
                                } else {
                                    // Handle other HTTP error codes
                                    Toast.makeText(ExtraSnacksActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DailyScannerModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(ExtraSnacksActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                }

                }

            }
        });
    }
}