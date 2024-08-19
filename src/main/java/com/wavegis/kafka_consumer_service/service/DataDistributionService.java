package com.wavegis.kafka_consumer_service.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;
import com.wavegis.kafka_consumer_service.model.dto.IowSensorListDTO;
import com.wavegis.kafka_consumer_service.model.dto.NtouSensorListDTO;
import com.wavegis.kafka_consumer_service.model.enums.PublisherEnum;
import com.wavegis.kafka_consumer_service.model.vo.IowPublisherPostVO;
import com.wavegis.kafka_consumer_service.model.vo.KaohsiungWrbPublisherPostVO;
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
    
    @Autowired
    private KaohsiungWrbService kaohsiungWrbService;

    private Map<String,List<IowSensorListDTO>> iowSensorDtoMap = IowPublisherApiService.iowSensorDtoMap;
    
    private Map<String,List<NtouSensorListDTO>> ntouSensorDtoMap = NtouPublisherApiService.ntouSensorDtoMap;
    
    private Function<String, KafkaDTO> prepareDto;
    
    private ExecutorService executor;
    
    @PostConstruct
    private void init() {
        
        // 初始化連接池數量
        executor = Executors.newFixedThreadPool(4);
        
        // 將資料處理和 API 呼叫抽成方法，減少重複碼
        prepareDto = (kafkaMessage) -> {
            KafkaDTO dto = new KafkaDTO();
            String[] strs = kafkaMessage.split(",");
            dto.setStrs(strs);
            return dto;
        };
    }
    
    public void distribution(String topices, PublisherEnum publisherEnum, String st_no, String kafka_message) {
        // if (true) {
        //     System.out.printf("%s,%s,%s: %s\n", topices, publisherEnum.name(), st_no, kafka_message);
        //     return;
        // }
        switch (publisherEnum) {
            case iow: {
                if(iowSensorDtoMap.containsKey(st_no)) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = iowPublisherApiService.postData(st_no, Collections.singletonList(Util.toVo(dto, new IowPublisherPostVO())));
                    logger.info("Iow---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                            topices, resCode, st_no, dto.getWaterInner(), dto.getDatatime());
                }
                break;
            }
            case ntou: {
                if(ntouSensorDtoMap.containsKey(st_no.toLowerCase())) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = ntouPublisherApiService.postData(st_no, Collections.singletonList(Util.toVo(dto, new NtouPublisherPostVO())));
                    logger.info("Ntou---topics={}, resCode={}, st_no={}, datatime={}, water_inner_bed={}, rain={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getWaterInnerBed(), dto.getRain());
                }
                break;
            }
            case tpeSewer: {
                if(tpeSewerService.hasStnosByKafkaString(kafka_message)) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = tpeSewerService.postData(st_no, Collections.singletonList(new TpeSewerPublisherPostVO().fromKafkaDTO(dto)));
                    logger.info("Tpesewer---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getWaterInner());
                }
                break;
            }
            case kaohsiungWrb: {
                if(kaohsiungWrbService.hasStnosByKafkaString(kafka_message)) {
                    KafkaDTO dto = prepareDto.apply(kafka_message);
                    int resCode = kaohsiungWrbService.postData(Collections.singletonList(new KaohsiungWrbPublisherPostVO().fromKafkaDTO(dto)));
                    logger.info("KaohsiungWrb---topics={}, resCode={}, st_no={}, datatime={}, water_inner={}",
                            topices, resCode, st_no, dto.getDatatime(), dto.getWaterInner());
                }
                break;
            }
            default: {
                logger.warn("upload type={} not impl", publisherEnum.name());
                break;
            }
        }
    }

}
