package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelappnitj.ItemAdapter;
import com.example.hostelappnitj.ModelResponse.constantsModel;
import com.example.hostelappnitj.ModelResponse.extra_item_model;
import com.example.hostelappnitj.ModelResponse.messExpenditureModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.example.hostelappnitj.totalExpenditureAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class setMessTotalExpenditureActivity extends AppCompatActivity {

    ExtendedFloatingActionButton btnAddItem;
    //    SharedPrefManager sharedPrefManager;
    String hostelName;
    Map<String,Integer> TotalExpenditurePerMonth;
    RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;
    private Calendar selectedCalendar;
    private totalExpenditureAdapter adapter;
    private List<Map<String, String>> itemList;
    ProgressDialog progressDialog;

    private Map<String, Integer> itemMap;
    List<String> items_list ,  checkedItems , item ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_mess_total_expenditure);
        btnAddItem=findViewById(R.id.floating_addExpenditureBtn);
        recyclerView= findViewById(R.id.recyleview_setExpenditure);
        TotalExpenditurePerMonth = new HashMap<>();

        progressDialog = new ProgressDialog(setMessTotalExpenditureActivity.this);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Editing in Progress\n");
        progressDialog.show();
        progressDialog.setCancelable(false);


        sharedPrefManager=new SharedPrefManager(setMessTotalExpenditureActivity.this);
//        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();

        itemMap = new HashMap<>();
        items_list = new ArrayList<>();
        item = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(this, hostelName, Toast.LENGTH_SHORT).show();

        showList();

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddItemDialog();
//                showList();
            }
        });
    }

    private void showList() {

        constantsModel model = new constantsModel(hostelName);

        String token = sharedPrefManager.getToken();
        Call<constantsModel> call = RetrofitClient.getInstance().getApi().getTotalExpenditure("Bearer " + token,model);
        call.enqueue(new Callback<constantsModel>() {
            @Override
            public void onResponse(Call<constantsModel> call, Response<constantsModel> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    if(response.body().getMessage().equals("success")){
//                        progressDialog.dismiss();
                        itemMap = response.body().getTotalExpenditurePerMonth();
                        for (Map.Entry<String, Integer> entry : itemMap.entrySet()) {
                            String item_price =  entry.getKey() + ": " + entry.getValue();
                            items_list.add(item_price);
                        }
                        Collections.sort(items_list);
                        adapter = new totalExpenditureAdapter(setMessTotalExpenditureActivity.this, items_list,hostelName);
                        recyclerView.setAdapter(adapter);

                    } else if (response.body().getMessage().equals("no item found")){
                        progressDialog.dismiss();
                        Toast.makeText(setMessTotalExpenditureActivity.this, "No Items found", Toast.LENGTH_SHORT).show();
                    }
                }else{
//                                check if token is not verified
                    if(response.code() == 500) {
                        // Unauthorized - Token is invalid or expired
                        // Redirect user to login screen or take appropriate action
                        AlertDialog.Builder builder = new AlertDialog.Builder(setMessTotalExpenditureActivity.this);
                        builder.setTitle("ALERT");
                        builder.setMessage("Your Session expired\nPlease login Again");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                return;
                            }
                        }).show();
                        // Redirect to login screen or logout user
                    } else {
                        // Handle other HTTP error codes
                        Toast.makeText(setMessTotalExpenditureActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<constantsModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(setMessTotalExpenditureActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }



    private void showAddItemDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_expenditure, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextMonthName = dialogView.findViewById(R.id.editTextMonthName);
        final EditText editTextExpenditure = dialogView.findViewById(R.id.editTextExpenditure);
//        final Button btnProceed = dialogView.findViewById(R.id.btnProceed);
        dialogBuilder.setTitle("Add Monthly Budget");

        editTextMonthName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(setMessTotalExpenditureActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(Calendar.YEAR, selectedYear);
                        selectedCalendar.set(Calendar.MONTH, selectedMonth);
                        selectedCalendar.set(Calendar.DAY_OF_MONTH, selectedDayOfMonth);

                        // Format selected date as desired (e.g., "MM/YYYY")
                        String formattedDate = String.format("%02d_%d", selectedMonth + 1, selectedYear);
                        editTextMonthName.setText(formattedDate);

                        // Enable the Proceed button since both fields are now populated
//                        btnProceed.setEnabled(true);
                    }
                }, year, month, dayOfMonth);

                datePickerDialog.show();
            }
        });
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                progressDialog = new ProgressDialog(setMessTotalExpenditureActivity.this);
                progressDialog.setTitle("Processing");
                progressDialog.setMessage("Editing in Progress\n");
                progressDialog.show();
                progressDialog.setCancelable(false);

                String MonthName = editTextMonthName.getText().toString().trim();
                String Expenditurestr=editTextExpenditure.getText().toString().trim();
                Integer monthlyExpenditure = Integer.parseInt(Expenditurestr);
                if(MonthName.isEmpty() || Expenditurestr.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill all required fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                messExpenditureModel model = new messExpenditureModel(hostelName,MonthName,monthlyExpenditure);


                String token = sharedPrefManager.getToken();
                Call<messExpenditureModel> call = RetrofitClient.getInstance().getApi().addTotalExpenditure("Bearer " + token,model);
                call.enqueue(new Callback<messExpenditureModel>() {
                    @Override
                    public void onResponse(Call<messExpenditureModel> call, Response<messExpenditureModel> response) {
                        if(response.isSuccessful()){
                            progressDialog.dismiss();
                            if(response.body().getMessage().equals("Expenditure added successfully")){
                                TotalExpenditurePerMonth=response.body().getTotalExpenditurePerMonth();
                                items_list.clear();
                                for (Map.Entry<String, Integer> entry : TotalExpenditurePerMonth.entrySet()) {
                                    String item_price =  entry.getKey() + ": " + entry.getValue();
                                    items_list.add(item_price);

                                }
                                Collections.sort(items_list);
                                adapter = new totalExpenditureAdapter(setMessTotalExpenditureActivity.this, items_list,hostelName);
                                recyclerView.setAdapter(adapter);
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(setMessTotalExpenditureActivity.this, "Error in inserting item", Toast.LENGTH_SHORT).show();
                            }
                        }else{
//                                check if token is not verified
                            if(response.code() == 500) {
                                // Unauthorized - Token is invalid or expired
                                // Redirect user to login screen or take appropriate action
                                AlertDialog.Builder builder = new AlertDialog.Builder(setMessTotalExpenditureActivity.this);
                                builder.setTitle("ALERT");
                                builder.setMessage("Your Session expired\nPlease login Again");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        return;
                                    }
                                }).show();
                                // Redirect to login screen or logout user
                            } else {
                                // Handle other HTTP error codes
                                Toast.makeText(setMessTotalExpenditureActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<messExpenditureModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(setMessTotalExpenditureActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
                dialog.dismiss();
            }
        });

        AlertDialog addItemDialog = dialogBuilder.create();
        addItemDialog.show();
    }
}