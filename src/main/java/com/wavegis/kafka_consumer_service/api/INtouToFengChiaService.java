package com.wavegis.kafka_consumer_service.api;

import com.wavegis.kafka_consumer_service.model.vo.StationStatusPostVO;
import com.wavegis.kafka_consumer_service.response.MessageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface INtouToFengChiaService {

    @POST(value = "stations/update")
    Call<MessageResponse> postData(
            @Body StationStatusPostVO stationStatusPostVO);
}
