package com.wavegis.kafka_consumer_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.IKaohsiungWrbService;
import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;
import com.wavegis.kafka_consumer_service.model.vo.KaohsiungWrbPublisherPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

@Service
public class KaohsiungWrbService {
    @Autowired
    private IKaohsiungWrbService iKaohsiungWrbService;

    public int postData(List<KaohsiungWrbPublisherPostVO> voList) {
        return Util.callApiResponseCode(iKaohsiungWrbService.postData(voList), "iKaohsiungWrbService.postData");
    }

	public Boolean hasStnosByKafkaString(String kafkaMessage) {
        KafkaDTO dto = new KafkaDTO();
        dto.setStrs(kafkaMessage.split(","));
        return dto.getDevice_type().equals("WG_R_W") && dto.getOrg_id().equals("112") && dto.getVersion().equals("WG-001");
	}
}
