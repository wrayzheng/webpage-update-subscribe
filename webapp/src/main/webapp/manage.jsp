<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>订阅管理中心</title>
        <link rel="stylesheet" type="text/css" href="<s:url value='/css/style.css' />" />
    </head>

    <body class="container">
    	<h1><span id="logout" class="icon icon-logout" title="注销"></span>订阅管理中心<span id="addUrl" class="icon icon-add" title="添加订阅记录"></span></h1>
    	<div class="manage-panel">
	    	<div class="button-panel">
	    		<button id="enable">启动</button>
	    		<button id="disable">暂停</button>
	    		<button id="setRealTimePush">实时推送</button>
	    		<button id="setIntegratedPush">整合推送</button>
	    		<button id="deleteUrl">删除订阅</button>
	    		<span class="user">当前用户：<s:property value="userName" /></span>
	    	</div>
	    	<table class="table-panel">
	    		<tr>
	    			<th class="h_select"><input class="selectAll" type="checkbox" /></th>
	    			<th class="h_title">标题</th>
	    			<th class="h_url">URL</th>
	    			<th class="h_status">状态</th>
	    			<th class="h_pushMethod">推送方式</th>
	    		</tr>
	    		<s:iterator value="urlList" var="record">
	    		<tr>
	    			<td><input type="checkbox" class="selectSingle" value="<s:property value='#record.urlID' />" /></td>
	    			<td>
	    				<span class="viewer"><span><s:property value='#record.title' /></span><span class="icon icon-pencil"></span></span>
	    				<span class="editor" style="display:none;"><input type="text" name="title" /><span class="icon icon-ok"></span><span class="icon icon-cancel"></span></span>
	    			</td>
	    			<td>
	    				<span class="viewer"><span><s:property value='#record.url' /></span><span class="icon icon-pencil"></span></span>
	    				<span class="editor" style="display:none;"><input type="text" name="url" /><span class="icon icon-ok"></span><span class="icon icon-cancel"></span></span>
	    			</td>
	    			<td><span class="status icon icon-<s:if test='#record.enabled'>start</s:if><s:else>pause</s:else>"></span></td>
	    			<td><span class="pushMethod icon icon-<s:if test='#record.realTimePush'>realTimePush</s:if><s:else>integratedPush</s:else>"></span></td>
	    		</tr>
	    		</s:iterator>
	    	</table>
    	</div>
    	
    	<div class="dialog-bg" id="add">
	        <div class="dialog dialog-compact">
	        	<h2>添加订阅记录</h2>
	        	<s:form id="add-form" method="post" namespace="/ajax" action="addUrl">
	            <div class="input-group">
	                <label>类别</label>
	                <select>
	                	<option>默认分类</option>
	                	<option>体育</option>
	                	<option>新闻</option>
	                	<option>博客</option>
	                </select>
	            </div>
	            <div class="input-group">
	                <label>标题</label>
	                <input type="text" name="title" required="true" />
	            </div>
	            <div class="input-group">
	                <label>URL</label>
	                <input type="text" name="url" required="true" />
	            </div>
	            <div class="input-group">
	                <label>屏蔽关键词</label>
	                <input type="text" name="block" />
	            </div>
	            <div class="input-group radio-group">
	                <label><input type="radio" name="realTimePush" value="true" checked />实时推送</label>
	                <label><input type="radio" name="realTimePush" value="false" />整合推送</label>
	            </div>
	            <input class="btn" type="submit" value="添加"/>
	        </s:form>
	        </div>
        </div>
    	
        <%@ include file="/include/footer.jsp" %>
        <script>
        $(document).ready(function(){
        	//添加按钮
        	$("#addUrl").click(function(){
        		$("#add").fadeIn(300);
        	});
        	
        	//注销按钮
        	$("#logout").click(function(){
        		window.location.href="<s:url namespace='/' action='logout' />";
        	});
        	
        	//隐藏表单
    		$(".dialog-bg").click(function(){
    			$(this).fadeOut(300);
    		});
    		
    		//阻止表单上鼠标点击事件的冒泡
    		$(".dialog").click(function(e){
    			e.stopPropagation();
    		});
        	
        	//全选checkbox
        	$(".selectAll").click(function(){
        		if($(this).prop("checked") == true) $(".selectSingle").prop("checked", true);
        		else $(".selectSingle").prop("checked", false);
        	});
        	
        	//启动按钮
	    	$("#enable").click(function(){
	    		$(".selectSingle:checked").each(function(){
	    			var iconSpan = $(this).closest("tr").find(".status");
	    			$.post("<s:url namespace='/ajax' action='enable' />",
	    					{"urlID": $(this).val()},
	    					function(data, status){
	    						console.log(data);
	    						if(data["success"]) iconSpan.removeClass("icon-pause").addClass("icon-start");
	    						else alert(data["errorMsg"]);
	    			});
	    		});
	    	});
        	
        	//暂停按钮
	    	$("#disable").click(function(){
	    		$(".selectSingle:checked").each(function(){
	    			var iconSpan = $(this).closest("tr").find(".status");
	    			$.post("<s:url namespace='/ajax' action='disable' />",
	    					{"urlID": $(this).val()},
	    					function(data, status){
	    						console.log(data);
	    						if(data["success"]) iconSpan.removeClass("icon-start").addClass("icon-pause");
	    						else alert(data["errorMsg"]);
	    			});
	    		});
	    	});
        	
        	//设置实时推送
        	$("#setRealTimePush").click(function(){
        		$(".selectSingle:checked").each(function(){
        			var iconSpan = $(this).closest("tr").find(".pushMethod");
        			$.post("<s:url namespace='/ajax' action='setRealTimePush' />",
        					{"urlID": $(this).val()},
        					function(data, status){
        						console.log(data);
        						if(data["success"]) iconSpan.removeClass("icon-integratedPush").addClass("icon-realTimePush");
        						else alert(data["errorMsg"]);
        			});
        		});
        	});
        	
        	//设置整合推送
        	$("#setIntegratedPush").click(function(){
        		$(".selectSingle:checked").each(function(){
        			var iconSpan = $(this).closest("tr").find(".pushMethod");
        			$.post("<s:url namespace='/ajax' action='setIntegratedPush' />",
        					{"urlID": $(this).val()},
        					function(data, status){
        						console.log(data);
        						if(data["success"]) iconSpan.removeClass("icon-realTimePush").addClass("icon-integratedPush");
        						else alert(data["errorMsg"]);
        			});
        		});
        	});
        	
        	//删除按钮
        	$("#deleteUrl").click(function(){
        		$(".selectSingle:checked").each(function(){
        			var record = $(this).closest("tr");
        			$.post("<s:url namespace='/ajax' action='deleteUrl' />",
	       					{"urlID": $(this).val()},
	       					function(data, status){
	       						console.log(data);
	       						if(data["success"]) {
	       							record.fadeOut(500, function(){
	       								$(this).remove();
	       							});
	       						}
	       						else alert(data["errorMsg"]);
	       			});
        		});
        	});
        	
        	//table内部元素绑定事件
        	//开始编辑（铅笔图标）
        	function onIconPencilClick(){
        		var viewer = $(this).parent();
        		var editor = viewer.hide().next();
        		var text = viewer.children().first().text();
        		editor.children().first().val(text);
        		editor.fadeIn(500);
        		editor.children().first().focus();
        	}
        	$(".icon-pencil").click(onIconPencilClick);
        	
        	//编辑完毕
        	function onIconOkClick(){
        		var editor = $(this).parent();
        		var viewer = editor.prev();
        		var text = editor.children().first().val();
        		var field = editor.find("input").attr("name");
        		var target;
        		if(field == "url") target = "<s:url namespace='/ajax' action='updateUrl' />";
        		else if(field == "title") target = "<s:url namespace='/ajax' action='updateUrlTitle' />";
        		var urlID = $(this).closest("tr").find(".selectSingle").prop("value");
        		$.post(target, "urlID="+urlID+"&"+field+"="+text,
    					function(data, status){
    						if(data["success"]) {
    			        		viewer.children().first().text(text);
    						} else {
    							console.log(data);
    							alert(data["errorMsg"]);
    						}
							editor.hide();
							viewer.fadeIn(500);
    			});
        	}
        	$(".icon-ok").click(onIconOkClick);
        	
        	//取消编辑
        	function onIconCancelClick() {
        		$(this).parent().hide().prev().fadeIn(500);
        	}
        	$(".icon-cancel").click(onIconCancelClick);
        	
        	//编辑快捷键（回车提交/Esc取消）
        	function onKeyDown(e){
        		if(e.keyCode == 13) $(this).nextAll(".icon-ok").trigger("click");
        		else if(e.keyCode == 27) $(this).nextAll(".icon-cancel").trigger("click");
        	}
        	$(".editor input").keydown(onKeyDown);
    		
    		//往table中添加新记录
    		function addRecord(urlID, title, url, realTimePush) {
    			var template = '<tr>'
	    			+ '<td><input type="checkbox" class="selectSingle" value="$urlID" /></td>'
	    			+ '<td>'
	    			+ '	<span class="viewer"><span>$title</span><span class="icon icon-pencil"></span></span>'
	    			+ '	<span class="editor" style="display:none;"><input type="text" name="title" /><span class="icon icon-ok"></span><span class="icon icon-cancel"></span></span>'
	    			+ '</td>'
	    			+ '<td>'
	    			+ '	<span class="viewer"><span>$url</span><span class="icon icon-pencil"></span></span>'
	    			+ '	<span class="editor" style="display:none;"><input type="text" name="url" /><span class="icon icon-ok"></span><span class="icon icon-cancel"></span></span>'
	    			+ '</td>'
	    			+ '<td><span class="status icon icon-start"></span></td>'
	    			+ '<td><span class="pushMethod icon icon-$howToPush"></span></td>'
	    			+ '</tr>';
	    		var howToPush = realTimePush == "true" ? "realTimePush" : "integratedPush";
	    		var newRecord = template.replace("$urlID", urlID).replace("$title", title).replace("$url", url).replace("$howToPush", howToPush);
	    		$(".dialog-bg").hide();
	    		var lastLine = $("table").append($(newRecord).hide()).find("tr:last-child").fadeIn(500);
	    		lastLine.find(".icon-pencil").click(onIconPencilClick);
	    		lastLine.find(".icon-ok").click(onIconOkClick);
	    		lastLine.find(".icon-cancel").click(onIconCancelClick);
	    		lastLine.find(".editor input").keydown(onKeyDown);
    		}
    		
    		//完成添加
    		$("#add-form").submit(function(){
    			var title = $(this).find("input[name='title']").val();
    			var url = $(this).find("input[name='url']").val();
    			var realTimePush = $(this).find("input[name='realTimePush']:checked").val();
    			
				$.post("<s:url namespace='/ajax' action='addUrl' />",
    					$(this).serialize(),
						function(data, status){
    					if(data["success"])
    						addRecord(data["urlID"], title, url, realTimePush);
    					else alert(data["errorMsg"]);
    			});
    			return false;
    		});
        	
        });
        </script>
    </body>
</html>