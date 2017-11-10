/*
 * @(#)module-scraper --- IJavaMail.java 
 */
package com.codebelief.app.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * @author 何涛
 * @version 1st   on 2017年11月10日
 */
public interface IJavaMail {
	/**
	 * 
	 * @Title: setDeploy
	 * @Description: 创建参数配置，用于连接邮件服务器的参数配置
	 * @return: Session
	 */
	public Session connectDeploy();
	
	/**
	 * 
	 * @Title: generateMail
	 * @Description: generate a new Email
	 * @param session
	 * @param content
	 * @return MimeMessage
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public MimeMessage generateMail(Session session, String content) 
			throws UnsupportedEncodingException, MessagingException;
	
	/**
	 * 
	 * @Title: sendMail
	 * @Description: send the Email to recipient
	 * @param session
	 * @param message
	 * @throws MessagingException
	 */
	public void sendMail(Session session, MimeMessage message) 
			throws MessagingException;
}

