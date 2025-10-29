package com.wavegis.kafka_consumer_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FloodValueAllDTO {

	@JsonProperty("st_no")
	private String stNo;

	@JsonProperty("org_id")
	private String orgId;

	@JsonProperty("datatime")
	private String datatime;
	
	@JsonProperty("sendtime")
	private String sendtime;
	
	@JsonProperty("device_type")
	private String deviceType;

	@JsonProperty("water")
	private Double waterInner;
	
	@JsonProperty("battery")
	private Double battery;
	
	@JsonProperty("rssi")
	private Double rssi;
	
	@JsonProperty("speed")
	private Double speed;
    
    @JsonProperty("ex_value1")
    private Double exValue1;
    
    @JsonProperty("ex_value2")
    private Double exValue2;

	@JsonProperty("trust")
	private Boolean trust;
	
	@JsonProperty("kafka")
	private Boolean kafka;
	
	@JsonProperty("olddata")
	private Boolean olddata;
	
	@JsonProperty("version")
	private String version;

    public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
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

    public Double getWaterInner() {
        return waterInner;
    }

    public void setWaterInner(Double waterInner) {
        this.waterInner = waterInner;
    }

    public Double getBattery() {
        return battery;
    }

    public void setBattery(Double battery) {
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

    public Double getExValue1() {
        return exValue1;
    }

    public void setExValue1(Double exValue1) {
        this.exValue1 = exValue1;
    }

    public Double getExValue2() {
        return exValue2;
    }

    public void setExValue2(Double exValue2) {
        this.exValue2 = exValue2;
    }

    public Boolean getTrust() {
        return trust;
    }

    public void setTrust(Boolean trust) {
        this.trust = trust;
    }

    public Boolean getKafka() {
        return kafka;
    }

    public void setKafka(Boolean kafka) {
        this.kafka = kafka;
    }

    public Boolean getOlddata() {
        return olddata;
    }

    public void setOlddata(Boolean olddata) {
        this.olddata = olddata;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return "FloodValueAllDTO [stNo=" + stNo + ", orgId=" + orgId + ", datatime=" + datatime + ", deviceType="
                + deviceType + ", waterInner=" + waterInner + ", battery=" + battery + ", rssi=" + rssi + ", speed="
                + speed + ", exValue1=" + exValue1 + ", exValue2=" + exValue2 + ", trust=" + trust + ", kafka=" + kafka
                + ", olddata=" + olddata + ", version=" + version + "]";
    }

}
