package com.example.hostelappnitj.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreRegisterResponse {
    private String roomNumber,rollNumber,email,hostelName;  //hostelListComponents
    private String message,error;//proceed function ===  2

    @SerializedName("hostels")    //means the actuall name in the response is users but we are using userList so we serialized annotations
    List<statusModel> hostelStatusList;  //getAllStatuses ===1

    public PreRegisterResponse(String roomNumber, String rollNumber, String email,String hostelName) {
        this.rollNumber = rollNumber;//unique
        this.email = email;//unique
        this.roomNumber = roomNumber;
        this.hostelName=hostelName;
        //room and hostel name to mark in proces to fill form

    }

    //===1  getAllStatuses - no constructor associated
    // ===2 proceed associated
    public PreRegisterResponse(String roomNumber, String hostelName) {
        this.roomNumber = roomNumber;
        this.hostelName = hostelName;
        //gets response as message and error
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
