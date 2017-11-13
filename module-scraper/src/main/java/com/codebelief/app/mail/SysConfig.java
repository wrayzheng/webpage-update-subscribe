/*
 * @(#)javaMail --- SysConfig.java 
 */
package com.codebelief.app.mail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
/**
 * @author 何涛
 * @version 1st   on 2017年11月13日
 */
public class SysConfig {
	private static final Properties Sys_Config = new Properties();
	
	private static final String Config_Path = 
			System.getProperty("user.home") + System.getProperty("file.separator") 
			+ ".wus" + System.getProperty("file.separator") + "email.properties";
	
	static{
		File file = new File(Config_Path);
		if(!file.exists()){
			try {
				createDefaultDbProperties(Config_Path);
			} catch (IOException e) {
				System.out.println("创建配置文件出错！");
				e.printStackTrace();
			}
		}
		try {
			Sys_Config.load(new FileInputStream(Config_Path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("配置文件读取失败！");
			e.printStackTrace();
		}
	}

	private static void createDefaultDbProperties(String path) throws IOException{
		File file = new File(path);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		file.createNewFile();
		
		FileWriter fw = new FileWriter(file);
		BufferedWriter write = new BufferedWriter(fw);
		write.write("mail.transport.protocol=");
		write.newLine();
		write.write("mail.smtp.port=");
		write.newLine();
		write.write("mail.smtp.host=");
		write.newLine();
		write.write("mail.smtp.username=");
		write.newLine();
		write.write("mail.smtp.password=");
		write.newLine();
		write.write("mail.smtp.connectiontimeout=");
		write.newLine();
		write.write("mail.smtp.timeout=");
		write.newLine();
		write.write("mail.smtp.auth=");
		write.flush();
		
		write.close();
		fw.close();
	}
	
	private SysConfig(){}
	
	public static Properties getProperties(){
		return Sys_Config;
	}
	
}


/*package com.codebelief.app.mail;

import java.io.IOException;
import java.util.Properties;

/**
 * 系统配置参数
 * @author Administrator
 *
 */
/*public class SysConfig {
	private static final Properties SYS_CONFIG = new Properties();
	private static final String SYS_CONFIG_FILE = "/sys_config.properties";
	static{
		try {
			SYS_CONFIG.load(SysConfig.class.getResourceAsStream(SYS_CONFIG_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private SysConfig(){}
	public static Properties getConfiguration(){
		return SYS_CONFIG;
	}
}*/
