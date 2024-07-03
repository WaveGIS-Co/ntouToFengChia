package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavegis.kafka_consumer_service.model.dto.IowSensorListDTO;
import com.wavegis.kafka_consumer_service.util.Util;

public class IowSensorListVO {
    
    @JsonProperty("id")
    private int id;
    
    @JsonProperty("org_id")
    private String orgId;
    
    @JsonProperty("iow_accounts_org_id")
    private String iowAccountsOrgId;
    
    @JsonProperty("st_no")
    private String stNo;
    
    @JsonProperty("device_type")
    private String deviceType;
    
    @JsonProperty("sensor_type")
    private String sensorType;
    
    @JsonProperty("iow_uuid")
    private String iowUuid;

    @JsonProperty("description")
    private String description;
    
    @JsonProperty("last_time")
    private String lastTime;
    
    @JsonProperty("last_data")
    private String lastData;

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

    public String getIowAccountsOrgId() {
        return iowAccountsOrgId;
    }

    public void setIowAccountsOrgId(String iowAccountsOrgId) {
        this.iowAccountsOrgId = iowAccountsOrgId;
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

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getIowUuid() {
        return iowUuid;
    }

    public void setIowUuid(String iowUuid) {
        this.iowUuid = iowUuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastData() {
        return lastData;
    }

    public void setLastData(String lastData) {
        this.lastData = lastData;
    }

    public IowSensorListDTO convertDto() {
        IowSensorListDTO dto = Util.toVo(this, new IowSensorListDTO());
        return dto;
    }
}
