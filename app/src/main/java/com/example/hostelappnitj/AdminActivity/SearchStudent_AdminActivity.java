package com.example.hostelappnitj.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hostelappnitj.R;
import com.squareup.picasso.Picasso;

public class SearchStudent_AdminActivity extends AppCompatActivity {
TextView  txtemail,txtphone,txtaddress,txtbranch,txtrollNumber,txtfatherName,txtfatherPhone,txtusername;
    TextView  txtemail2,txtphone2,txtaddress2,txtbranch2,txtrollNumber2,txtfatherName2,txtfatherPhone2,txtusername2,txtroom;
ImageView imgavatar1 , imgavatar2 ;
String occupied ,roomNum;
CardView cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student_admin);

        txtroom=findViewById(R.id.txtroom);

        txtemail=findViewById(R.id.txtStudentemail1);
        txtemail2=findViewById(R.id.txtStudentemail2);
        txtphone=findViewById(R.id.txtStudentphone1);
        txtphone2=findViewById(R.id.txtStudentphone2);

        txtaddress=findViewById(R.id.txtStudentAddress1);
        txtaddress2=findViewById(R.id.txtStudentAddress2);

        txtbranch=findViewById(R.id.txtStudentbranch1);
        txtbranch2=findViewById(R.id.txtStudentbranch2);

        txtrollNumber = findViewById(R.id.txtStudentRollNumber1);
        txtrollNumber2 = findViewById(R.id.txtStudentRollNumber2);

        txtfatherName = findViewById(R.id.txtStudentFather1);
        txtfatherName2 = findViewById(R.id.txtStudentFather2);

        txtusername=findViewById(R.id.txtUsername1);
        txtusername2=findViewById(R.id.txtUsername2);

        imgavatar1=findViewById(R.id.imgStudnetImg1);
        imgavatar2=findViewById(R.id.imgStudnetImg2);

        cardView2 = findViewById(R.id.cardView4);



        Intent intent = getIntent();

        occupied= intent.getStringExtra("occupied");
        roomNum=intent.getStringExtra("roomNumber");


        if(occupied.equals("1")){
            cardView2.setVisibility(View.INVISIBLE);
            txtusername2.setVisibility(View.INVISIBLE);
        }
//        display the information there
        txtusername.setText(intent.getStringExtra("userName"));
        txtemail.setText(intent.getStringExtra("email"));
        txtphone.setText(intent.getStringExtra("phone"));
        txtaddress.setText(intent.getStringExtra("address"));
        txtbranch.setText(intent.getStringExtra("branch"));
        txtrollNumber.setText(intent.getStringExtra("rollNumber"));
        txtfatherName.setText(intent.getStringExtra("fatherName"));
        String avatar1 = intent.getStringExtra("avatar");
        txtroom.setText(roomNum);


        Picasso.get().load(avatar1).resize(550,550).centerCrop().into(imgavatar1);


        if (occupied.equals("2")){
//        display the information there
            txtusername.setText(intent.getStringExtra("userName1"));
            txtemail.setText(intent.getStringExtra("email1"));
            txtphone.setText(intent.getStringExtra("phone1"));
            txtaddress.setText(intent.getStringExtra("address1"));
            txtbranch.setText(intent.getStringExtra("branch1"));
            txtrollNumber.setText(intent.getStringExtra("rollNumber1"));
            txtfatherName.setText(intent.getStringExtra("fatherName1"));
            String avatar = intent.getStringExtra("avatar1");
            txtroom.setText(roomNum);

            Picasso.get().load(avatar).resize(550,550).centerCrop().into(imgavatar1);


            txtusername2.setText(intent.getStringExtra("userName2"));
            txtemail2.setText(intent.getStringExtra("email2"));
            txtphone2.setText(intent.getStringExtra("phone2"));
            txtaddress2.setText(intent.getStringExtra("address2"));
            txtbranch2.setText(intent.getStringExtra("branch2"));
            txtrollNumber2.setText(intent.getStringExtra("rollNumber2"));
            txtfatherName2.setText(intent.getStringExtra("fatherName2"));
            String avatar2 = intent.getStringExtra("avatar2");

            Picasso.get().load(avatar2).resize(550,550).centerCrop().into(imgavatar2);

        }


    }
}