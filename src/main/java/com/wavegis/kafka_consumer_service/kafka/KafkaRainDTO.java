package com.wavegis.kafka_consumer_service.kafka;

public class KafkaRainDTO {
    
    private String[] strs;
    
    private String stNo;
    
    private String orgId;
    
    private Double battery;
    
    private String datatime;
    
    private Double min_10;
    
    private Double min_30;
    
    private Double rain;
    
    private Double hour_3;
    
    private Double hour_6;
    
    private Double hour_12;
    
    private Double hour_24;
    
    private void converDto() {
        
        this.stNo = strs[0].replace("\"", "");
        this.orgId = strs[1];
        this.battery = Double.valueOf(strs[2]);
        this.datatime = strs[3];
        this.min_10 = Double.valueOf(strs[4]);
        this.min_30 = Double.valueOf(strs[5]);
        this.rain = Double.valueOf(strs[6]);
        this.hour_3 = Double.valueOf(strs[7]);
        this.hour_6 = Double.valueOf(strs[8]);
        this.hour_12 = Double.valueOf(strs[9]);
        this.hour_24 = Double.valueOf(strs[10]);
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
