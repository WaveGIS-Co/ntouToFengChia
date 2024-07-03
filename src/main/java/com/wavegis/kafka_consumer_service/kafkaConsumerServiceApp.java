package com.wavegis.kafka_consumer_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.wavegis.kafka_consumer_service.service.IowPublisherApiService;
import com.wavegis.kafka_consumer_service.service.NtouPublisherApiService;
import com.wavegis.kafka_consumer_service.service.SchedulerPoolTaskService;
import com.wavegis.kafka_consumer_service.service.TpeSewerService;

@Component
public class kafkaConsumerServiceApp implements ApplicationRunner{
    
    @Autowired
    private SchedulerPoolTaskService schedulerPoolTaskService;
    
    @Autowired
    private IowPublisherApiService iowPublisherApiService;
    
    @Autowired
    private NtouPublisherApiService ntouPublisherApiService;
    
    private void init() {
        //初始化Iow關聯表
        iowPublisherApiService.initIowSensorDtoMap(true);
        //初始化Ntou關聯表
        ntouPublisherApiService.initNtouSensorDtoMap(true);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        this.init();
        
        Runnable reGetSensorListData = () ->{
            iowPublisherApiService.initIowSensorDtoMap(false);
            ntouPublisherApiService.initNtouSensorDtoMap(false);
        };
        schedulerPoolTaskService.addTaskToScheduler(reGetSensorListData, "0 0 * * * *", "ReSensorList");
    }

}
