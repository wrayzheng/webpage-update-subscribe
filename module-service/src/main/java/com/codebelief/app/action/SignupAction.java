package com.codebelief.app.action;

import java.sql.Time;

import com.codebelief.app.DAO.IUserDAO;
import com.codebelief.app.DAOFactory.UserDAOFactory;
import com.codebelief.app.VO.User;
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
	private boolean success;
	private String errorMsg;

	@Override
	public String execute() throws Exception {
		User user = new User(userName, password, email, Time.valueOf("20:00:00"));
		IUserDAO userDAO;
		
		try{
			userDAO = UserDAOFactory.getUserDAOInstance();
			userDAO.doInsert(user);
		} catch(Exception e) {
			success = false;
			errorMsg = "注册失败！";
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
