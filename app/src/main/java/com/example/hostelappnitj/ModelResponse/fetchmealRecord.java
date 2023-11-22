package com.example.hostelappnitj.ModelResponse;

import java.util.List;

public class fetchmealRecord {
    List<mealRecord> mealList;
    String error;
    String rollNumber , message , hostelName,roomNumber ;

    public fetchmealRecord(String rollNumber, String hostelName, String roomNumber) {
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
        this.roomNumber = roomNumber;
    }

    public List<mealRecord> getMealList() {
        return mealList;
    }

    public void setMealList(List<mealRecord> mealList) {
        this.mealList = mealList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
