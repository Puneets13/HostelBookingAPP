package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.createCollectionModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createMessCollectionActivity extends AppCompatActivity {

    EditText etHostelName , etMessStartDate,etInitalAmountM;
    AppCompatButton btnUpdateCollection;
    ProgressDialog progressDialog ;   //this will give the background box also
    SharedPrefManager sharedPrefManager;
    String hostelName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mess_collection);

        etHostelName = findViewById(R.id.etHostelNameM);
        etMessStartDate = findViewById(R.id.etMessStartDate);
        etMessStartDate.setInputType(InputType.TYPE_NULL);
        etInitalAmountM = findViewById(R.id.etInitalAmountM);
        btnUpdateCollection = findViewById(R.id.btnUpdateCollection);
        sharedPrefManager = new SharedPrefManager(createMessCollectionActivity.this);
        hostelName=sharedPrefManager.getUser().getUsername();
        etHostelName.setText(hostelName);

        btnUpdateCollection.setOnClickListener(new View.OnClickListener() {

            String txtInitialAmount = etInitalAmountM.getText().toString();
            String MessStartDate = etMessStartDate.getText().toString();
            @Override
            public void onClick(View view) {
                String txtInitialAmount = etInitalAmountM.getText().toString();
                String MessStartDate = etMessStartDate.getText().toString();
                if (txtInitialAmount.isEmpty()) {
                    etInitalAmountM.requestFocus();
                    etInitalAmountM.setError("Please enter Initial Amount");
                    return;
                }
                if (MessStartDate.isEmpty()) {
                    etMessStartDate.requestFocus();
                    etMessStartDate.setError("Please enter Mess Start Date");
                    return;
                } else {

                    progressDialog = new ProgressDialog(createMessCollectionActivity.this);
                    progressDialog.setTitle("Updating");
                    progressDialog.setMessage("Processing Data..");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    createCollectionModel model = new createCollectionModel(MessStartDate, hostelName, txtInitialAmount);
                    Call<createCollectionModel> call = RetrofitClient.getInstance().getApi().createCollection(model);
                    call.enqueue(new Callback<createCollectionModel>() {
                        @Override
                        public void onResponse(Call<createCollectionModel> call, Response<createCollectionModel> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                if (response.body().getMessage().equals("Mess updated successfully.")) {
                                    Toast.makeText(createMessCollectionActivity.this, "Date Updated", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                if (response.body().getMessage().equals("success")) {
                                    Toast.makeText(createMessCollectionActivity.this, "created successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<createCollectionModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(createMessCollectionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        etMessStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the DatePickerDialog when the EditText is clicked
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                createMessCollectionActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Format the selected date in yyyy-MM-dd format
                        String selectedDate = String.format("%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                        etMessStartDate.setText(selectedDate);
                    }
                },
                year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }
}