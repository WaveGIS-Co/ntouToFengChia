package com.wavegis.kafka_consumer_service.service;

import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.INtouToFengChiaService;
import com.wavegis.kafka_consumer_service.model.vo.StationStatusPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

import retrofit2.Call;

@Service
public class NtouToFengChiaService {

private INtouToFengChiaService iNtouToFengChiaService;

public int uploadData(StationStatusPostVO vo){
    return this.uploadDataNtouSensor(vo);
}

private int uploadDataNtouSensor(StationStatusPostVO vo){
     Call<Void> call = iNtouToFengChiaService.postData(vo);
    int responseCode = Util.callApiResponseCode(call, "postData");
        return responseCode;
}
}
