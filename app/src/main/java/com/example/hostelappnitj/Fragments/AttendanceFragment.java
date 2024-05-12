//package com.example.hostelappnitj.Fragments;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.GridView;
//import android.widget.TextView;
//
//import androidx.fragment.app.Fragment;
//
//import com.example.hostelappnitj.CalendarAdapter;
//import com.example.hostelappnitj.ModelResponse.CalendarDay;
//import com.example.hostelappnitj.R;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Locale;
//
//public class AttendanceFragment extends Fragment {
//
//    private GridView gridViewCalendar;
//    private CalendarAdapter calendarAdapter;
//
//    private TextView tvMonthYear;
//    private Button btnPrevMonth, btnNextMonth;
//    private Calendar currentMonthCalendar;
//
//    public AttendanceFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
//
//        gridViewCalendar = view.findViewById(R.id.gridViewCalendar);
//        tvMonthYear = view.findViewById(R.id.tvMonthYear);
//        btnPrevMonth = view.findViewById(R.id.btnPrevMonth);
//        btnNextMonth = view.findViewById(R.id.btnNextMonth);
//
//        // Set current month calendar
//        currentMonthCalendar = Calendar.getInstance();
//
//        // Display initial calendar
//        updateCalendar();
//
//        // Set listeners for navigation buttons
//        btnPrevMonth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentMonthCalendar.add(Calendar.MONTH, -1);
//                updateCalendar();
//            }
//        });
//
//        btnNextMonth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentMonthCalendar.add(Calendar.MONTH, 1);
//                updateCalendar();
//            }
//        });
//
//        return view;
//    }
//
//    private void updateCalendar() {
//        // Update month name in header
//        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
//        tvMonthYear.setText(sdf.format(currentMonthCalendar.getTime()));
//
//        // Get current month and year
//        int currentMonth = currentMonthCalendar.get(Calendar.MONTH);
//        int currentYear = currentMonthCalendar.get(Calendar.YEAR);
//
//        // Generate calendar days for current month
//        List<String> days = new ArrayList<>();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(currentMonthCalendar.getTimeInMillis());
//        calendar.set(Calendar.DAY_OF_MONTH, 1); // Set calendar to the first day of the month
//
//        int maxDaysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//        // Add days of the current month
//        for (int i = 1; i <= maxDaysOfMonth; i++) {
//            days.add(String.valueOf(i));
//        }
//
//        // Add empty placeholders for previous month's days
//        int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Day of week of the 1st day
//        for (int i = Calendar.SUNDAY; i < startDayOfWeek; i++) {
//            days.add(""); // Add empty placeholders for previous month's days
//        }
//
//        // Set adapter to populate the GridView
//        calendarAdapter = new CalendarAdapter(getActivity(), days, currentMonth, currentYear);
//        gridViewCalendar.setAdapter(calendarAdapter);
//    }
//
//
//}




//
//<!--<?xml version="1.0" encoding="utf-8"?>-->
//        <!--<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"-->
//        <!--    android:layout_width="match_parent"-->
//        <!--    android:layout_height="match_parent">-->
//
//        <!--    &lt;!&ndash; Month Name and Navigation Buttons &ndash;&gt;-->
//        <!--    <LinearLayout-->
//        <!--        android:id="@+id/layoutHeader"-->
//        <!--        android:layout_width="match_parent"-->
//        <!--        android:layout_height="wrap_content"-->
//        <!--        android:orientation="horizontal"-->
//        <!--        android:gravity="center">-->
//
//        <!--        <Button-->
//        <!--            android:id="@+id/btnPrevMonth"-->
//        <!--            android:layout_width="wrap_content"-->
//        <!--            android:layout_height="wrap_content"-->
//        <!--            android:text="◀" />-->
//
//        <!--        <TextView-->
//        <!--            android:id="@+id/tvMonthYear"-->
//        <!--            android:layout_width="wrap_content"-->
//        <!--            android:layout_height="wrap_content"-->
//        <!--            android:textSize="20sp"-->
//        <!--            android:textStyle="bold"-->
//        <!--            android:layout_marginStart="16dp"-->
//        <!--            android:layout_marginEnd="16dp"-->
//        <!--            android:text="Month Year" />-->
//
//        <!--        <Button-->
//        <!--            android:id="@+id/btnNextMonth"-->
//        <!--            android:layout_width="wrap_content"-->
//        <!--            android:layout_height="wrap_content"-->
//        <!--            android:text="▶" />-->
//
//        <!--    </LinearLayout>-->
//
//        <!--    &lt;!&ndash; Calendar GridView &ndash;&gt;-->
//        <!--    <GridView-->
//        <!--        android:id="@+id/gridViewCalendar"-->
//        <!--        android:layout_width="match_parent"-->
//        <!--        android:layout_height="match_parent"-->
//        <!--        android:numColumns="7"-->
//        <!--        android:horizontalSpacing="1dp"-->
//        <!--        android:verticalSpacing="1dp"-->
//        <!--        android:stretchMode="columnWidth"-->
//        <!--        android:gravity="center"-->
//        <!--        android:layout_below="@id/layoutHeader" />-->
//
//        <!--</RelativeLayout>-->
//






//
//
//
//package com.example.hostelappnitj.Fragments;
//
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.speech.tts.TextToSpeech;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.GridView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.widget.AppCompatButton;
//import androidx.core.util.Pair;
//import androidx.fragment.app.Fragment;
//
//import com.example.hostelappnitj.CalendarAdapter;
//import com.example.hostelappnitj.MessFragments.ApplyLeaveActivity;
//import com.example.hostelappnitj.ModelResponse.CalendarDay;
//import com.example.hostelappnitj.ModelResponse.leaveModel;
//import com.example.hostelappnitj.R;
//import com.example.hostelappnitj.RetrofitClient;
//import com.example.hostelappnitj.SharedPrefManager;
//import com.google.android.material.datepicker.CalendarConstraints;
//import com.google.android.material.datepicker.DateValidatorPointForward;
//import com.google.android.material.datepicker.MaterialDatePicker;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class AttendanceFragment extends Fragment {
//
//
//    private AppCompatButton submitDateButton , setDateButton , btncountTotalDiets , btnCountDietPerMonth;
//    String startDateString , endDateString ;
//    SharedPrefManager sharedPrefManager;
//    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;
//    String startMeal , endMeal;
//    TextToSpeech textToSpeech ;
//    ProgressDialog progressDialog;
//    String year_str;
//    String selectMonthInNumber;
//
//    TextView txtStartDate , txtEndDate;
//
//
//
//    public AttendanceFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
//
//
//        submitDateButton = view.findViewById(R.id.submitDateButton);
//        setDateButton = view.findViewById(R.id.getCalendarButton);
//        txtStartDate = view.findViewById(R.id.txtStartDate);
//        txtEndDate= view.findViewById(R.id.txtEndDate);
//        btncountTotalDiets= view.findViewById(R.id.btnCountTotalMeal);
//        btnCountDietPerMonth=  view.findViewById(R.id.btnCountMealPerMonth);
//        sharedPrefManager=new SharedPrefManager(getActivity());
//        rollNumber = sharedPrefManager.getUser().getRollNumber();
//        hostelName=sharedPrefManager.getHostelUser().getHostelName();
//        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();
//
//
//
//
//        //        text To speech
//        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
//
//            @Override
//            public void onInit(int i) {
//                if (i == TextToSpeech.SUCCESS) {
//                    int result = textToSpeech.setLanguage(Locale.US);
//                    if (result == TextToSpeech.LANG_MISSING_DATA ||
//                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                        Log.e("TTS", "Lenguage not supported");
//                    }
//                } else {
//                    Log.e("TTS", "Initialization failed");
//                }
//            }
//        });
//
//        // Set the maximum date to 25 days from the current date
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, 25);
//
//
//        btncountTotalDiets.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressDialog = new ProgressDialog(getActivity());
//                progressDialog.setTitle("Checking Records");
//                progressDialog.setMessage("Counting all diets\nHave patience.....");
//                progressDialog.show();
//                progressDialog.setCancelable(false);
//                Calendar calendar = Calendar.getInstance();
//                int year =  calendar.get(Calendar.YEAR);
//                year_str = String.valueOf(year);
//                leaveModel model = new leaveModel(rollNumber,hostelName,year_str);
//                Call<leaveModel>call = RetrofitClient.getInstance().getApi().countTotalDiet(model);
//                call.enqueue(new Callback<leaveModel>() {
//                    @Override
//                    public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
//                        progressDialog.dismiss();
//                        leaveModel responseFromAPI = response.body();
//                        if(response.isSuccessful()){
//
////                            Toast.makeText(getActivity(), "received", Toast.LENGTH_SHORT).show();
//                            if(responseFromAPI.getMessage().equals("Total diet count retrieved successfully")){
////                                Toast.makeText(getActivity(), "total diets : "+responseFromAPI.getDietCount(), Toast.LENGTH_SHORT).show();
////                         add alert dialog box here
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                builder.setTitle("MESS RECORD");
//                                builder.setMessage("RollNumber : "+rollNumber+"\nRoom Number : "+roomNumber +"\nHostel : "+hostelName + "\nYour total diets consumed till date since beginning of the semester are : "+responseFromAPI.getDietCount()+" diets");
//                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        dialogInterface.dismiss();
//                                    }
//                                }).show();
//
//
//
//
//                            }else{
//                                Toast.makeText(getActivity(), "Error in fetching diets", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<leaveModel> call, Throwable t) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//        btnCountDietPerMonth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                showMonthSelectionDialog();
//            }
//        });
//
//
//        setDateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Show the DatePickerDialog when the button is pressed
//                showDatePickerDialog();
//            }
//        });
//
//        Spinner dropdownStartMeal =  view.findViewById(R.id.spinnerStartMeal);
//        dropdownStartMeal.setPrompt("Start Meal");
//        String[] items = new String[]{
//                "breakfast"
//                , "lunch",
//                "dinner"
//        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
////set the spinners adapter to the previously created one.
//        dropdownStartMeal.setAdapter(adapter);
//        dropdownStartMeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                startMeal = (String) parent.getItemAtPosition(position);
////                    Toast.makeText(getActivity(), startMeal, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//
//
//        Spinner dropdownEndMeal =  view.findViewById(R.id.spinnerEndMeal);
//        dropdownEndMeal.setPrompt("End Meal");
//        String[] items1 = new String[]{
//                "breakfast"
//                , "lunch",
//                "dinner"
//        };
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
////set the spinners adapter to the previously created one.
//        dropdownEndMeal.setAdapter(adapter1);
//        dropdownEndMeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                endMeal = (String) parent.getItemAtPosition(position);
////                Toast.makeText(getActivity(), endMeal, Toast.LENGTH_SHORT).show();
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//
//
//        submitDateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressDialog = new ProgressDialog(getActivity());
//                progressDialog.setTitle("Applying Leave");
//                progressDialog.setMessage("Updating records....");
//                progressDialog.show();
//                progressDialog.setCancelable(false);
//
//                if(txtStartDate.equals("Not selected yet") && txtEndDate.equals("Not selected yet")){
//                    progressDialog.dismiss();
//                    Toast.makeText(getActivity(), "Please select the Valid Date", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(startMeal.equals(null)){
//                    progressDialog.dismiss();
//                    Toast.makeText(getActivity(), "Please select start meal", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(endMeal.equals(null)){
//                    progressDialog.dismiss();
//                    Toast.makeText(getActivity(), "Please select end meal", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                leaveModel model = new leaveModel(rollNumber,hostelName,roomNumber,startDateString,endDateString,startMeal,endMeal);
//                Call<leaveModel> call = RetrofitClient.getInstance().getApi().applyLeave(model);
//                call.enqueue(new Callback<leaveModel>() {
//                    @Override
//                    public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
//                        leaveModel responseFromAPI = response.body();
//                        progressDialog.dismiss();
////                        Toast.makeText(getActivity(), "received", Toast.LENGTH_SHORT).show();
//                        if(response.isSuccessful()){
////                            Toast.makeText(getActivity(), " response received", Toast.LENGTH_SHORT).show();
//
//                            if(responseFromAPI.getMessage().equals("Leave cant be applied")){
//                                Toast.makeText(getActivity(), "End date is not valid", Toast.LENGTH_SHORT).show();
//                            }
//                            if(responseFromAPI.getMessage().equals("Leave applied successfully")){
//                                Toast.makeText(getActivity(), "Leave has been applied Successfully", Toast.LENGTH_SHORT).show();
//                                String speak =  " Your Leave has been applied";
//                                textToSpeech.setSpeechRate(1);
//                                textToSpeech.speak(speak, TextToSpeech.QUEUE_FLUSH,null);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<leaveModel> call, Throwable t) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//        return view;
//    }
//
//
//    private void showDatePickerDialog() {
//
//        Calendar currentDate = Calendar.getInstance();
//
//// Set the range for 25 days after the current date
//        Calendar maxDate = Calendar.getInstance();
//        maxDate.add(Calendar.DAY_OF_MONTH, 25);
//
//        Calendar minDate = Calendar.getInstance();
//        minDate.add(Calendar.DAY_OF_MONTH, 0);
//
//// Create constraints for the date range
//        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
//                .setStart(currentDate.getTimeInMillis())
//                .setEnd(maxDate.getTimeInMillis())
//                .setValidator(DateValidatorPointForward.from(minDate.getTimeInMillis()));
//
//        constraintsBuilder.setStart(currentDate.getTimeInMillis());
//        constraintsBuilder.setEnd(maxDate.getTimeInMillis());
//
//        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
//        builder.setCalendarConstraints(constraintsBuilder.build()); // to set the constraints
//
//        builder.setTitleText("Select a Start-Date & End-Date for leave");
//        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();
//        datePicker.addOnPositiveButtonClickListener(selection -> {
//
//            // Retrieving the selected start and end dates
//            Long startDate = selection.first;
//            Long endDate = selection.second;
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//            startDateString = sdf.format(new Date(startDate));
//            endDateString = sdf.format(new Date(endDate));
//
//            txtStartDate.setText(startDateString);
//            txtEndDate.setText(endDateString);
//            // Creating the date range string
//            String selectedDateRange = startDateString + " - " + endDateString;
//
//            // Displaying the selected date range in the TextView
//            Toast.makeText(getActivity(), selectedDateRange, Toast.LENGTH_SHORT).show();
//        });
//
//        // Showing the date picker dialog
//        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
//
//    }
//
//    private void showMonthSelectionDialog() {
//        // Get the months array
//        final String[] months = getResources().getStringArray(R.array.months);
//
//        // Create a dialog with a spinner
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Select a Month");
//
//        // Inflate the layout containing the Spinner
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogLayout = inflater.inflate(R.layout.dialog_month_selection, null);
//
//        // Find the Spinner in the layout
//        final Spinner spinnerMonths = dialogLayout.findViewById(R.id.spinnerMonths);
//
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, months);
//
//
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Apply the adapter to the spinner
//        spinnerMonths.setAdapter(adapter);
//
//        // Set the layout for the dialog
//        builder.setView(dialogLayout);
//
//        // Set up the buttons
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Get the selected month
//                String selectedMonthName = spinnerMonths.getSelectedItem().toString();
//                selectMonthInNumber="";
//                switch (selectedMonthName){
//                    case "January":
//                        selectMonthInNumber = "1";
//                        break;
//                    case "February":
//                        selectMonthInNumber = "2";
//                        break;
//                    case "March":
//                        selectMonthInNumber = "3";
//                        break;
//                    case "April":
//                        selectMonthInNumber = "4";
//                        break;
//                    case "May":
//                        selectMonthInNumber = "5";
//                        break;
//                    case "June":
//                        selectMonthInNumber = "6";
//                        break;
//                    case "July":
//                        selectMonthInNumber = "7";
//                        break;
//                    case "August":
//                        selectMonthInNumber = "8";
//                        break;
//                    case "September":
//                        selectMonthInNumber = "9";
//                        break;
//                    case "October":
//                        selectMonthInNumber = "10";
//                        break;
//                    case "November":
//                        selectMonthInNumber = "11";
//                        break;
//                    case "December":
//                        selectMonthInNumber = "12";
//                        break;
//
//
//                }
//                // Display a Toast with the selected month
////                Toast.makeText(getActivity(), "Selected Month: " + selectedMonthName, Toast.LENGTH_SHORT).show();
////                Toast.makeText(getActivity(), "Selected Month: " + selectMonthInNumber, Toast.LENGTH_SHORT).show();
//
//
////                if(selectMonthInNumber.equals("-1")){
////                    Toast.makeText(getActivity(), "Select Valid Month", Toast.LENGTH_SHORT).show();
////                    return;
////                }
//                progressDialog = new ProgressDialog(getActivity());
//                progressDialog.setTitle("Checking Records");
//                progressDialog.setMessage("Counting diets\nHave patience.....");
//                progressDialog.show();
//                progressDialog.setCancelable(false);
//                leaveModel model = new leaveModel(rollNumber,hostelName,year_str,selectMonthInNumber);
//                Call<leaveModel>call = RetrofitClient.getInstance().getApi().countDietPerMonth(model);
//                call.enqueue(new Callback<leaveModel>() {
//                    @Override
//                    public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
//                        progressDialog.dismiss();
//                        leaveModel responseFromAPI = response.body();
//                        if(response.isSuccessful()){
//
////                            Toast.makeText(getActivity(), "received", Toast.LENGTH_SHORT).show();
//                            if(responseFromAPI.getMessage().equals("Total diet count retrieved successfully")){
////                                Toast.makeText(getActivity(), "total diets : "+responseFromAPI.getDietCount(), Toast.LENGTH_SHORT).show();
////                         add alert dialog box here
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                builder.setTitle("MESS RECORD");
//                                builder.setMessage("RollNumber : "+rollNumber+"\nRoom Number : "+roomNumber +"\nHostel : "+hostelName +"\nMonth : "+selectedMonthName + "\nYour monthly diets consumed are : "+responseFromAPI.getDietCount()+" diets");
//                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        dialogInterface.dismiss();
//                                    }
//                                }).show();
//                            }else{
//                                Toast.makeText(getActivity(), "Error in fetching diets", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<leaveModel> call, Throwable t) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        });
//
//        builder.setNegativeButton("Cancel", null);
//
//        // Show the dialog
//        builder.setCancelable(false);
//        builder.show();
//    }
//
//
//
//}



package com.example.hostelappnitj.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.example.hostelappnitj.Acitvity.ExtraSnacksActivity;
import com.example.hostelappnitj.ModelResponse.leaveModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceFragment extends Fragment {

    private AppCompatButton  btncountTotalDiets, btnCountDietPerMonth;
//    private String startDateString, endDateString;
    private SharedPrefManager sharedPrefManager;
    private String rollNumber, hostelName, roomNumber, year_str, selectMonthInNumber;
//    private TextView txtStartDate, txtEndDate;
    private TextToSpeech textToSpeech;
    private ProgressDialog progressDialog;

    public AttendanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);

//        submitDateButton = view.findViewById(R.id.submitDateButton);
//        setDateButton = view.findViewById(R.id.getCalendarButton);
//        txtStartDate = view.findViewById(R.id.txtStartDate);
//        txtEndDate = view.findViewById(R.id.txtEndDate);
        btncountTotalDiets = view.findViewById(R.id.btnCountTotalMeal);
        btnCountDietPerMonth = view.findViewById(R.id.btnCountMealPerMonth);

        sharedPrefManager = new SharedPrefManager(requireContext());
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName = sharedPrefManager.getHostelUser().getHostelName();
        roomNumber = sharedPrefManager.getHostelUser().getRoomNumber();

        textToSpeech = new TextToSpeech(requireContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported");
                }
            } else {
                Log.e("TTS", "Initialization failed");
            }
        });


        String userType = sharedPrefManager.getAdmin();



        btncountTotalDiets.setOnClickListener(v -> {

            if(userType.equals("Admin")){
//            binding.btnRoomBook3.setVisibility(View.INVISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("ALERT");
                builder.setMessage("Not Applicable for ADMIN");
                builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
                builder.show();

            }else {

                progressDialog = ProgressDialog.show(requireContext(), "Checking Records",
                        "Counting all diets\nPlease wait...", true, false);

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                year_str = String.valueOf(year);
                leaveModel model = new leaveModel(rollNumber, hostelName, year_str);

                RetrofitClient.getInstance().getApi().countTotalDiet(model).enqueue(new Callback<leaveModel>() {
                    @Override
                    public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            leaveModel responseFromAPI = response.body();
                            if (responseFromAPI != null && responseFromAPI.getMessage().equals("Total diet count retrieved successfully")) {
                                showCountTotalDietsDialog(responseFromAPI.getDietCount());
                            } else {
                                Toast.makeText(requireContext(), "Error in fetching diets", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<leaveModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        btnCountDietPerMonth.setOnClickListener(v -> showMonthSelectionDialog());

        btnCountDietPerMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userType.equals("Admin")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("ALERT");
                    builder.setMessage("Not Applicable for ADMIN");
                    builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
                    builder.show();
                }else{
                    showMonthSelectionDialog();
                }
            }
        });
//        setDateButton.setOnClickListener(v -> showDatePickerDialog());
        return view;
    }

//    private void showDatePickerDialog() {
//        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
//                .setValidator(DateValidatorPointForward.now());
//
//        MaterialDatePicker.Builder<androidx.core.util.Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
//        builder.setCalendarConstraints(constraintsBuilder.build());
//        builder.setTitleText("Select a Start-Date & End-Date for leave");
//
//        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();
//        datePicker.addOnPositiveButtonClickListener(selection -> {
//            Long startDate = selection.first;
//            Long endDate = selection.second;
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//            startDateString = sdf.format(new Date(startDate));
//            endDateString = sdf.format(new Date(endDate));
//
//            txtStartDate.setText(startDateString);
//            txtEndDate.setText(endDateString);
//        });
//
//        datePicker.show(getChildFragmentManager(), "DATE_PICKER");
//    }
//
    private void showMonthSelectionDialog() {
        final String[] months = getResources().getStringArray(R.array.months);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select a Month");

        View dialogLayout = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_month_selection, null);
        Spinner spinnerMonths = dialogLayout.findViewById(R.id.spinnerMonths);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, months);
        spinnerMonths.setAdapter(adapter);

        builder.setView(dialogLayout);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String selectedMonthName = spinnerMonths.getSelectedItem().toString();
            int selectedMonthIndex = spinnerMonths.getSelectedItemPosition() + 1;
            selectMonthInNumber = String.valueOf(selectedMonthIndex);

            progressDialog = ProgressDialog.show(requireContext(), "Checking Records",
                    "Counting diets\nPlease wait...", true, false);

            leaveModel model = new leaveModel(rollNumber, hostelName, year_str, selectMonthInNumber);

            RetrofitClient.getInstance().getApi().countDietPerMonth(model).enqueue(new Callback<leaveModel>() {
                @Override
                public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        leaveModel responseFromAPI = response.body();
                        if (responseFromAPI != null && responseFromAPI.getMessage().equals("Total diet count retrieved successfully")) {
                            showCountDietPerMonthDialog(selectedMonthName, responseFromAPI.getDietCount());
                        } else {
                            Toast.makeText(requireContext(), "Error in fetching diets", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<leaveModel> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showCountTotalDietsDialog(int dietCount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("MESS RECORD");
        builder.setMessage("RollNumber: " + rollNumber + "\nRoom Number: " + roomNumber + "\nHostel: " + hostelName + "\nTotal diets consumed: " + dietCount + " diets");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showCountDietPerMonthDialog(String monthName, int dietCount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("MESS RECORD");
        builder.setMessage("RollNumber: " + rollNumber + "\nRoom Number: " + roomNumber + "\nHostel: " + hostelName + "\nMonth: " + monthName + "\nMonthly diets consumed: " + dietCount + " diets");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
