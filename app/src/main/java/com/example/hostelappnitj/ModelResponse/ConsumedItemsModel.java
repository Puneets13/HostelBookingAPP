package com.example.hostelappnitj.ModelResponse;

import java.util.List;

public class ConsumedItemsModel {
    String date,breakfast,lunch,dinner;
    List<ExtraItemModel> breakfastExtra ,lunchExtra, eveningExtra,dinnerExtra;
    Integer extraTotal;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public List<ExtraItemModel> getBreakfastExtra() {
        return breakfastExtra;
    }

    public void setBreakfastExtra(List<ExtraItemModel> breakfastExtra) {
        this.breakfastExtra = breakfastExtra;
    }

    public List<ExtraItemModel> getLunchExtra() {
        return lunchExtra;
    }

    public void setLunchExtra(List<ExtraItemModel> lunchExtra) {
        this.lunchExtra = lunchExtra;
    }

    public List<ExtraItemModel> getEveningExtra() {
        return eveningExtra;
    }

    public void setEveningExtra(List<ExtraItemModel> eveningExtra) {
        this.eveningExtra = eveningExtra;
    }

    public List<ExtraItemModel> getDinnerExtra() {
        return dinnerExtra;
    }

    public void setDinnerExtra(List<ExtraItemModel> dinnerExtra) {
        this.dinnerExtra = dinnerExtra;
    }

    public Integer getExtraTotal() {
        return extraTotal;
    }

    public void setExtraTotal(Integer extraTotal) {
        this.extraTotal = extraTotal;
    }
}
