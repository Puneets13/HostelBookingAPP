//package com.example.hostelappnitj.Acitvity;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.GridView;
//
//import com.example.hostelappnitj.CalendarAdapter;
//import com.example.hostelappnitj.R;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//public class AttendanceActivity extends AppCompatActivity {
//
//    private GridView gridViewCalendar;
//    private CalendarAdapter calendarAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_attendance);
//
//
//        gridViewCalendar = findViewById(R.id.gridViewCalendar);
//
//        // Generate dummy calendar data (e.g., days of the month)
//        List<String> days = new ArrayList<>();
//        Calendar calendar = Calendar.getInstance();
//        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        for (int i = 1; i <= daysInMonth; i++) {
//            days.add(String.valueOf(i));
//        }
//
//        // Initialize and set the adapter to the GridView
//        calendarAdapter = new CalendarAdapter(getApplicationContext(), days);
//        gridViewCalendar.setAdapter(calendarAdapter);
//    }
//}