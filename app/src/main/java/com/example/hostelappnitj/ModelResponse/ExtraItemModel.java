package com.example.hostelappnitj.ModelResponse;

public class ExtraItemModel {
    String  item ;
    Integer amount;

    public ExtraItemModel(String item, Integer amount) {
        this.item = item;
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
