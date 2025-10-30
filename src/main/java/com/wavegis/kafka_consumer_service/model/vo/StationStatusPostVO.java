package com.wavegis.kafka_consumer_service.model.vo;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class StationStatusPostVO {
    @JsonProperty(value = "stationCodeName")
    private String stationCodeName;// 測站代號

    @JsonProperty(value = "stationTime")
    private String stationTime;

    @JsonProperty("isNetworkFailed")
    private boolean networkFailed;

    @JsonProperty(value = "rainGauge")
    private RainGaugeVO rainGauge;

    @JsonProperty(value = "waterGaugeArray")
    private List<WaterGaugePostVO> waterGaugeArray;

    public StationStatusPostVO(String stationCodeName, String stationTime, boolean networkFailed) {
        this.stationCodeName = stationCodeName;
        this.stationTime = stationTime;
        this.networkFailed = networkFailed;

    }

}
