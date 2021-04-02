package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelOrderResponse {
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("new")
    @Expose
    private String _new;
    @SerializedName("data")
    @Expose
    private CancelorderData data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNew() {
        return _new;
    }

    public void setNew(String _new) {
        this._new = _new;
    }

    public CancelorderData getData() {
        return data;
    }

    public void setData(CancelorderData data) {
        this.data = data;
    }

}
