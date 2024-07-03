package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavegis.kafka_consumer_service.model.dto.NtouSensorListDTO;
import com.wavegis.kafka_consumer_service.util.Util;

public class NtouSensorListVO {

    @JsonProperty("id")
    private int id;
    
    @JsonProperty("org_id")
    private String orgId;
        
    @JsonProperty("st_no")
    private String stNo;
    
    @JsonProperty("device_type")
    private String deviceType;

    @JsonProperty("stt_no")
    private String sttNo;
    
    @JsonProperty("dev_id")
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

    public NtouSensorListDTO convertDto() {
        NtouSensorListDTO dto = Util.toVo(this, new NtouSensorListDTO());
        return dto;
    }

}
