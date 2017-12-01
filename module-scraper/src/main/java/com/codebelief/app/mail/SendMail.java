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
	public static void sendMail(String recipient, Map<String, Object> parameters) throws InterruptedException{
		//启动邮件服务器
		EmailServer emailServer = new EmailServer();
		emailServer.init();
		
		//启动模板服务
		EmailTemplateService emailTemplateService = new FreemarkerEmailTemplateService();
		emailTemplateService.init();//模板引擎初始化
		
		//组装邮件发送门面类
		EmailSendFacade emailSendFacade = new EmailSendFacade();
		emailSendFacade.setEmailServer(emailServer);//注册邮件服务器
		emailSendFacade.setEmailTemplateService(emailTemplateService);//注册模板
		
		//测试数据
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setFrom(EmailServer.username);
		emailInfo.setTo(recipient);
		emailInfo.addParameter("urlMap", parameters);
		//发送
		emailSendFacade.send(emailInfo);
		Thread.sleep(1000);
	}
	
	public static void main(String[] args) {
		try {
			PushMail.PushUpdateMail("Wray");
			System.out.println("mail sent.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test(String[] args) {
		Map<String, Object> urlMap = new HashMap<>();
		LinkedList<SingleUpdateRecord> updateList = new LinkedList<>();
		for(int i = 0; i < 5; i++)
			updateList.add(new SingleUpdateRecord("http://baidu.com/" + i, "更新" + i));
		urlMap.put("0", new DeltaObject("今日哈工大", "http://today.hit.edu.cn", updateList));
		
		updateList = new LinkedList<>();
		for(int i = 0; i < 5; i++)
			updateList.add(new SingleUpdateRecord("http://xxx.com/" + i, "新标题" + i));
		urlMap.put("1", new DeltaObject("凤凰新闻", "http://ifeng.com", updateList));
		
		try {
			sendMail("850738350@qq.com", urlMap);
			System.out.println("Email sent.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
