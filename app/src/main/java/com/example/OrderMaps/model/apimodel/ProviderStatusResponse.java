package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderStatusResponse {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("new")
    @Expose
    private String _new;
    @SerializedName("data")
    @Expose
    private ProviderStatusData data;

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

    public ProviderStatusData getData() {
        return data;
    }

    public void setData(ProviderStatusData data) {
        this.data = data;
    }



}
