package com.example.OrderMaps.model.apimodel.orderdetailsmodel;

import java.util.List;

import com.example.OrderMaps.model.apimodel.Datum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsResponse {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("data")
    @Expose
    private List<Datum2> data = null;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Datum2> getData() {
        return data;
    }

    public void setData(List<Datum2> data) {
        this.data = data;
    }



}
