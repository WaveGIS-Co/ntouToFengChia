package com.wavegis.kafka_consumer_service.api;

import java.util.List;

import com.wavegis.kafka_consumer_service.model.vo.NtouPublisherPostVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface INtouPublisherService {
    
    @POST(value = "data/postData")
    Call<Void> postData(
            @Query("st_no") String st_no,
            @Body List<NtouPublisherPostVO> voList);
}
