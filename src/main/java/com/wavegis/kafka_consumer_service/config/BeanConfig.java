package com.wavegis.kafka_consumer_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.wavegis.kafka_consumer_service.api.IChanghuaSewerageDepartmentApiService;
import com.wavegis.kafka_consumer_service.api.IIowPublisherService;
import com.wavegis.kafka_consumer_service.api.IKaohsiungWrbService;
import com.wavegis.kafka_consumer_service.api.INewTaipeiService;
import com.wavegis.kafka_consumer_service.api.INewTaipeiSewerService;
import com.wavegis.kafka_consumer_service.api.INtouPublisherService;
import com.wavegis.kafka_consumer_service.api.IPostgresApiService;
import com.wavegis.kafka_consumer_service.api.ITpeSewerPublisherService;
import com.wavegis.kafka_consumer_service.util.RetrofitFactory;


@Configuration
public class BeanConfig {
    
    @Autowired
    private ServiceConfig serviceConfig;
    
    @Bean
    public IPostgresApiService iPostgresApiService() {
        return RetrofitFactory.createService(IPostgresApiService.class, serviceConfig.getApi187PostgresApiUrl()); 
    }
    
    @Bean
    public IIowPublisherService iIowPublisherService() {
        return RetrofitFactory.createService(IIowPublisherService.class, serviceConfig.getApi187IowUrl()); 
    }
    
    @Bean
    public INtouPublisherService iNtouPublisherService() {
        return RetrofitFactory.createService(INtouPublisherService.class, serviceConfig.getApi187NtouUrl()); 
    }
    
    @Bean
    public ITpeSewerPublisherService iTpeSewerPublisherService() {
        return RetrofitFactory.createService(ITpeSewerPublisherService.class, serviceConfig.getApi187TpesewerUrl()); 
    }

    @Bean
    public IKaohsiungWrbService iKaohsiungWrbService() {
        return RetrofitFactory.createService(IKaohsiungWrbService.class, serviceConfig.getApiKaohsiungWrbUrl()); 
    }
    
    @Bean
    public IChanghuaSewerageDepartmentApiService iChanghuaSewerageDepartmentApiService() {
        return RetrofitFactory.createService(IChanghuaSewerageDepartmentApiService.class, serviceConfig.getApiChanghuaVmUrl()); 
    }
    
    @Bean
    public INewTaipeiService iNewTaipeiService() {
        return RetrofitFactory.createService(INewTaipeiService.class, serviceConfig.getApiNtpcBaseUrl()); 
    }
    
    @Bean
    public INewTaipeiSewerService iNewTaipeiSewerService() {
        return RetrofitFactory.createService(INewTaipeiSewerService.class, serviceConfig.getApiNtpcSewerBaseUrl()); 
    }
	
	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
		threadPoolTaskScheduler.setPoolSize(5);
		threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
		return threadPoolTaskScheduler;	
	} 

}
