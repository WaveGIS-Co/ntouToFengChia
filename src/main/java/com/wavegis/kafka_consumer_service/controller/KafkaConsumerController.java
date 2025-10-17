package com.wavegis.kafka_consumer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class KafkaConsumerController {

    private static final String TOPIC = "sensordata";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @ApiOperation(value="kafka測試純文本" ,notes="")
    @RequestMapping(value = "template/string", method = RequestMethod.POST)
    public void sendMessage(String kafkaVos) {
        kafkaTemplate.send(TOPIC, kafkaVos);
    }
}
