package com.codebelief.app;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;

/**
 * @author: Wray Zheng
 * @date: 2017-11-10
 * @description: TODO
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Webpage Update Subscribe started");
		MySQLDatabaseConnection.initialDatabaseDeploy();
		System.out.println("Initialization complete");
	}

}
