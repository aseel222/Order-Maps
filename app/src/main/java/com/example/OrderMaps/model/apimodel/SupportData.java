package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupportData {
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
