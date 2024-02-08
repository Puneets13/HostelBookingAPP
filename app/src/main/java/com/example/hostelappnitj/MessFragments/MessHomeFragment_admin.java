package com.example.hostelappnitj.MessFragments;

import android.Manifest;
import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.ExtraSnacksActivity;
import com.example.hostelappnitj.Acitvity.MessRecordList_Activity;
import com.example.hostelappnitj.Acitvity.dietRecordActivity;
import com.example.hostelappnitj.Acitvity.successScanActivity;
import com.example.hostelappnitj.ModelResponse.DailyScannerModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessHomeFragment_admin extends Fragment {
    ImageView imageViewHostels ;
    AppCompatButton btndailyScanner , btnextrasScanner , btngetDietRecord , btnInvoice , btnDietRecord;
    TextToSpeech textToSpeech ;
    SharedPrefManager sharedPrefManager;
    private DialogInterface.OnClickListener dialogClickListener;
    ProgressDialog progressDialog;
    ExtendedFloatingActionButton floatingActionButton_call;
    private static final int REQUEST_PHONE_CALL = 1;

    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;

    public MessHomeFragment_admin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mess_home_admin, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Inflate the layout for this fragment
        imageViewHostels=view.findViewById(R.id.imageView_hostels);
        btndailyScanner = view.findViewById(R.id.daily_scanner);
        btnextrasScanner = view.findViewById(R.id.extras_scanner);
        btngetDietRecord = view.findViewById(R.id.getDietRedcord);
        btnDietRecord=view.findViewById(R.id.dietRecord);
        btnInvoice = view.findViewById(R.id.invoice);

        floatingActionButton_call=view.findViewById(R.id.floatingActionButton_Call);

        sharedPrefManager=new SharedPrefManager(getActivity());
        email = sharedPrefManager.getHostelResponse().getEmail();

        sharedPrefManager=new SharedPrefManager(getActivity());
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();


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


        btngetDietRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessRecordList_Activity.class);
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
            // Check the time ranges and print appropriate messages
//            if (hour >= 7 && hour < 11) {  // 7:00 AM to 11:00 AM
////                    System.out.println("Good Morning!");
//                mealType = "breakfast";
//            }
            if (hour >= 10 && hour < 13) {  // 7:00 AM to 11:00 AM
//                    System.out.println("Good Morning!");
//                Toast.makeText(getActivity(), "its ", Toast.LENGTH_SHORT).show();
                mealType = "lunch";
            }
            else if (hour >= 12 && hour < 15) {   // 12:00PM to 3:00PM
//                    System.out.println("Good Afternoon!");
                mealType = "lunch";
            }
//            else if ((hour >= 19 && hour < 22 && minute >= 30) || (hour == 22 && minute <= 30)) { // 7:30 PM to 10:30 PM
////                    System.out.println("Good Evening!");
//                mealType = "dinner";
//            }
            else if ((hour >= 15 && hour < 24 ) ) { // 7:30 PM to 10:30 PM
//                    System.out.println("Good Evening!");
                Toast.makeText(getActivity(), "Dinner Done", Toast.LENGTH_SHORT).show();
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

                DailyScannerModel model1 = new DailyScannerModel(roomNumber,rollNumber,hostelName,month,year,mealType,formattedDate);
                Call<DailyScannerModel> call = RetrofitClient.getInstance().getApi().DailyCodeScanner(model1);
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