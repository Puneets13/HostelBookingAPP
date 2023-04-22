package com.example.hostelappnitj.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class fetchAllStudentList {
    @SerializedName("list")
    List<person> userList;
    String message,userName , hostelName;

    public fetchAllStudentList(String hostelName) {
        this.hostelName = hostelName;
    }

    public List<person> getUserList() {
        return userList;
    }

    public void setUserList(List<person> userList) {
        this.userList = userList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }
}
