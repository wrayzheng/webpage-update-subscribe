package com.codebelief.app.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.codebelief.app.SignupInfoHandler;
import com.codebelief.app.dao.UserDao;
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
	private boolean success = false;
	private String errorMsg;
	private UserDao userDao;

	@Override
	public String execute() throws Exception {
		
		try{
			if(userDao.isExist(userName)) {
				errorMsg = "用户已存在！";
				return ERROR;
			}
		} catch(Exception e) {
			e.printStackTrace(System.err);
			errorMsg = "访问数据库出错！";
			return ERROR;
		}
		
		try {
			SignupInfoHandler.sendConfirmEmail(userName, email, password);
		} catch(Exception e) {
			errorMsg = "发送验证邮件失败！";
			return ERROR;
		}
		
		success = true;
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

	public UserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
