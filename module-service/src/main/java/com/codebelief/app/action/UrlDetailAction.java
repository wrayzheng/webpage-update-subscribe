package com.codebelief.app.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: Wray Zheng
 * @date: 2017-10-17
 * @description: 显示单条订阅记录详情
 */
public class UrlDetailAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private int urlID;

	@Override
	public String execute() throws Exception {
		if(urlID == 0) {
			return ERROR;
		}
		else {
			return SUCCESS;
		}
	}

	public int getUrlID() {
		return urlID;
	}

	public void setUrlID(int urlID) {
		this.urlID = urlID;
	}
}
