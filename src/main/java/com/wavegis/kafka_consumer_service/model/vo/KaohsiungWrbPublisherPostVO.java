package com.wavegis.kafka_consumer_service.model.vo;

import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;

public class KaohsiungWrbPublisherPostVO {
    private Double batteryvol;
    private String datatime;
    private Double rssi;
    private String st_no;
    private Double water_inner;
    private String version;
    public Double getBatteryvol() {
        return batteryvol;
    }
    public void setBatteryvol(Double batteryvol) {
        this.batteryvol = batteryvol;
    }
    public String getDatatime() {
        return datatime;
    }
    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }
    public Double getRssi() {
        return rssi;
    }
    public void setRssi(Double rssi) {
        this.rssi = rssi;
    }
    public String getSt_no() {
        return st_no;
    }
    public void setSt_no(String st_no) {
        this.st_no = st_no;
    }
    public Double getWater_inner() {
        return water_inner;
    }
    public void setWater_inner(Double water_inner) {
        this.water_inner = water_inner;
    }
    public KaohsiungWrbPublisherPostVO fromKafkaDTO(KafkaDTO vo) {
        KaohsiungWrbPublisherPostVO model = this;
        model.setSt_no(vo.getSt_no());
        model.setDatatime(vo.getDatatime());
        model.setBatteryvol(vo.getBattery());
        model.setRssi(Double.valueOf(vo.getRssi()));
        model.setWater_inner(vo.getWater_inner());
        model.setVersion(vo.getVersion());
        return this;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    } 
}
