package com.example.hostelappnitj;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelappnitj.BreakfastExtraAdapter;
import com.example.hostelappnitj.ExtrasAdapter;
import com.example.hostelappnitj.LunchExtraAdapter;
import com.example.hostelappnitj.ModelResponse.ConsumedItemsModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.snackExtraAdapter;

import java.util.ArrayList;
import java.util.List;

public class detailMessAdapter extends RecyclerView.Adapter<detailMessAdapter.ViewHolder> {
    private Context context;
    private List<ConsumedItemsModel> consumedItems;

    public detailMessAdapter(Context context, List<ConsumedItemsModel> consumedItems) {
        this.context = context;
        this.consumedItems = consumedItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detailmess_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConsumedItemsModel meal = consumedItems.get(position);
        holder.date.setText("Date : " + meal.getDate());

        holder.etExtraBreakfastHeading.setVisibility(View.GONE);
        holder.etExtraLunchHeading.setVisibility(View.GONE);
        holder.etExtraSnacksHeading.setVisibility(View.GONE);
        holder.etExtraDinnerHeading.setVisibility(View.GONE);

        if (meal.getBreakfast().equals("Consumed")) {
            holder.breakfast.setText("Breakfast : Consumed");
        } else {
            holder.breakfast.setText("Breakfast : Not Consumed");
        }

        if (meal.getBreakfastExtra().size() > 0) {
            holder.etExtraBreakfastHeading.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            holder.breakfastExtra.setLayoutManager(layoutManager);

            BreakfastExtraAdapter adapter = new BreakfastExtraAdapter(context, meal.getBreakfastExtra());
            holder.breakfastExtra.setAdapter(adapter);
        }

        if (meal.getLunch().equals("Consumed")) {
            holder.lunch.setText("Lunch : Consumed");
        } else {
            holder.lunch.setText("Lunch : Not Consumed");
        }

        if (meal.getLunchExtra().size() > 0) {
            holder.etExtraLunchHeading.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            holder.lunchExtra.setLayoutManager(layoutManager);

            LunchExtraAdapter adapter = new LunchExtraAdapter(context, meal.getLunchExtra());
            holder.lunchExtra.setAdapter(adapter);
        }

        if (meal.getEveningExtra().size() > 0) {
            holder.etExtraSnacksHeading.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            holder.snackExtra.setLayoutManager(layoutManager);

            snackExtraAdapter adapter = new snackExtraAdapter(context, meal.getEveningExtra());
            holder.snackExtra.setAdapter(adapter);
        }

        if (meal.getDinner().equals("Consumed")) {
            holder.dinner.setText("Dinner : Consumed");
        } else {
            holder.dinner.setText("Dinner : Not Consumed");
        }

        if (meal.getDinnerExtra().size() > 0) {
            holder.etExtraDinnerHeading.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            holder.dinnerExtra.setLayoutManager(layoutManager);

            ExtrasAdapter adapter = new ExtrasAdapter(context, meal.getDinnerExtra());
            holder.dinnerExtra.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return consumedItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, breakfast, lunch, dinner, etExtraBreakfastHeading, etExtraLunchHeading, etExtraDinnerHeading, etExtraSnacksHeading;
        RecyclerView breakfastExtra, lunchExtra, snackExtra, dinnerExtra;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.etDateM);
            breakfast = itemView.findViewById(R.id.etbreakfastM);
            lunch = itemView.findViewById(R.id.etlunchM);
            dinner = itemView.findViewById(R.id.etdinnerM);
            breakfastExtra = itemView.findViewById(R.id.etbreakfastExtraM);
            lunchExtra = itemView.findViewById(R.id.etlunchExtraM);
            dinnerExtra = itemView.findViewById(R.id.etdinnerExtraM);
            snackExtra = itemView.findViewById(R.id.etSnackExtraM);
            etExtraBreakfastHeading = itemView.findViewById(R.id.etExtraBreakfastHeading);
            etExtraLunchHeading = itemView.findViewById(R.id.etExtraLunchHeading);
            etExtraDinnerHeading = itemView.findViewById(R.id.etExtraDinnerHeading);
            etExtraSnacksHeading = itemView.findViewById(R.id.etExtraSnacksHeading);

            etExtraBreakfastHeading.setVisibility(View.GONE);
            etExtraLunchHeading.setVisibility(View.GONE);
            etExtraSnacksHeading.setVisibility(View.GONE);
            etExtraDinnerHeading.setVisibility(View.GONE);
        }
    }
}








//package com.example.hostelappnitj;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.hostelappnitj.Acitvity.DetailMessBillActivity;
//import com.example.hostelappnitj.ModelResponse.ConsumedItemsModel;
//import com.example.hostelappnitj.ModelResponse.ExtraItemModel;
//import com.example.hostelappnitj.ModelResponse.MessDetailModel;
//import com.example.hostelappnitj.ModelResponse.mealRecord;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class detailMessAdapter extends RecyclerView.Adapter<detailMessAdapter.ViewHolder> {
//    private Context context;
//    private List<ConsumedItemsModel> consumedItems;
//    private List<ExtraItemModel> itemBreakfast;
//    private List<ExtraItemModel> itemLunch;
//    private List<ExtraItemModel> itemDinner;
//    private List<ExtraItemModel> itemSnacks;
//
//
//    public detailMessAdapter(Context context, List<ConsumedItemsModel> consumedItems) {
//        this.context = context;
//        this.consumedItems = consumedItems;
//    }
//
//
//    @NonNull
//    @Override
//    public detailMessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.detailmess_item, parent, false);
//        return new detailMessAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull detailMessAdapter.ViewHolder holder, int position) {
//        ConsumedItemsModel meal = consumedItems.get(position);
//        holder.date.setText("Date : "+  meal.getDate() );
//
//        holder.etExtraBreakfastHeading.setVisibility(View.GONE);
//        holder.etExtraLunchHeading.setVisibility(View.GONE);
//        holder.etExtraSnacksHeading.setVisibility(View.GONE);
//        holder.etExtraDinnerHeading.setVisibility(View.GONE);
//
////        Toast.makeText(context, meal.getDate(), Toast.LENGTH_SHORT).show();
//        if(consumedItems.get(position).getBreakfast().equals("Consumed")){
//            holder.breakfast.setText("Breakfast : Consumed");
//        }else{
//            holder.breakfast.setText("Breakfast : Not Consumed");
//        }
//
//        if(meal.getBreakfastExtra().size() > 0){
//
//            holder.etExtraBreakfastHeading.setVisibility(View.VISIBLE);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//            holder.breakfastExtra.setLayoutManager(layoutManager);
//
//            itemBreakfast = new ArrayList<>();
//
//            itemBreakfast = meal.getBreakfastExtra();
//
//            BreakfastExtraAdapter adapter = new BreakfastExtraAdapter(context,itemBreakfast) ;
//            holder.breakfastExtra.setAdapter(adapter);
//
//        }
//
//        if(meal.getLunch().equals("Consumed")){
//            holder.lunch.setText("Lunch : Consumed");
//        }else{
//            holder.lunch.setText("Lunch : Not Consumed");
//        }
//
//        if(meal.getLunchExtra().size() > 0){
//            holder.etExtraLunchHeading.setVisibility(View.VISIBLE);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//            holder.lunchExtra.setLayoutManager(layoutManager);
//
//            itemLunch = new ArrayList<>();
//
//            itemLunch = meal.getLunchExtra();
//
//            LunchExtraAdapter adapter = new LunchExtraAdapter(context,itemLunch) ;
//            holder.lunchExtra.setAdapter(adapter);
//        }
//
//        if(meal.getEveningExtra().size() > 0){
//            holder.etExtraSnacksHeading.setVisibility(View.VISIBLE);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//            holder.snackExtra.setLayoutManager(layoutManager);
//
//            itemSnacks = new ArrayList<>();
//
//            itemSnacks = meal.getEveningExtra();
//
//            snackExtraAdapter adapter = new snackExtraAdapter(context,itemLunch) ;
//            holder.snackExtra.setAdapter(adapter);
//
//        }
//
//        if(meal.getDinner().equals("Consumed")){
//            holder.dinner.setText("Dinner : Consumed");
//        }else{
//            holder.dinner.setText("Dinner : Not Consumed");
//        }
//
//        if(meal.getDinnerExtra().size() > 0){
//            holder.etExtraDinnerHeading.setVisibility(View.VISIBLE);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//            holder.dinnerExtra.setLayoutManager(layoutManager);
//
//            itemDinner = new ArrayList<>();
//
//            itemDinner = meal.getDinnerExtra();
//            ExtrasAdapter adapter = new ExtrasAdapter(context,itemDinner);
//            holder.dinnerExtra.setAdapter(adapter);
//
//        }
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return consumedItems.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView date,breakfast , lunch , dinner , etExtraBreakfastHeading,etExtraLunchHeading , etExtraDinnerHeading,etExtraSnacksHeading;
//        RecyclerView breakfastExtra ,lunchExtra,snackExtra;
//        RecyclerView dinnerExtra;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            date=itemView.findViewById(R.id.etDateM);
//            breakfast=itemView.findViewById(R.id.etbreakfastM);
//            lunch=itemView.findViewById(R.id.etlunchM);
//            dinner=itemView.findViewById(R.id.etdinnerM);
//            breakfastExtra=itemView.findViewById(R.id.etbreakfastExtraM);
//            lunchExtra = itemView.findViewById(R.id.etlunchExtraM);
//            dinnerExtra = itemView.findViewById(R.id.etdinnerExtraM);
//            snackExtra = itemView.findViewById(R.id.etSnackExtraM);
//            etExtraBreakfastHeading = itemView.findViewById(R.id.etExtraBreakfastHeading);
//            etExtraLunchHeading = itemView.findViewById(R.id.etExtraLunchHeading);
//            etExtraDinnerHeading = itemView.findViewById(R.id.etExtraDinnerHeading);
//            etExtraSnacksHeading = itemView.findViewById(R.id.etExtraSnacksHeading);
//
//            etExtraBreakfastHeading.setVisibility(View.GONE);
//            etExtraLunchHeading.setVisibility(View.GONE);
//            etExtraSnacksHeading.setVisibility(View.GONE);
//            etExtraDinnerHeading.setVisibility(View.GONE);
//
//        }
//    }
//}
