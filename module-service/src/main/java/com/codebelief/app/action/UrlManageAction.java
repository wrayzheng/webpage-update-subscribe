package com.codebelief.app.action;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.codebelief.app.bean.Url;
import com.codebelief.app.dao.UrlDao;
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
	private List<Url> urlList;
	private String errorMsg;
	private UrlDao urlDao;
	
	@Override
	public String execute() throws Exception {
		try {
			urlList = getUrlDao().doFindAll(userName);
			return SUCCESS;
		} catch (Exception e) {
			setErrorMsg("访问数据库出错！");
    		e.printStackTrace();
		}
		return ERROR;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Url> getUrlList() {
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

	public UrlDao getUrlDao() {
		return urlDao;
	}

	@Autowired
	public void setUrlDao(UrlDao urlDao) {
		this.urlDao = urlDao;
	}

}
