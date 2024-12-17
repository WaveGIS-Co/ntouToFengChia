package com.wavegis.kafka_consumer_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RainDataDTO {

    @JsonProperty("stid")
    private String stNo;
    
    @JsonProperty("org_id")
    private String orgId;
    
    @JsonProperty("datatime")
    private String datatime;
    
    @JsonProperty("battery")
    private Double battery;
    
    @JsonProperty("min_10")
    private Double min_10;
    
    @JsonProperty("min_30")
    private Double min_30;
    
    @JsonProperty("rain")
    private Double rain;
    
    @JsonProperty("hour_3")
    private Double hour_3;
    
    @JsonProperty("hour_6")
    private Double hour_6;
    
    @JsonProperty("hour_12")
    private Double hour_12;
    
    @JsonProperty("hour_24")
    private Double hour_24;

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

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public Double getBattery() {
        return battery;
    }

    public void setBattery(Double battery) {
        this.battery = battery;
    }

    public Double getMin_10() {
        return min_10;
    }

    public void setMin_10(Double min_10) {
        this.min_10 = min_10;
    }

    public Double getMin_30() {
        return min_30;
    }

    public void setMin_30(Double min_30) {
        this.min_30 = min_30;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    public Double getHour_3() {
        return hour_3;
    }

    public void setHour_3(Double hour_3) {
        this.hour_3 = hour_3;
    }

    public Double getHour_6() {
        return hour_6;
    }

    public void setHour_6(Double hour_6) {
        this.hour_6 = hour_6;
    }

    public Double getHour_12() {
        return hour_12;
    }

    public void setHour_12(Double hour_12) {
        this.hour_12 = hour_12;
    }

    public Double getHour_24() {
        return hour_24;
    }

    public void setHour_24(Double hour_24) {
        this.hour_24 = hour_24;
    }
}
