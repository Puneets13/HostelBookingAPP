package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.innvoiceModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InnvoiceActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    TextView txtRollNumber , txtMonth, txtYear , txtPerDietCost , txtDietCount,txttotalInvoice,txtHostelName,txtemail,txtHostelNameHeading;
    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;
    ProgressDialog progressDialog;
    AppCompatButton btnPrintInvocie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innvoice);
        txtDietCount=findViewById(R.id.dietCount);
        txtRollNumber=findViewById(R.id.invoice_roll_number);
        txtHostelName=findViewById(R.id.invoice_hostel_name);
        txtHostelNameHeading = findViewById(R.id.txtHostelNameHeading);
        txtMonth =findViewById(R.id.invoice_month);
        txtYear =findViewById(R.id.invoice_year);
        txtPerDietCost =findViewById(R.id.perDietCost);
        txttotalInvoice = findViewById(R.id.totalInvoice);
        txtemail = findViewById(R.id.invoice_email);
         btnPrintInvocie = findViewById(R.id.btnPrintInvocie);



        sharedPrefManager=new SharedPrefManager(InnvoiceActivity.this);
        email = sharedPrefManager.getHostelResponse().getEmail();
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();

        progressDialog = new ProgressDialog(InnvoiceActivity.this);
        progressDialog.setTitle("Generating Innvoice");
        progressDialog.setMessage("Processing..\nHave patience.....");
        progressDialog.show();
        progressDialog.setCancelable(false);


        Intent intent=getIntent();
        month = intent.getStringExtra("month");
        year =  intent.getStringExtra("year");

        txtemail.setText(email);
        txtMonth.setText(month);
        txtYear.setText(year);
        txtRollNumber.setText(rollNumber);
        txtHostelName.setText(hostelName);
        txtHostelNameHeading.setText("Mess Invoice\n"+hostelName);
//        Toast.makeText(this, hostelName, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, rollNumber, Toast.LENGTH_SHORT).show();


        btnPrintInvocie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailMessBillActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                startActivity(intent);
            }
        });
        innvoiceModel model = new innvoiceModel(rollNumber,hostelName,month,year);
        Call<innvoiceModel> call = RetrofitClient.getInstance().getApi().generateInvoice(model);
        call.enqueue(new Callback<innvoiceModel>() {
            @Override
            public void onResponse(Call<innvoiceModel> call, Response<innvoiceModel> response) {

                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    String mess = response.body().getMessage();
                    String error = response.body().getError();

                    if(response.body().getMessage().equals("Invoice generated successfully")){
                        // Convert the string to a double
                        String dietCount = response.body().getDietCount();
                        String PerDietCost = response.body().getPerDietCost();
                        String totalInvoice = response.body().getTotalInvoice();

                        double doubledietCount = Double.parseDouble(dietCount);
                        double doublePerDietCost = Double.parseDouble(PerDietCost);
                        double doubletotalInvoice = Double.parseDouble(totalInvoice);

                        // Round the double to three decimal places


                        // Use DecimalFormat to format the number
                        txtDietCount.setText(roundToThreeDecimal(doubledietCount));
                        txtPerDietCost.setText(roundToThreeDecimal(doublePerDietCost)+"/-");
                        txttotalInvoice.setText(roundToThreeDecimal(doubletotalInvoice)+"/-");
                    }
                    if(response.body().getMessage().equals("Wait till end of month")){
                        progressDialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(InnvoiceActivity.this);
                        builder.setTitle("Error");
                        builder.setMessage("Innvoice has not been generated yet");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        }).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(InnvoiceActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<innvoiceModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(InnvoiceActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    // Function to round a double to three decimal places
    public static String roundToThreeDecimal(double value) {
        DecimalFormat df = new DecimalFormat("#.###"); // Define the format

        // Use DecimalFormat to format the number
        String roundedString = df.format(value);

        // Parse the formatted string back to a double
        return roundedString ;
    }
}