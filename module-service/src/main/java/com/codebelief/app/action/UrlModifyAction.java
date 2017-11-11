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
	private boolean success;
	private String errorMsg;
	
	public String noPermission() {
		return ERROR;
	}
	
	public String enable() {
		success = true;
		return SUCCESS;
	}
	
	public String disable() {
		success = true;
		return SUCCESS;
	}
	
	public String setRealTimePush() {
		success = true;
		return SUCCESS;
	}
	
	public String setIntegratedPush() {
		success = true;
		return SUCCESS;
	}
	
	public String updateUrl() {
		success = true;
		return SUCCESS;
	}
	
	public String updateUrlTitle() {
		success = true;
		return SUCCESS;
	}
	
	public String deleteUrl() {
		success = true;
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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
