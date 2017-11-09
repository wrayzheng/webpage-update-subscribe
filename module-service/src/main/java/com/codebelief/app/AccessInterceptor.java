package com.codebelief.app;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author: Wray Zheng
 * @date: 2017-10-20
 * @description: 访问权限验证
 */
public class AccessInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		String userName = (String) session.get("userName");
		
		if(null == userName) return Action.LOGIN;
		else return invocation.invoke();
	}
}
