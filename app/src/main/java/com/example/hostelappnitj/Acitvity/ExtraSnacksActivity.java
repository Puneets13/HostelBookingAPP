package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelappnitj.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExtraSnacksActivity extends AppCompatActivity {
    EditText etExtraAmount;
    TextToSpeech textToSpeech ;

    AppCompatButton btnConfirmPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_snacks);
        etExtraAmount = findViewById(R.id.etExtrasAmount);
        btnConfirmPayment = findViewById(R.id.btnExtrasPayment);

        DateFormat dateFormat2 = new SimpleDateFormat("hh.mm aa");
        String dateString2 = dateFormat2.format(new Date()).toString();
//        System.out.println("Current date and time in AM/PM: "+dateString2);

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
                String amount = etExtraAmount.getText().toString();
                if(amount.isEmpty()){
                    etExtraAmount.requestFocus();
                    etExtraAmount.setError("Please enter Amount");
                    return;
                }
                int amountInt = Integer.parseInt(amount);
                if( amountInt<=0 || amountInt>1000){
                    etExtraAmount.setText("");
                    Toast.makeText(ExtraSnacksActivity.this, "Please enter valid amount", Toast.LENGTH_SHORT).show();
                    return;
                }else{
//
                    String speak =  amount +" coins received.   Enjoy your meal";
                    textToSpeech.setSpeechRate(1);
                    textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                    Intent intent = new Intent(ExtraSnacksActivity.this, successScanActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


            }
        });
    }
}