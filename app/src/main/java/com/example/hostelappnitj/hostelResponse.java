package com.example.hostelappnitj;

public class hostelResponse {
    private String userName;
    private String email;
    private String rollNumber,roomNumber,hostelName;

    public hostelResponse(String userName, String email, String rollNumber, String roomNumber, String hostelName) {
        this.userName = userName;
        this.email = email;
        this.rollNumber = rollNumber;
        this.roomNumber = roomNumber;
        this.hostelName = hostelName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }
}
