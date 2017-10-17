package com.codebelief.app.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: Wray Zheng
 * @date: 2017-10-17
 * @description: 更改订阅记录的单个属性
 */
public class UrlModifyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int urlID;
	private String url;
	private String urlTitle;
	
	public String enable() {
		return SUCCESS;
	}
	
	public String disable() {
		return SUCCESS;
	}
	
	public String setRealTimePush() {
		return SUCCESS;
	}
	
	public String setIntegratedPush() {
		return SUCCESS;
	}
	
	public String updateUrl() {
		return SUCCESS;
	}
	
	public String updateUrlTitle() {
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
}
