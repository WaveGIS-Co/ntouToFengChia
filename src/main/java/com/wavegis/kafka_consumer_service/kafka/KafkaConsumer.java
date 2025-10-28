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

       // filterDatas就是測試方便，需要測哪一個的時候把那個listener的filterDatas註解 然後測試資料org_id設為test
    private boolean filterDatas(String org_id, String st_no) {

        if (org_id == null || "test".equals(org_id)) {
            logger.warn("[FILTERED] org_id={} is null or test", org_id);
            return false;
        }

        if (st_no == null || st_no.isEmpty()) {
            logger.error("[FILTERED] st_no is null or empty");
            return false;
        }
        return true;
    }

    // ----------------------------- iow upload -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-iow-0", topics = "sensordata", groupId = "kafka_consumer_service_java-iow")
    public void listenWavegisSensorIow(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }
        // this.filterDatas(orgId, stNo);沒用的東西
        dataDistributionService.distribution("sensordata", PublisherEnum.iow, orgId, stNo, kafkaMessage);

        ack.acknowledge();
    }

    @KafkaListener(id = "kafka_consumer_service_java-iow-1", topics = "sensordata_post", groupId = "kafka_consumer_service_java-iow")
    public void listenOtherSensorIow(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }
        dataDistributionService.distribution("sensordata_post", PublisherEnum.iow, orgId, stNo, kafkaMessage);

        ack.acknowledge();
    }

    // ----------------------------- ntou upload -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-ntou-0", topics = "sensordata", groupId = "kafka_consumer_service_java-ntou")
    public void listenWavegisSensorNtou(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0].replace("\"", "");
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }
        dataDistributionService.distribution("sensordata", PublisherEnum.ntou, orgId, stNo, kafkaMessage);

        // dataDistributionService.distribution("sensordata",
        // PublisherEnum.changhuaSewer, orgId, stNo, kafkaMessage);

        ack.acknowledge();
    }
//南投資料丟逢甲
    @KafkaListener(id = "kafka_consumer_service_java-ntouToFengChia-0", topics = "sensordata", groupId = "kafka_consumer_service_java-ntouToFengChia")
    public void listenWavegisSensorNtouToFengChia(String kafkaMessage, Acknowledgment ack) {
        String stNo = kafkaMessage.split(",")[0].replace("\"", "");
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }

        dataDistributionService.distribution("sensordata", PublisherEnum.ntouToFengChia, orgId, stNo, kafkaMessage);
        ack.acknowledge();
    }

    @KafkaListener(id = "kafka_consumer_service_java-ntou-1", topics = "sensordata_post", groupId = "kafka_consumer_service_java-ntou")
    public void listenOtherSensorNtou(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }
        dataDistributionService.distribution("sensordata_post", PublisherEnum.ntou, orgId, stNo, kafkaMessage);

        ack.acknowledge();
    }

    // ----------------------------- tpeSewer upload
    // -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-tpeSewer-0", topics = "sensordata", groupId = "kafka_consumer_service_java-tpeSewer")
    public void listenWavegisSensorTpeSewer(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }
        dataDistributionService.distribution("sensordata", PublisherEnum.tpeSewer, orgId, stNo, kafkaMessage);

        ack.acknowledge();
    }

    // ----------------------------- kaohsiungWrb upload
    // -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-kaohsiungWrb-0", topics = "sensordata", groupId = "kafka_consumer_service_java-kaohsiungWrb")
    public void listenWavegisSensorKaohsiungWrb(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0];
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }
        dataDistributionService.distribution("sensordata", PublisherEnum.kaohsiungWrb, orgId, stNo, kafkaMessage);

        ack.acknowledge();
    }

    // ----------------------------- ntpc upload -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-ntpc-0", topics = "sensordata", groupId = "kafka_consumer_service_java-ntpc")
    public void listenWavegisSensorNtpc(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0].replace("\"", "");
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }

        dataDistributionService.distribution("sensordata", PublisherEnum.ntpc, orgId, stNo, kafkaMessage);

        dataDistributionService.distribution("sensordata", PublisherEnum.ntpcSewer, orgId, stNo, kafkaMessage);

        ack.acknowledge();
    }

    @KafkaListener(id = "kafka_consumer_service_java-ntpc-1", topics = "raindata", groupId = "kafka_consumer_service_java-ntpc")
    public void listenWavegisRaindataNtpc(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0].replace("\"", "");
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }

        dataDistributionService.distribution("raindata", PublisherEnum.ntpc, orgId, stNo, kafkaMessage);

        dataDistributionService.distribution("raindata", PublisherEnum.ntpcSewer, orgId, stNo, kafkaMessage);

        ack.acknowledge();
    }

    // ----------------------------- changhua upload
    // -----------------------------------
    @KafkaListener(id = "kafka_consumer_service_java-changhuaSewer-0", topics = "sensordata", groupId = "kafka_consumer_service_java-changhuaSewer")
    public void listenWavegisSensorChanghuaSewer(String kafkaMessage, Acknowledgment ack) {

        String stNo = kafkaMessage.split(",")[0].replace("\"", "");
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }

        dataDistributionService.distribution("sensordata", PublisherEnum.changhuaSewer, orgId, stNo, kafkaMessage);

        ack.acknowledge();
    }

    @KafkaListener(id = "kafka_consumer_service_java-changhuaFloodWater-0", topics = "sensordata", groupId = "kafka_consumer_service_java-changhuaFloodWater")
    public void listenWavegisSensorChanghuaFloodWater(String kafkaMessage, Acknowledgment ack) {
        String stNo = kafkaMessage.split(",")[0].replace("\"", "");
        String orgId = kafkaMessage.split(",")[1];
        if (!filterDatas(orgId, stNo)) {
            return;
        }
        dataDistributionService.distribution("sensordata", PublisherEnum.changhuaFloodWater, orgId, stNo, kafkaMessage);
        ack.acknowledge();

    }

}
