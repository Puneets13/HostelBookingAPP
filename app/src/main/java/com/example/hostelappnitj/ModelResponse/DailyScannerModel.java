package com.example.hostelappnitj.ModelResponse;

import com.example.hostelappnitj.hostelResponse;

import java.security.SecureRandom;
import java.util.List;

public class DailyScannerModel {
    private String roomNumber , rollNumber , hostelName,userName , month , year , mealType , timestamp,formatedTime_24;
    private String error , message,scan ;
    private List<String>item;
    private int amount;
    private int enterpaisa;
    private hostelResponse hostelResponse;


    public DailyScannerModel(String roomNumber, String hostelName, String month, String year, String mealType, String timestamp, String formatedTime_24, List<String> item, int amount) {
        this.roomNumber = roomNumber;
        this.hostelName = hostelName;
        this.month = month;
        this.year = year;
        this.mealType = mealType;
        this.timestamp = timestamp;
        this.formatedTime_24 = formatedTime_24;
        this.item = item;
        this.amount = amount;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getFormatedTime_24() {
        return formatedTime_24;
    }

    public void setFormatedTime_24(String fromatedTime_24) {
        this.formatedTime_24 = fromatedTime_24;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public DailyScannerModel(String roomNumber, String rollNumber, String hostelName, String month, String year, String mealType, String timestamp,String formatedTime_24) {
        this.roomNumber = roomNumber;
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
        this.month = month;
        this.year = year;
        this.mealType = mealType;
        this.timestamp = timestamp;
        this.formatedTime_24 = formatedTime_24;
    }


    public DailyScannerModel(String roomNumber, String rollNumber, String hostelName, String month, String year, String mealType, String timestamp) {
        this.roomNumber = roomNumber;
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
        this.month = month;
        this.year = year;
        this.mealType = mealType;
        this.timestamp = timestamp;
        this.formatedTime_24 = formatedTime_24;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DailyScannerModel(String roomNumber, String rollNumber, String hostelName) {
        this.roomNumber = roomNumber;
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
    }

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public DailyScannerModel(String roomNumber, String rollNumber, String hostelName, int enterpaisa) {
        this.roomNumber = roomNumber;
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
        this.enterpaisa = enterpaisa;
    }

    public hostelResponse getHostelResponse() {
        return hostelResponse;
    }

    public void setHostelResponse(hostelResponse hostelResponse) {
        this.hostelResponse = hostelResponse;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
