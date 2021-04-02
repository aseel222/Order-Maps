package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillOrderResponse {
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("data")
    @Expose
    private List<BillDatum> data = null;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<BillDatum> getData() {
        return data;
    }

    public void setData(List<BillDatum> data) {
        this.data = data;
    }
}
