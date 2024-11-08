package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChsewerPostVO {

    @JsonProperty(value="st_no")
    private String stNo;//測站代碼
    
    @JsonProperty(value="sendtime")
    private String sendtime;//資料上傳時間
    
    @JsonProperty(value="datatime")
    private String datatime;//資料量測時間
    
    @JsonProperty(value="device_type")
    private String deviceType;//設備類型

    @JsonProperty(value="water_inner")
    private Double waterInner;//深度(公分)

    public String getStNo() {
        return stNo;
    }

    public void setStNo(String stNo) {
        this.stNo = stNo;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Double getWaterInner() {
        return waterInner;
    }

    public void setWaterInner(Double waterInner) {
        this.waterInner = waterInner;
    }

	
}
