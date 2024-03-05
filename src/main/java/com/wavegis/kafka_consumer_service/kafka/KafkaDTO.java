package com.wavegis.kafka_consumer_service.kafka;

public class KafkaDTO {
    
    private String[] strs;
    
    private String st_no;
    
    private String org_id;
    
    private String device_type;
    
    private String datatime;
    
    private String sendtime;
    
    private double battery;
    
    private String rssi;
    
    private String speed;
    
    private double water_inner;
    
    private double water_inner_bed;
    
    private String version;
    
    private double rain;
    
    private void converDto() {
        
        this.st_no = strs[0].replace("\"", "");
        this.org_id = strs[1];
        this.device_type = strs[2];
        this.datatime = strs[3];
        this.sendtime = strs[4];
        this.battery = Double.valueOf(strs[5]);
        this.rssi = strs[6];
        this.speed = strs[7];
        this.water_inner = Double.valueOf(strs[8]);
        this.water_inner_bed = Double.valueOf(strs[9]);
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

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
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
