<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录</title>
    </head>

    <body>
        <s:form id="form" method="post" namespace="/ajax" action="login">
            <div class="input-group">
                <span class="input-group-addon">用户名</span>
                <input class="form-control" type="text" name="userName" required="true" />
            </div>
            <div class="input-group">
                <span class="input-group-addon">密码</span>
                <input class="form-control" type="password" name="password" required="true" />
            </div>
            <div class="input-center">
                <input class="btn btn-default" type="submit" value="登录"/>
            </div>
        </s:form>
        
        <%@ include file="/include/footer.jsp" %>
		<script src="js/sha256.js"></script>
    	<script>
    	$(document).ready(function(){
    		$("#form").submit(function(){
    			password = $("input[name=password]");
    			originPw = password.val();
    			encryptedPw = SHA256(originPw);
    			password.val(encryptedPw);
    			$.post("<s:url namespace='/ajax' action='login' />",
					$("#form").serialize(),
					function(data, status){
						if(data["success"])
							window.location.href="<s:url namespace='/user' action='overview' />";
						else {
							password.val(originPw);
							alert(data["errorMsg"]);
						}
				});
    			return false;
    		});
    	});
    	</script>
    </body>
    
</html>