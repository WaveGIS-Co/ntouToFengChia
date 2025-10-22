package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RainGaugeVO {

    @JsonProperty(value = "codeName")
    private String codeName;        //設備代號

    @JsonProperty(value="accumulateRainfall")
    private Double accumulateRainfall;      //雨量
}
