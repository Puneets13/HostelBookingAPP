package com.example.hostelappnitj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelappnitj.ModelResponse.person;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter1 extends RecyclerView.Adapter<UserAdapter1.ViewHolder>{
    Context context;
    List<person> userList;
//    make a constructor for adapter

    public UserAdapter1(Context context, List<person> userList) {
        this.context = context;
        this.userList = userList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.admin_student_item_homefragment,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText(userList.get(position).getUsername());
        holder.useremail.setText(userList.get(position).getEmail());
        holder.userPhone.setText(userList.get(position).getPhone());
        holder.userRollNumber.setText(userList.get(position).getRollNumber());
        holder.userFather.setText(userList.get(position).getFatherName());
        holder.branch.setText(userList.get(position).getBranch());
        holder.address.setText(userList.get(position).getAddress());
        holder.hostelName.setText(userList.get(position).getHostelName());

        String image = userList.get(position).getAvatar();
        Picasso.get().load(image).resize(550,550).centerCrop().into(holder.userImage);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,useremail,userPhone,userRollNumber,userFather,branch,address,hostelName;
        ImageView userImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.txtusername);
            useremail=itemView.findViewById(R.id.txtStudentemail);
            userPhone=itemView.findViewById(R.id.txtStudentphone);
            userRollNumber=itemView.findViewById(R.id.txtStudentRollNumber);
            userFather=itemView.findViewById(R.id.txtStudentFather);
            userImage=itemView.findViewById(R.id.imgStudent);
            address=itemView.findViewById(R.id.txtStudentAddress);
            branch=itemView.findViewById(R.id.txtStudentbranch);
            hostelName=itemView.findViewById(R.id.txtStudentHostelName);

        }
    }
}
