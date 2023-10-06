package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.hostelappnitj.R;
import com.example.hostelappnitj.SharedPrefManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class successScanActivity extends AppCompatActivity {
TextView txtStudentName , txtHostelname , txtRollNumber , txtRoomNumber , txtDate , txtTime , txtEmail;
SharedPrefManager sharedPrefManager;
    TextToSpeech textToSpeech ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_scan);
        // Adding this line will prevent taking screenshot in your app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

//        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        txtStudentName = findViewById(R.id.txtStudentName);
        txtRollNumber = findViewById(R.id.txtStudentRollNumber);
        txtRoomNumber = findViewById(R.id.txtStudentRoomNumber);
        txtHostelname = findViewById(R.id.txtHostelname);
        txtEmail = findViewById(R.id.txtEmail);
        sharedPrefManager= new SharedPrefManager(this.getApplicationContext());


        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh.mm aa");
        String dateString2 = dateFormat2.format(new Date()).toString();
//        System.out.println("Current date and time in AM/PM: "+dateString2);
        txtTime.setText(dateString2);
        txtEmail.setText(sharedPrefManager.getUser().getEmail());
        txtHostelname.setText(sharedPrefManager.getUser().getHostelName());
        txtRollNumber.setText(sharedPrefManager.getUser().getRollNumber());
//        txtRoomNumber.setText(sharedPrefManager.getUser().getRoomNumber());
        txtStudentName.setText(sharedPrefManager.getUser().getUsername());

        //        text To speech
        textToSpeech = new TextToSpeech(successScanActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        String speak =  "Scanned Successfull";
        textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);

    }
}