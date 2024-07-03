package com.wavegis.kafka_consumer_service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.IIowPublisherService;
import com.wavegis.kafka_consumer_service.api.IPostgresApiService;
import com.wavegis.kafka_consumer_service.model.dto.IowSensorListDTO;
import com.wavegis.kafka_consumer_service.model.vo.IowPublisherPostVO;
import com.wavegis.kafka_consumer_service.model.vo.IowSensorListVO;
import com.wavegis.kafka_consumer_service.util.Util;

import retrofit2.Call;

@Service
public class IowPublisherApiService {
    
    private static final Logger logger = LoggerFactory.getLogger(IowPublisherApiService.class);

    @Autowired
    private IPostgresApiService iowPublisherApiService;
    
    @Autowired
    private IIowPublisherService iIowPublisherService;
    
    public static Map<String,List<IowSensorListDTO>> iowSensorDtoMap = new HashMap<String,List<IowSensorListDTO>>();
    
    private List<IowSensorListDTO> getDataIowSensorList(String org_id, String st_no, String device_type, String sensor_type, String iow_uuid) {
        Call<List<IowSensorListVO>> call = iowPublisherApiService.getDataIowSensorList(org_id, st_no, device_type, sensor_type, iow_uuid);
        List<IowSensorListDTO> dtoList = Util.callListApi(call, "getDataIowSensorList").stream()
                .map(vo -> vo.convertDto())
                .collect(Collectors.toList());
        return dtoList ;
    }
    
    private int postDataIowSensorList(String st_no, List<IowPublisherPostVO> voList) {
        Call<Void> call = iIowPublisherService.postData(st_no, voList);
        int responseCode = Util.callApiResponseCode(call, "postData");
        return responseCode;
    }
    
    public List<IowSensorListDTO> getData() {
        return this.getDataIowSensorList(null, null, null, null, null);
    }
    
    public int postData(String st_no, List<IowPublisherPostVO> voList) {
        return this.postDataIowSensorList(st_no ,voList);
    }
    
    public void initIowSensorDtoMap(Boolean init) {
        
        Map<String,List<IowSensorListDTO>> iowSensorDtoMapTemp = new HashMap<String,List<IowSensorListDTO>>();
        List<IowSensorListDTO> iowSensorDtoList = this.getDataIowSensorList(null, null, null, null, null);
        List<IowSensorListDTO> listTemp ;
        String stNo;
        for(IowSensorListDTO iowSensorDto : iowSensorDtoList) {
            
            stNo = iowSensorDto.getStNo();
            if(init) {
                if(!iowSensorDtoMap.containsKey(stNo)) {
                    listTemp = new ArrayList<IowSensorListDTO>();
                    listTemp.add(iowSensorDto);
                    iowSensorDtoMap.put(stNo, listTemp);
                }else {
                    iowSensorDtoMap.get(stNo).add(iowSensorDto);
                }
            }else {
                if(!iowSensorDtoMapTemp.containsKey(stNo)) {
                    listTemp = new ArrayList<IowSensorListDTO>();
                    listTemp.add(iowSensorDto);
                    iowSensorDtoMapTemp.put(stNo, listTemp);
                }else {
                    iowSensorDtoMapTemp.get(stNo).add(iowSensorDto);
                }
            }
        }
        
        if(!init) {
            logger.info("re:iowSensorDtoMap start");
            iowSensorDtoMap.putAll(iowSensorDtoMapTemp);
            iowSensorDtoMapTemp.clear();
            logger.info("re:iowSensorDtoMap finish");
        }
        
        logger.info("init:iowSensorDtoMap.size = {}",iowSensorDtoMap.size());
    }
}
