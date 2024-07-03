package com.wavegis.kafka_consumer_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.ITpeSewerPublisherService;
import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;
import com.wavegis.kafka_consumer_service.model.vo.TpeSewerPublisherPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

@Service
public class TpeSewerService {
    
    @Autowired
    private ITpeSewerPublisherService iTpeSewerPublisherService;

    public int postData(String st_no, List<TpeSewerPublisherPostVO> voList) {
        return Util.callApiResponseCode(iTpeSewerPublisherService.postData(st_no ,voList), "iTpeSewerPublisherService.postData");
    }

	public Boolean hasStnosByKafkaString(String kafkaMessage) {
        KafkaDTO dto = new KafkaDTO();
        dto.setStrs(kafkaMessage.split(","));
        return dto.getDeviceType().equals("WG_R_W") && dto.getOrgId().equals("68");
	}
}
