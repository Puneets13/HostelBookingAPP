package com.example.hostelappnitj.ModelResponse;

public class innvoiceModel {
   public String rollNumber , hostelName , month , year , dietCount , perDietCost , totalInvoice , message , error;

    public innvoiceModel(String rollNumber, String hostelName, String month, String year) {
        this.rollNumber = rollNumber;
        this.hostelName = hostelName;
        this.month = month;
        this.year = year;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDietCount() {
        return dietCount;
    }

    public void setDietCount(String dietCount) {
        this.dietCount = dietCount;
    }

    public String getPerDietCost() {
        return perDietCost;
    }

    public void setPerDietCost(String perDietCost) {
        this.perDietCost = perDietCost;
    }

    public String getTotalInvoice() {
        return totalInvoice;
    }

    public void setTotalInvoice(String totalInvoice) {
        this.totalInvoice = totalInvoice;
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
}
