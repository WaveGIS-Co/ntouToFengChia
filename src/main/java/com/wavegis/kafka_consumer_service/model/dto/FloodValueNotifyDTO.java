package com.wavegis.kafka_consumer_service.model.dto;

import com.google.gson.annotations.SerializedName;

public class FloodValueNotifyDTO {

	@SerializedName("st_no")
	private String stNo;

	@SerializedName("org_id")
	private String orgId;

	@SerializedName("datatime")
	private String datatime;

	@SerializedName("water_inner")
	private Double waterInner;
	
	@SerializedName("water_inner_bed")
	private Double waterInnerBed;

	@SerializedName("battery")
	private Double battery;
	
	@SerializedName("rssi")
	private Double rssi;

	@SerializedName("trust")
	private Boolean trust;
	
	@SerializedName("notify")
	private Boolean notify;
	
	@SerializedName("bedok")
	private Boolean bedok;
	
	@SerializedName("kafka")
	private Boolean kafka;
	
	@SerializedName("olddata")
	private Boolean olddata;
	
	@SerializedName("version")
	private String version;

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

    public Double getWaterInnerBed() {
        return waterInnerBed;
    }

    public void setWaterInnerBed(Double waterInnerBed) {
        this.waterInnerBed = waterInnerBed;
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

    public Boolean getTrust() {
        return trust;
    }

    public void setTrust(Boolean trust) {
        this.trust = trust;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    public Boolean getBedok() {
        return bedok;
    }

    public void setBedok(Boolean bedok) {
        this.bedok = bedok;
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

    @Override
    public String toString() {
        return "FloodValueNotifyDTO [stNo=" + stNo + ", orgId=" + orgId + ", datatime=" + datatime + ", waterInner="
                + waterInner + ", waterInnerBed=" + waterInnerBed + ", battery=" + battery + ", rssi=" + rssi
                + ", trust=" + trust + ", notify=" + notify + ", bedok=" + bedok + ", kafka=" + kafka + ", olddata="
                + olddata + ", version=" + version + "]";
    }

}
