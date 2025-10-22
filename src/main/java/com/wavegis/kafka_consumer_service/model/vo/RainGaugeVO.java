package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RainGaugeVO {

    @JsonProperty(value = "codeName")
    private String codeName;        //設備代號

    @JsonProperty(value="accumulateRainfall")
    private Double accumulateRainfall;      //雨量
}
