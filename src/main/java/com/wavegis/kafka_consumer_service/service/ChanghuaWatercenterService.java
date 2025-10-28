package com.wavegis.kafka_consumer_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.IChanghuaUploaderService;
import com.wavegis.kafka_consumer_service.model.vo.ChuploadPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

import retrofit2.Call;
@Service
public class ChanghuaWatercenterService {
    @Autowired
    private IChanghuaUploaderService iChanghuaUploaderService;

    public int uploadData(List<ChuploadPostVO> voList) {
        return this.uploadDataChanghuaSensorList(voList);
    }

    private int uploadDataChanghuaSensorList(List<ChuploadPostVO> voList) {
        Call<Void> call = iChanghuaUploaderService.uploadData(voList);
        int responseCode = Util.callApiResponseCode(call, "uploadData");
        return responseCode;
    }

}
