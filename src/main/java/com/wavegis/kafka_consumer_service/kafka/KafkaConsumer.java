package com.wavegis.kafka_consumer_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.service.DataDistributionService;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    
    @Autowired
    private DataDistributionService dataDistributionService;

    private void filterDatas(String org_id, String st_no) {
        
        if(org_id == null || "test".equals(org_id)) {
            logger.error("org_id={} is test",org_id);
            return ;
        }
        
        if(st_no == null || "".equals(st_no)) {
            logger.error("st_no is error");
            return ;
        }
    }

    @KafkaListener(id = "kafka_consumer_service_java-0", topics = "sensordata", groupId = "kafka_consumer_service_java")
    public void listenWavegisSensor(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        this.filterDatas(orgId, stNo);
        dataDistributionService.distribution("sensordata", stNo, kafkaMessage);

        ack.acknowledge();
    }
    
    @KafkaListener(id = "kafka_consumer_service_java-1", topics = "sensordata_post", groupId = "kafka_consumer_service_java")
    public void listenOtherSensor(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        this.filterDatas(orgId, stNo);
        dataDistributionService.distribution("sensordata_post", stNo, kafkaMessage);

        ack.acknowledge();
    }
}
