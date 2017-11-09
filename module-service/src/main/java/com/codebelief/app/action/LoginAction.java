package com.codebelief.app.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.codebelief.app.DAO.IUserDAO;
import com.codebelief.app.DAOFactory.UserDAOFactory;
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
	
	public String execute() throws Exception {
		if(verify()) {
			session.put("userName", userName);
			return SUCCESS;
		}
		else return ERROR;
	}
	
	public String logout() {
		session.clear();
		return SUCCESS;
	}
	
	public boolean verify() {
		IUserDAO userDAO;
		
		try {
			userDAO = UserDAOFactory.getUserDAOInstance();
			String pw = userDAO.doFindPassword(userName);
			userDAO.free();
			success = pw.equals(this.password);
			if(!success) { errorMsg = "密码错误！"; }
		} catch (Exception e) {
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

}
