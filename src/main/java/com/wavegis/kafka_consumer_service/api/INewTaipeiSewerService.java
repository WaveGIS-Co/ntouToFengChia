package com.wavegis.kafka_consumer_service.api;

import java.util.List;

import com.wavegis.kafka_consumer_service.model.dto.FloodListAllDTO;
import com.wavegis.kafka_consumer_service.model.dto.FloodValueAllDTO;
import com.wavegis.kafka_consumer_service.model.dto.FloodValueDTO;
import com.wavegis.kafka_consumer_service.model.dto.FloodValueNotifyDTO;
import com.wavegis.kafka_consumer_service.model.dto.RainDataDTO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface INewTaipeiSewerService {

    @POST(value = "flood/postFloodValue_notify")
    Call<ResponseBody> postFloodValueNotify(@Body List<FloodValueNotifyDTO> dtos);
    
    @POST(value = "flood/postFloodValue_All")
    Call<ResponseBody> postFloodValueAll(@Body List<FloodValueAllDTO> dtos);
    
    //上傳water_inner還原成修正水位
    @POST(value = "flood/postFloodValue_All_Re")
    Call<ResponseBody> postFloodValueAllRe(@Body List<FloodValueAllDTO> dtos);
    
    @POST(value = "flood/postFloodValue")
    Call<ResponseBody> postFloodValue(@Body List<FloodValueDTO> dtos);
    
    @POST(value = "rain/postRainData")
    Call<ResponseBody> postRainData(@Body List<RainDataDTO> dtos);
    
    @GET(value = "flood/getFloodListAll")
    Call<List<FloodListAllDTO>> getFloodListAll(
            @Query ("st_no") String st_no,
            @Query ("org_id") String org_id,
            @Query ("supplier") String supplier,
            @Query ("type") String type,
            @Query ("source") String source,
            @Query ("unit") String unit,
            @Query ("display") String display,
            @Query ("group_type") String group_type
            );
}
