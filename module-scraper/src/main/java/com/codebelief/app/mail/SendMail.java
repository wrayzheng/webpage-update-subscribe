/*
 * @(#)module-scraper --- SendMail.java 
 */
package com.codebelief.app.mail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.codebelief.app.DAO.IUrlDAO;
import com.codebelief.app.DAOFactory.ContentDAOFactory;
import com.codebelief.app.DAOFactory.UrlDAOFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.Url;
import com.codebelief.app.compare.SingleUpdateRecord;

/**
 * @author 何涛
 * @version 1st   on 2017年11月13日
 */
public class SendMail {
	public static void sendMail(String template, String title, String recipient,
			Map<Object, Object> parameter) throws InterruptedException {
		//启动邮件服务器
		EmailServer emailServer = new EmailServer();
		emailServer.init();
		
		//启动模板服务
		EmailTemplateService emailTemplateService = new FreemarkerEmailTemplateService();
		emailTemplateService.init();//模板引擎初始化
		
		//设置数据
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setTitle(title);
		emailInfo.setFrom(EmailServer.username);
		emailInfo.setTo(recipient);
		
		//获取模版文件
		String content = emailTemplateService.getText(template, parameter);
		emailInfo.setContent(content);
		//发送
		emailServer.send(emailInfo);
		Thread.sleep(1000);
	}
	
	public static void test(String[] args) {
		try {
			PushMail.PushUpdateMail("Wray");
			System.out.println("mail sent.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testSignupMail(String[] args) {
		Map<Object, Object> parameter = new HashMap<>();
		parameter.put("url", "http://app.codebelief.com/webpage-update-subscribe/");
		try {
			sendMail("signup", "欢迎注册网页更新订阅系统", "mczon@qq.com", parameter);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testUpdateMail(String[] args) {
		Map<Object, Object> urlMap = new HashMap<>();
		LinkedList<SingleUpdateRecord> updateList = new LinkedList<>();
		for(int i = 0; i < 5; i++)
			updateList.add(new SingleUpdateRecord("http://baidu.com/" + i, "更新" + i));
		urlMap.put("0", new DeltaObject("今日哈工大", "http://today.hit.edu.cn", updateList));
		
		updateList = new LinkedList<>();
		for(int i = 0; i < 5; i++)
			updateList.add(new SingleUpdateRecord("http://xxx.com/" + i, "新标题" + i));
		urlMap.put("1", new DeltaObject("凤凰新闻", "http://ifeng.com", updateList));
		
		Map<Object, Object> parameter = new HashMap<>();
		parameter.put("urlMap", urlMap);
		
		try {
			sendMail("update", "网页更新订阅新内容推送", "850738350@qq.com", parameter);
			System.out.println("Email sent.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
