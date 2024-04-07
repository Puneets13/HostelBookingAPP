package com.example.hostelappnitj.ModelResponse;
public class CalendarDay {
    private String date;
    private int dietCount;
    private boolean onLeave;

    public CalendarDay(String date, int dietCount, boolean onLeave) {
        this.date = date;
        this.dietCount = dietCount;
        this.onLeave = onLeave;
    }

    public String getDate() {
        return date;
    }

    public int getDietCount() {
        return dietCount;
    }

    public boolean isOnLeave() {
        return onLeave;
    }
}
