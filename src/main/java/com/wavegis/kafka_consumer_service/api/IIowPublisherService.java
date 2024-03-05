package com.wavegis.kafka_consumer_service.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.wavegis.kafka_consumer_service.model.vo.IowPublisherPostVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IIowPublisherService {

    @POST(value = "data/postData")
    Call<Void> postData(
            @Query(value = "st_no") @RequestParam(required = true) String st_no,
            @Body List<IowPublisherPostVO> voList);
}
