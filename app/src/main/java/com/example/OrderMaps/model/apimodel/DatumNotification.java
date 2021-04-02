package com.example.OrderMaps.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumNotification {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("send_sms")
    @Expose
    private Boolean sendSms;
    @SerializedName("send_email")
    @Expose
    private Boolean sendEmail;
    @SerializedName("send_fcm")
    @Expose
    private Boolean sendFcm;
    @SerializedName("model_name")
    @Expose
    private String modelName;
    @SerializedName("model_id")
    @Expose
    private Integer modelId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("created")
    @Expose
    private Object created;
    @SerializedName("modified")
    @Expose
    private Object modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getSendSms() {
        return sendSms;
    }

    public void setSendSms(Boolean sendSms) {
        this.sendSms = sendSms;
    }

    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public Boolean getSendFcm() {
        return sendFcm;
    }

    public void setSendFcm(Boolean sendFcm) {
        this.sendFcm = sendFcm;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getCreated() {
        return created;
    }

    public void setCreated(Object created) {
        this.created = created;
    }

    public Object getModified() {
        return modified;
    }

    public void setModified(Object modified) {
        this.modified = modified;
    }

}
