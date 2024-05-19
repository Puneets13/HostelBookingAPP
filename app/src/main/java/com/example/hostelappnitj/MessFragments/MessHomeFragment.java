package com.example.hostelappnitj.MessFragments;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.ExtraSnacksActivity;
import com.example.hostelappnitj.Acitvity.InnvoiceActivity;
import com.example.hostelappnitj.Acitvity.SignInActivity;
import com.example.hostelappnitj.Acitvity.dietRecordActivity;
import com.example.hostelappnitj.Acitvity.scannerActivity;
import com.example.hostelappnitj.Acitvity.setMessTotalExpenditureActivity;
import com.example.hostelappnitj.Acitvity.successScanActivity;
import com.example.hostelappnitj.Hostels.Mess_Rules;
import com.example.hostelappnitj.MainActivity;
import com.example.hostelappnitj.ModelResponse.DailyScannerModel;
import com.example.hostelappnitj.ModelResponse.leaveModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessHomeFragment extends Fragment {
    ImageView imageViewHostels ;
    AppCompatButton btndailyScanner , btnextrasScanner , btnApplyLeave , btngetDietCount , btnDietRecord,btngenerateInvocie;
    TextToSpeech textToSpeech ;
    SharedPrefManager sharedPrefManager;
    private DialogInterface.OnClickListener dialogClickListener;
    ProgressDialog progressDialog;
    TextView txtEmail;
    ExtendedFloatingActionButton floatingActionButton_call;
    private static final int REQUEST_PHONE_CALL = 1;
    private Calendar selectedCalendar;
    String year_str;
    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate,selectMonthInNumber;
    public MessHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mess_home, container, false);
        imageViewHostels=view.findViewById(R.id.imageView_hostels);
        btndailyScanner = view.findViewById(R.id.daily_scanner);
        btnextrasScanner = view.findViewById(R.id.extras_scanner);
        btnApplyLeave = view.findViewById(R.id.getLeave);
        btnDietRecord=view.findViewById(R.id.dietRecord);
        btngenerateInvocie = view.findViewById(R.id.generateInvocie);
        btngetDietCount = view.findViewById(R.id.getDietCount);
        txtEmail = view.findViewById(R.id.txtEmail);

        floatingActionButton_call=view.findViewById(R.id.floatingActionButton_Call);

        sharedPrefManager=new SharedPrefManager(getActivity());
        email = sharedPrefManager.getHostelResponse().getEmail();

        sharedPrefManager=new SharedPrefManager(getActivity());
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();

        txtEmail.setText(email);

        //        text To speech
        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {

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


        int[] imageArray = { R.drawable.img_h1,R.drawable.img_h4,R.drawable.img_11, R.drawable.img_h5,R.drawable.img_h6,R.drawable.img_h7  };

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                imageViewHostels.setImageResource(imageArray[i]);
                i++;
                if (i > imageArray.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
//
//
//        btnInvoice.setOnClickListener(v -> {
//            progressDialog = ProgressDialog.show(requireContext(), "Checking Records",
//                    "Counting all diets\nPlease wait...", true, false);
//
//            Calendar calendar = Calendar.getInstance();
//            int year = calendar.get(Calendar.YEAR);
//            year_str = String.valueOf(year);
//            leaveModel model = new leaveModel(rollNumber, hostelName, year_str);
//
//            RetrofitClient.getInstance().getApi().countTotalDiet(model).enqueue(new Callback<leaveModel>() {
//                @Override
//                public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
//                    progressDialog.dismiss();
//                    if (response.isSuccessful()) {
//                        leaveModel responseFromAPI = response.body();
//                        if (responseFromAPI != null && responseFromAPI.getMessage().equals("Total diet count retrieved successfully")) {
//                            showCountTotalDietsDialog(responseFromAPI.getDietCount());
//                        } else {
//                            Toast.makeText(requireContext(), "Error in fetching diets", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<leaveModel> call, Throwable t) {
//                    progressDialog.dismiss();
//                    Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        });
//
//

        btngenerateInvocie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
                final String[] month_str = new String[1];
                final String[] year_Str = new String[1];

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(Calendar.YEAR, selectedYear);
                        selectedCalendar.set(Calendar.MONTH, selectedMonth);
                        selectedCalendar.set(Calendar.DAY_OF_MONTH, selectedDayOfMonth);

                        int month_temp = (selectedMonth + 1);
                        month_str[0] = String.valueOf(month_temp);
                        year_Str[0] = String.valueOf(selectedYear);
//                        editTextMonthName.setText(formattedDate);

                        Intent intent = new Intent(getActivity(), InnvoiceActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        String selectedMonth_year = month_str[0]+"_"+year_Str[0];
                        Toast.makeText(getActivity(), selectedMonth_year, Toast.LENGTH_SHORT).show();
                        intent.putExtra("month", month_str[0]);
                        intent.putExtra("year", year_Str[0]);
                        startActivity(intent);
                    }
                }, year, month, dayOfMonth);

                datePickerDialog.show();


            }
        });

//        btngetDietCount.setOnClickListener(new View.OnClickListener() {
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
////                            Toast.makeText(ApplyLeaveActivity.this, "received", Toast.LENGTH_SHORT).show();
//                            if(responseFromAPI.getMessage().equals("Total diet count retrieved successfully")){
////                                Toast.makeText(ApplyLeaveActivity.this, "total diets : "+responseFromAPI.getDietCount(), Toast.LENGTH_SHORT).show();
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
//
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

        btngetDietCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMonthSelectionDialog();
            }
        });
        btndailyScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanCode();
            }
        });

        btnextrasScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanExtrasCode();
            }
        });


        btnApplyLeave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ApplyLeaveActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        btnDietRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), dietRecordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        floatingActionButton_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }

                dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            // on below line we are setting a click listener
                            // for our positive button
                            case DialogInterface.BUTTON_POSITIVE:
//                        Make the Call Action
//ask for runtime permisson
                                Intent callIntent=new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:"+"6283021307"));//change the number
                                startActivity(callIntent);
//
                                break;
                            // on below line we are setting click listener
                            // for our negative button.
                            case DialogInterface.BUTTON_NEGATIVE:
                                // on below line we are dismissing our dialog box.
                                dialog.dismiss();
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // on below line we are setting message for our dialog box.
                builder.setTitle("EMERGENCY CALL");
                builder.setMessage("Make a call to Main Gate Security Guard ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();

            }
        });

        return view;

    }


    private void showMonthSelectionDialog() {
        final String[] months = getResources().getStringArray(R.array.months);

        Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            year_str = String.valueOf(year);
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

//            Toast.makeText(getActivity(), selectMonthInNumber, Toast.LENGTH_SHORT).show();
            leaveModel model = new leaveModel(rollNumber, hostelName, year_str, selectMonthInNumber);

            RetrofitClient.getInstance().getApi().countDietPerMonth(model).enqueue(new Callback<leaveModel>() {
                @Override
                public void onResponse(Call<leaveModel> call, Response<leaveModel> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        leaveModel responseFromAPI = response.body();
                        if (responseFromAPI != null && responseFromAPI.getMessage().equals("Total diet count retrieved successfully")) {
                            String dietcount = String.valueOf(responseFromAPI.getDietCount());
                            Toast.makeText(getActivity(),dietcount, Toast.LENGTH_SHORT).show();
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



    private void showCountDietPerMonthDialog(String monthName, int dietCount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("MESS RECORD");
        builder.setMessage("RollNumber: " + rollNumber + "\nRoom Number: " + roomNumber + "\nHostel: " + hostelName + "\nMonth: " + monthName + "\nMonthly diets consumed: " + dietCount + " diets");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showCountTotalDietsDialog(int dietCount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("MESS RECORD");
        builder.setMessage("RollNumber: " + rollNumber + "\nRoom Number: " + roomNumber + "\nHostel: " + hostelName + "\nTotal diets consumed: " + dietCount + " diets");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void ScanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(false);
        options.setCaptureActivity(CaptureActivity.class);
        barLauncher.launch(options);
    }
    private void ScanExtrasCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(false);
        options.setCaptureActivity(CaptureActivity.class);
        barLauncherExtras.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result->{
        if(result.getContents() != null)
        {

            String dailyScanner = "@#$%^NIT_MESS_DailyScanner@#$%^";
            int hashNumberDaily= dailyScanner.hashCode();
            String hashCodeNumberString = String.valueOf(hashNumberDaily);

            String ExtraScanner = "@#$%^NIT_MESS_ExtraScanner";
            int hashNumberExtras= ExtraScanner.hashCode();
            String hashCodeExtraNumberString = String.valueOf(hashNumberExtras);

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
                Toast.makeText(getActivity(), "Visit in Mess Timings", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("ALERT");
                builder.setMessage("Mess Closed\nPlease visit in Meal Timings\nHappy to see you for next meal. "+mealType);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }


            if(hashCodeNumberString.equals(result.getContents())){
//            Daily scanner

                Log.v(TAG,"FORMATTED DATE"+formattedDate);
                Toast.makeText(getActivity(), formattedDate, Toast.LENGTH_SHORT).show();

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");

                // Get the current date and time
                Date currentDate1 = new Date();

                // Format the current time using SimpleDateFormat
                String formattedTime1 = dateFormat1.format(currentDate1);

//                CONVERT DATE TO UTC FORMAT FOR THIS TO MATCH TIME ZONE
                // Get the current date and time
                Date currentDate3 = new Date();
                // Create a date formatter for UTC time zone
                SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                // Format the date as a string in UTC
                String utcDateTimeString = dateFormat3.format(currentDate3);


                DailyScannerModel model1 = new DailyScannerModel(roomNumber,rollNumber,hostelName,month,year,mealType,utcDateTimeString,formattedTime1);
//                DailyScannerModel model1 = new DailyScannerModel(roomNumber,rollNumber,hostelName,month,year,mealType,formattedDate,formattedTime1);


                String token = sharedPrefManager.getToken();
                Call<DailyScannerModel> call = RetrofitClient.getInstance().getApi().DailyCodeScanner("Bearer " + token,model1);
                call.enqueue(new Callback<DailyScannerModel>() {
                    @Override
                    public void onResponse(Call<DailyScannerModel> call, Response<DailyScannerModel> response) {
//                        progressDialog.dismiss();
                        DailyScannerModel responseFromAPI = response.body();
                        if(response.isSuccessful()){
                            if(responseFromAPI.getMessage().equals("you can scan")  && responseFromAPI.getScan().equals("yes") ){
//                               Toast.makeText(getActivity(), "received", Toast.LENGTH_SHORT).show();
                                DateFormat dateFormat2 = new SimpleDateFormat("hh mm aa");
                                String dateString2 = dateFormat2.format(new Date()).toString();
                                String speak =  " Time "+ dateString2 + " Enjoy Your "+mealType;
                                textToSpeech.setSpeechRate(1);
                                textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);

                                Intent intent = new Intent(getActivity(), successScanActivity.class);
//                             Toast.makeText(getActivity(), "Daily meal", Toast.LENGTH_SHORT).show();
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                            else if(responseFromAPI.getMessage().equals("you can not scan")  && responseFromAPI.getScan().equals("No") ){
//                               Toast.makeText(getActivity(), "received", Toast.LENGTH_SHORT).show();
//                               DateFormat dateFormat2 = new SimpleDateFormat("hh mm aa");
//                               String dateString2 = dateFormat2.format(new Date()).toString();
                                String speak =  " You have already consumed your "+mealType;
                                textToSpeech.setSpeechRate(1);
                                textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("ALERT");
                                builder.setMessage("You have already consumed your "+mealType);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();

                            }
                            else if(responseFromAPI.getMessage().equals("Prev diet not effected, consecutive 3 meals found")  && responseFromAPI.getScan().equals("yes") ){
                                Toast.makeText(getActivity(), "on Leave", Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("ALERT");
                                builder.setMessage("You were on Leave\nPrevious diets not affected");
                                builder.setCancelable(false);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DateFormat dateFormat2 = new SimpleDateFormat("hh mm aa");
                                        String dateString2 = dateFormat2.format(new Date()).toString();
                                        String speak =  " Time "+ dateString2 + " Enjoy Your "+mealType;
                                        textToSpeech.setSpeechRate(1);
                                        textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                                        dialogInterface.dismiss();

                                        Intent intent = new Intent(getActivity(), successScanActivity.class);
                                        Toast.makeText(getActivity(), "Daily meal", Toast.LENGTH_SHORT).show();
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }).show();
                            }
                            else if(responseFromAPI.getMessage().equals("Prev diet effected, consecutive 3 meals not found")  && responseFromAPI.getScan().equals("yes") ){
                                Toast.makeText(getActivity(), "on Leave", Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("ALERT");
                                builder.setMessage("You were on Leave\nContinous 3 diets not found\nPrevious 3 diets affected");
                                builder.setCancelable(false);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DateFormat dateFormat2 = new SimpleDateFormat("hh mm aa");
                                        String dateString2 = dateFormat2.format(new Date()).toString();
                                        String speak =  " Time "+ dateString2 + " Enjoy Your "+mealType;
                                        textToSpeech.setSpeechRate(1);
                                        textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                                        dialogInterface.dismiss();

                                        Intent intent = new Intent(getActivity(), successScanActivity.class);
                                        Toast.makeText(getActivity(), "Daily meal", Toast.LENGTH_SHORT).show();
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }).show();
                            }
                            else {
                                Toast.makeText(getActivity(), "Transaction Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else{
//                                check if token is not verified
                            if(response.code() == 500) {
                                // Unauthorized - Token is invalid or expired
                                // Redirect user to login screen or take appropriate action
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("ALERT");
                                builder.setMessage("Your Session expired\nPlease login Again");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(getActivity(), SignInActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        return;
                                    }
                                }).show();
                                // Redirect to login screen or logout user
                            } else {
                                // Handle other HTTP error codes
                                Toast.makeText(getActivity(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DailyScannerModel> call, Throwable t) {
//                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }





            if(hashCodeExtraNumberString.equals(result.getContents())) {
//            Extra scanner
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("ALERT");
                    builder.setMessage("Please use Extra's Meal Scanner");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();

            }
        }
    });
    ActivityResultLauncher<ScanOptions> barLauncherExtras = registerForActivityResult(new ScanContract(), result->{
        if(result.getContents() != null)
        {


            String dailyScanner = "@#$%^NIT_MESS_DailyScanner@#$%^";
            int hashNumberDaily= dailyScanner.hashCode();
            String hashCodeNumberString = String.valueOf(hashNumberDaily);

            String ExtraScanner = "@#$%^NIT_MESS_ExtraScanner";
            int hashNumberExtras= ExtraScanner.hashCode();
            String hashCodeExtraNumberString = String.valueOf(hashNumberExtras);

            if(hashCodeExtraNumberString.equals(result.getContents())){
                String speak =  "Please Enter Amount";
                textToSpeech.setSpeechRate(1);
                textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                Intent intent = new Intent(getActivity(), ExtraSnacksActivity.class);
//                Toast.makeText(getActivity(), "Extra meal", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            if(hashCodeNumberString.equals(result.getContents())){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("ALERT");
                builder.setMessage("Please use Daily Meal Scanner");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }

        }
    });

}