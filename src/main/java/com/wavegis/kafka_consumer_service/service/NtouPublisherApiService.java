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

import com.wavegis.kafka_consumer_service.api.INtouPublisherService;
import com.wavegis.kafka_consumer_service.api.IPostgresApiService;
import com.wavegis.kafka_consumer_service.model.dto.NtouSensorListDTO;
import com.wavegis.kafka_consumer_service.model.vo.NtouPublisherPostVO;
import com.wavegis.kafka_consumer_service.model.vo.NtouSensorListVO;
import com.wavegis.kafka_consumer_service.util.Util;

import retrofit2.Call;

@Service
public class NtouPublisherApiService {
    
    private static final Logger logger = LoggerFactory.getLogger(NtouPublisherApiService.class);
    
    @Autowired
    private IPostgresApiService ntouPublisherApiService;
    
    @Autowired
    private INtouPublisherService iNtouPublisherService;
    
    public static Map<String,List<NtouSensorListDTO>> ntouSensorDtoMap = new HashMap<String,List<NtouSensorListDTO>>();
    
    private List<NtouSensorListDTO> getDataNtouSensorList(String org_id, String st_no, String device_type, String stt_no, String dev_id) {
        Call<List<NtouSensorListVO>> call = ntouPublisherApiService.getDataNtouSensorList(org_id, st_no, device_type, stt_no, dev_id);
        List<NtouSensorListDTO> dtoList = Util.callListApi(call, "getDataNtouSensorList").stream()
                .map(vo -> vo.convertDto())
                .collect(Collectors.toList());
        return dtoList ;
    }
    
    private int postDataNtouSensorList(String st_no, List<NtouPublisherPostVO> voList) {
        Call<Void> call = iNtouPublisherService.postData(st_no, voList);
        int responseCode = Util.callApiResponseCode(call, "postData");
        return responseCode;
    }
    
    public List<NtouSensorListDTO> getData() {
        return this.getDataNtouSensorList(null, null, null, null, null);
    }
    
    public int postData(String st_no, List<NtouPublisherPostVO> voList) {
        return this.postDataNtouSensorList(st_no ,voList);
    }
    
    public void initNtouSensorDtoMap(Boolean init) {
        
        Map<String,List<NtouSensorListDTO>> ntouSensorDtoMapTemp = new HashMap<String,List<NtouSensorListDTO>>();
        List<NtouSensorListDTO> ntouSensorDtoList = this.getDataNtouSensorList(null, null, null, null, null);
        List<NtouSensorListDTO> listTemp ;
        String devId;
        for(NtouSensorListDTO ntouSensorDto : ntouSensorDtoList) {
            
            devId = ntouSensorDto.getSt_no().toLowerCase();
            if(init) {
                if(!ntouSensorDtoMap.containsKey(devId)) {
                    listTemp = new ArrayList<NtouSensorListDTO>();
                    listTemp.add(ntouSensorDto);
                    ntouSensorDtoMap.put(devId, listTemp);
                }else {
                    ntouSensorDtoMap.get(devId).add(ntouSensorDto);
                }
            }else {
                if(!ntouSensorDtoMapTemp.containsKey(devId)) {
                    listTemp = new ArrayList<NtouSensorListDTO>();
                    listTemp.add(ntouSensorDto);
                    ntouSensorDtoMapTemp.put(devId, listTemp);
                }else {
                    ntouSensorDtoMapTemp.get(devId).add(ntouSensorDto);
                }
            }
        }
        
        if(!init) {
            logger.info("re:ntouSensorDtoMap start");
            ntouSensorDtoMap.putAll(ntouSensorDtoMapTemp);
            ntouSensorDtoMapTemp.clear();
            logger.info("re:ntouSensorDtoMap finish");
        }
        
        logger.info("init:ntouSensorDtoMap.size = {}",ntouSensorDtoMap.size());
    }

}
