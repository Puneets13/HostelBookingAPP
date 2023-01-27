package com.example.hostelappnitj.ModelResponse;

public class hostel_ID_Response {
    private  String message;
    hostel hostel;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public com.example.hostelappnitj.ModelResponse.hostel getHostel() {
        return hostel;
    }

    public void setHostel(com.example.hostelappnitj.ModelResponse.hostel hostel) {
        this.hostel = hostel;
    }
}
