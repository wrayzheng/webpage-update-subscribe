/*
 * @(#)module-scraper --- SendMail.java 
 */
package com.codebelief.app.mail;

import java.util.Map;

/**
 * @author 何涛
 * @version 1st   on 2017年11月13日
 */
public class SendMail {
	public static void sendMail(String recipient, Map<String, String> parameters) throws InterruptedException{
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
		for(String key: parameters.keySet()){
			emailInfo.addParameter(key, parameters.get(key));
		}
		//发送
		emailSendFacade.send(emailInfo);
		Thread.sleep(10000);
	}
}
