package com.jxf.car.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jxf.car.service.customer.UserMonthBillService;
import com.jxf.common.tools.MyDateUtil;

@Component
public class UserBillTask {

	private static Logger logger = Logger.getLogger(UserBillTask.class);

	@Autowired
	private UserMonthBillService billService;

	/**
	 * 每天定时任务
	 */
	// @Scheduled(cron = "0 01 00 ? * *")
	@Scheduled(cron = "0 0/1 * * * *?")
	public void dayJob() {
		logger.info("------------开始执行每日任务");
		
		billService.createUserMonthBillByDay(MyDateUtil.getYestoday());
		logger.info("-----------结束执行每日任务");
	}

}
