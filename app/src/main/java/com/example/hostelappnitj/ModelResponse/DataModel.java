package com.example.hostelappnitj.ModelResponse;

public class DataModel {
    private String username,gender;
    private String email , hostelName;
    private String rollNumber;
    private String password ;
    private String message="" ;
    private String error="" ;
    private String token;
    private User user ;
    private String user_Id;

    private hostel hostel ;

    public hostel getHostel() {
        return hostel;
    }

    public void setHostel(hostel hostel) {
        this.hostel = hostel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // For Register
    public DataModel(String rollNumber, String email, String password,String username,String gender) {
        this.rollNumber = rollNumber;
        this.email = email;
        this.password = password;
        this.username=username;
        this.gender=gender;
    }

    //    For Login
    public DataModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public DataModel(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
