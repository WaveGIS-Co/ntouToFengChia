package com.wavegis.kafka_consumer_service.model.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationStatusPostVO {
    @JsonProperty(value = "stationCodeName")
    private String stationCodeName;// 測站代號

    @JsonProperty(value = "stationTime")
    private LocalDateTime stationTime;

    @JsonProperty(value = "isNetworkFailed")
    private boolean isNetworkFailed;

    @JsonProperty(value = "rainGauge")
    private RainGaugeVO rainGauge;

    @JsonProperty(value = "waterGaugeArray")
    private List<WaterGaugePostVO> waterGaugeArray;

    public StationStatusPostVO(String stationCodeName, LocalDateTime stationTime, boolean isNetworkFailed) {
        this.stationCodeName = stationCodeName;
        this.stationTime = stationTime;
        this.isNetworkFailed = isNetworkFailed;

    }

    public StationStatusPostVO(String stationCodeName, LocalDateTime stationTime, boolean isNetworkFailed,
            RainGaugeVO rainGauge) {
        this.stationCodeName = stationCodeName;
        this.stationTime = stationTime;
        this.isNetworkFailed = isNetworkFailed;
        this.rainGauge = rainGauge;
    }

    public StationStatusPostVO(String stationCodeName, LocalDateTime stationTime, boolean isNetworkFailed,
            List<WaterGaugePostVO> waterGaugeArray) {
        this.stationCodeName = stationCodeName;
        this.stationTime = stationTime;
        this.isNetworkFailed = isNetworkFailed;
        this.waterGaugeArray = waterGaugeArray;
    }

}
