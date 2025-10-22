package com.wavegis.kafka_consumer_service.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "ntoufilter")
@Getter
@Setter
@Service
public class NtouConfig {

    private Map<String,String> ntouWaterStnoMap;

    private Map<String,String> ntouRainStnoMap;

    private Map<String,String> ntouFloodStnoMap;




}
