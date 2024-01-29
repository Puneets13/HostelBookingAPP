package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.DailyScannerModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtraSnacksActivity extends AppCompatActivity {
    EditText etExtraAmount;
    TextToSpeech textToSpeech ;

    AppCompatButton btnConfirmPayment;
    String rollNumber , hostelName , roomNumber;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;
    TextView txtHostelname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_snacks);
        etExtraAmount = findViewById(R.id.etExtrasAmount);
        btnConfirmPayment = findViewById(R.id.btnExtrasPayment);
        txtHostelname = findViewById(R.id.txtHostelname);

        DateFormat dateFormat2 = new SimpleDateFormat("hh.mm aa");
        String dateString2 = dateFormat2.format(new Date()).toString();

        sharedPrefManager=new SharedPrefManager(ExtraSnacksActivity.this);
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();
        txtHostelname.setText(hostelName);


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
                    progressDialog = new ProgressDialog(ExtraSnacksActivity.this);
                    progressDialog.setTitle("Processing");
                    progressDialog.setMessage("Transaction in progress..");
                    progressDialog.show();
                    progressDialog.setCancelable(false);

//                    DailyScannerModel model = new DailyScannerModel(roomNumber,rollNumber,hostelName,amountInt);
                    DailyScannerModel model = new DailyScannerModel(roomNumber,rollNumber,hostelName,amountInt);
                    Call<DailyScannerModel> call = RetrofitClient.getInstance().getApi().getExtraMeal(model);
                    call.enqueue(new Callback<DailyScannerModel>() {
                        @Override
                        public void onResponse(Call<DailyScannerModel> call, Response<DailyScannerModel> response) {
                            progressDialog.dismiss();
                            DailyScannerModel responseFromAPI = response.body();
                            if(response.isSuccessful()){
                                if(responseFromAPI.getMessage().equals("success")){

                                    String speak =  amount +" coins received.   Enjoy your meal";
                                    textToSpeech.setSpeechRate(1);
                                    textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                                    Intent intent = new Intent(ExtraSnacksActivity.this, successScanActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();

                                }else {
                                    Toast.makeText(ExtraSnacksActivity.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DailyScannerModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(ExtraSnacksActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });
    }
}