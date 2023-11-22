package com.example.hostelappnitj.ModelResponse;

public class leaveModel {
    String rollNumber , hostelName , roomNumber , startDate , endDate , startMeal , endMeal;
    String message , error ;
    int dietCount ;
    String year , month;
    public leaveModel(String rollNumber, String hostelName, String roomNumber, String startDate, String endDate, String startMeal, String endMeal) {
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startMeal = startMeal;
        this.endMeal = endMeal;
    }

    public leaveModel(String rollNumber, String hostelName, String year) {
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
        this.year = year;
    }

    public leaveModel(String rollNumber, String hostelName, String year, String month) {
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
        this.year = year;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDietCount() {
        return dietCount;
    }

    public void setDietCount(int dietCount) {
        this.dietCount = dietCount;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartMeal() {
        return startMeal;
    }

    public void setStartMeal(String startMeal) {
        this.startMeal = startMeal;
    }

    public String getEndMeal() {
        return endMeal;
    }

    public void setEndMeal(String endMeal) {
        this.endMeal = endMeal;
    }
}
