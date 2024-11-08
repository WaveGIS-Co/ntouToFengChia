package com.wavegis.kafka_consumer_service.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KafkaDTO {
    
    private String[] strs;
    
    @JsonProperty("st_no")
    private String stNo;
    
    @JsonProperty("org_id")
    private String orgId;
    
    @JsonProperty("device_type")
    private String deviceType;
    
    @JsonProperty("datatime")
    private String datatime;
    
    @JsonProperty("sendtime")
    private String sendtime;
    
    @JsonProperty("battery")
    private Double battery;
    
    @JsonProperty("rssi")
    private Double rssi;
    
    @JsonProperty("speed")
    private Double speed;
    
    @JsonProperty("water_inner")
    private Double waterInner;
    
    @JsonProperty("water_inner_bed")
    private Double waterInnerBed;
    
    @JsonProperty("version")
    private String version;
    
    @JsonProperty("rain")
    private double rain;
    
    private void converDto() {
        
        this.stNo = strs[0].replace("\"", "");
        this.orgId = strs[1];
        this.deviceType = strs[2];
        this.datatime = strs[3];
        this.sendtime = strs[4];
        this.battery = Double.valueOf(strs[5]);
        this.rssi = Double.valueOf(strs[6]);
        this.speed = Double.valueOf(strs[7]);
        this.waterInner = Double.valueOf(strs[8]);
        this.waterInnerBed = Double.valueOf(strs[9]);
        this.version = strs[10].replace("\"", "");
        this.rain = Double.valueOf(strs[11]);
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
        this.converDto();
    }

    public String getStNo() {
        return stNo;
    }

    public void setStNo(String stNo) {
        this.stNo = stNo;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public Double getRssi() {
        return rssi;
    }

    public void setRssi(Double rssi) {
        this.rssi = rssi;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

}
