package com.codebelief.app.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/*
 * @author: Surflyan<yanjilaing0128@outlook.com>
 * Date: 2017-10-18
 */

public class MyCronTrigger {
	
	public static void main(String[] args) throws Exception {
		
		//Scheduler: 代表一个quartz的独立容器，Trigger 和 JobDetail可以在注册到Scheduler中
		//通过SchedulerFactory 可以创建一个Scheduler实例
	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    
	    
	    //创建Job实例
		JobDetail job = JobBuilder
				.newJob(CrawlJob.class)
				.withIdentity("crawJob", "group1").build();
	
	    //定义Job执行的触发规则
		//这里采用CronTrigger子类，通过Cron定义出复杂的时间规则
	    CronTrigger trigger = TriggerBuilder
			    .newTrigger()
			    .withIdentity("cronTrigger","group")
			    .withSchedule(
					    CronScheduleBuilder.cronSchedule("0/50 * * * * ?"))
			    .build();
        sched.start();
        
        //将Trigger绑定到一个JobDetail中，Trigger触发，JobDetail执行
	    sched.scheduleJob(job,trigger);
	}
}
