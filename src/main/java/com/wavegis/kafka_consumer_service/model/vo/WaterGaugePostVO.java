package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WaterGaugePostVO {

    @JsonProperty(value = "codeName")
    private String CodeName;
    @JsonProperty(value = "currentWaterLevel")
    private Double CurrentWaterLevel;
}
