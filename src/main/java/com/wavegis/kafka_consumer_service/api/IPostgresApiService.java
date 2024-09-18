package com.wavegis.kafka_consumer_service.api;

import java.util.List;

import com.wavegis.kafka_consumer_service.model.vo.IowSensorListVO;
import com.wavegis.kafka_consumer_service.model.vo.NtouSensorListVO;
import com.wavegis.kafka_consumer_service.model.dto.NtouDevicesDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPostgresApiService {
    
    @GET(value = "iowPublisher/data/sensorList")
    Call<List<IowSensorListVO>> getDataIowSensorList(
        @Query ("org_id") String org_id,
        @Query ("st_no") String st_no,
        @Query ("device_type") String device_type,
        @Query ("sensor_type") String sensor_type,
        @Query ("iow_uuid") String iow_uuid);
    
    @GET(value = "ntouPublisher/data/ntouSensorList")
    Call<List<NtouSensorListVO>> getDataNtouSensorList(
        @Query ("org_id") String org_id,
        @Query ("st_no") String st_no,
        @Query ("device_type") String device_type,
        @Query ("stt_no") String stt_no,
        @Query ("dev_id") String dev_id);
    
    @GET(value = "ntouPublisher/data/ntouDevices")
    Call<List<NtouDevicesDTO>> getDataNtouDevices(
        @Query ("org_id") String orgId,
        @Query ("dev_id") String devId,
        @Query ("dev_purpose") String devPurpose);
}
