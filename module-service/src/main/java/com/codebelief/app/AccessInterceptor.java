package com.codebelief.app;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.codebelief.app.dao.UrlDao;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author: Wray Zheng
 * @date: 2017-11-11
 * @description: 操作权限验证
 */
public class AccessInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String userName;
	private int urlID = -1;
	private ActionInvocation invocation;
	private UrlDao urlDao;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		this.invocation = invocation;
		request = (HttpServletRequest)invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		userName = (String)invocation.getInvocationContext().getSession().get("userName");
		
		if(null == userName) {
			setErrorMsg("用户尚未登录！");
			return "noPermission";
		}
		
		String tempUrlID = request.getParameter("urlID");
		//添加订阅记录的情况
		if(null == tempUrlID) return invocation.invoke();
		
		try {
			urlID = Integer.valueOf(tempUrlID);
		} catch (Exception e) {
			setErrorMsg("无效的urlID！");
			return "noPermission";
		}
		
		//调试日志
		System.out.println(this.getClass().getSimpleName()
				+ ": userName=" + userName + " urlID=" + urlID
				+ " action=" + invocation.getInvocationContext().getName());
		
		//判断UrlID是否属于当前用户
		try {
			if(userName.toLowerCase().equals(getUrlDao().doFindUserName(urlID).toLowerCase())) {
				return invocation.invoke();
			} else {
				setErrorMsg("没有操作权限！");
			}
		} catch(Exception e) {
			setErrorMsg("访问数据库出错!");
		}

		return "noPermission";
	}
	
	private void setErrorMsg(String errorMsg) {
		invocation.getInvocationContext().put("errorMsg", errorMsg);
	}

	public UrlDao getUrlDao() {
		return urlDao;
	}

	@Autowired
	public void setUrlDao(UrlDao urlDao) {
		this.urlDao = urlDao;
	}

}