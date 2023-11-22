package com.example.hostelappnitj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelappnitj.ModelResponse.mealRecord;

import java.util.List;

public class userAdapterMess extends RecyclerView.Adapter<userAdapterMess.ViewHolder>{

    Context context ;
    List<mealRecord> userList;

    public userAdapterMess(Context context, List<mealRecord> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public userAdapterMess.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.meal_item,parent,false);

        return new userAdapterMess.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userAdapterMess.ViewHolder holder, int position) {
//bind the views in this method

        holder.date.setText("Date : "+ userList.get(position).getDate());
        if(userList.get(position).getBreakfast().equals("1")){
            holder.breakfast.setText("Breakfast : Consumed");
        }
        if(userList.get(position).getBreakfast().equals("2")){
            holder.breakfast.setText("Breakfast : onLeave");
        }
        if(userList.get(position).getBreakfast().equals("0")){
            holder.breakfast.setText("Breakfast : Missed");
        }

        if(userList.get(position).getLunch().equals("1")){
            holder.lunch.setText("lunch : Consumed");
        }
        if(userList.get(position).getLunch().equals("2")){
            holder.lunch.setText("lunch : onLeave");
        }
        if(userList.get(position).getLunch().equals("0")){
            holder.lunch.setText("lunch : Missed");
        }

        if(userList.get(position).getDinner().equals("1")){
            holder.dinner.setText("Dinner : Consumed");
        }
        if(userList.get(position).getDinner().equals("2")){
            holder.dinner.setText("Dinner : onLeave");
        }
        if(userList.get(position).getDinner().equals("0")){
            holder.dinner.setText("Dinner : Missed");
        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        Type casting the Views of the User_item that needs to be used inside the recycler view
        TextView date,breakfast , lunch , dinner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.etDate);
            breakfast=itemView.findViewById(R.id.etbreakfast);
            lunch=itemView.findViewById(R.id.etlunch);
            dinner=itemView.findViewById(R.id.etdinner);

        }
    }
}
