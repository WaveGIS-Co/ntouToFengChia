package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;

public class TpeSewerPublisherPostVO {
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
    private double battery;
    
    @JsonProperty("rssi")
    private double rssi;
    
    @JsonProperty("speed")
    private String speed;
    
    @JsonProperty("water_inner")
    private double waterInner;
    
    @JsonProperty("water_inner_bed")
    private double waterInnerBed;
    
    @JsonProperty("version")
    private String version;
    
    @JsonProperty("rain")
    private double rain;

    public TpeSewerPublisherPostVO fromKafkaDTO(KafkaDTO vo) {
        TpeSewerPublisherPostVO model = this;
        model.setStNo(vo.getSt_no());
        model.setOrgId(vo.getOrg_id());
        model.setDeviceType(vo.getDevice_type());
        model.setDatatime(vo.getDatatime());
        model.setSendtime(vo.getSendtime());
        model.setBattery(vo.getBattery());
        model.setRssi(Double.valueOf(vo.getRssi()));
        model.setSpeed(vo.getSpeed());
        model.setWaterInner(vo.getWater_inner());
        model.setWaterInner(vo.getWater_inner_bed());
        model.setVersion(vo.getVersion());
        model.setRain(vo.getRain());
        return this;
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

    public double getRssi() {
        return rssi;
    }

    public void setRssi(double rssi) {
        this.rssi = rssi;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
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
