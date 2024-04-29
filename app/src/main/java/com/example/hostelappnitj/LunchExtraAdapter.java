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

public class LunchExtraAdapter  extends RecyclerView.Adapter<LunchExtraAdapter.ViewHolder>{
    private Context context;
    private List<ExtraItemModel> itemExtraLunch;

    public LunchExtraAdapter(Context context, List<ExtraItemModel> itemExtraLunch) {
        this.context = context;
        this.itemExtraLunch = itemExtraLunch;
    }

    @NonNull
    @Override
    public LunchExtraAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.extra_dinner_tile, parent, false);
        return new LunchExtraAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LunchExtraAdapter.ViewHolder holder, int position) {
        ExtraItemModel meal = itemExtraLunch.get(position);

        holder.itemName.setText(itemExtraLunch.get(position).getItem().toString());
        holder.itemPrice.setText(itemExtraLunch.get(position).getAmount().toString());

    }

    @Override
    public int getItemCount() {
        return itemExtraLunch.size();
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


