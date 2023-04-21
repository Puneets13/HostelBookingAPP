package com.example.hostelappnitj;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;

//    make a constructor for adapter

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.admin_student_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,useremail,userPhone,userRollNumber,userFather;
        ImageView userImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.txtusername);
            useremail=itemView.findViewById(R.id.txtStudentemail);
            userPhone=itemView.findViewById(R.id.txtStudentphone);
            userRollNumber=itemView.findViewById(R.id.txtStudentRollNumber);
            userFather=itemView.findViewById(R.id.txtStudentFather);
        }
    }
}
