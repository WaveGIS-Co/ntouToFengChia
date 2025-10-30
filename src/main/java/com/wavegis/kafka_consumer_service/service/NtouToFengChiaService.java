package com.wavegis.kafka_consumer_service.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.INtouToFengChiaService;
import com.wavegis.kafka_consumer_service.model.vo.StationStatusPostVO;
import com.wavegis.kafka_consumer_service.response.MessageResponse;

import retrofit2.Call;
import retrofit2.Response;

@Service
public class NtouToFengChiaService {

    @Autowired
    private INtouToFengChiaService iNtouToFengChiaService;

    public String postData(StationStatusPostVO vo) {
        return this.postDataNtouSensor(vo);
    }

    private String postDataNtouSensor(StationStatusPostVO vo) {

        Call<MessageResponse> call = iNtouToFengChiaService.postData(vo);
        Response<MessageResponse> res = null;

        try {
            // 執行同步呼叫
            res = call.execute();

            if (res.isSuccessful() && res.body() != null) {

                return res.body().getMessage();
            } else if (res.errorBody() != null) {
                return "Error: " + res.code();
            } else {
                return "Error: " + res.message();
            }

        } catch (IOException e) {
            // 處理連線例外（網路問題、timeout、伺服器斷線）
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
    }

}
