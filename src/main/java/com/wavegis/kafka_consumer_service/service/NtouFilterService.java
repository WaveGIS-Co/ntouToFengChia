package com.wavegis.kafka_consumer_service.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.config.NtouConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NtouFilterService {

    private final NtouConfig config;

    public boolean isNtouWaterStation(String stno) {
        return config.getWaterStnos() != null && config.getWaterStnos().containsKey(stno);
    }

    public boolean isNtouRainStation(String stno) {
        return config.getRainStnos() != null && config.getRainStnos().containsKey(stno);
    }

    public boolean isNtouFloodStation(String stno) {
        return config.getFloodStnos() != null && config.getFloodStnos().containsKey(stno);
    }


    @PostConstruct
    public void printConfig() {
        System.out.println("[DEBUG] Water stnos: " + config.getWaterStnos());
        System.out.println("[DEBUG] Flood stnos: " + config.getRainStnos());
        System.out.println("[DEBUG] Flood stnos: " + config.getFloodStnos());
    }
}
