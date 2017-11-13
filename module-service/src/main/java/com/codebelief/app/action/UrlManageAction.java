package com.codebelief.app.action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.codebelief.app.DAO.IUrlDAO;
import com.codebelief.app.DAOFactory.UrlDAOFactory;
import com.codebelief.app.VO.Url;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: Wray Zheng
 * @date: 2017-10-15
 * @description: 管理所有订阅记录
 */
public class UrlManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String userName = (String) ActionContext.getContext().getSession().get("userName");
	private LinkedList<Url> urlList;
	private String errorMsg;
	
	@Override
	public String execute() throws Exception {
		IUrlDAO urlDAO;
		try {
			urlDAO = UrlDAOFactory.getUrlDAOInstance();
			urlList = urlDAO.doFindAll(userName);
			return SUCCESS;
		} catch (Exception e) {
			setErrorMsg("访问数据库出错！");
		}
		return ERROR;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LinkedList<Url> getUrlList() {
		return urlList;
	}

	public void setUrlList(LinkedList<Url> urlList) {
		this.urlList = urlList;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
