package com.example.OrderMaps.model.apimodel.orderdetailsmodel;

import com.example.OrderMaps.model.apimodel.Datum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("data")
        @Expose
        private List<DatumSearch> data = null;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<DatumSearch> getData() {
            return data;
        }

        public void setData(List<DatumSearch> data) {
            this.data = data;
        }
}

