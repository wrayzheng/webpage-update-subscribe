package com.codebelief.app.scheduler;

import com.codebelief.app.scraper.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/*
 * @author: Surflyan<yanjilaing0128@outlook.com>
 * Date: 2017-10-18
 */

public class CrawlJob implements Job {
	//定义 Job 接口方法
	// JobExecutionContext 类提供了调度上下文的各种信息
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//System.out.println(context.getTrigger().getCalendarName() + "triggered.time is :" + (new Date()));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        System.out.println(sdf.format(new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
