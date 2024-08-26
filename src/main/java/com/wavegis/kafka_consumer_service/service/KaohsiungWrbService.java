package com.wavegis.kafka_consumer_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.IKaohsiungWrbService;
import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;
import com.wavegis.kafka_consumer_service.model.vo.KaohsiungWrbPublisherFloodPostVO;
import com.wavegis.kafka_consumer_service.model.vo.KaohsiungWrbPublisherSewerPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

@Service
public class KaohsiungWrbService {
    @Autowired
    private IKaohsiungWrbService iKaohsiungWrbService;

    public int postDataSewer(List<KaohsiungWrbPublisherSewerPostVO> voList) {
        return Util.callApiResponseCode(iKaohsiungWrbService.postSewerData(voList), "iKaohsiungWrbService.postSewerData");
    }

    public Boolean hasStnosByKafkaStringSewer(String kafkaMessage) {
        KafkaDTO dto = new KafkaDTO();
        dto.setStrs(kafkaMessage.split(","));
        return dto.getDeviceType().equals("WG_R_W") && dto.getOrgId().equals("112") && dto.getVersion().equals("WG-001");
    }

    public int postDataFlood(List<KaohsiungWrbPublisherFloodPostVO> voList) {
        return Util.callApiResponseCode(iKaohsiungWrbService.postFloodData(voList), "iKaohsiungWrbService.postFloodData");
    }

    public Boolean hasStnosByKafkaStringFlood(String kafkaMessage) {
        KafkaDTO dto = new KafkaDTO();
        dto.setStrs(kafkaMessage.split(","));
        return dto.getDeviceType().equals("flood") && dto.getOrgId().equals("112");
    }
}
