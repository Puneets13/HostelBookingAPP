package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hostelappnitj.R;
import com.example.hostelappnitj.SharedPrefManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class successScanActivity extends AppCompatActivity {
TextView txtStudentName , txtHostelname , txtRollNumber , txtRoomNumber , txtDate , txtTime , txtEmail;
SharedPrefManager sharedPrefManager;
ImageView img1 , img2 , img3 , img4 , img5 , img6 , img7 , img8 , img9 , imageCircle;
    Animation fadeAnim , zoom_out , side_slide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_scan);
        // Adding this line will prevent taking screenshot in your app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

//        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        txtStudentName = findViewById(R.id.txtStudentName);
        txtRollNumber = findViewById(R.id.txtStudentRollNumber);
        txtRoomNumber = findViewById(R.id.txtStudentRoomNumber);
        txtHostelname = findViewById(R.id.txtHostelname);
        txtEmail = findViewById(R.id.txtEmail);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);
        imageCircle=findViewById(R.id.imgCircle);

        sharedPrefManager= new SharedPrefManager(this.getApplicationContext());

        fadeAnim= AnimationUtils.loadAnimation(this,R.anim.middle_anim_success);
        zoom_out=AnimationUtils.loadAnimation(this,R.anim.zoom_out_success);
        side_slide = AnimationUtils.loadAnimation(this,R.anim.side_slide);

        img1.setAnimation(side_slide);
        img7.setAnimation(fadeAnim);
        img5.setAnimation(zoom_out);
        img6.setAnimation(fadeAnim);
        img9.setAnimation(fadeAnim);
        img2.setAnimation(zoom_out);
        img3.setAnimation(side_slide);
        img4.setAnimation(zoom_out);
        img8.setAnimation(zoom_out);
        imageCircle.setAnimation(zoom_out);

        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh.mm aa");
        String dateString2 = dateFormat2.format(new Date()).toString();
//        System.out.println("Current date and time in AM/PM: "+dateString2);
        txtTime.setText(dateString2);
        txtEmail.setText(sharedPrefManager.getUser().getEmail());
        txtHostelname.setText(sharedPrefManager.getHostelUser().getHostelName());
        txtRollNumber.setText(sharedPrefManager.getUser().getRollNumber());
//        txtRoomNumber.setText(sharedPrefManager.getHostelUser().getRoomNumber());
        txtStudentName.setText(sharedPrefManager.getUser().getUsername());


    }
}