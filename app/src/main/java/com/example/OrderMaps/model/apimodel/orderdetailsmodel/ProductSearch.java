package com.example.OrderMaps.model.apimodel.orderdetailsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductSearch {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("parcode")
    @Expose
    private String parcode;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("_joinData")
    @Expose
    private JoinDataSearch joinData;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getParcode() {
        return parcode;
    }

    public void setParcode(String parcode) {
        this.parcode = parcode;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public JoinDataSearch getJoinData() {
        return joinData;
    }

    public void setJoinData(JoinDataSearch joinData) {
        this.joinData = joinData;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
