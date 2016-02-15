package com.jxf.car.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserBillTask {

	private static Logger logger = Logger.getLogger(UserBillTask.class);

	/**
	 * 分钟定时任务
	 */
	@Scheduled(cron = "0 0/1 * * * *?")
	public void dayJob() {
		logger.info("------------开始执行每日任务");

		logger.info("-----------结束执行每日任务");
	}

}
