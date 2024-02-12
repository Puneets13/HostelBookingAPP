package com.example.hostelappnitj;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelappnitj.Acitvity.MessRecordList_Activity;
import com.example.hostelappnitj.Acitvity.setExtraItem_Activity;
import com.example.hostelappnitj.ModelResponse.extra_item_model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {


 private Context context;
 private List<String> itemList;
 private List<String> checkedItems;
 private String hostelName;
 ProgressDialog progressDialog;


 public ItemAdapter(Context context, List<String> itemList,String hostelName) {
  this.context = context;
  this.itemList = itemList;
  this.checkedItems = new ArrayList<>();
  this.hostelName=hostelName;
 }

 @NonNull
 @Override
 public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
  View view = LayoutInflater.from(context).inflate(R.layout.set_extra_tile_item, parent, false);
  return new ItemViewHolder(view);
 }

 @Override
 public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
  String item = itemList.get(position);

  String[] parts = item.split(":");
  String itemName = parts[0].trim(); // Trim any leading or trailing spaces

  String itemPrice = parts[1].trim(); // Trim any leading or trailing spaces

  holder.txtItemName.setText(itemName);
  holder.txtItemPrice.setText(itemPrice);


//  FOR DELETING

  holder.btnDelte.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {


    progressDialog = new ProgressDialog(context);
    progressDialog.setTitle("Processing");
    progressDialog.setMessage("Deleting Item\n");
    progressDialog.show();
    progressDialog.setCancelable(false);


    extra_item_model model = new extra_item_model(hostelName,itemName);
 Call<extra_item_model> call = RetrofitClient.getInstance().getApi().deleteEntry(model);
 call.enqueue(new Callback<extra_item_model>() {
  @Override
  public void onResponse(Call<extra_item_model> call, Response<extra_item_model> response) {
   if(response.isSuccessful()){
    progressDialog.dismiss();
    if(response.body().getMessage().equals("success")){
     Toast.makeText(context.getApplicationContext(), "Item Deleted", Toast.LENGTH_SHORT).show();
     int position= holder.getAdapterPosition();
     itemList.remove(position);
     notifyItemRemoved(position);

    }
   }
  }

  @Override
  public void onFailure(Call<extra_item_model> call, Throwable t) {
   progressDialog.dismiss();
   Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
  }
 });

   }
  });


//  FOR EDITING
  holder.btnEdit.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
    int position= holder.getAdapterPosition();
    showAddItemDialog(itemName,itemPrice,position) ;

   }
  });

 }

 private void showAddItemDialog(String itemName,String itemPrice,Integer position) {
  AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
  LayoutInflater inflater = LayoutInflater.from(context);
  View dialogView = inflater.inflate(R.layout.dialog_add_item, null);
  dialogBuilder.setView(dialogView);

  final EditText editTextItemName = dialogView.findViewById(R.id.editTextItemName);
  final EditText editTextItemPrice = dialogView.findViewById(R.id.editTextItemPrice);

  editTextItemName.setText(itemName);
  editTextItemPrice.setText(itemPrice);


  dialogBuilder.setTitle("Edit Item");

  dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
   public void onClick(DialogInterface dialog, int whichButton) {

    progressDialog = new ProgressDialog(context);
    progressDialog.setTitle("Processing");
    progressDialog.setMessage("Editing in Progress\n");
    progressDialog.show();
    progressDialog.setCancelable(false);

    String newitemName = editTextItemName.getText().toString().trim().toUpperCase();
    String newitemPricestr=editTextItemPrice.getText().toString().trim();
    Integer newitemPrice = Integer.parseInt(newitemPricestr);
    if( newitemName.isEmpty() || newitemPrice ==0){
     Toast.makeText(context,"Please fill all required fields", Toast.LENGTH_SHORT).show();
     return;
    }
//    extra_item_model model = new extra_item_model(itemPrice,hostelName,itemName);

    extra_item_model model = new extra_item_model(hostelName,newitemName,itemName,newitemPrice);

    Call<extra_item_model> call = RetrofitClient.getInstance().getApi().editExtraItem(model);
    call.enqueue(new Callback<extra_item_model>() {
     @Override
     public void onResponse(Call<extra_item_model> call, Response<extra_item_model> response) {
      if(response.isSuccessful()){
       progressDialog.dismiss();
       if(response.body().getMessage().equals("success")){
        Toast.makeText(context, "Edited Successfully", Toast.LENGTH_SHORT).show();


        itemList.set(position,newitemName+":"+newitemPrice);

        // Add the new item
//        String newItem = newitemName + ":" + newitemPrice;
//        itemList.add(newItem);

        // Sort the list
//        Collections.sort(itemList);

        // Notify adapter of dataset change
        notifyDataSetChanged();

       }else{
        progressDialog.dismiss();
        Toast.makeText(context, "Error in inserting item", Toast.LENGTH_SHORT).show();
       }
      }
     }

     @Override
     public void onFailure(Call<extra_item_model> call, Throwable t) {
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


 @Override
 public int getItemCount() {
  return itemList.size();
 }

 public static class ItemViewHolder extends RecyclerView.ViewHolder {
  TextView txtItemName, txtItemPrice;
  AppCompatButton btnEdit , btnDelte;

  public ItemViewHolder(@NonNull View itemView) {
   super(itemView);
   txtItemName = itemView.findViewById(R.id.txtItemName);
   txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
   btnDelte=itemView.findViewById(R.id.btnDelete);
   btnEdit=itemView.findViewById(R.id.btnEdit);

  }
 }
}
