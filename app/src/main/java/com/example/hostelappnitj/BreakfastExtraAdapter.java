package com.example.hostelappnitj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelappnitj.ModelResponse.ExtraItemModel;

import java.util.List;

public class BreakfastExtraAdapter extends RecyclerView.Adapter<BreakfastExtraAdapter.ViewHolder>{
    private Context context;
    private List<ExtraItemModel> itemExtraBreakfast;

    public BreakfastExtraAdapter(Context context, List<ExtraItemModel> itemExtraBreakfast) {
        this.context = context;
        this.itemExtraBreakfast = itemExtraBreakfast;
    }

    @NonNull
    @Override
    public BreakfastExtraAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.extra_dinner_tile, parent, false);
        return new BreakfastExtraAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastExtraAdapter.ViewHolder holder, int position) {
        ExtraItemModel meal = itemExtraBreakfast.get(position);

        holder.itemName.setText(itemExtraBreakfast.get(position).getItem().toString());
        holder.itemPrice.setText(itemExtraBreakfast.get(position).getAmount().toString());

    }

    @Override
    public int getItemCount() {
        return itemExtraBreakfast.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName , itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.txtItemNameExtraDinner);
            itemPrice=itemView.findViewById(R.id.txtItemPriceExtraDinner);
        }
    }


}
