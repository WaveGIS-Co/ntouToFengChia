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
import com.wavegis.kafka_consumer_service.model.vo.IowPublisherPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

@Service
public class DataDistributionService {
    
    private static final Logger logger = LoggerFactory.getLogger(DataDistributionService.class);
    
    @Autowired
    private IowPublisherApiService iowPublisherApiService;

    private Map<String,List<IowSensorListDTO>>iowSensorDtoMap = IowPublisherApiService.iowSensorDtoMap;
    
    public void distribution(String st_no, String kafkaMessage) {
        
        if(iowSensorDtoMap.containsKey(st_no)) {
            KafkaDTO dto = new KafkaDTO();
            String[] strs = kafkaMessage.split(",");
            dto.setStrs(strs);
            
            List<IowPublisherPostVO> iowPublisherPostVoList = new ArrayList<IowPublisherPostVO>();
            iowPublisherPostVoList.add(Util.toVo(dto, new IowPublisherPostVO()));
            int resCode = iowPublisherApiService.postData(st_no, iowPublisherPostVoList);
            logger.info("resCode={},st_no={},datatime={}",resCode,st_no,dto.getDatatime());
        }
    }
}
