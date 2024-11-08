package com.wavegis.kafka_consumer_service.api;

import java.util.List;

import com.wavegis.kafka_consumer_service.model.vo.ChsewerPostVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IChanghuaSewerageDepartmentApiService {

    @POST(value = "api/changhua_sewerage_department_api/"+"data/postData")
    Call<Void> postData(@Body List<ChsewerPostVO> voList);
}
