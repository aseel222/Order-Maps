package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FcmTokenResponse {
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("data")
    @Expose
    private FcmData data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public FcmData getData() {
        return data;
    }

    public void setData(FcmData data) {
        this.data = data;
    }
}
