package com.wavegis.kafka_consumer_service.service;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;


@ConfigurationProperties(prefix = "filter")
@Getter
@Setter
@Service
public class FilterService {

    private List<String> waterStnos;
    private List<String> floodStnos;

    public boolean isFloodStation(String stno) {
        return floodStnos.contains(stno);
    }

    public boolean isWaterStation(String stno) {
        return waterStnos.contains(stno);
    }
}

