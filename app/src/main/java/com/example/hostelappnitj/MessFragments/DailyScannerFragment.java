package com.example.hostelappnitj.MessFragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.ExtraSnacksActivity;
import com.example.hostelappnitj.Acitvity.RegisterationActivity;
import com.example.hostelappnitj.Acitvity.RoomConfirmer;
import com.example.hostelappnitj.Acitvity.successScanActivity;
import com.example.hostelappnitj.MBH_A_Hostel.Floor_1_SeatMatrix_A;
import com.example.hostelappnitj.ModelResponse.DailyScannerModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
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


public class DailyScannerFragment extends Fragment {
    AppCompatButton btndailyScanner;
    TextToSpeech textToSpeech ;
    String rollNumber , hostelName , roomNumber;
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
//                progressDialog = new ProgressDialog(getActivity());
//                progressDialog.setTitle("Processing");
//                progressDialog.setMessage("Transaction in progress..");
//                progressDialog.show();
//                progressDialog.setCancelable(false);

//                DailyScannerModel model = new DailyScannerModel(roomNumber,rollNumber,hostelName);
//                Call<DailyScannerModel> call = RetrofitClient.getInstance().getApi().getDailyMeal(model);
//                call.enqueue(new Callback<DailyScannerModel>() {
//                    @Override
//                    public void onResponse(Call<DailyScannerModel> call, Response<DailyScannerModel> response) {
//                        progressDialog.dismiss();
//                       DailyScannerModel responseFromAPI = response.body();
//                        if(response.isSuccessful()){
//                           if(responseFromAPI.getMessage().equals("success")){
//                               Toast.makeText(getActivity(), "received", Toast.LENGTH_SHORT).show();
////                               DateFormat dateFormat2 = new SimpleDateFormat("hh mm aa");
////                               String dateString2 = dateFormat2.format(new Date()).toString();
////                               String speak =  " Time "+ dateString2 + " Enjoy Your Meal";
////                               textToSpeech.setSpeechRate(1);
////                               textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
//
//                               Intent intent = new Intent(getActivity(), successScanActivity.class);
////                Toast.makeText(getActivity(), "Daily meal", Toast.LENGTH_SHORT).show();
//                               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                               startActivity(intent);
//
//                            }else {
//                               Toast.makeText(getActivity(), "Transaction Failed", Toast.LENGTH_SHORT).show();
//                           }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<DailyScannerModel> call, Throwable t) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

                DateFormat dateFormat2 = new SimpleDateFormat("hh mm aa");
                String dateString2 = dateFormat2.format(new Date()).toString();
                String speak =  " Time "+ dateString2 + " Enjoy Your Meal";
                textToSpeech.setSpeechRate(1);
                textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                Intent intent = new Intent(getActivity(), successScanActivity.class);
////                Toast.makeText(getActivity(), "Daily meal", Toast.LENGTH_SHORT).show();
                               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(intent);

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