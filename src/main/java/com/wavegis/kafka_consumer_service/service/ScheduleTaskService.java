package com.wavegis.kafka_consumer_service.service;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTaskService {
	
	private TaskScheduler scheduler;
	
	public static Thread dataOutputThread;
	
	public ScheduleTaskService(TaskScheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	public void addTaskToScheduler(Runnable task, String cron) {
		scheduler.schedule(task, new CronTrigger(cron));
	}

}
