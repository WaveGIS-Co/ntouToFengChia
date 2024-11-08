package com.wavegis.kafka_consumer_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.model.enums.PublisherEnum;
import com.wavegis.kafka_consumer_service.service.DataDistributionService;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    
    @Autowired
    private DataDistributionService dataDistributionService;

    private void filterDatas(String org_id, String st_no) {
        
        if(org_id == null || "test".equals(org_id)) {
            logger.error("org_id={} is null or test",org_id);
            return ;
        }
        
        if(st_no == null || "".equals(st_no)) {
            logger.error("st_no is error");
            return ;
        }
    }

    // ----------------------------- iow upload -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-iow-0", topics = "sensordata", groupId = "kafka_consumer_service_java-iow")
    public void listenWavegisSensorIow(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        this.filterDatas(orgId, stNo);
        dataDistributionService.distribution("sensordata", PublisherEnum.iow, stNo, kafkaMessage);
        
        ack.acknowledge();
    }
    
    @KafkaListener(id = "kafka_consumer_service_java-iow-1", topics = "sensordata_post", groupId = "kafka_consumer_service_java-iow")
    public void listenOtherSensorIow(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        this.filterDatas(orgId, stNo);
        dataDistributionService.distribution("sensordata_post", PublisherEnum.iow, stNo, kafkaMessage);

        ack.acknowledge();
    }

    // ----------------------------- ntou upload -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-ntou-0", topics = "sensordata", groupId = "kafka_consumer_service_java-ntou")
    public void listenWavegisSensorNtou(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0].replace("\"", "");
        String orgId = kafkaMessage.split(",")[1];
        this.filterDatas(orgId, stNo);
        dataDistributionService.distribution("sensordata", PublisherEnum.ntou, stNo, kafkaMessage);
        
        dataDistributionService.distribution("sensordata", PublisherEnum.changhuaSewer, stNo, kafkaMessage);
        
        ack.acknowledge();
    }
    
    @KafkaListener(id = "kafka_consumer_service_java-ntou-1", topics = "sensordata_post", groupId = "kafka_consumer_service_java-ntou")
    public void listenOtherSensorNtou(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        this.filterDatas(orgId, stNo);
        dataDistributionService.distribution("sensordata_post", PublisherEnum.ntou, stNo, kafkaMessage);

        ack.acknowledge();
    }
    
    // ----------------------------- tpeSewer upload -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-tpeSewer-0", topics = "sensordata", groupId = "kafka_consumer_service_java-tpeSewer")
    public void listenWavegisSensorTpeSewer(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        this.filterDatas(orgId, stNo);
        dataDistributionService.distribution("sensordata", PublisherEnum.tpeSewer, stNo, kafkaMessage);
        
        ack.acknowledge();
    }
    
    // ----------------------------- kaohsiungWrb upload -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-kaohsiungWrb-0", topics = "sensordata", groupId = "kafka_consumer_service_java-kaohsiungWrb")
    public void listenWavegisSensorKaohsiungWrb(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        this.filterDatas(orgId, stNo);
        dataDistributionService.distribution("sensordata", PublisherEnum.kaohsiungWrb, stNo, kafkaMessage);
        
        ack.acknowledge();
    }
    

}
