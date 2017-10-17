/*
 * @(#)SeeUTomorrow --- DatabaseConnection.java 
 */
package com.codebelief.app.DatabaseConnection;

import java.sql.Connection;
/**
 * @author 何涛
 * @version 1st   on 2017年10月13日
 */
public interface DatabaseConnection {
	public Connection getConnection();
	
	public void free();
}
