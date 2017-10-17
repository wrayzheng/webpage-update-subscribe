/*
 * @(#)SeeUTomorrow --- DatabaseConnectionFactory.java 
 */
package com.codebelief.app.DatabaseConnection;

import java.io.IOException;

/**
 * 
 * @ClassName: DatabaseConnectionFactory
 * @Description: Return an instantiated MySQLDatabaseConnection Object
 * @author 何涛
 * @date 2017年10月13日
 *
 */
public class DatabaseConnectionFactory {
	public static MySQLDatabaseConnection getMySQLDatabaseConnection() throws IOException{
		return new MySQLDatabaseConnection();
	}
}
