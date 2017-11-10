/*
 * @(#)module-scraper --- JavaMail.java 
 */
package com.codebelief.app.mail;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
/**
 * @author 何涛
 * @version 1st   on 2017年11月9日
 */
public class JavaMail implements IJavaMail{
	private static String myMailSMTPHost = null;
	private static String myMailAccount = null;
	private static String myMailPassword = null;
	private String recipientName = null;
	private String recipientAccount = null;
	private String content = null;
	
	/**
	 * @Description construction method
	 * @param recipientAccount
	 */
	public JavaMail(String recipientAddress, String recipientName){
		this.recipientAccount = recipientAddress;
		this.recipientName = recipientName;
	}
	
	/**
	 * @Description construction method
	 * @param recipientAccount
	 * @param content
	 */
	public JavaMail(String recipientAddress, String recipientName, String content){
		this.recipientAccount = recipientAddress;
		this.recipientName = recipientName;
		this.content = content;
	}
	
	/**
	 * 
	 * @Title: getRecipientName
	 * @Description: get the recipient's username
	 * @return String
	 */
	public String getRecipientName(){
		return recipientName;
	}
	
	/**
	 * 
	 * @Title: setRecipientName
	 * @Description: set the recipient's username
	 * @param recipientName
	 */
	public void setRecipientName(String recipientName){
		this.recipientName = recipientName;
	}
	
	/**
	 * 
	 * @Title: getRecipientAccount
	 * @Description: get recipient's account
	 * @return String
	 */
	public String getRecipientAccount() {
		return recipientAccount;
	}
	
	/**
	 * 
	 * @Title: setRecipientAccount
	 * @Description: set recipient's account
	 * @param recipientAccount
	 */
	public void setRecipientAccount(String recipientAccount) {
		this.recipientAccount = recipientAccount;
	}
	
	/**
	 * 
	 * @Title: getContent
	 * @Description: get the Email's content
	 * @return String
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 
	 * @Title: setContent
	 * @Description: set the Email's content
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 
	 * @Title: initialMailDeploy
	 * @Description: initial the deploy about user's information
	 * @param mailSMTPHost
	 * @param mailAccount
	 * @param mailPassword
	 */
	public static void initialMailDeploy(
			String mailSMTPHost,
			String mailAccount,
			String mailPassword){
		myMailSMTPHost = mailSMTPHost;
		myMailAccount = mailAccount;
		myMailPassword = mailPassword;
	}
	
	/**
	 * 
	 * @Title: setDeploy
	 * @Description: 创建参数配置，用于连接邮件服务器的参数配置
	 * @return: Session
	 */
	public Session connectDeploy(){
		Properties pros = new Properties();
		pros.setProperty("mail.transport.protocol", "smtp");
		pros.setProperty("mail.smtp.host", myMailSMTPHost);
		pros.setProperty("mail.smtp.auth","true");
		Session session = Session.getInstance(pros);
		session.setDebug(true);
		return session;
	}
	
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
			throws UnsupportedEncodingException, MessagingException{
		return createMimeMessage(session, content);
	}
	
	/**
	 * 
	 * @Title: createMimeMessage
	 * @Description: Generate a new Email
	 * @param session
	 * @param content
	 * @return MimeMessage
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	private MimeMessage createMimeMessage(Session session, String content) 
			throws UnsupportedEncodingException, MessagingException{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(myMailAccount, "网页订阅管理网站", "UTF-8"));
		message.setRecipient(MimeMessage.RecipientType.TO, 
				new InternetAddress(
						recipientAccount, 
						recipientName,
						"UTF-8"));
		message.setSubject("网页订阅管理", "UTF-8");
		message.setContent(content, "text/html;charset = UTF-8");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}
	
	/**
	 * 
	 * @Title: sendMail
	 * @Description: send the Email to recipient
	 * @param session
	 * @param message
	 * @throws MessagingException
	 */
	public void sendMail(Session session, MimeMessage message) throws MessagingException{
		Transport transport = session.getTransport();
		transport.connect(myMailAccount, myMailPassword);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
}

