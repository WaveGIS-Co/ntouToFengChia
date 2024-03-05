package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavegis.kafka_consumer_service.model.dto.IowSensorListDTO;
import com.wavegis.kafka_consumer_service.util.Util;

public class IowSensorListVO {
    
    @JsonProperty("id")
    private int id;
    
    @JsonProperty("org_id")
    private String org_id;
    
    @JsonProperty("iow_accounts_org_id")
    private String iow_accounts_org_id;
    
    @JsonProperty("st_no")
    private String st_no;
    
    @JsonProperty("device_type")
    private String device_type;
    
    @JsonProperty("sensor_type")
    private String sensor_type;
    
    @JsonProperty("iow_uuid")
    private String iow_uuid;

    @JsonProperty("description")
    private String description;
    
    @JsonProperty("last_time")
    private String last_time;
    
    @JsonProperty("last_data")
    private String last_data;

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
    
    public String getIow_accounts_org_id() {
        return iow_accounts_org_id;
    }

    public void setIow_accounts_org_id(String iow_accounts_org_id) {
        this.iow_accounts_org_id = iow_accounts_org_id;
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

    public String getSensor_type() {
        return sensor_type;
    }

    public void setSensor_type(String sensor_type) {
        this.sensor_type = sensor_type;
    }

    public String getIow_uuid() {
        return iow_uuid;
    }

    public void setIow_uuid(String iow_uuid) {
        this.iow_uuid = iow_uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getLast_data() {
        return last_data;
    }

    public void setLast_data(String last_data) {
        this.last_data = last_data;
    }

    public IowSensorListDTO convertToDto() {
        IowSensorListDTO dto = Util.toVo(this, new IowSensorListDTO());
        return dto;
    }
}
