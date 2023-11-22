package com.example.hostelappnitj.MessFragments;

import android.app.AlertDialog;
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
import com.example.hostelappnitj.Acitvity.successScanActivity;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.SharedPrefManager;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExtrasScannerFragment extends Fragment {
    AppCompatButton btnExtrasScanner;
    TextToSpeech textToSpeech ;
    String rollNumber , hostelName , roomNumber;
    SharedPrefManager sharedPrefManager;
    public ExtrasScannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_extras_scanner, container, false);
        btnExtrasScanner = view.findViewById(R.id.extras_scanner);


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

        btnExtrasScanner.setOnClickListener(new View.OnClickListener() {
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
        barLauncherExtras.launch(options);
    }
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
//            Extra scanner
                String speak =  "Please Enter Amount ";
                textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                textToSpeech.setSpeechRate(1);

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