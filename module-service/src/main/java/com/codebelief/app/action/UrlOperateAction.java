package com.codebelief.app.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.codebelief.app.bean.Url;
import com.codebelief.app.dao.ContentDao;
import com.codebelief.app.dao.UrlDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author: Wray Zheng
 * @date: 2017-10-17
 * @description: 添加、更改订阅记录信息
 */
public class UrlOperateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int urlID;
	private String url;
	private String title;
	private boolean realTimePush;
	private boolean success = false;
	private String errorMsg;
	
	private UrlDao urlDao;
	private ContentDao contentDao;

    public String noPermission() {
        return ERROR;
    }
    
    public String addUrl() {
    	String userName = (String)ActionContext.getContext().getSession().get("userName");
    	try {
    		Url tempUrl = new Url(userName, title, url, true, realTimePush);
    		if(!urlDao.doInsert(tempUrl)) {
    			errorMsg = "添加记录失败！";
    			urlID = -1;
    		} else {
    			success = true;
    			urlID = tempUrl.getUrlID();
    			return SUCCESS;
    		}
    	} catch (Exception e) {
    		errorMsg = "访问数据库出错！";
    		e.printStackTrace();
    	}
    	return ERROR;
    }

	public String enable() {
		try {
			if(!urlDao.doUpdateEnabled(urlID, true)){
				errorMsg = "请检查UrlID！";
			} else {
                success = true;
                return SUCCESS;
            }
		} catch (Exception e) {
			errorMsg = "数据库访问出错！";
    		e.printStackTrace();
		}
		return ERROR;
	}

	public String disable() {
		try {
			if(!urlDao.doUpdateEnabled(urlID, false)){
				errorMsg = "请检查UrlID！";
			} else  {
				success = true;
                return SUCCESS;
            }
		} catch (Exception e) {
			errorMsg = "数据库访问出错！";
    		e.printStackTrace();
		}
		return ERROR;
	}

	public String setRealTimePush() {
		try {
			if(!urlDao.doUpdateRealTimePush(urlID, true)){
				errorMsg = "请检查UrlID！";
			} else  {
                success = true;
                return SUCCESS;
            }
		} catch (Exception e) {
			errorMsg = "数据库访问出错！";
    		e.printStackTrace();
		}
		return ERROR;
	}

	public String setIntegratedPush() {
		try {
			if(!urlDao.doUpdateRealTimePush(urlID, false)){
				errorMsg = "请检查UrlID！";
			} else {
				success = true;
                return SUCCESS;
            }
		} catch (Exception e) {
			errorMsg = "数据库访问出错！";
    		e.printStackTrace();
		}
		return ERROR;
	}

	public String updateUrl() {
		try {
			if(!urlDao.doUpdateUrl(urlID, url)){
				errorMsg = "更新URL失败！";
			} else {
                success = true;
                return SUCCESS;
            }
		} catch (Exception e) {
			errorMsg = "数据库访问出错！";
    		e.printStackTrace();
		}
		return ERROR;
	}

	public String updateUrlTitle() {
		try {
			if(!urlDao.doUpdateTitle(urlID, title)){
				errorMsg = "更新标题失败！";
			} else  {
                success = true;
                return SUCCESS;
            }
		} catch (Exception e) {
			errorMsg = "数据库访问出错！";
    		e.printStackTrace();
		}
		return ERROR;
	}

	public String deleteUrl() {
		try{
			contentDao.doDeleteByUrlID(urlID);

			if(!urlDao.doDelete(urlID)){
				success = false;
				errorMsg = "请检查urlID！";
			} else {
                success = true;
                return SUCCESS;
            }
		}catch(Exception e){
			errorMsg = "数据库访问出错！";
    		e.printStackTrace();
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

	public UrlDao getUrlDao() {
		return urlDao;
	}

	@Autowired
	public void setUrlDao(UrlDao urlDao) {
		this.urlDao = urlDao;
	}

	public ContentDao getContentDao() {
		return contentDao;
	}

	@Autowired
	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}
}
