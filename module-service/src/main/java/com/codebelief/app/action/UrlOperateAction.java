package com.codebelief.app.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: Wray Zheng
 * @date: 2017-10-17
 * @description: 单条订阅记录的增删改
 */
public class UrlOperateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int urlID;
	private String url;
	private String urlTitle;
	private boolean enabled;
	private boolean realTimePush;

	public String add() {
		return SUCCESS;
	}

	public String update() {
		return SUCCESS;
	}
	
	public String delete() {
		return SUCCESS;
	}
	
	public int getUrlID() {
		return urlID;
	}

	public void setUrlID(int urlID) {
		this.urlID = urlID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isRealTimePush() {
		return realTimePush;
	}

	public void setRealTimePush(boolean realTimePush) {
		this.realTimePush = realTimePush;
	}
}
