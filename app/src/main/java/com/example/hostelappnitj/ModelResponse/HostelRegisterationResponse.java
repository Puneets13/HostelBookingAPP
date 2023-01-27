package com.example.hostelappnitj.ModelResponse;

public class HostelRegisterationResponse {
  private   String message , status ;
  private   hostel hostel;

    private String userName;
    private String email,year;
    private String rollNumber,roomNumber,hostelName;
    private String  phone , address,branch,fatherPhone , fatherName;


    public HostelRegisterationResponse(String userName, String email, String rollNumber, String roomNumber, String hostelName, String phone, String address, String branch, String fatherPhone, String fatherName,String year) {
        this.userName = userName;
        this.email = email;
        this.rollNumber = rollNumber;
        this.roomNumber = roomNumber;
        this.hostelName = hostelName;
        this.phone = phone;
        this.address = address;
        this.branch = branch;
        this.fatherPhone = fatherPhone;
        this.fatherName = fatherName;
        this.year=year;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
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



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public com.example.hostelappnitj.ModelResponse.hostel getHostel() {
        return hostel;
    }

    public void setHostel(com.example.hostelappnitj.ModelResponse.hostel hostel) {
        this.hostel = hostel;
    }
}
