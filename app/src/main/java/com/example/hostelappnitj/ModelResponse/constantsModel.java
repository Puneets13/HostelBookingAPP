package com.example.hostelappnitj.ModelResponse;

import java.util.List;
import java.util.Map;

public class constantsModel {
    private  String hostelName , message ;
    private Map<String, Integer> item;

    private Map<String, Integer> TotalExpenditurePerMonth;
    public constantsModel(String hostelName) {
        this.hostelName = hostelName;
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

    public Map<String, Integer> getItem() {
        return item;
    }

    public Map<String, Integer> getTotalExpenditurePerMonth() {
        return TotalExpenditurePerMonth;
    }

    public void setTotalExpenditurePerMonth(Map<String, Integer> totalExpenditurePerMonth) {
        TotalExpenditurePerMonth = totalExpenditurePerMonth;
    }

    public void setItem(Map<String, Integer> item) {
        this.item = item;
    }
}
