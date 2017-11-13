package com.codebelief.app.DatabaseConnection;

import java.sql.Connection;
/**
 * @author 何涛
 * @version 1st   on 2017年10月13日
 */
public interface DatabaseConnection {
	
	
	/**
	 * 
	 * @Title: getConnection
	 * @Description: return the Connection to the database
	 * @return Connection
	 */
	public Connection getConnection();
	
	/**
	 * 
	 * @Title: free
	 * @Description: Close the connection to the database
	 */
	public void free();
}
