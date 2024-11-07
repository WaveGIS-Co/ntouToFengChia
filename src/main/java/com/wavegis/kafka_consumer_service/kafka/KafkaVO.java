package com.wavegis.kafka_consumer_service.kafka;

import java.time.LocalDateTime;

import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KafkaVO {

    @JsonProperty("st_no")
    private String st_no;
    
    @JsonProperty("datatime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Past
    private LocalDateTime datatime;
    
    @JsonProperty("sendtime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Past
    private LocalDateTime sendtime;
    
    @JsonProperty("water_inner")
    private Double water_inner;
    
    public String getSt_no() {
        return st_no;
    }
    
    public void setSt_no(String st_no) {
        this.st_no = st_no;
    }
    
    public LocalDateTime getDatatime() {
        return datatime;
    }
    
    public void setDatatime(LocalDateTime datatime) {
        this.datatime = datatime;
    }
    
    public LocalDateTime getSendtime() {
        return sendtime;
    }
    
    public void setSendtime(LocalDateTime sendtime) {
        this.sendtime = sendtime;
    }
    
    public Double getWater_inner() {
        return water_inner;
    }
    
    public void setWater_inner(Double water_inner) {
        this.water_inner = water_inner;
    }
}
