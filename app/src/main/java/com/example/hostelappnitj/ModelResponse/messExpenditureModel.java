package com.example.hostelappnitj.ModelResponse;

import java.util.Map;

public class messExpenditureModel {

    private  String message,hostelName,month,error;
    Integer expenditure,prevExpenditure;
    Map<String,Integer> TotalExpenditurePerMonth;

    public messExpenditureModel(String hostelName, String month, Integer expenditure) {
        this.hostelName = hostelName;
        this.month = month;
        this.expenditure = expenditure;
    }

    public messExpenditureModel(String hostelName, String month, Integer expenditure, Integer prevExpenditure) {
        this.hostelName = hostelName;
        this.month = month;
        this.expenditure = expenditure;
        this.prevExpenditure = prevExpenditure;
    }

    public messExpenditureModel(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Integer expenditure) {
        this.expenditure = expenditure;
    }

    public Integer getPrevExpenditure() {
        return prevExpenditure;
    }

    public void setPrevExpenditure(Integer prevExpenditure) {
        this.prevExpenditure = prevExpenditure;
    }

    public Map<String, Integer> getTotalExpenditurePerMonth() {
        return TotalExpenditurePerMonth;
    }

    public void setItems(Map<String, Integer> TotalExpenditurePerMonth) {
        this.TotalExpenditurePerMonth = TotalExpenditurePerMonth;
    }
}
