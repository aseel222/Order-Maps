package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatisticsDatum {
    @SerializedName("company")
    @Expose
    private StasticsCompany company;
    @SerializedName("amount")
    @Expose
    private String amount;

    public StasticsCompany getCompany() {
        return company;
    }

    public void setCompany(StasticsCompany company) {
        this.company = company;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
