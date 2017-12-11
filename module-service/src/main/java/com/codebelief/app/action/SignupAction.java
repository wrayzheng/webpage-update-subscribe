package com.codebelief.app.action;

import java.sql.Time;

import com.codebelief.app.DAO.IUserDAO;
import com.codebelief.app.DAOFactory.UserDAOFactory;
import com.codebelief.app.VO.User;
import com.codebelief.app.SignupInfoHandler;
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

	@Override
	public String execute() throws Exception {
		IUserDAO userDAO;
		
		try{
			userDAO = UserDAOFactory.getUserDAOInstance();
			if(userDAO.isExist(userName)) {
				errorMsg = "用户已存在！";
				return ERROR;
			}
		} catch(Exception e) {
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

}
