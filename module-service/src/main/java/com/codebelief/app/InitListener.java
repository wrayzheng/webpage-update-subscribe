package com.codebelief.app;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;

import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.scheduler.MyCronTrigger;

/**
 * @author: Wray Zheng
 * @date: 2017-11-10
 * @description: Web 应用启动监听
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			MyCronTrigger.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Webpage Update Subscribe started");
		MySQLDatabaseConnection.initialDatabaseDeploy();
		try {
			MyCronTrigger.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Initialization complete");
	}

}
