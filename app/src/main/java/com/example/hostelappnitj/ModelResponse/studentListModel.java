package com.example.hostelappnitj.ModelResponse;

public class studentListModel {

   private String roomNumber,hostelName,message;
    private person person1 , person2;



    public studentListModel(String roomNumber, String hostelName) {
        this.roomNumber = roomNumber;
        this.hostelName = hostelName;
    }

    public person getPerson1() {
        return person1;
    }

    public void setPerson1(person person1) {
        this.person1 = person1;
    }

    public person getPerson2() {
        return person2;
    }

    public void setPerson2(person person2) {
        this.person2 = person2;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
