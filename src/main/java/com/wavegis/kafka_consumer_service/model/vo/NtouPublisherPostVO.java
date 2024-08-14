package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NtouPublisherPostVO {
    
    @JsonProperty("st_no")
    private String stNo;
    
    @JsonProperty("device_type")
    private String deviceType;
    
    @JsonProperty("datatime")
    private String datatime;
    
    @JsonProperty("sendtime")
    private String sendtime;
    
    @JsonProperty("water_inner")
    private double waterInner;
    
    @JsonProperty("water_inner_bed")
    private double waterInnerBed;
    
    @JsonProperty("rain")
    private double rain;

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

    public double getWaterInnerBed() {
        return waterInnerBed;
    }

    public void setWaterInnerBed(double waterInnerBed) {
        this.waterInnerBed = waterInnerBed;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

}
