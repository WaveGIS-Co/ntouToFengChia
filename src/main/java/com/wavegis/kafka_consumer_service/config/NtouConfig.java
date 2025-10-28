package com.wavegis.kafka_consumer_service.config;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ntouconfig")
public class NtouConfig {

    private Map<String, String> waterStnos;

    private Map<String, String> rainStnos;

    private Map<String, String> floodStnos;

    @PostConstruct
    public void printMaps() {
        System.out.println("=== NTOU Water ===");
        System.out.println(waterStnos);
        System.out.println("=== NTOU Rain ===");
        System.out.println(rainStnos);
        System.out.println("=== NTOU Flood ===");
        System.out.println(floodStnos);
    }

}
