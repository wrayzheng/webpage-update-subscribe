package com.codebelief.app.action;

import com.codebelief.app.compare.ContentHandler;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: Wray Zheng
 * @date: 2017-12-10
 * @description: 删除当前用户订阅 URL 网页存储在数据库的部分内容，以便马上爬取时能产生更新。
 */
public class TestAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int urlID = -1;
	private boolean success = false;
	private String errorMsg;

	@Override
	public String execute() throws Exception {
		String userName = (String)ActionContext.getContext().getSession().get("userName");
		if(userName == null) {
			errorMsg = "用户未登录！";
			return ERROR;
		}
		try {
			ContentHandler.testRemoveSomeRecords(userName, 5);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			errorMsg = "测试删除部分数据失败！";
			return ERROR;
		}
		success = true;
		return SUCCESS;
	}
	
	public int getUrlID() {
		return urlID;
	}

	public void setUrlID(int urlID) {
		this.urlID = urlID;
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
