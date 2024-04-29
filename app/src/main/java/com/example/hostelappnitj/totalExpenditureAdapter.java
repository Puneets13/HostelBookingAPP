package com.example.hostelappnitj;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelappnitj.Acitvity.MessRecordList_Activity;
import com.example.hostelappnitj.Acitvity.setExtraItem_Activity;
import com.example.hostelappnitj.Acitvity.setMessTotalExpenditureActivity;
import com.example.hostelappnitj.ModelResponse.extra_item_model;
import com.example.hostelappnitj.ModelResponse.messExpenditureModel;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class totalExpenditureAdapter extends RecyclerView.Adapter<totalExpenditureAdapter.ItemViewHolder> {


    private Context context;
    private List<String> itemList;
    private String hostelName;
    ProgressDialog progressDialog;
    private Calendar selectedCalendar;


    public totalExpenditureAdapter(Context context, List<String> itemList,String hostelName) {
        this.context = context;
        this.itemList = itemList;
        this.hostelName=hostelName;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.set_expenditure_tile, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String item = itemList.get(position);

        String[] parts = item.split(":");
        String itemName = parts[0].trim(); // Trim any leading or trailing spaces

        String itemPrice = parts[1].trim(); // Trim any leading or trailing spaces

//        MAPPING DATE 04_2024 WITH APRIL 2024
        String[] dateParts = itemName.split("_");
        String monthStr = dateParts[0]; // Get the month part (e.g., "04")
        String yearStr = dateParts[1];  // Get the year part (e.g., "2024")


        // Convert month string to integer (assuming it's in "MM" format)
        int month = Integer.parseInt(monthStr);
        // Create a Calendar instance to set the month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1); // Subtract 1 because Calendar.MONTH is zero-based
        // Get month name from calendar using DateFormatSymbols
        String monthName = new DateFormatSymbols(Locale.ENGLISH).getMonths()[month - 1];
        // Format year string
        String formattedYear = yearStr;
        // Combine month name and year
        String finalDate = monthName + " " + formattedYear;
        holder.txtItemName.setText(finalDate);
        holder.txtItemPrice.setText(itemPrice);


        //  FOR EDITING
        holder.btnEditExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position= holder.getAdapterPosition();
                showAddItemDialog(itemName,itemPrice,position) ;

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtItemName, txtItemPrice,btnEditExpenditure;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtMonthName);
            txtItemPrice = itemView.findViewById(R.id.txtMonthlyExpenditure);
            btnEditExpenditure = itemView.findViewById(R.id.btnEditExpenditure);

        }
    }

    private void showAddItemDialog(String itemName,String itemPrice,Integer position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_add_expenditure, null);
        dialogBuilder.setView(dialogView);

        int prev_expenditure = Integer.parseInt(itemPrice);
        final EditText editTextMonthName = dialogView.findViewById(R.id.editTextMonthName);
        final EditText editTextExpenditure = dialogView.findViewById(R.id.editTextExpenditure);

        editTextMonthName.setText(itemName);
        editTextMonthName.setEnabled(false);
//        final Button btnProceed = dialogView.findViewById(R.id.btnProceed);
        dialogBuilder.setTitle("Add Monthly Budget");

        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                progressDialog = new ProgressDialog(context);
                progressDialog.setTitle("Processing");
                progressDialog.setMessage("Editing in Progress\n");
                progressDialog.show();
                progressDialog.setCancelable(false);


                String newMonthName = editTextMonthName.getText().toString().trim();
                String newExpenditurestr=editTextExpenditure.getText().toString().trim();
                Integer monthlyExpenditure = Integer.parseInt(newExpenditurestr);
                if(newMonthName.isEmpty() || newExpenditurestr.isEmpty()){
                    Toast.makeText(context,"Please fill all required fields", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    return;
                }

                messExpenditureModel model = new messExpenditureModel(hostelName,newMonthName,monthlyExpenditure,prev_expenditure);

                Call<messExpenditureModel> call = RetrofitClient.getInstance().getApi().editTotalExpenditure(model);
                call.enqueue(new Callback<messExpenditureModel>() {
                    @Override
                    public void onResponse(Call<messExpenditureModel> call, Response<messExpenditureModel> response) {
                        if(response.isSuccessful()){
                            progressDialog.dismiss();
                            if(response.body().getMessage().equals("success")){
                                Toast.makeText(context, "Edited Successfully", Toast.LENGTH_SHORT).show();


                                itemList.set(position,newMonthName+":"+newExpenditurestr);

                                notifyDataSetChanged();

                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(context, "Error in inserting item", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<messExpenditureModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
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
