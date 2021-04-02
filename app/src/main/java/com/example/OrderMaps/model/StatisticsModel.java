package com.example.OrderMaps.model;

public class StatisticsModel {
    String companyname;
    String stasticsnum;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getStasticsnum() {
        return stasticsnum;
    }

    public void setStasticsnum(String stasticsnum) {
        this.stasticsnum = stasticsnum;
    }

    public StatisticsModel(String companyname, String stasticsnum) {
        this.companyname = companyname;
        this.stasticsnum = stasticsnum;
    }
}
