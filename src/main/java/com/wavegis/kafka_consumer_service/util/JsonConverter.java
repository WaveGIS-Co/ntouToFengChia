package com.wavegis.kafka_consumer_service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConverter {

     private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // 支援 Java 8 時間 API
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // 關掉 timestamp 模式

    public static String convert(Object obj) {

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            return "{ \"error\": \"JSON 轉換失敗: " + e.getMessage() + "\" }";
        }

    }
}
