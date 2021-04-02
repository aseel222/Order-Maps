package com.example.OrderMaps.model.apimodel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogOutResponse {
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("data")
    @Expose
    private String data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
