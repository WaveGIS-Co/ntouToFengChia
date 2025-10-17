package com.wavegis.kafka_consumer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChuploadPostVO {
    @JsonProperty(value = "st_no")
    private String stNo; // 測站代碼

    @JsonProperty(value = "org_id")
    private String orgId; // 機關代碼

    @JsonProperty(value = "datatime")
    private String datatime; // 資料時間

    @JsonProperty(value = "device_type")
    private String deviceType; // 設備類型

    @JsonProperty(value = "water")
    private Double water; // 水位值

    @JsonProperty(value = "battery")
    private Double battery; // 電池電壓

    @JsonProperty(value = "rssi")
    private Double rssi; // 訊號強度

    @JsonProperty(value = "trust")
    private Boolean trust; // 是否可信

    @JsonProperty(value = "olddata")
    private Boolean olddata; // 是否為舊資料

    @JsonProperty(value = "version")
    private String version; // 資料版本

    //kafka資料轉拋彰化主機的格式轉換方法
    public ChuploadPostVO toChuploadPostVO(KafkaDTO dto) {
        ChuploadPostVO vo = new ChuploadPostVO();
        vo.setStNo(dto.getStNo());
        vo.setOrgId(dto.getOrgId());
        vo.setDatatime(dto.getDatatime());
        vo.setDeviceType(dto.getDeviceType());
        vo.setWater(dto.getWaterInner());
        vo.setBattery(dto.getBattery());
        vo.setRssi(dto.getRssi());
        vo.setTrust(true);
        vo.setOlddata(false);
        vo.setVersion(dto.getVersion());
        return vo;
    }

}
