package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeBillResponse {
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("data")
    @Expose
    private HomeBillData data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public HomeBillData getData() {
        return data;
    }

    public void setData(HomeBillData data) {
        this.data = data;
    }
}
