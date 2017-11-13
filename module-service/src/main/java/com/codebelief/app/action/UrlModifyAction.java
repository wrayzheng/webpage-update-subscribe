package com.codebelief.app.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.codebelief.app.DAOFactory.*;
import com.codebelief.app.VO.Url;

import com.codebelief.app.DAO.*;
/**
 * @author: Wray Zheng
 * @date: 2017-10-17
 * @description: 更改订阅记录的单个属性
 */
public class UrlModifyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int urlID;
	private String url;
	private String title;
	private boolean realTimePush;
	private boolean success = false;
	private String errorMsg;

    public String noPermission() {
        return ERROR;
    }
    
    public String addUrl() {
    	String userName = (String)ActionContext.getContext().getSession().get("userName");
    	try {
    		IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
    		urlID = urlDAO.doInsert(userName, title, url, true, realTimePush);
    		if(-1 == urlID) {
    			errorMsg = "添加记录失败！";
    		} else {
    			success = true;
    			return SUCCESS;
    		}
    	} catch (Exception e) {
    		errorMsg = "访问数据库出错！";
    	}
    	return ERROR;
    }

	public String enable() {
		try {
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			if(!urlDAO.doUpdateEnabled(urlID, true)){
				errorMsg = "请检查UrlID！";
			} else {
                success = true;
                return SUCCESS;
            }
		} catch (Exception e) {
			errorMsg = "数据库访问出错！";
		}
		return ERROR;
	}

	public String disable() {
		try {
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			if(!urlDAO.doUpdateEnabled(urlID, false)){
				errorMsg = "请检查UrlID！";
			} else  {
				success = true;
                return SUCCESS;
            }
			urlDAO.free();
		} catch (Exception e) {
			success = false;
			errorMsg = "数据库访问出错！";
		}
		return ERROR;
	}

	public String setRealTimePush() {
		try {
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			if(!urlDAO.doUpdateRealTimePush(urlID, true)){
				errorMsg = "请检查UrlID！";
			} else  {
                success = true;
                return SUCCESS;
            }
			urlDAO.free();
		} catch (Exception e) {
			success = false;
			errorMsg = "数据库访问出错！";
		}
		return ERROR;
	}

	public String setIntegratedPush() {
		try {
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			if(!urlDAO.doUpdateRealTimePush(urlID, false)){
				errorMsg = "请检查UrlID！";
			} else {
				success = true;
                return SUCCESS;
            }
			urlDAO.free();
		} catch (Exception e) {
			success = false;
			errorMsg = "数据库访问出错！";
		}
		return ERROR;
	}

	public String updateUrl() {
		try {
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			if(!urlDAO.doUpdateUrl(urlID, url)){
				errorMsg = "更新URL失败！";
			} else {
                success = true;
                return SUCCESS;
            }
			urlDAO.free();
		} catch (Exception e) {
			success = false;
			errorMsg = "数据库访问出错！";
		}
		return ERROR;
	}

	public String updateUrlTitle() {
		try {
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			if(!urlDAO.doUpdateTitle(urlID, title)){
				errorMsg = "更新标题失败！";
			} else  {
                success = true;
                return SUCCESS;
            }
			urlDAO.free();
		} catch (Exception e) {
			success = false;
			errorMsg = "数据库访问出错！";
		}
		return ERROR;
	}

	public String deleteUrl() {
		try{
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			if(!urlDAO.doDelete(urlID)){
				success = false;
				errorMsg = "请检查urlID！";
			} else {
                success = true;
                return SUCCESS;
            }
		}catch(Exception e){
			success = false;
			errorMsg = "数据库访问出错！";
		}
		return ERROR;
	}

	public int getUrlID() {
		return urlID;
	}

	public void setUrlID(int urlID) {
		this.urlID = urlID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String urlTitle) {
		this.title = urlTitle;
	}

	public boolean isRealTimePush() {
		return realTimePush;
	}

	public void setRealTimePush(boolean realTimePush) {
		this.realTimePush = realTimePush;
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
