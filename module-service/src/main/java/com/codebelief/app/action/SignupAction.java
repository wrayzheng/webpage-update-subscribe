package com.codebelief.app.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: Wray Zheng
 * @date: 2017-10-17
 * @description: 处理新用户注册
 */
public class SignupAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String email;
	private String password;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
