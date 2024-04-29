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

public class ExtrasAdapter extends RecyclerView.Adapter<ExtrasAdapter.ViewHolder>{
    private Context context;
    private List<ExtraItemModel> itemExtraDinner;

    public ExtrasAdapter(Context context, List<ExtraItemModel> itemExtraDinner) {
        this.context = context;
        this.itemExtraDinner = itemExtraDinner;
    }

    @NonNull
    @Override
    public ExtrasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.extra_dinner_tile, parent, false);
        return new ExtrasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtrasAdapter.ViewHolder holder, int position) {
        ExtraItemModel meal = itemExtraDinner.get(position);

        holder.itemName.setText(itemExtraDinner.get(position).getItem().toString());
        holder.itemPrice.setText(itemExtraDinner.get(position).getAmount().toString());

    }

    @Override
    public int getItemCount() {
        return itemExtraDinner.size();
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
