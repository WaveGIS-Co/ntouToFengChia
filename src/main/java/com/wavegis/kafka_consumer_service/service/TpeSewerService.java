package com.wavegis.kafka_consumer_service.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.api.IFlood8210Service;
import com.wavegis.kafka_consumer_service.api.ITpeSewerPublisherService;
import com.wavegis.kafka_consumer_service.model.dto.FloodListAll8210DTO;
import com.wavegis.kafka_consumer_service.model.vo.TpeSewerPublisherPostVO;
import com.wavegis.kafka_consumer_service.util.Util;

@Service
public class TpeSewerService {
    
    private static final Logger logger = LoggerFactory.getLogger(TpeSewerService.class);

    @Autowired
    private IFlood8210Service iFlood8210Service;

    @Autowired
    private ITpeSewerPublisherService iTpeSewerPublisherService;

    public volatile Set<String> tpeSewerStnos = new HashSet<String>();

    public int postData(String st_no, List<TpeSewerPublisherPostVO> voList) {
        return Util.callApiResponseCode(iTpeSewerPublisherService.postData(st_no ,voList), "iTpeSewerPublisherService.postData");
    }

    public void initTpesewerStnoSet(Boolean init) {
        
        Runnable initRun = ()->{
            List<FloodListAll8210DTO> dtos = Util.callListApi(iFlood8210Service.getFloodListAll("WG_R_W", "68"), "iFlood8210Service.getFloodListAll");
            if (dtos == null) {
                logger.warn("iFlood8210Service.getFloodListAll(\"WG_R_W\", \"68\") get null response");
                return;
            }
            tpeSewerStnos = dtos.stream()
                .map(FloodListAll8210DTO::getSt_no)
                .collect(Collectors.toSet());
        };

        if(!init) {
            logger.info("re:tpeSewerStnos start");
            initRun.run();
            logger.info("re:tpeSewerStnos finish");
        } else {
            initRun.run();
        }
        
        logger.info("init:tpeSewerStnos.size = {}", tpeSewerStnos.size());
    }

    public Boolean isTpeswerHasStnos(String st_no) {
        return tpeSewerStnos.contains(st_no);
    }
}
