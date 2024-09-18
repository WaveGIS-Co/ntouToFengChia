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
import com.wavegis.kafka_consumer_service.model.dto.NtouDevicesDTO;
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
    
    public static Map<String,List<NtouDevicesDTO>> ntouDevicesDtoMap = new HashMap<String,List<NtouDevicesDTO>>();
    
    public List<NtouDevicesDTO> getData() {
        return this.getDataNtouDevices(null, null, null);
    }
    
    public int postData(String st_no, List<NtouPublisherPostVO> voList) {
        return this.postDataNtouSensorList(st_no ,voList);
    }
    
    public void initNtouSensorDtoMap(Boolean init) {
        
        Map<String,List<NtouDevicesDTO>> ntouDevicesDtoMapTemp = new HashMap<String,List<NtouDevicesDTO>>();
        List<NtouDevicesDTO> ntouDevicesDtoList = this.getDataNtouDevices(null, null, null);
        List<NtouDevicesDTO> listTemp ;
        String stNo;
        for(NtouDevicesDTO ntouDevicesDto : ntouDevicesDtoList) {
            
            stNo = ntouDevicesDto.getStNo();
            if(init) {
                if(!ntouDevicesDtoMap.containsKey(stNo)) {
                    listTemp = new ArrayList<NtouDevicesDTO>();
                    listTemp.add(ntouDevicesDto);
                    ntouDevicesDtoMap.put(stNo, listTemp);
                }else {
                    ntouDevicesDtoMap.get(stNo).add(ntouDevicesDto);
                }
            }else {
                if(!ntouDevicesDtoMapTemp.containsKey(stNo)) {
                    listTemp = new ArrayList<NtouDevicesDTO>();
                    listTemp.add(ntouDevicesDto);
                    ntouDevicesDtoMapTemp.put(stNo, listTemp);
                }else {
                    ntouDevicesDtoMapTemp.get(stNo).add(ntouDevicesDto);
                }
            }
        }
        
        if(!init) {
            logger.info("re:ntouDevicesDtoMap start");
            ntouDevicesDtoMap.putAll(ntouDevicesDtoMapTemp);
            ntouDevicesDtoMapTemp.clear();
            logger.info("re:ntouDevicesDtoMap finish");
        }
        
        logger.info("init:ntouDevicesDtoMap.size = {}",ntouDevicesDtoMap.size());
    }
    
    public void initNtouDevicesDtoMap(Boolean init) {
        
        Map<String,List<NtouDevicesDTO>> ntouDevicesDtoMapTemp = new HashMap<String,List<NtouDevicesDTO>>();
        List<NtouDevicesDTO> ntouDevicesDtoList = this.getDataNtouDevices(null, null, null);
        String stNo;
        for(NtouDevicesDTO ntouDevicesDto : ntouDevicesDtoList) {
            
            stNo = ntouDevicesDto.getStNo();
            if(init) {
                if(!ntouDevicesDtoMap.containsKey(stNo)) {
                    ntouDevicesDtoMap.put(stNo, null);
                }
            }else {
                if(!ntouDevicesDtoMapTemp.containsKey(stNo)) {
                    ntouDevicesDtoMapTemp.put(stNo, null);
                }
            }
        }
        
        if(!init) {
            logger.info("re:ntouDevicesDtoMap start");
            ntouDevicesDtoMap.putAll(ntouDevicesDtoMapTemp);
            ntouDevicesDtoMapTemp.clear();
            logger.info("re:ntouDevicesDtoMap finish");
        }
        
        logger.info("init:ntouDevicesDtoMap.size = {}",ntouDevicesDtoMap.size());
    }
    
    private List<NtouSensorListDTO> getDataNtouSensorList(String org_id, String st_no, String device_type, String stt_no, String dev_id) {
        Call<List<NtouSensorListVO>> call = ntouPublisherApiService.getDataNtouSensorList(org_id, st_no, device_type, stt_no, dev_id);
        List<NtouSensorListDTO> dtoList = Util.callListApi(call, "getDataNtouSensorList").stream()
                .map(vo -> vo.convertDto())
                .collect(Collectors.toList());
        return dtoList ;
    }
    
    private List<NtouDevicesDTO> getDataNtouDevices(String org_id, String dev_id, String dev_purpose){
        Call<List<NtouDevicesDTO>> call = ntouPublisherApiService.getDataNtouDevices(org_id, dev_id, dev_purpose);
        List<NtouDevicesDTO> dtoList = Util.callListApi(call, "getDataNtouDevices").stream()
                .map(vo -> Util.toVo(vo, new NtouDevicesDTO()))
                .collect(Collectors.toList());
        return dtoList ;
    }
    
    private int postDataNtouSensorList(String st_no, List<NtouPublisherPostVO> voList) {
        Call<Void> call = iNtouPublisherService.postData(st_no, voList);
        int responseCode = Util.callApiResponseCode(call, "postData");
        return responseCode;
    }

}
