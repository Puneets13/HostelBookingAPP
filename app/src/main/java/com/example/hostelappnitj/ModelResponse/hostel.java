package com.example.hostelappnitj.ModelResponse;

public class hostel {
    private String _id , userName1 , email1,phone1,address1,branch1,rollNumber1,fatherName1,fatherPhone1,roomNumber,hostelName;
    private String       userName2 , email2,phone2,address2,branch2,rollNumber2,fatherName2,fatherPhone2;
    private String message , status ;

//    public hostel(String roomNumber) {
//        this.roomNumber = roomNumber;
//    }

    public hostel(String _id, String roomNumber,String hostelName, String userName1, String email1,String rollNumber1, String phone1,String fatherName1, String fatherPhone1 , String address1, String branch1) {
        this._id = _id;
        this.userName1 = userName1;
        this.email1 = email1;
        this.phone1 = phone1;
        this.address1 = address1;
        this.branch1 = branch1;
        this.rollNumber1 = rollNumber1;
        this.fatherName1 = fatherName1;
        this.fatherPhone1 = fatherPhone1;
        this.roomNumber = roomNumber;
        this.hostelName = hostelName;
    }

    public hostel( String roomNumber, String hostelName) {
        this.roomNumber = roomNumber;
        this.hostelName = hostelName;
    }

    public hostel(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getFatherName1() {
        return fatherName1;
    }

    public void setFatherName1(String fatherName1) {
        this.fatherName1 = fatherName1;
    }

    public String getFatherPhone1() {
        return fatherPhone1;
    }

    public void setFatherPhone1(String fatherPhone1) {
        this.fatherPhone1 = fatherPhone1;
    }

    public String getFatherName2() {
        return fatherName2;
    }

    public void setFatherName2(String fatherName2) {
        this.fatherName2 = fatherName2;
    }

    public String getFatherPhone2() {
        return fatherPhone2;
    }

    public void setFatherPhone2(String fatherPhone2) {
        this.fatherPhone2 = fatherPhone2;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername1() {
        return userName1;
    }

    public void setUsername1(String username1) {
        this.userName1 = username1;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getBranch1() {
        return branch1;
    }

    public void setBranch1(String branch1) {
        this.branch1 = branch1;
    }

    public String getRollNumber1() {
        return rollNumber1;
    }

    public void setRollNumber1(String rollNumber1) {
        this.rollNumber1 = rollNumber1;
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

    public String getUsername2() {
        return userName2;
    }

    public void setUsername2(String username2) {
        this.userName2 = username2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getBranch2() {
        return branch2;
    }

    public void setBranch2(String branch2) {
        this.branch2 = branch2;
    }

    public String getRollNumber2() {
        return rollNumber2;
    }

    public void setRollNumber2(String rollNumber2) {
        this.rollNumber2 = rollNumber2;
    }
}


