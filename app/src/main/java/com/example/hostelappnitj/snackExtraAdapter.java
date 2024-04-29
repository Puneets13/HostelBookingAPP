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

public class snackExtraAdapter extends RecyclerView.Adapter<snackExtraAdapter.ViewHolder>{
    private Context context;
    private List<ExtraItemModel> itemExtraEvening;

    public snackExtraAdapter(Context context, List<ExtraItemModel> itemExtraEvening) {
        this.context = context;
        this.itemExtraEvening = itemExtraEvening;
    }

    @NonNull
    @Override
    public snackExtraAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.extra_dinner_tile, parent, false);
        return new snackExtraAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull snackExtraAdapter.ViewHolder holder, int position) {
        ExtraItemModel meal = itemExtraEvening.get(position);

        holder.itemName.setText(itemExtraEvening.get(position).getItem().toString());
        holder.itemPrice.setText(itemExtraEvening.get(position).getAmount().toString());

    }

    @Override
    public int getItemCount() {
        return itemExtraEvening.size();
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
