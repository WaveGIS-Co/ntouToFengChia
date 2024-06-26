package com.wavegis.kafka_consumer_service.model.vo;

import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;

public class TpeSewerPublisherPostVO {
    private String st_no;
    
    private String org_id;
    
    private String device_type;
    
    private String datatime;
    
    private String sendtime;
    
    private double battery;
    
    private double rssi;
    
    private String speed;
    
    private double water_inner;
    
    private double water_inner_bed;
    
    private String version;
    
    private double rain;

    public TpeSewerPublisherPostVO fromKafkaDTO(KafkaDTO vo) {
        TpeSewerPublisherPostVO model = this;
        model.setSt_no(vo.getSt_no());
        model.setOrg_id(vo.getOrg_id());
        model.setDevice_type(vo.getDevice_type());
        model.setDatatime(vo.getDatatime());
        model.setSendtime(vo.getSendtime());
        model.setBattery(vo.getBattery());
        model.setRssi(Double.valueOf(vo.getRssi()));
        model.setSpeed(vo.getSpeed());
        model.setWater_inner(vo.getWater_inner());
        model.setWater_inner_bed(vo.getWater_inner_bed());
        model.setVersion(vo.getVersion());
        model.setRain(vo.getRain());
        return this;
    } 

    public String getSt_no() {
        return st_no;
    }

    public void setSt_no(String st_no) {
        this.st_no = st_no;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

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
