package com.example.hostelappnitj.ModelResponse;

import java.util.List;

public class MessDetailModel {
    String message,hostelName,rollNumber , roomNumber,error,month , year;
    List<ConsumedItemsModel> consumedItems;

    public MessDetailModel(String rollNumber, String hostelName, String roomNumber,String month, String year) {
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
        this.month = month;
        this.year = year;
        this.roomNumber=roomNumber;
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

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ConsumedItemsModel> getConsumedItems() {
        return consumedItems;
    }

    public void setConsumedItems(List<ConsumedItemsModel> consumedItems) {
        this.consumedItems = consumedItems;
    }
}
