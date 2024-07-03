package com.wavegis.kafka_consumer_service.model.dto;

import com.google.gson.annotations.SerializedName;

public class IowPublisherDTO {

    @SerializedName("org_id")
    private String orgId;
    
    @SerializedName("st_no")
    private String stNo;
    
    @SerializedName("device_type")
    private String deviceType;
    
    @SerializedName("datatime")
    private String datatime;
    
    @SerializedName("sendtime")
    private String sendtime;
    
    @SerializedName("water_inner")
    private double waterInner;
    
    @SerializedName("rain")
    private double rain;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStNo() {
        return stNo;
    }

    public void setStNo(String stNo) {
        this.stNo = stNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public double getWaterInner() {
        return waterInner;
    }

    public void setWaterInner(double waterInner) {
        this.waterInner = waterInner;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }
}
