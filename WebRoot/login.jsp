<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>神华物资集团台帐管理系统</title>
<link href="login/skin1/style.css" rel="stylesheet" type="text/css" id="skin"/>
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/login.js"></script>

<!--引入弹窗组件start-->
<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
<!--引入弹窗组件end-->

<!--修正IE6支持透明png图片start-->
<script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
<!--修正IE6支持透明png图片end-->

<!--居中显示start-->
<script type="text/javascript" src="js/method/center-plugin.js"></script>
<script>
	var broswerFlag;
	var msg1 = "";
	$(function(){
		 $('.login_main').center();
		 var msg = this.form1.msg.value;
		 if(msg!=null && msg!=""){
			 $("#logmsg").css({
					"display":"inline"
				});
		 }
		 document.getElementById("msg").value = "";
	})
	$(function() {
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		var g = window.navigator.userAgent.substring(30, 33);
		if (g == "6.0") {
			broswerFlag = "IE6"
		} else {
			if (g == "7.0") {
				broswerFlag = "IE7"
			} else {
				if (g == "8.0") {
					broswerFlag = "IE8"
				} else {
					if (g == "9.0") {
						broswerFlag = "IE9"
					}
				}
			}
		}
	} else {
		if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
			broswerFlag = "Firefox"
		} else {
			if (window.navigator.userAgent.indexOf("Opera") >= 0) {
				broswerFlag = "Opera"
			} else {
				if (window.navigator.userAgent.indexOf("Safari") >= 1) {
					broswerFlag = "Safari"
				} else {
					broswerFlag = "Other"
				}
			}
		}
	}
	if (broswerFlag != "IE9") {
		msg1 = "系统不支持您的"+broswerFlag+"浏览器，为了您的正常使用，建议您下载最新版IE9";
		top.Dialog.alert(msg1);
		$("#download").css({
			"display":"inline"
		});
	}
	
	})
</script>
<!--居中显示end-->
<style>
/*提示信息*/	
#cursorMessageDiv {
	position: absolute;
	z-index: 99999;
	border: solid 1px #cc9933;
	background: #ffffcc;
	padding: 2px;
	margin: 0px;
	display: none;
	line-height:150%;
}
/*提示信息*/
</style>
</head>
<body >

	<div class="login_main">
					
		<div class="login_top">
			<div class="login_title"></div>
		</div>
		
		<div class="login_middle">
			<div class="login_middleleft"></div>
			<div class="login_middlecenter">
					<div id="download" style="display:none;">
					<a href='javascript:window.open("http://windows.microsoft.com/zh-CN/internet-explorer/downloads/ie-9/worldwide-languages")' title="点击进入下载">
					<b>IE9中文简体官网下载</b></a>
					</div>
					<form action="login.action" class="login_form" method="post" name="form1">
					<input type="hidden" name="msg" id="msg" value="${msg }">
					
					<div class="login_user"><input type="text" name=username></div>
					<div class="login_pass"><input type="password" name=password></div>
					<div class="clear"></div>
					<div class="login_button">
						<div class="login_button_left"><input type="submit" value="" onfocus="this.blur()"/></div>
						<div class="login_button_right"><input type="reset" value="" onfocus="this.blur()"/></div>
						<div class="clear"></div>
					</div>
					</form>
					<div class="login_info" id="logmsg" style="display:none;">您的用户名密码有误，请重新输入。</div>
					
			</div>
			<div class="login_middleright"></div>
			<div class="clear"></div>
		</div>
		<div class="login_bottom">
			<div class="login_copyright">神华物资集团台帐管理系统</div>
		</div>
	</div>

</body>
</html>
