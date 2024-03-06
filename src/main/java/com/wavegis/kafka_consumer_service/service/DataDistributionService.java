package com.wavegis.kafka_consumer_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.kafka.KafkaDTO;
import com.wavegis.kafka_consumer_service.model.dto.IowSensorListDTO;
import com.wavegis.kafka_consumer_service.model.dto.NtouSensorListDTO;
import com.wavegis.kafka_consumer_service.model.vo.IowPublisherPostVO;
import com.wavegis.kafka_consumer_service.model.vo.NtouPublisherPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

@Service
public class DataDistributionService {
    
    private static final Logger logger = LoggerFactory.getLogger(DataDistributionService.class);
    
    @Autowired
    private IowPublisherApiService iowPublisherApiService;
    
    @Autowired
    private NtouPublisherApiService ntouPublisherApiService;

    private Map<String,List<IowSensorListDTO>>iowSensorDtoMap = IowPublisherApiService.iowSensorDtoMap;
    
    private Map<String,List<NtouSensorListDTO>> ntouSensorDtoMap = NtouPublisherApiService.ntouSensorDtoMap;
    
    public void distribution(String st_no, String kafkaMessage) {
        
        List<IowPublisherPostVO> iowPublisherPostVoList;
        List<NtouPublisherPostVO> ntouPublisherPostVoList;
        KafkaDTO dto;
        String[] strs;
        
        if(iowSensorDtoMap.containsKey(st_no)) {
            dto = new KafkaDTO();
            strs = kafkaMessage.split(",");
            dto.setStrs(strs);
            
            iowPublisherPostVoList = new ArrayList<IowPublisherPostVO>();
            iowPublisherPostVoList.add(Util.toVo(dto, new IowPublisherPostVO()));
            int resCode = iowPublisherApiService.postData(st_no, iowPublisherPostVoList);
            logger.info("Iow---resCode={}, st_no={}, water_inner={}, datatime={}",resCode, st_no, dto.getWater_inner(), dto.getDatatime());
        }
        
        if(ntouSensorDtoMap.containsKey(st_no.toLowerCase())) {
            dto = new KafkaDTO();
            strs = kafkaMessage.split(",");
            dto.setStrs(strs);
            
            ntouPublisherPostVoList = new ArrayList<NtouPublisherPostVO>();
            ntouPublisherPostVoList.add(Util.toVo(dto, new NtouPublisherPostVO()));
//            int resCode = ntouPublisherApiService.postData(st_no, ntouPublisherPostVoList);
            int resCode = 999;
            logger.info("Ntou---resCode={}, st_no={}, water_inner={}, datatime={}",resCode, st_no, dto.getWater_inner(), dto.getDatatime());
        }
    }
}
