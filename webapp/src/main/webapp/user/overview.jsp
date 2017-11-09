<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>订阅信息概览</title>
    </head>

    <body>
        <div class="item-list">
        	Hello, <s:property value="userName" />!<br />
        	
        	<a href="<s:url value='/addUrl.jsp' />">添加URL</a>
        </div>
    </body>
</html>