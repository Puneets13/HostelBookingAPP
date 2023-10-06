package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelappnitj.R;

public class ExtraSnacksActivity extends AppCompatActivity {
    EditText etExtraAmount;
    AppCompatButton btnConfirmPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_snacks);
        etExtraAmount = findViewById(R.id.etExtrasAmount);
        btnConfirmPayment = findViewById(R.id.btnExtrasPayment);

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
//                    write logic here
                    Intent intent = new Intent(ExtraSnacksActivity.this, successScanActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


            }
        });
    }
}