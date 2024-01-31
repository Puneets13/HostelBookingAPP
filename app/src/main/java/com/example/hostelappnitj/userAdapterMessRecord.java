//package com.example.hostelappnitj;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.hostelappnitj.ModelResponse.mealRecord;
//import com.squareup.picasso.Picasso;
//
//import java.time.LocalTime;
//import java.util.List;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class userAdapterMessRecord extends  RecyclerView.Adapter<userAdapterMessRecord.ViewHolder>{
//    Context context ;
//    List<mealRecord> userList;
//
//    public userAdapterMessRecord(Context context, List<mealRecord> userList) {
//        this.context = context;
//        this.userList = userList;
//    }
//
//    @NonNull
//    @Override
//    public userAdapterMessRecord.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(context).inflate(R.layout.mess_recordlist_item,parent,false);
//
//        return new userAdapterMessRecord.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull userAdapterMessRecord.ViewHolder holder, int position) {
//        holder.rollNumber.setText(userList.get(position).getRollNumber());
//        holder.roomNumber.setText(userList.get(position).getRoomNumber());
//        holder.date.setText(userList.get(position).getDate());
//        holder.userName.setText(userList.get(position).getUserName());
//        String imageFromDatabase= userList.get(position).getAvatar();
//////center crop is use to not the image to be streched when resized
//        Picasso.get().load(imageFromDatabase).resize(550,550).centerCrop().into(holder.avatar);
//    }
//
//    @Override
//    public int getItemCount() {
//        return userList.size();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        //        Type casting the Views of the User_item that needs to be used inside the recycler view
//        TextView date,userName , rollNumber , roomNumber,txtMeal;
//        CircleImageView avatar;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            avatar=itemView.findViewById(R.id.avatar);
//            date=itemView.findViewById(R.id.txtDate);
//            rollNumber=itemView.findViewById(R.id.txtRollNumber);
//            userName =itemView.findViewById(R.id.txtUserName);
//            roomNumber =itemView.findViewById(R.id.txtRoomNumber);
//            txtMeal=itemView.findViewById(R.id.txtMeal);
//
//        }
//    }
//}



package com.example.hostelappnitj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelappnitj.ModelResponse.mealRecord;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class userAdapterMessRecord extends  RecyclerView.Adapter<userAdapterMessRecord.ViewHolder>{
    Context context ;
    List<mealRecord> userList;

    public userAdapterMessRecord(Context context, List<mealRecord> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public userAdapterMessRecord.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.mess_recordlist_item,parent,false);

        return new userAdapterMessRecord.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userAdapterMessRecord.ViewHolder holder, int position) {
        holder.rollNumber.setText(userList.get(position).getRollNumber());
        holder.roomNumber.setText(userList.get(position).getRoomNumber());
        holder.date.setText(userList.get(position).getDate());
        holder.userName.setText(userList.get(position).getUserName());
        String imageFromDatabase= userList.get(position).getAvatar();
////center crop is use to not the image to be streched when resized
        Picasso.get().load(imageFromDatabase).resize(550,550).centerCrop().into(holder.avatar);
        holder.mealType.setText((userList.get(position).getMeal_type()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        Type casting the Views of the User_item that needs to be used inside the recycler view
        TextView date,userName , rollNumber , roomNumber,mealType;
        CircleImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar=itemView.findViewById(R.id.avatar);
            date=itemView.findViewById(R.id.txtDate);
            rollNumber=itemView.findViewById(R.id.txtRollNumber);
            userName =itemView.findViewById(R.id.txtUserName);
            roomNumber =itemView.findViewById(R.id.txtRoomNumber);
            mealType=itemView.findViewById(R.id.txtMeal);

        }
    }
}