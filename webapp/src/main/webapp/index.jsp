<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>网页更新订阅</title>
        <link rel="stylesheet" type="text/css" href="<s:url value='/css/style.css' />" />
        <style>body {background: #67c2d4;}</style>
	</head>

	<body>
        <div class="cover">
			<h1>网页更新订阅</h1>
			<div class="ts">
		        <p>想要关注某个网页的变化？</p>
		        <p>我们能自动检测网页的变化，并推送至您的邮箱！</p>
	        </div>
	        <p>开始体验吧&nbsp;↓</p>
			<div class="box">
				<button id="login-btn">登录</button>
				<span class="ts">或者</span>
				<button id="signup-btn">注册</button>
			</div>
        </div>
		<div class="dialog-bg" id="login">
	        <div class="dialog">
	        	<h2>登录</h2>
	        	<s:form id="login-form" method="post" namespace="/ajax" action="login">
	            <div class="input-group">
	                <label>用户名</label>
	                <input type="text" name="userName" required="true" />
	            </div>
	            <div class="input-group">
	                <label>密码</label>
	                <input type="password" name="password" required="true" />
	            </div>
	            <input class="btn" type="submit" value="登录"/>
	        </s:form>
	        </div>
        </div>
		<div class="dialog-bg" id="signup">
	        <div class="dialog">
	        	<h2>注册</h2>
	        	<s:form id="signup-form" method="post" namespace="/ajax" action="signup">
	            <div class="input-group">
	                <label>用户名</label>
	                <input type="text" name="userName" required="true" />
	            </div>
	            <div class="input-group">
	                <label>密码</label>
	                <input type="password" name="password" required="true" />
	            </div>
	            <div class="input-group">
	                <label>确认密码</label>
	                <input type="password" name="password-check" required="true" />
	            </div>
	            <div class="input-group">
	                <label>邮箱</label>
	                <input type="text" name="email" required="true" />
	            </div>
	            <input class="btn" type="submit" value="注册"/>
	        </s:form>
	        </div>
        </div>
        
        <%@ include file="/include/footer.jsp" %>
		<script src="js/sha256.js"></script>
    	<script>
    	$(document).ready(function(){
    		//登录按钮
    		$("#login-btn").click(function(){
    			//查询登录状态
    			$.post("<s:url namespace='/ajax' action='isLogined' />",
    					function(data, status) {
    						if(data["success"])
    							window.location.href="<s:url action='manage' namespace='/user' />";
    						else $("#login").fadeIn(300);
    			});
    		});
    		
    		//注册按钮
    		$("#signup-btn").click(function(){
    			$("#signup").fadeIn(300);
    		});
    		
    		//隐藏表单
    		$(".dialog-bg").click(function(){
    			$(this).fadeOut(300);
    		});
    		
    		//阻止表单上鼠标点击事件的冒泡
    		$(".dialog").click(function(e){
    			e.stopPropagation();
    		});

    		//登录表单
    		$("#login-form").submit(function(){
    			password = $("#login-form input[name=password]");
    			originPw = password.val();
    			encryptedPw = SHA256(originPw);
    			password.val(encryptedPw);
    			$.post("<s:url namespace='/ajax' action='login' />",
					$("#login-form").serialize(),
					function(data, status){
						if(data["success"])
							window.location.href="<s:url namespace='/user' action='manage' />";
						else {
							password.val(originPw);
							alert(data["errorMsg"]);
						}
				});
    			return false;
    		});
    		
    		//注册表单
    		$("#signup-form").submit(function(){
    			if($("#signup-form input[name=password]").val() != $("#signup-form input[name=password-check]").val()) {
    				alert("两次密码输入不一致！");
    				return false;
    			}
    			password = $("#signup-form input[name=password]");
    			originPw = password.val();
    			encryptedPw = SHA256(originPw);
    			password.val(encryptedPw);
    			$.post("<s:url namespace='/ajax' action='signup' />",
					$("#signup-form").serialize(),
					function(data, status){
						if(data["success"]) {
							alert("注册完成，请登录邮箱激活账号！");
							$("#signup").hide();
						}
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
