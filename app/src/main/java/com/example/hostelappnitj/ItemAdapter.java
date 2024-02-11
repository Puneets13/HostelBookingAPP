package com.example.hostelappnitj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {


 private Context context;
 private List<String> itemList;
 private List<String> checkedItems;


 public ItemAdapter(Context context, List<String> itemList) {
  this.context = context;
  this.itemList = itemList;
  this.checkedItems = new ArrayList<>();
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
 }

 @Override
 public int getItemCount() {
  return itemList.size();
 }

 public static class ItemViewHolder extends RecyclerView.ViewHolder {
  TextView txtItemName, txtItemPrice;

  public ItemViewHolder(@NonNull View itemView) {
   super(itemView);
   txtItemName = itemView.findViewById(R.id.txtItemName);
   txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
  }
 }
}
