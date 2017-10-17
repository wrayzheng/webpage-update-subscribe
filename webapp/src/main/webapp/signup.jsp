<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册新用户</title>
    </head>

    <body>
        <s:form id="form" method="post" action="signup">
            <div class="input-group">
                <span class="input-group-addon">用户名</span>
                <input class="form-control" type="text" name="userName" required="true" />
            </div>
            <div class="input-group">
                <span class="input-group-addon">邮箱</span>
                <input class="form-control" type="text" name="email" required="true" />
            </div>
            <div class="input-group">
                <span class="input-group-addon">密码</span>
                <input class="form-control" type="password" name="password" required="true" />
            </div>
            <div class="input-group">
                <span class="input-group-addon">确认密码</span>
                <input class="form-control" type="password" name="password-check" required="true" />
            </div>
            <div class="input-center">
                <input class="btn btn-default" type="submit" value="注册"/>
            </div>
        </s:form>
    </body>
</html>