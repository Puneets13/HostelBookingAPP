package com.example.hostelappnitj.MessFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hostelappnitj.Acitvity.RoomConfirmer;
import com.example.hostelappnitj.Acitvity.successScanActivity;
import com.example.hostelappnitj.MBH_A_Hostel.Floor_1_SeatMatrix_A;
import com.example.hostelappnitj.R;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Locale;


public class DailyScannerFragment extends Fragment {
    AppCompatButton btndailyScanner;
    TextToSpeech textToSpeech ;


    public DailyScannerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_scanner, container, false);
        btndailyScanner = view.findViewById(R.id.daily_scanner);

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

            //        text To speech
            textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status!= TextToSpeech.ERROR){
                        textToSpeech.setLanguage(Locale.ENGLISH);
                    }
                }
            });
            String speak =  "Scanned Successfull";
            textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();

            Intent intent = new Intent(getActivity(), successScanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    });
}