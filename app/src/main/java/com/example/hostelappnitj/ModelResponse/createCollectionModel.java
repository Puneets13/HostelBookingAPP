package com.example.hostelappnitj.ModelResponse;

public class createCollectionModel {
    String messStartDate,hostelName,initialAmount,message , error;

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

    public createCollectionModel(String messStartDate, String hostelName, String initialAmount) {
        this.messStartDate = messStartDate;
        this.hostelName = hostelName;
        this.initialAmount = initialAmount;
    }

    public String getMessStartDate() {
        return messStartDate;
    }

    public void setMessStartDate(String messStartDate) {
        this.messStartDate = messStartDate;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(String initialAmount) {
        this.initialAmount = initialAmount;
    }
}
