package com.codebelief.app.DatabaseConnection;

import java.util.*;
import java.io.*;
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
	
	private static String host = null;
	private static String port = null;
	private static String database = null;
	private static String userName = null;
	private static String password = null;
	private Connection conn = null;
	private String url = null;
	
	public MySQLDatabaseConnection() throws SQLException, ClassNotFoundException {
		url="jdbc:mysql://"+host+":"+port+"/"+database+"?characterEncoding=utf-8";
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url,userName,password);
	}
	
	@Override
	/**
	 * @Title: getConnection
	 * @Description: return the Connection to the database
	 * @return Connection
	 */
	public Connection getConnection() {
		return conn;
	}

	@Override
	/**
	 * @Title: free
	 * @Description: Close the connection to the database
	 */
	public void free() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.print("Close failed!");
			e.printStackTrace();
		}
	}

	/**
	 * @Title: initialDatabaseDeploy
	 * @Description: Initiate the database configuration.
	 * 				 If the needed configuration file, 
	 * 				 db.properties, is not exist under the path of ${user.home}/.wus/db.properties,
	 * 				 then build it under this path.
	 * 				 If the contents contained in needed configuration file are not complete,
	 * 				 then add the default settings into the configuration file.
	 * 				 Else use the file that developers has builded directly.
	 */
	public static void initialDatabaseDeploy() {
		String home = System.getProperty("user.home");
		String fileSeparator = System.getProperty("file.separator");
		String path = home + fileSeparator+".wus"+fileSeparator+"db.properties";
		File file = new File(path);
		if(!file.exists()){
			try {
				createDefaultDbProperties(path,"root","mysql","localhost","3306","mysql");
			} catch (IOException e) {
				System.out.println("创建配置文件出错！");
				e.printStackTrace();
			}
		}
		try {
			readDbProperties(path);
		} catch (IOException e) {
			System.out.println("读取配置文件失败！");
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Title: createDefaultDbProperties
	 * @Description: Create the configuration file if this file is not exist
	 * 				 using the given arguments.
	 * @param path
	 * @param username
	 * @param password
	 * @param host
	 * @param port
	 * @param database
	 * @throws IOException
	 */
	private static void createDefaultDbProperties(String path, 
			String username, String password, String host, String port, String database) throws IOException{
		File file = new File(path);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		file.createNewFile();
		
		FileWriter fw = new FileWriter(file);
		BufferedWriter write = new BufferedWriter(fw);
		write.write("username=" + username);
		write.newLine();
		write.write("password=" + password);
		write.newLine();
		write.write("host=" + host);
		write.newLine();
		write.write("database=" + database);
		write.newLine();
		write.write("port=" + port);
		write.flush();
		
		write.close();
		fw.close();
	}
	
	/**
	 * 
	 * @Title: readDbProperties
	 * @Description: Get the configuration information from /.wus/do.properties
	 * @param path
	 * @throws IOException
	 */
	private static void readDbProperties(String path) throws IOException{
		Properties prop = new Properties();
		FileInputStream file = null;
		file = new FileInputStream(path);
		prop.load(file);
		
		userName = prop.getProperty("username");//"root";
		password = prop.getProperty("password");//"mysql";
		host = prop.getProperty("host");//"localhost";
		port = prop.getProperty("port");//"3306";
		database = prop.getProperty("database");//"mysql";
	}
}
