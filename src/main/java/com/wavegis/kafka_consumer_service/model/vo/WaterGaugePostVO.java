package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaterGaugePostVO {

    @JsonProperty(value = "codeName")
    private String CodeName;
    @JsonProperty(value = "currentWaterLevel")
    private Double CurrentWaterLevel;





}
