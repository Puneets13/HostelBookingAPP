package com.example.hostelappnitj.ModelResponse;

public class person {
    String email,phone,address,branch,rollNumber,fatherName,fatherPhone,avatar,username;

    public person(String email, String phone, String address, String branch, String rollNumber, String fatherName, String fatherPhone, String avatar,String username) {
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.branch = branch;
        this.rollNumber = rollNumber;
        this.fatherName = fatherName;
        this.fatherPhone = fatherPhone;
        this.avatar = avatar;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
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

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
