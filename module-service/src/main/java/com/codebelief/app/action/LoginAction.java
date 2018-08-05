package com.codebelief.app.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.codebelief.app.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: Wray Zheng
 * @date: 2017-10-17
 * @description: 处理用户登录
 */
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private boolean success;
	private String errorMsg;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private UserDao userDao;
	
	public String execute() throws Exception {
		if(verify()) {
			session.put("userName", userName);
			return SUCCESS;
		}
		else return ERROR;
	}
	
	public String isLogined() {
		if(null != session.get("userName")) {
			success = true;
			return SUCCESS;
		} else {
			errorMsg = "用户尚未登陆！";
			return ERROR;
		}
	}
	
	public String logout() {
		session.clear();
		return SUCCESS;
	}
	
	public boolean verify() {
		
		try {
			String pw = userDao.doFindPassword(userName);
			success = pw.equals(this.password);
			if(!success) { errorMsg = "密码错误！"; }
		} catch (Exception e) {
			e.printStackTrace(System.err);
			success = false;
			errorMsg = "请检查用户名！";
		}
		
		return success;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public UserDao getUserDAO() {
		return userDao;
	}

	@Autowired
	public void setUserDAO(UserDao userDAO) {
		this.userDao = userDAO;
	}

}
