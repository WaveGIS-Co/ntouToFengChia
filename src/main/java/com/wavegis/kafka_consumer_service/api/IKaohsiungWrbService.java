package com.wavegis.kafka_consumer_service.api;

import java.util.List;

import com.wavegis.kafka_consumer_service.model.vo.KaohsiungWrbPublisherPostVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IKaohsiungWrbService {
    @POST(value = "upload/sewerwater/kafka")
    Call<Void> postData(
            @Body List<KaohsiungWrbPublisherPostVO> voList);
}
