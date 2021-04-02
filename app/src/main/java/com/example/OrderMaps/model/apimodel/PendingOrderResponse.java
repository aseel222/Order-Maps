package com.example.OrderMaps.model.apimodel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PendingOrderResponse {

    @SerializedName("state")
    private String state;
    @SerializedName("data")
    private ArrayList<Datum> data = null;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }



}
