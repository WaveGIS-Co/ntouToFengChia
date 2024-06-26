package com.wavegis.kafka_consumer_service.api;

import java.util.List;

import com.wavegis.kafka_consumer_service.model.dto.FloodListAll8210DTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IFlood8210Service {

    @GET("flood/getFloodListAll")
    Call<List<FloodListAll8210DTO>> getFloodListAll(@Query("type") String type, @Query("org_id") String org_id);

}
