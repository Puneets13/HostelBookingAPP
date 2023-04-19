package com.example.hostelappnitj.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreRegisterResponse {
    private String roomNumber,rollNumber,email,hostelName;
    private String message,error;

    @SerializedName("hostels")    //means the actuall name in the response is users but we are using userList so we serialized annotations
    List<statusModel> hostelStatusList;

    public PreRegisterResponse(String roomNumber, String rollNumber, String email,String hostelName) {
        this.roomNumber = roomNumber;
        this.rollNumber = rollNumber;
        this.email = email;
        this.hostelName=hostelName;
    }

    public List<statusModel> getHostelStatusList() {
        return hostelStatusList;
    }

    public void setHostelStatusList(List<statusModel> hostelStatusList) {
        this.hostelStatusList = hostelStatusList;
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

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
