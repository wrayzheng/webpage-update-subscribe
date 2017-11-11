<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkbox Test</title>
        <link rel="stylesheet" type="text/css" href="<s:url value='/css/style.css' />" />
    </head>

    <body>
    	<div class="batch-mode">
    		<button id="enable">启动</button>
    		<button id="disable">暂停</button>
    		<button id="setRealTimePush">设为实时推送</button>
    		<button id="setIntegratedPush">设为整合推送</button>
    		<button id="delete">删除订阅</button>
    	</div>
    	<table class="manage-panel" border="1">
    		<tr>
    			<th><input class="selectAll" type="checkbox" /></th>
    			<th>名称</th>
    			<th>URL</th>
    			<th>状态</th>
    			<th>推送方式</th>
    		</tr>
    		<tr>
    			<td><input type="checkbox" class="selectSingle" value="5" /></td>
    			<td class="title">
    				<span class="viewer"><span>今日哈工大</span><span class="icon icon-pencil"></span></span>
    				<span class="editor" style="display:none;"><input type="text" /><span class="icon icon-ok"></span><span class="icon icon-cancel"></span></span>
    			</td>
    			<td class="url">
    				<span class="viewer"><span>http://today.hit.edu.cn</span><span class="icon icon-pencil"></span></span>
    				<span class="editor" style="display:none;"><input type="text" /><span class="icon icon-ok"></span></span>
    			</td>
    			<td><span class="status icon icon-start"></span></td>
    			<td><span class="pushMethod icon icon-realTimePush"></span></td>
    		</tr>
    		<tr>
    			<td><input type="checkbox" class="selectSingle" value="2" /></td>
    			<td class="title">
    				<span class="viewer"><span>阮一峰的博客</span><span class="icon icon-pencil"></span></span>
    				<span class="editor" style="display:none;"><input type="text" /><span class="icon icon-ok"></span><span class="icon icon-cancel"></span></span>
    			</td>
    			<td class="url">
    				<span class="viewer"><span>http://ruanyifeng.com</span><span class="icon icon-pencil"></span></span>
    				<span class="editor" style="display:none;"><input type="text" /><span class="icon icon-ok"></span></span>
    			</td>
    			<td><span class="status icon icon-pause"></span></td>
    			<td><span class="pushMethod icon icon-integratedPush"></span></td>
    		</tr>
    	</table>
        <%@ include file="/include/footer.jsp" %>
        <script>
        $(document).ready(function(){
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
        	$("#delete").click(function(){
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
        	
        	//开始编辑（铅笔图标）
        	$(".icon-pencil").click(function(){
        		var viewer = $(this).parent();
        		var editor = viewer.hide().next();
        		var text = viewer.children().first().text();
        		editor.children().first().val(text);
        		editor.fadeIn(500);
        		editor.children().first().focus();
        	});
        	
        	//编辑完毕
        	function commit(editor){
        		var viewer = editor.hide().prev();
        		var text = editor.children().first().val();
        		viewer.children().first().text(text);
        		viewer.fadeIn(500);
        		var urlID = $(this).closest("tr").find(".selectSingle").prop("value");
        		$.post("<s:url namespace='/ajax' action='updateUrlTitle' />",
    					{"urlID": urlID, "urlTitle": text},
    					function(data, status){
    						if(!data["success"]) alert(data["errorMsg"]);
    					});
        	}
        	$(".icon-ok").click(function(){
        		commit($(this).parent());
        		});
        	
        	//取消编辑
        	function cancel(editor) {
        		editor.hide().prev().fadeIn(500);
        	}
        	$(".icon-cancel").click(function(){
        		cancel($(this).parent());
        	});
        	
        	//编辑快捷键（回车提交/Esc取消）
        	$(".editor input").keydown(function(e){
        		if(e.keyCode == 13) commit($(this).parent());
        		else if(e.keyCode == 27) cancel($(this).parent());
        	});
        	
        });
        </script>
    </body>
</html>