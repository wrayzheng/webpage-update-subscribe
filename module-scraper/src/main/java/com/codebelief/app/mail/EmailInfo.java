package com.codebelief.app.mail;

import java.util.HashMap;
import java.util.Map;


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
	
	/**
	 * 邮件中的参数
	 */
	private Map<Object, Object> parameters = new HashMap<Object, Object>();

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
	public Map<Object, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<Object, Object> parameters) {
		this.parameters = parameters;
	}
	public void addParameter(Object key,Object value){
		this.parameters.put(key, value);
	}
	public void removeParameter(Object key){
		this.parameters.remove(key);
	}
}
