package com.codebelief.app.mail;

/** 
 * 邮件实体对象
 */
public class EmailInfo{
	
	/**
	 * 发件人
	 */
	private String from;
	
	/**
	 * 收件人
	 */
	private String to;
	
	/**
	 * 邮件内容
	 */
	private String content;
	
	/**
	 * 邮件标题
	 */
	private String title;

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
