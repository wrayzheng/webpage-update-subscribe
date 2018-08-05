/*
 * @(#)module-scraper --- SendMail.java 
 */
package com.codebelief.app.mail;

import java.util.HashMap;
import java.util.Map;

import com.codebelief.app.pushupdate.PushMail;;

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
	
	public static void main(String[] args) {
		try {
			PushMail.PushUpdateMail("wray");
			System.out.println("Email sent.");
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
			e.printStackTrace();
		}
	}
}
