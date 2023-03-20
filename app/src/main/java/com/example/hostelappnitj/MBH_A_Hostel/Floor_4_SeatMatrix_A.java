package com.example.hostelappnitj.MBH_A_Hostel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.TextView;

import com.alexvasilkov.gestures.views.interfaces.GestureView;
import com.example.hostelappnitj.ModelResponse.hostel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.SharedPrefManager;
import com.example.hostelappnitj.databinding.ActivityFloor1SeatMatrix2Binding;
import com.example.hostelappnitj.databinding.ActivityFloor4SeatMatrix2Binding;

import java.util.List;

public class Floor_4_SeatMatrix_A extends AppCompatActivity {

    private ActivityFloor4SeatMatrix2Binding binding;
    AppCompatButton room301;
    AppCompatButton btnBook3;
    GestureView gestureView;
    SharedPrefManager sharedPrefManager;
    String hostelName, username, rollNumber, email, branch;
    TextView display;
    List<hostel> hostelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor4_seat_matrix2);
    }
}