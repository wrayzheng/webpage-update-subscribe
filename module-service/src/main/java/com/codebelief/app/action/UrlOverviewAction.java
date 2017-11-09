package com.codebelief.app.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: Wray Zheng
 * @date: 2017-10-15
 * @description: 显示所有订阅记录
 */
public class UrlOverviewAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		String userName = (String) ActionContext.getContext().getSession().get("userName");
		if(null == userName) return ERROR;

		ActionContext.getContext().put("userName", userName); 
		return SUCCESS;
	}

}
