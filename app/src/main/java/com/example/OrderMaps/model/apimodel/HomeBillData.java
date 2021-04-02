package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeBillData {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("today")
    @Expose
    private Integer today;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }
}
