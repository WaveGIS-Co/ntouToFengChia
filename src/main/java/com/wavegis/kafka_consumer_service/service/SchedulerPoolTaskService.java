package com.wavegis.kafka_consumer_service.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.wavegis.kafka_consumer_service.service.SchedulerPoolTaskService;

@Service
public class SchedulerPoolTaskService {
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerPoolTaskService.class);
	
	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;
	
	private final ConcurrentHashMap<String, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();
	 
	public void addTaskToScheduler(Runnable task, String cron, String key) {
		if(scheduledFutures.get(key) == null) {
			threadPoolTaskScheduler.setBeanName(key);
			threadPoolTaskScheduler.setThreadNamePrefix(key);
			threadPoolTaskScheduler.initialize();
			ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task,  new CronTrigger(cron));
			scheduledFutures.put(key, future);
			logger.info(key+"，任務開始執行");
		}else {
//			logger.info(key+"，正在執行中，不必為此任務再次建立執行續");
		}
		
	}

	public void shutDownTaskToScheduler(String key) {
	    final ScheduledFuture<?> toBeRemovedFuture = scheduledFutures.get(key);
		if (toBeRemovedFuture != null) {
	        toBeRemovedFuture.cancel(false);
	        scheduledFutures.remove(key);
	        logger.info(key+"，已經取消");
		}
	}

}
