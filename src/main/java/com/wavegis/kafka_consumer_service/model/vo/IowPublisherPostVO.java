package com.wavegis.kafka_consumer_service.model.vo;

import com.wavegis.kafka_consumer_service.model.dto.IowPublisherDTO;
import com.wavegis.kafka_consumer_service.util.Util;

public class IowPublisherPostVO {

    private String org_id;
    
    private String device_type;
    
    private String st_no;
    
    private String datatime;
    
    private String sendtime;
    
    private double water_inner;
    
    private double rain;

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getSt_no() {
        return st_no;
    }

    public void setSt_no(String st_no) {
        this.st_no = st_no;
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

    public double getWater_inner() {
        return water_inner;
    }

    public void setWater_inner(double water_inner) {
        this.water_inner = water_inner;
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
        return "IowPublisherPostVO [org_id=" + org_id + ", device_type=" + device_type + ", st_no=" + st_no
                + ", datatime=" + datatime + ", sendtime=" + sendtime + ", water_inner=" + water_inner + ", rain="
                + rain + "]";
    }
}
