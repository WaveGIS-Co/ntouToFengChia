package com.wavegis.kafka_consumer_service.model.dto;

import com.google.gson.annotations.SerializedName;

public class NtouSensorListDTO {

    @SerializedName("id")
    private int id;
    
    @SerializedName("org_id")
    private String org_id;
        
    @SerializedName("st_no")
    private String st_no;
    
    @SerializedName("device_type")
    private String device_type;
    
    @SerializedName("stt_no")
    private String stt_no;
    
    @SerializedName("dev_id")
    private String dev_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getSt_no() {
        return st_no;
    }

    public void setSt_no(String st_no) {
        this.st_no = st_no;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getStt_no() {
        return stt_no;
    }

    public void setStt_no(String stt_no) {
        this.stt_no = stt_no;
    }

    public String getDev_id() {
        return dev_id;
    }

    public void setDev_id(String dev_id) {
        this.dev_id = dev_id;
    }
}
