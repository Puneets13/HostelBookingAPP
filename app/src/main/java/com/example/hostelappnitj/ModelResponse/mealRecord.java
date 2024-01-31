package com.example.hostelappnitj.ModelResponse;

public class mealRecord {
private String date , breakfast , lunch , dinner,meal_type;
    private String rollNumber , roomNumber,hostelName,avatar,userName,message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    public String getUserName() {
        return userName;
    }

    public mealRecord(String date, String breakfast, String lunch, String dinner) {
        this.date = date;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }
//
//    public mealRecord(String userName, String avatar, String rollNumber, String roomNumber,String date, String meal) {
//        this.date = date;
//        this.meal = meal;
//        this.rollNumber = rollNumber;
//        this.roomNumber = roomNumber;
//        this.avatar = avatar;
//        this.userName = userName;
//    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
