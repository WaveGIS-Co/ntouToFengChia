package com.wavegis.kafka_consumer_service.kafka;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;
import com.wavegis.kafka_consumer_service.service.DataDistributionService;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    
    @Autowired
    private DataDistributionService dataDistributionService;
   
    public static Acknowledgment ack;
    
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

    @KafkaListener(topics = "sensordata")
    public void listen(String kafkaMessage, Acknowledgment ack) {

//        logger.info("1.kafkaMessage={}",kafkaMessage);
        
        String st_no = kafkaMessage.split(",")[0];
        String org_id = kafkaMessage.split(",")[1];
        this.filterDatas(org_id, st_no);
        dataDistributionService.distribution(st_no, kafkaMessage);
        KafkaConsumer.ack = ack;    

        KafkaConsumer.kafkaAcknowledge(st_no);
        return ;

    }
    
    public static void kafkaAcknowledge(String st_no) {
        KafkaConsumer.ack.acknowledge();
    }
}
