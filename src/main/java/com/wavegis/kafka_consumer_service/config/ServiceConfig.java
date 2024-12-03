package com.wavegis.kafka_consumer_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "wavegis.iow-publisher-upload-service")
@Configuration
public class ServiceConfig {
	
	private String apiIowBaseUrl;
	
	private String api8210BaseUrl;
	
	private String api8899BaseUrl;
	
	private String apiJavaBaseUrl;
	
	private String api187PostgresApiUrl;
	
	private String api187IowUrl;
	
	private String api187NtouUrl;

	private String api187TpesewerUrl;

    private String apiKaohsiungWrbUrl;
    
    private String apiChanghuaVmUrl;
    
    private String apiNtpcBaseUrl;
	
	private String scheduleTime;

    public String getApiIowBaseUrl() {
        return apiIowBaseUrl;
    }

    public void setApiIowBaseUrl(String apiIowBaseUrl) {
        this.apiIowBaseUrl = apiIowBaseUrl;
    }

    public String getApi8210BaseUrl() {
        return api8210BaseUrl;
    }

    public void setApi8210BaseUrl(String api8210BaseUrl) {
        this.api8210BaseUrl = api8210BaseUrl;
    }

    public String getApi8899BaseUrl() {
        return api8899BaseUrl;
    }

    public void setApi8899BaseUrl(String api8899BaseUrl) {
        this.api8899BaseUrl = api8899BaseUrl;
    }

    public String getApiJavaBaseUrl() {
        return apiJavaBaseUrl;
    }

    public void setApiJavaBaseUrl(String apiJavaBaseUrl) {
        this.apiJavaBaseUrl = apiJavaBaseUrl;
    }

    public String getApi187IowUrl() {
        return api187IowUrl;
    }

    public void setApi187IowUrl(String api187IowUrl) {
        this.api187IowUrl = api187IowUrl;
    }

    public String getApi187NtouUrl() {
        return api187NtouUrl;
    }

    public void setApi187NtouUrl(String api187NtouUrl) {
        this.api187NtouUrl = api187NtouUrl;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getApi187PostgresApiUrl() {
        return api187PostgresApiUrl;
    }

    public void setApi187PostgresApiUrl(String api187PostgresApiUrl) {
        this.api187PostgresApiUrl = api187PostgresApiUrl;
    }

    public String getApi187TpesewerUrl() {
        return api187TpesewerUrl;
    }

    public void setApi187TpesewerUrl(String api187TpesewerUrl) {
        this.api187TpesewerUrl = api187TpesewerUrl;
    }

    public String getApiKaohsiungWrbUrl() {
        return apiKaohsiungWrbUrl;
    }

    public void setApiKaohsiungWrbUrl(String apiKaohsiungWrbUrl) {
        this.apiKaohsiungWrbUrl = apiKaohsiungWrbUrl;
    }

    public String getApiChanghuaVmUrl() {
        return apiChanghuaVmUrl;
    }

    public void setApiChanghuaVmUrl(String apiChanghuaVmUrl) {
        this.apiChanghuaVmUrl = apiChanghuaVmUrl;
    }

    public String getApiNtpcBaseUrl() {
        return apiNtpcBaseUrl;
    }

    public void setApiNtpcBaseUrl(String apiNtpcBaseUrl) {
        this.apiNtpcBaseUrl = apiNtpcBaseUrl;
    }
 
}
