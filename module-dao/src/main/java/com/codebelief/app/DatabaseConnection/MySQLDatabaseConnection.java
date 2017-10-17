package com.codebelief.app.DatabaseConnection;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @ClassName: MySQLDatabaseConnection
 * @Description: Connecting with Database
 * @author 何涛
 * @date 2017年10月13日
 *
 */
public class MySQLDatabaseConnection implements DatabaseConnection{
	
	private String DriverClass = null;//"com.mysql.jdbc.Driver";
	private String Url = null;//"jdbc:mysql://localhost:3306/bookdb";
	private String UserName = null;//"root";
	private String Password = null;//"www.12358.com";
	private Connection conn = null;
	
	public MySQLDatabaseConnection() throws IOException{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("F://java/workspace/SeeUTomorrow/src/dbProperties.properties");
		prop.load(file);
		
		DriverClass = prop.getProperty("DriverClass");
		Url = prop.getProperty("Url");
		UserName = prop.getProperty("UserName");
		Password = prop.getProperty("Password");
		
		try {
			Class.forName(DriverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(Url,UserName,Password);
		} catch (SQLException e) {
			System.out.print("Connecting Action failed!");
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection() {
		return conn;
	}

	@Override
	public void free() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.print("Close failed!");
			e.printStackTrace();
		}
	}
	
}
