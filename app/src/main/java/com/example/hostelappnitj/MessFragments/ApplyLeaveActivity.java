package com.example.hostelappnitj.MessFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.util.Pair;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.RegisterationActivity;
import com.example.hostelappnitj.Acitvity.SignInActivity;
import com.example.hostelappnitj.MainActivity;
import com.example.hostelappnitj.ModelResponse.DailyScannerModel;
import com.example.hostelappnitj.ModelResponse.leaveModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplyLeaveActivity extends AppCompatActivity {

    private AppCompatButton submitDateButton , setDateButton , btncountTotalDiets , btnCountDietPerMonth;
    String startDateString , endDateString ;
    SharedPrefManager sharedPrefManager;
    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;
    String startMeal , endMeal;
    TextToSpeech textToSpeech ;
    ProgressDialog progressDialog;
    String year_str;
    String selectMonthInNumber;

TextView txtStartDate , txtEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);

        submitDateButton = findViewById(R.id.submitDateButton);
        setDateButton = findViewById(R.id.getCalendarButton);
        txtStartDate=findViewById(R.id.txtStartDate);
        txtEndDate=findViewById(R.id.txtEndDate);
        btncountTotalDiets= findViewById(R.id.btnCountTotalMeal);
        btnCountDietPerMonth=findViewById(R.id.btnCountMealPerMonth);
        sharedPrefManager=new SharedPrefManager(ApplyLeaveActivity.this);
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();

        //        text To speech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Lenguage not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        // Set the maximum date to 25 days from the current date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 25);


        btncountTotalDiets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(ApplyLeaveActivity.this);
                progressDialog.setTitle("Checking Records");
                progressDialog.setMessage("Counting all diets\nHave patience.....");
                progressDialog.show();
                progressDialog.setCancelable(false);
                Calendar calendar = Calendar.getInstance();
                int year =  calendar.get(Calendar.YEAR);
                 year_str = String.valueOf(year);
                leaveModel model = new leaveModel(rollNumber,hostelName,year_str);
                String token = sharedPrefManager.getToken();
                Call<leaveModel>call = RetrofitClient.getInstance().getApi().countTotalDiet(model);
                call.enqueue(new Callback<leaveModel>() {
                    @Override
                    public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
                        progressDialog.dismiss();
                        leaveModel responseFromAPI = response.body();
                        if(response.isSuccessful()){

//                            Toast.makeText(ApplyLeaveActivity.this, "received", Toast.LENGTH_SHORT).show();
                            if(responseFromAPI.getMessage().equals("Total diet count retrieved successfully")){
//                                Toast.makeText(ApplyLeaveActivity.this, "total diets : "+responseFromAPI.getDietCount(), Toast.LENGTH_SHORT).show();
//                         add alert dialog box here
                                AlertDialog.Builder builder = new AlertDialog.Builder(ApplyLeaveActivity.this);
                                builder.setTitle("MESS RECORD");
                                builder.setMessage("RollNumber : "+rollNumber+"\nRoom Number : "+roomNumber +"\nHostel : "+hostelName + "\nYour total diets consumed till date since beginning of the semester are : "+responseFromAPI.getDietCount()+" diets");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();




                            }else{
                                Toast.makeText(ApplyLeaveActivity.this, "Error in fetching diets", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<leaveModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ApplyLeaveActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnCountDietPerMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showMonthSelectionDialog();
            }
        });


        setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the DatePickerDialog when the button is pressed
                showDatePickerDialog();
            }
        });

        Spinner dropdownStartMeal = findViewById(R.id.spinnerStartMeal);
        dropdownStartMeal.setPrompt("Start Meal");
        String[] items = new String[]{
                 "breakfast"
                , "lunch",
                "dinner"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdownStartMeal.setAdapter(adapter);
        dropdownStartMeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    startMeal = (String) parent.getItemAtPosition(position);
//                    Toast.makeText(ApplyLeaveActivity.this, startMeal, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        Spinner dropdownEndMeal = findViewById(R.id.spinnerEndMeal);
        dropdownEndMeal.setPrompt("End Meal");
        String[] items1 = new String[]{
                "breakfast"
                , "lunch",
                "dinner"
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
//set the spinners adapter to the previously created one.
        dropdownEndMeal.setAdapter(adapter1);
        dropdownEndMeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    endMeal = (String) parent.getItemAtPosition(position);
//                Toast.makeText(ApplyLeaveActivity.this, endMeal, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        submitDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(ApplyLeaveActivity.this);
                progressDialog.setTitle("Applying Leave");
                progressDialog.setMessage("Updating records....");
                progressDialog.show();
                progressDialog.setCancelable(false);

                if(txtStartDate.equals("Not selected yet") && txtEndDate.equals("Not selected yet")){
                    progressDialog.dismiss();
                    Toast.makeText(ApplyLeaveActivity.this, "Please select the Valid Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(startMeal.equals(null)){
                    progressDialog.dismiss();
                    Toast.makeText(ApplyLeaveActivity.this, "Please select start meal", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(endMeal.equals(null)){
                    progressDialog.dismiss();
                    Toast.makeText(ApplyLeaveActivity.this, "Please select end meal", Toast.LENGTH_SHORT).show();
                    return;
                }

                leaveModel model = new leaveModel(rollNumber,hostelName,roomNumber,startDateString,endDateString,startMeal,endMeal);
                String token = sharedPrefManager.getToken();
                Call<leaveModel> call = RetrofitClient.getInstance().getApi().applyLeave("Bearer " + token,model);
                call.enqueue(new Callback<leaveModel>() {
                    @Override
                    public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
                        leaveModel responseFromAPI = response.body();
                        progressDialog.dismiss();
//                        Toast.makeText(ApplyLeaveActivity.this, "received", Toast.LENGTH_SHORT).show();
                        if(response.isSuccessful()){
//                            Toast.makeText(ApplyLeaveActivity.this, " response received", Toast.LENGTH_SHORT).show();

                            if(responseFromAPI.getMessage().equals("Leave cant be applied")){
                                Toast.makeText(ApplyLeaveActivity.this, "End date is not valid", Toast.LENGTH_SHORT).show();
                            }
                            if(responseFromAPI.getMessage().equals("Leave applied successfully")){
                                Toast.makeText(ApplyLeaveActivity.this, "Leave has been applied Successfully", Toast.LENGTH_SHORT).show();
                                String speak =  " Your Leave has been applied";
                                textToSpeech.setSpeechRate(1);
                                textToSpeech.speak(speak, TextToSpeech.QUEUE_FLUSH,null);
                            }
                        } else{
//                                check if token is not verified
                            if(response.code() == 500) {
                                // Unauthorized - Token is invalid or expired
                                // Redirect user to login screen or take appropriate action
                                AlertDialog.Builder builder = new AlertDialog.Builder(ApplyLeaveActivity.this);
                                builder.setTitle("ALERT");
                                builder.setMessage("Your Session expired\nPlease login Again");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(ApplyLeaveActivity.this, SignInActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
//                                        dialogInterface.dismiss();
                                        return;
                                    }
                                }).show();
                                // Redirect to login screen or logout user
                            } else {
                                // Handle other HTTP error codes
                                Toast.makeText(ApplyLeaveActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<leaveModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ApplyLeaveActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void showDatePickerDialog() {

        Calendar currentDate = Calendar.getInstance();

// Set the range for 25 days after the current date
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.DAY_OF_MONTH, 25);

        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.DAY_OF_MONTH, 0);

// Create constraints for the date range
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                .setStart(currentDate.getTimeInMillis())
                .setEnd(maxDate.getTimeInMillis())
                .setValidator(DateValidatorPointForward.from(minDate.getTimeInMillis()));

        constraintsBuilder.setStart(currentDate.getTimeInMillis());
        constraintsBuilder.setEnd(maxDate.getTimeInMillis());

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setCalendarConstraints(constraintsBuilder.build()); // to set the constraints

        builder.setTitleText("Select a Start-Date & End-Date for leave");
        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();
        datePicker.addOnPositiveButtonClickListener(selection -> {

            // Retrieving the selected start and end dates
            Long startDate = selection.first;
            Long endDate = selection.second;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            startDateString = sdf.format(new Date(startDate));
            endDateString = sdf.format(new Date(endDate));

            txtStartDate.setText(startDateString);
            txtEndDate.setText(endDateString);
            // Creating the date range string
            String selectedDateRange = startDateString + " - " + endDateString;

            // Displaying the selected date range in the TextView
            Toast.makeText(this, selectedDateRange, Toast.LENGTH_SHORT).show();
        });

        // Showing the date picker dialog
        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");

    }

    private void showMonthSelectionDialog() {
        // Get the months array
        final String[] months = getResources().getStringArray(R.array.months);

        // Create a dialog with a spinner
        AlertDialog.Builder builder = new AlertDialog.Builder(ApplyLeaveActivity.this);
        builder.setTitle("Select a Month");

        // Inflate the layout containing the Spinner
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_month_selection, null);

        // Find the Spinner in the layout
        final Spinner spinnerMonths = dialogLayout.findViewById(R.id.spinnerMonths);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, months);


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerMonths.setAdapter(adapter);

        // Set the layout for the dialog
        builder.setView(dialogLayout);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get the selected month
                String selectedMonthName = spinnerMonths.getSelectedItem().toString();
                selectMonthInNumber="";
                switch (selectedMonthName){
                    case "January":
                        selectMonthInNumber = "1";
                        break;
                    case "February":
                        selectMonthInNumber = "2";
                        break;
                    case "March":
                        selectMonthInNumber = "3";
                        break;
                    case "April":
                        selectMonthInNumber = "4";
                        break;
                    case "May":
                        selectMonthInNumber = "5";
                        break;
                    case "June":
                        selectMonthInNumber = "6";
                        break;
                    case "July":
                        selectMonthInNumber = "7";
                        break;
                    case "August":
                        selectMonthInNumber = "8";
                        break;
                    case "September":
                        selectMonthInNumber = "9";
                        break;
                    case "October":
                        selectMonthInNumber = "10";
                        break;
                    case "November":
                        selectMonthInNumber = "11";
                        break;
                    case "December":
                        selectMonthInNumber = "12";
                        break;


                }
                // Display a Toast with the selected month
//                Toast.makeText(ApplyLeaveActivity.this, "Selected Month: " + selectedMonthName, Toast.LENGTH_SHORT).show();
//                Toast.makeText(ApplyLeaveActivity.this, "Selected Month: " + selectMonthInNumber, Toast.LENGTH_SHORT).show();


//                if(selectMonthInNumber.equals("-1")){
//                    Toast.makeText(ApplyLeaveActivity.this, "Select Valid Month", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                progressDialog = new ProgressDialog(ApplyLeaveActivity.this);
                progressDialog.setTitle("Checking Records");
                progressDialog.setMessage("Counting diets\nHave patience.....");
                progressDialog.show();
                progressDialog.setCancelable(false);
                leaveModel model = new leaveModel(rollNumber,hostelName,year_str,selectMonthInNumber);
                Call<leaveModel>call = RetrofitClient.getInstance().getApi().countDietPerMonth(model);
                call.enqueue(new Callback<leaveModel>() {
                    @Override
                    public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
                        progressDialog.dismiss();
                        leaveModel responseFromAPI = response.body();
                        if(response.isSuccessful()){

//                            Toast.makeText(ApplyLeaveActivity.this, "received", Toast.LENGTH_SHORT).show();
                            if(responseFromAPI.getMessage().equals("Total diet count retrieved successfully")){
//                                Toast.makeText(ApplyLeaveActivity.this, "total diets : "+responseFromAPI.getDietCount(), Toast.LENGTH_SHORT).show();
//                         add alert dialog box here
                                AlertDialog.Builder builder = new AlertDialog.Builder(ApplyLeaveActivity.this);
                                builder.setTitle("MESS RECORD");
                                builder.setMessage("RollNumber : "+rollNumber+"\nRoom Number : "+roomNumber +"\nHostel : "+hostelName +"\nMonth : "+selectedMonthName + "\nYour monthly diets consumed are : "+responseFromAPI.getDietCount()+" diets");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();
                            }else{
                                Toast.makeText(ApplyLeaveActivity.this, "Error in fetching diets", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<leaveModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ApplyLeaveActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        builder.setNegativeButton("Cancel", null);

        // Show the dialog
        builder.setCancelable(false);
        builder.show();
    }


}