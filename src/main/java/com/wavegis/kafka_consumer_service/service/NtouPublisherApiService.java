package com.wavegis.kafka_consumer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.INtouPublisherService;
import com.wavegis.kafka_consumer_service.api.IPostgresApiService;

@Service
public class NtouPublisherApiService {
    
    @Autowired
    private IPostgresApiService ntouPublisherApiService;
    
    @Autowired
    private INtouPublisherService iNtouPublisherService;

}
