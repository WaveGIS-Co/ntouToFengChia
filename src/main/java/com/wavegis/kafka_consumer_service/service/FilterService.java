// package com.wavegis.kafka_consumer_service.service;

// import java.util.List;

// import javax.annotation.PostConstruct;

// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.stereotype.Service;

// import lombok.Getter;
// import lombok.Setter;

// @ConfigurationProperties(prefix = "filter")
// @Getter
// @Setter
// @Service
// public class FilterService {

//     private List<String> waterStnos;

//     private List<String> floodStnos;


//     public boolean isFloodStation(String stno) {
//         return floodStnos != null && floodStnos.contains(stno);
//     }

//     public boolean isWaterStation(String stno) {
//         return waterStnos != null && waterStnos.contains(stno);
//     }

//     @PostConstruct
//     public void printConfig() {
//         System.out.println("[DEBUG] Water stnos: " + waterStnos);
//         System.out.println("[DEBUG] Flood stnos: " + floodStnos);
//     }

// }
