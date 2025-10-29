package com.wavegis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "sensordata" })
@DirtiesContext
public class KafkaLinstenerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private String receivedMessage;
    private CountDownLatch latch = new CountDownLatch(1);

    // 模擬 KafkaListener
    @KafkaListener(topics = "sensordata", groupId = "test-group")
    public void listen(ConsumerRecord<String, String> record) {
        receivedMessage = record.value();
        System.out.println("收到 Kafka 訊息：" + receivedMessage);
        latch.countDown();
    }

    @Test
    void testSendAndReceive() throws Exception {
        String msg = "\"WG_RR_W_00109\",CH01,2025-10-20 09:00:00,12.3";
        kafkaTemplate.send("sensordata", msg);

        // 等待 listener 接收訊息（最多等 5 秒）
        boolean received = latch.await(5, TimeUnit.SECONDS);

        assert received : "Listener 沒有收到訊息";
        assert receivedMessage.contains("WG_RR_W_00109");
        System.out.println("✅ 測試成功，Listener 收到：" + receivedMessage);
    }

}
