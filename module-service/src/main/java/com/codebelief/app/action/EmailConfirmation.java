package com.codebelief.app.action;

import java.sql.Time;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.codebelief.app.SignupInfoHandler;
import com.codebelief.app.DAO.IUserDAO;
import com.codebelief.app.DAOFactory.UserDAOFactory;
import com.codebelief.app.VO.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: Wray Zheng
 * @date: 2017-12-05
 * @description: 用户邮箱确认
 */
public class EmailConfirmation extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = ServletActionContext.getRequest();
	private String userName;
	private String email;
	private String password;
	private String msg;
	private String info;

	public String execute() throws Exception {
		String paramEncoded = info;
		// 解密 URL 参数，获得用户注册信息
		try {
			String[] signupInfo = SignupInfoHandler.getSignupInfo(paramEncoded);
			setUserName(signupInfo[0]);
			setEmail(signupInfo[1]);
			setPassword(signupInfo[2]);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		// 注册用户
		User user = new User(userName, password, email, Time.valueOf("20:00:00"));
		IUserDAO userDAO;
		try {
			userDAO = UserDAOFactory.getUserDAOInstance();
			userDAO.doInsert(user);
		} catch (Exception e) {
			e.printStackTrace();
			setMsg("激活用户失败，请尝试重新注册！");
			return SUCCESS;
		}

		setMsg("账号激活成功，请返回首页登录。");
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}
