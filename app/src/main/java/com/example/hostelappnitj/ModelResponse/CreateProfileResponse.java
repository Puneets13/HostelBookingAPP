package com.example.hostelappnitj.ModelResponse;

public class CreateProfileResponse {
    String username , phone , address ,message , branch;
User user;
    public CreateProfileResponse(String username, String phone, String address, String branch) {
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.branch=branch;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
