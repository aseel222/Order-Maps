package com.example.OrderMaps.model;

public class MoneyModel {
    String ordername;
    String orderdetails;
    String moneytxt;

    public MoneyModel(String ordername, String orderdetails, String moneytxt) {
        this.ordername = ordername;
        this.orderdetails = orderdetails;
        this.moneytxt = moneytxt;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(String orderdetails) {
        this.orderdetails = orderdetails;
    }

    public String getMoneytxt() {
        return moneytxt;
    }

    public void setMoneytxt(String moneytxt) {
        this.moneytxt = moneytxt;
    }
}
