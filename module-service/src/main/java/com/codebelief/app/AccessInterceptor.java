package com.codebelief.app;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.StrutsStatics;
import com.codebelief.app.DAO.IUrlDAO;
import com.codebelief.app.DAOFactory.UrlDAOFactory;
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
	private String errorMsg;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		request = (HttpServletRequest)invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		userName = (String)invocation.getInvocationContext().getSession().get("userName");
		
		try {
			urlID = Integer.valueOf(request.getParameter("urlID"));
		} catch (Exception e) {
			errorMsg = "无效的urlID！";
		}
		
		System.out.println(this.getClass().getSimpleName()
				+ ": userName=" + userName + " urlID=" + urlID
				+ " action=" + invocation.getInvocationContext().getName());
		
		//如果urlID无效，则直接返回
		if(urlID == -1);
		else if(null == userName) {
			errorMsg = "用户尚未登录！";
		} else {
			try {
				IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
				if(userName.toLowerCase().equals(urlDAO.doFindUserName(urlID).toLowerCase())) {
					return invocation.invoke();
				} else {
					errorMsg = "没有操作权限！";
				}
			} catch(Exception e) {
				errorMsg = "访问数据库出错!";
			}
		}
		
		invocation.getInvocationContext().put("errorMsg", errorMsg);
		return "noPermission";
	}

}