/*
 * @(#)module-scraper --- MailFactory.java 
 */
package com.codebelief.app.mailFactory;

import com.codebelief.app.mail.IJavaMail;
import com.codebelief.app.mail.JavaMail;
/**
 * @author 何涛
 * @version 1st   on 2017年11月10日
 */
public class MailFactory {
	public static IJavaMail getMailInstance(
			String recipientAccount, 
			String recipientName){
		return new JavaMail(recipientAccount,recipientName);
	}
}
