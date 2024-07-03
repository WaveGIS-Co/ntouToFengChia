package com.wavegis.kafka_consumer_service.model.dto;

import com.google.gson.annotations.SerializedName;

public class NtouSensorListDTO {

    @SerializedName("id")
    private int id;
    
    @SerializedName("org_id")
    private String orgId;
        
    @SerializedName("st_no")
    private String stNo;
    
    @SerializedName("device_type")
    private String deviceType;
    
    @SerializedName("stt_no")
    private String sttNo;
    
    @SerializedName("dev_id")
    private String devId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

}
