package com.wavegis.kafka_consumer_service.model.vo;

public class NtouPublisherVO {
    
    private String device_type;
    
    private String datatime;
    
    private String sendtime;
    
    private double water_inner;
    
    private double water_inner_bed;
    
    private double rain;

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
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

    public double getWater_inner() {
        return water_inner;
    }

    public void setWater_inner(double water_inner) {
        this.water_inner = water_inner;
    }

    public double getWater_inner_bed() {
        return water_inner_bed;
    }

    public void setWater_inner_bed(double water_inner_bed) {
        this.water_inner_bed = water_inner_bed;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

 
}
