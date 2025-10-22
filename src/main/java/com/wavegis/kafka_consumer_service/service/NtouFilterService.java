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
        return config.getNtouWaterStnoMap() != null && config.getNtouWaterStnoMap().containsKey(stno);
    }

    public boolean isNtouRainStation(String stno) {
        return config.getNtouRainStnoMap() != null && config.getNtouRainStnoMap().containsKey(stno);
    }

    public boolean isNtouFloodStation(String stno) {
        return config.getNtouFloodStnoMap() != null && config.getNtouFloodStnoMap().containsKey(stno);
    }

    public String getStationCodeName(String stno){
        if(isNtouWaterStation(stno)){
            return config.getNtouWaterStnoMap().get(stno);
        }else if(isNtouWaterStation(stno)){
            return config.getNtouRainStnoMap().get(stno);
        }else if (isNtouFloodStation(stno)) {
            return config.getNtouFloodStnoMap().get(stno);
        }
        return null;
    }

    @PostConstruct
    public void printConfig() {
        System.out.println("[DEBUG] Water stnos: " + config.getNtouWaterStnoMap());
        System.out.println("[DEBUG] Flood stnos: " + config.getNtouRainStnoMap());
        System.out.println("[DEBUG] Flood stnos: " + config.getNtouFloodStnoMap());
    }
}
