package com.codebelief.app.mail;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 郵件服務器
 * 
 * @author Administrator
 * 
 */
public class EmailServer {
	private static final int POOL_SIZE = 5;
	private Session session;
	private ExecutorService theadPool;
	public static String username;
	/**
	 * 郵件監聽器
	 */
	//private List<EmailSendListener> emailSendListeners = new ArrayList<EmailSendListener>();

	public void init() {
		final Properties properties = SysConfig.getProperties();
		this.theadPool = Executors.newFixedThreadPool(POOL_SIZE);
		this.session = Session.getDefaultInstance(properties,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(properties
								.getProperty("mail.smtp.username"), properties
								.getProperty("mail.smtp.password"));
					}
				});
		username = properties.getProperty("mail.smtp.username");
		//设置调试模式（输出调试信息）
		this.session.setDebug(false);
	}

	/**
	 * 發送單條email
	 * 
	 * @param emailInfo
	 */
	public void send(final EmailInfo emailInfo) {
		this.theadPool.execute(new Runnable() {
			public void run() {
				EmailContext emailContext = new EmailContext();
				emailContext.setEmailInfo(emailInfo);
				try {
					Message msg = buildEmailMessage(emailInfo);
					Transport.send(msg);
				} catch (Exception e) {
					emailContext.setThrowable(e);
				}
			}
		});
	}

	private Message buildEmailMessage(EmailInfo emailInfo)
			throws AddressException, MessagingException {
		MimeMessage message = new MimeMessage(this.session);
		message.setFrom(convertString2InternetAddress(emailInfo.getFrom()));
		message.setRecipient(Message.RecipientType.TO, convertString2InternetAddress(emailInfo.getTo()));

		Multipart multipart = new MimeMultipart();
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(emailInfo.getContent(), "text/html;charset=UTF-8");
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		message.setSubject(emailInfo.getTitle());
		message.saveChanges();
		return message;
	}

	private InternetAddress convertString2InternetAddress(String address)
			throws AddressException {
		return new InternetAddress(address);
	}
}
