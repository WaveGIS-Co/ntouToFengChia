package com.wavegis.kafka_consumer_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.IChanghuaSewerageDepartmentApiService;
import com.wavegis.kafka_consumer_service.model.vo.ChsewerPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

import retrofit2.Call;

@Service
public class ChanghuaService {

    @Autowired
    private IChanghuaSewerageDepartmentApiService iChanghuaSewerageDepartmentApiService;
    
    public int postData(List<ChsewerPostVO> voList) {
        return this.postDataChanghuaSensorList(voList);
    }
    
    private int postDataChanghuaSensorList(List<ChsewerPostVO> voList) {
        Call<Void> call = iChanghuaSewerageDepartmentApiService.postData(voList);
        int responseCode = Util.callApiResponseCode(call, "postData");
        return responseCode;
    }
}
