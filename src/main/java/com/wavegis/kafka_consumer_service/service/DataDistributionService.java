package com.wavegis.kafka_consumer_service.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.eclipse.jetty.server.RequestLog.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;
import com.wavegis.kafka_consumer_service.model.dto.IowSensorListDTO;
import com.wavegis.kafka_consumer_service.model.dto.NtouSensorListDTO;
import com.wavegis.kafka_consumer_service.model.vo.IowPublisherPostVO;
import com.wavegis.kafka_consumer_service.model.vo.NtouPublisherPostVO;
import com.wavegis.kafka_consumer_service.model.vo.TpeSewerPublisherPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

@Service
public class DataDistributionService {
    
    private static final Logger logger = LoggerFactory.getLogger(DataDistributionService.class);
    
    @Autowired
    private IowPublisherApiService iowPublisherApiService;
    
    @Autowired
    private NtouPublisherApiService ntouPublisherApiService;
    
    @Autowired
    private TpeSewerService tpeSewerService;

    private Map<String,List<IowSensorListDTO>>iowSensorDtoMap = IowPublisherApiService.iowSensorDtoMap;
    
    private Map<String,List<NtouSensorListDTO>> ntouSensorDtoMap = NtouPublisherApiService.ntouSensorDtoMap;
    
    private Function<String, Boolean> isTpeswerHasStnos = st_no->tpeSewerService.isTpeswerHasStnos(st_no);
    
    private Function<String, KafkaDTO> prepareDto;
    
    @PostConstruct
    private void init() {
        
        // 將資料處理和 API 呼叫抽成方法，減少重複碼
        prepareDto = (kafkaMessage) -> {
            KafkaDTO dto = new KafkaDTO();
            String[] strs = kafkaMessage.split(",");
            dto.setStrs(strs);
            return dto;
        };
    }
    
    public void distribution(String topices, String st_no, String kafka_message) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        CompletableFuture<Void> futureIow = CompletableFuture.runAsync(() -> {
            if(iowSensorDtoMap.containsKey(st_no)) {
                KafkaDTO dto = prepareDto.apply(kafka_message);
                List<IowPublisherPostVO> iowPublisherPostVoList = new ArrayList<IowPublisherPostVO>();
                iowPublisherPostVoList.add(Util.toVo(dto, new IowPublisherPostVO()));
                int resCode = iowPublisherApiService.postData(st_no, iowPublisherPostVoList);
                logger.info("Iow---topics={}, resCode={}, st_no={}, water_inner={}, datatime={}",topices, resCode, st_no, dto.getWater_inner(), dto.getDatatime());
            }
        },executor);
        
        CompletableFuture<Void> futureNtou = CompletableFuture.runAsync(() -> {
            if(ntouSensorDtoMap.containsKey(st_no.toLowerCase())) {
                KafkaDTO dto = prepareDto.apply(kafka_message);
                List<NtouPublisherPostVO> ntouPublisherPostVoList = new ArrayList<NtouPublisherPostVO>();
                ntouPublisherPostVoList.add(Util.toVo(dto, new NtouPublisherPostVO()));
                int resCode = ntouPublisherApiService.postData(st_no, ntouPublisherPostVoList);
                logger.info("Ntou---topics={}, resCode={}, st_no={}, water_inner={}, datatime={}",topices, resCode, st_no, dto.getWater_inner(), dto.getDatatime());
            }
        },executor);
        
        CompletableFuture<Void> futureTpesewer = CompletableFuture.runAsync(() -> {
            if(isTpeswerHasStnos.apply(st_no)) {
                KafkaDTO dto = prepareDto.apply(kafka_message);
                int resCode = tpeSewerService.postData(st_no, Collections.singletonList(new TpeSewerPublisherPostVO().fromKafkaDTO(dto)));
                logger.info("Tpesewer---topics={}, resCode={}, st_no={}, water_inner={}, datatime={}",topices, resCode, st_no, dto.getWater_inner(), dto.getDatatime());
            }
        },executor);
        

        CompletableFuture.allOf(futureIow, futureNtou, futureTpesewer)
        .thenRun(() -> logger.info("All conditions completed."))
        .join();

        executor.shutdown();
    }
}
