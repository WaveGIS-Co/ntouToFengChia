package com.wavegis.kafka_consumer_service.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String convert(Object obj) {

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            return "{ \"error\": \"JSON 轉換失敗: " + e.getMessage() + "\" }";
        }

    }
}
