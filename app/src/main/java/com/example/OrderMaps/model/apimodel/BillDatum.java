package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillDatum {


        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("provider_id")
        @Expose
        private Integer providerId;
        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("modified")
        @Expose
        private String modified;
        @SerializedName("order")
        @Expose
        private OrderBill order;
        @SerializedName("provider")
        @Expose
        private Provider provider;
        @SerializedName("company")
        @Expose
        private Company company;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public Integer getProviderId() {
            return providerId;
        }

        public void setProviderId(Integer providerId) {
            this.providerId = providerId;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public OrderBill getOrder() {
            return order;
        }

        public void setOrder(OrderBill order) {
            this.order = order;
        }

        public Provider getProvider() {
            return provider;
        }

        public void setProvider(Provider provider) {
            this.provider = provider;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

    }

