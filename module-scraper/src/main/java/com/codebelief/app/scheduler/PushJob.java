package com.codebelief.app.scheduler;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.codebelief.app.pushupdate.PushUpdateMessageTime;
import com.codebelief.app.scraper.Controller;

/**
 * @author: Wray Zheng
 * @date: 2017-12-10
 * @description: TODO
 */
public class PushJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        System.out.println(sdf.format(new Date()) + " -- start integrated push.");
	        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
	        Time time = new Time(format.parse("20:00:00").getTime());
	        PushUpdateMessageTime.PushUpdateTime(time);
	        System.out.println(sdf.format(new Date()) + " -- finish integrated push.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
