package com.codebelief.app.action;

import com.opensymphony.xwork2.ActionSupport;
import com.codebelief.app.DAOFactory.*;
import com.codebelief.app.VO.*;

import java.sql.SQLException;

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
	private String urlTitle;
	private boolean success = false;
	private String errorMsg;

    public String noPermission()  {
        return ERROR;
    }

	public String enable() {
		try {
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			if(!urlDAO.doUpdateEnable(urlID, true)){
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
			if(!urlDAO.doUpdateEnable(urlID, false)){
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
			if(!urlDAO.doUpdateTitle(urlID, urlTitle)){
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

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
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
