package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NtouPublisherPostVO {
    
    @JsonProperty("device_type")
    private String deviceType;
    
    @JsonProperty("datatime")
    private String datatime;
    
    @JsonProperty("sendtime")
    private String sendtime;
    
    @JsonProperty("water_inner_bed")
    private double waterInnerBed;

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

    public double getWaterInnerBed() {
        return waterInnerBed;
    }

    public void setWaterInnerBed(double waterInnerBed) {
        this.waterInnerBed = waterInnerBed;
    }

}
