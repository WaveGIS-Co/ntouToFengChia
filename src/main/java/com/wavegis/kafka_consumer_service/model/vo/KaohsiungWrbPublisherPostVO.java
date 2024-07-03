package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;

public class KaohsiungWrbPublisherPostVO {

    @JsonProperty("batteryvol")
    private Double batteryvol;

    @JsonProperty("datatime")
    private String datatime;

    @JsonProperty("rssi")
    private Double rssi;

    @JsonProperty("st_no")
    private String stNo;

    @JsonProperty("water_inner")
    private Double waterInner;

    @JsonProperty("version")
    private String version;

    public KaohsiungWrbPublisherPostVO fromKafkaDTO(KafkaDTO vo) {
        KaohsiungWrbPublisherPostVO model = this;
        model.setStNo(vo.getSt_no());
        model.setDatatime(vo.getDatatime());
        model.setBatteryvol(vo.getBattery());
        model.setRssi(Double.valueOf(vo.getRssi()));
        model.setWaterInner(vo.getWater_inner());
        model.setVersion(vo.getVersion());
        return this;
    }

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

    public String getStNo() {
        return stNo;
    }

    public void setStNo(String stNo) {
        this.stNo = stNo;
    }

    public Double getWaterInner() {
        return waterInner;
    }

    public void setWaterInner(Double waterInner) {
        this.waterInner = waterInner;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
