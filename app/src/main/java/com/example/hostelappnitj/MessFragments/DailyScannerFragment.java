package com.example.hostelappnitj.MessFragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.successScanActivity;
import com.example.hostelappnitj.MainActivity;
import com.example.hostelappnitj.ModelResponse.DailyScannerModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DailyScannerFragment extends Fragment {
    AppCompatButton btndailyScanner;
    TextToSpeech textToSpeech ;
    String rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;


    public DailyScannerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_scanner, container, false);
        btndailyScanner = view.findViewById(R.id.daily_scanner);

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


        ScanCode();

        btndailyScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanCode();
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
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result->{
        if(result.getContents() != null)
        {



            String dailyScanner = "@#$%^NIT_MESS_DailyScanner@#$%^";
            int hashNumberDaily= dailyScanner.hashCode();
            String hashCodeNumberString = String.valueOf(hashNumberDaily);

            String ExtraScanner = "@#$%^NIT_MESS_ExtraScanner";
            int hashNumberExtras= ExtraScanner.hashCode();
            String hashCodeExtraNumberString = String.valueOf(hashNumberExtras);


            if(hashCodeNumberString.equals(result.getContents())){
//            Daily scanner

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
                if (hour >= 0 && hour < 3) {  // 7:00 AM to 11:00 AM
//                    System.out.println("Good Morning!");
                    Toast.makeText(getActivity(), "its breakfst", Toast.LENGTH_SHORT).show();
                    mealType = "breakfast";
                }
                else if (hour >= 12 && hour < 18) {   // 12:00PM to 3:00PM
//                  System.out.println("Good Afternoon!");
                    mealType = "lunch";
                }
//                else if ((hour >= 19 && hour < 22 && minute >= 30) || (hour == 22 && minute <= 30)) { // 7:30 PM to 10:30 PM
////                    System.out.println("Good Evening!");
//                    mealType = "dinner";
//                }

//                CHANGED
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


//                DailyScannerModel model = new DailyScannerModel(roomNumber,rollNumber,hostelName ,  );
                DailyScannerModel model1 = new DailyScannerModel(roomNumber,rollNumber,hostelName,month,year,mealType,formattedDate,formattedTime);
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

//                DateFormat dateFormat2 = new SimpleDateFormat("hh mm aa");
//                String dateString2 = dateFormat2.format(new Date()).toString();
//                String speak =  " Time "+ dateString2 + " Enjoy Your Meal";
//                textToSpeech.setSpeechRate(1);
//                textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
//                Intent intent = new Intent(getActivity(), successScanActivity.class);
//////                Toast.makeText(getActivity(), "Daily meal", Toast.LENGTH_SHORT).show();
//                               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                               startActivity(intent);

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

}