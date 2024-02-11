package com.example.hostelappnitj.ModelResponse;

import java.util.Map;

public class extra_item_model {
    private  String message,hostelName,itemName,error,prevItemName;
    Integer itemPrice,prevItemPrice;
    Map<String,Integer> items;

    public extra_item_model(Integer itemPrice, String hostelName, String itemName) {
        this.itemPrice = itemPrice;
        this.hostelName = hostelName;
        this.itemName = itemName;

    }

    public extra_item_model(String hostelName, String itemName) {
        this.hostelName = hostelName;
        this.itemName = itemName;
    }

    public extra_item_model(String hostelName, String itemName, String prevItemName, Integer itemPrice) {
        this.hostelName = hostelName;
        this.itemName = itemName;
        this.prevItemName = prevItemName;
        this.itemPrice = itemPrice;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getPrevItemPrice() {
        return prevItemPrice;
    }

    public void setPrevItemPrice(Integer prevItemPrice) {
        this.prevItemPrice = prevItemPrice;
    }

    public String getPrevItemName() {
        return prevItemName;
    }

    public void setPrevItemName(String prevItemName) {
        this.prevItemName = prevItemName;
    }
}
