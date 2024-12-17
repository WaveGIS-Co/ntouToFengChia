package com.wavegis.kafka_consumer_service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.INewTaipeiSewerService;
import com.wavegis.kafka_consumer_service.model.dto.FloodListAllDTO;
import com.wavegis.kafka_consumer_service.model.dto.FloodValueAllDTO;
import com.wavegis.kafka_consumer_service.model.dto.FloodValueDTO;
import com.wavegis.kafka_consumer_service.model.dto.FloodValueNotifyDTO;
import com.wavegis.kafka_consumer_service.model.dto.RainDataDTO;
import com.wavegis.kafka_consumer_service.util.Util;

import okhttp3.ResponseBody;
import retrofit2.Call;

@Service
public class NewTaipeiSewerService {

    private static final Logger logger = LoggerFactory.getLogger(NewTaipeiService.class);
    
    @Autowired
    private INewTaipeiSewerService iNewTaipeiSewerService;

    public int postData(List<FloodValueAllDTO> dtos) {
        dtos.stream().forEach(item ->{
            item.setTrust(true);
            item.setKafka(false);
            item.setOlddata(false);
            item.setVersion(item.getVersion() + "-187.java.kafka");
        });
//        System.out.println(dtos.toString());
        return this.postFloodValue_All_Re(dtos);
    }
    
    public int postRainData(List<RainDataDTO> dtos) {
//        System.out.println(dtos.toString());
//        return 200;
      return this.postRainDataApi(dtos);
  }
    
    private int postFloodValue_notify(List<FloodValueNotifyDTO> dtos) {
        Call<ResponseBody> call = iNewTaipeiSewerService.postFloodValueNotify(dtos);
        int responseCode = Util.callApiResponseCode(call, "postFloodValue_notify");
        return responseCode;
    }
    
    private int postFloodValue_All_Re(List<FloodValueAllDTO> dtos) {
        Call<ResponseBody> call = iNewTaipeiSewerService.postFloodValueAllRe(dtos);
        int responseCode = Util.callApiResponseCode(call, "postFloodValue_All_Re");
        return responseCode;
    }
    
    private int postFloodValue(List<FloodValueDTO> dtos) {
        Call<ResponseBody> call = iNewTaipeiSewerService.postFloodValue(dtos);
        int responseCode = Util.callApiResponseCode(call, "postFloodValue");
        return responseCode;
    }
    
    private int postRainDataApi(List<RainDataDTO> dtos) {
        Call<ResponseBody> call = iNewTaipeiSewerService.postRainData(dtos);
        int responseCode = Util.callApiResponseCode(call, "postRainData");
        return responseCode;
    }
    
    private List<FloodListAllDTO> getFloodListAll(String st_no, String org_id) {
        Call<List<FloodListAllDTO>> call = iNewTaipeiSewerService.getFloodListAll(st_no, org_id, null, null, null, null, null, null);
        return Util.callListApi(call, "getFloodListAll");
    }
}
