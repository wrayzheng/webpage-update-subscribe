package com.codebelief.app.action;

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
	
	@Override
	public String execute() throws Exception {
		if(verify()) {
			ActionContext.getContext().getSession().put("UserName", userName);
			ActionContext.getContext().put("success", true);
		}
		else {
			ActionContext.getContext().put("success", false);
		}
		
		return SUCCESS;
	}
	
	public boolean verify() {
		boolean result = false;
//		IUserDAO userDAO;
//		
//		try {
//			userDAO = UserDAOFactory.getUserDAOInstance();
//			//sql�������ݵ��쳣��ֱ���ڷ����ڲ����񣬷��ؿ�ֵ��û��Ҫ�׳��쳣
//			result = userDAO.doFindPassword(userName).equals(password);
//			userDAO.free();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		String password = "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824";
		result = password.equals(this.password);
		
		return result;
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

}
