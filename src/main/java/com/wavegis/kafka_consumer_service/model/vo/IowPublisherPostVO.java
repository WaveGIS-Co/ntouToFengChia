package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavegis.kafka_consumer_service.model.dto.IowPublisherDTO;
import com.wavegis.kafka_consumer_service.util.Util;

public class IowPublisherPostVO {

    @JsonProperty("org_id")
    private String orgId;
    
    @JsonProperty("device_type")
    private String deviceType;
    
    @JsonProperty("st_no")
    private String stNo;
    
    @JsonProperty("datatime")
    private String datatime;
    
    @JsonProperty("sendtime")
    private String sendtime;
    
    @JsonProperty("water_inner")
    private double waterInner;
    
    @JsonProperty("rain")
    private double rain;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getStNo() {
        return stNo;
    }

    public void setStNo(String stNo) {
        this.stNo = stNo;
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

    public double getWaterInner() {
        return waterInner;
    }

    public void setWaterInner(double waterInner) {
        this.waterInner = waterInner;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }
    
    public IowPublisherDTO convertDto() {
        IowPublisherDTO dto = Util.toVo(this, new IowPublisherDTO());
        return dto;
    }

    @Override
    public String toString() {
        return "IowPublisherPostVO [orgId=" + orgId + ", deviceType=" + deviceType + ", stNo=" + stNo + ", datatime="
                + datatime + ", sendtime=" + sendtime + ", waterInner=" + waterInner + ", rain=" + rain + "]";
    }
}
