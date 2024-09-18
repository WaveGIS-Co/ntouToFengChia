package com.wavegis.kafka_consumer_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NtouDevicesDTO {

    @JsonProperty("org_id")
    private String orgId;
    
    @JsonProperty("st_no")
    private String stNo;
    
    @JsonProperty("dev_id")
    private String devId ;

    @JsonProperty("lon")
    private Double lon ;
    
    @JsonProperty("lat")
    private Double lat ;
    
    @JsonProperty("device_type")
    private String deviceType;
    
    @JsonProperty("dev_purpose")
    private String devPurpose ;
    
    @JsonProperty("manufacturer")
    private String manufacturer ;
    
    @JsonProperty("dev_model")
    private String devModel ;
    
    @JsonProperty("depoist_date")
    private String depoistDate ;
    
    @JsonProperty("trans_method")
    private String transMethod ;
    
    @JsonProperty("ip")
    private String ip ;
    
    @JsonProperty("power_model")
    private String powerModel ;
    
    @JsonProperty("sampling_period")
    private Integer samplingPeriod ;
    
    @JsonProperty("measure_period")
    private Integer measurePeriod ;
    
    @JsonProperty("upload_period")
    private Integer uploadPeriod ;
    
    @JsonProperty("base_elev")
    private Double baseElev ;    
    
    @JsonProperty("val_name")
    private String valName;
    
    @JsonProperty("stt_no")
    private String sttNo;
    
    public NtouDevicesDTO() {
        super();
    }

    public NtouDevicesDTO(String orgId, String stNo, String devPurpose, String devId) {
        super();
        this.orgId = orgId;
        this.stNo = stNo;
        this.devPurpose = devPurpose;
        this.devId = devId;
    }
    
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    public String getStNo() {
        return stNo;
    }

    public void setStNo(String stNo) {
        this.stNo = stNo;
    }

    public String getSttNo() {
        return sttNo;
    }

    public void setSttNo(String sttNo) {
        this.sttNo = sttNo;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getValName() {
        return valName;
    }

    public void setValName(String valName) {
        this.valName = valName;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDevPurpose() {
        return devPurpose;
    }

    public void setDevPurpose(String devPurpose) {
        this.devPurpose = devPurpose;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDevModel() {
        return devModel;
    }

    public void setDevModel(String devModel) {
        this.devModel = devModel;
    }

    public String getDepoistDate() {
        return depoistDate;
    }

    public void setDepoistDate(String depoistDate) {
        this.depoistDate = depoistDate;
    }

    public String getTransMethod() {
        return transMethod;
    }

    public void setTransMethod(String transMethod) {
        this.transMethod = transMethod;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPowerModel() {
        return powerModel;
    }

    public void setPowerModel(String powerModel) {
        this.powerModel = powerModel;
    }

    public Integer getSamplingPeriod() {
        return samplingPeriod;
    }

    public void setSamplingPeriod(Integer samplingPeriod) {
        this.samplingPeriod = samplingPeriod;
    }

    public Integer getMeasurePeriod() {
        return measurePeriod;
    }

    public void setMeasurePeriod(Integer measurePeriod) {
        this.measurePeriod = measurePeriod;
    }

    public Integer getUploadPeriod() {
        return uploadPeriod;
    }

    public void setUploadPeriod(Integer uploadPeriod) {
        this.uploadPeriod = uploadPeriod;
    }

    public Double getBaseElev() {
        return baseElev;
    }

    public void setBaseElev(Double baseElev) {
        this.baseElev = baseElev;
    }
}
