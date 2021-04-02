package com.example.OrderMaps.model.apimodel.orderdetailsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatumSearch {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("company_id")
    @Expose
    private Integer companyId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("tax")
    @Expose
    private Integer tax;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("products")
    @Expose
    private List<ProductSearch> products = null;
    @SerializedName("company")
    @Expose
    private CompanySearch company;
    @SerializedName("provider")
    @Expose
    private ProviderSearch provider;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<ProductSearch> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSearch> products) {
        this.products = products;
    }

    public CompanySearch getCompany() {
        return company;
    }

    public void setCompany(CompanySearch company) {
        this.company = company;
    }

    public ProviderSearch getProvider() {
        return provider;
    }

    public void setProvider(ProviderSearch provider) {
        this.provider = provider;
    }

}
