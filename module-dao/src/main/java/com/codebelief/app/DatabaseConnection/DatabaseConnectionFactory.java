package com.codebelief.app.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 
 * @ClassName: DatabaseConnectionFactory
 * @Description: Return an instantiated MySQLDatabaseConnection Object
 * @author 何涛
 * @date 2017年10月13日
 *
 */
public class DatabaseConnectionFactory {
	public static MySQLDatabaseConnection getMySQLDatabaseConnection() throws ClassNotFoundException, SQLException{
		return new MySQLDatabaseConnection();
	}
}
