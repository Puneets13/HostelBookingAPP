package com.example.hostelappnitj;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CalendarAdapter extends BaseAdapter {

    private Context context;
    private List<String> days;
    private int currentMonth;
    private int currentYear;

    public CalendarAdapter(Context context, List<String> days, int currentMonth, int currentYear) {
        this.context = context;
        this.days = days;
        this.currentMonth = currentMonth;
        this.currentYear = currentYear;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_calendar, parent, false);
        }

        TextView textViewCalendarItem = convertView.findViewById(R.id.textViewCalendarItem);
        String day = days.get(position);

        // Check if the day is empty (placeholder for days before or after the current month)
        if (day.isEmpty()) {
            textViewCalendarItem.setText(""); // Clear text for empty placeholders
            textViewCalendarItem.setTextColor(Color.TRANSPARENT); // Hide empty placeholders
        } else {
            int dayOfMonth = Integer.parseInt(day);

            // Set text
            textViewCalendarItem.setText(day);

            // Determine if the day belongs to the current month and year
            boolean isCurrentMonth = dayOfMonth == getCurrentDayOfMonth() && currentMonth == getCurrentMonth() && currentYear == getCurrentYear();

            // Customize appearance based on whether it's the current month and year
            if (isCurrentMonth) {
                textViewCalendarItem.setTextColor(Color.RED); // Highlight current day with red color
            } else {
                textViewCalendarItem.setTextColor(Color.BLACK); // Reset color for other days
            }
        }

        return convertView;
    }

    private int getCurrentDayOfMonth() {
        // Get current day of the month
        return java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);
    }

    private int getCurrentMonth() {
        // Get current month (0-based, so January is 0)
        return java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    }

    private int getCurrentYear() {
        // Get current year
        return java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    }
}
