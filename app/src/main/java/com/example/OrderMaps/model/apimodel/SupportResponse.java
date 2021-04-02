package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupportResponse {
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
