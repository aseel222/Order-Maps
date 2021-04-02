package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatisticsResponse {
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("data")
    @Expose
    private List<StatisticsDatum> data = null;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<StatisticsDatum> getData() {
        return data;
    }

    public void setData(List<StatisticsDatum> data) {
        this.data = data;
    }
}
