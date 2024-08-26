package com.wavegis.kafka_consumer_service.api;

import java.util.List;

import com.wavegis.kafka_consumer_service.model.vo.KaohsiungWrbPublisherFloodPostVO;
import com.wavegis.kafka_consumer_service.model.vo.KaohsiungWrbPublisherSewerPostVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IKaohsiungWrbService {
    @POST(value = "upload/sewerwater/kafka")
    Call<Void> postSewerData(
            @Body List<KaohsiungWrbPublisherSewerPostVO> voList);
    @POST(value = "upload/flood/kafka")
    Call<Void> postFloodData(
            @Body List<KaohsiungWrbPublisherFloodPostVO> voList);
}
