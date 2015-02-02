<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="<%=path %>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path %>/js/framework.js"></script>
<link href="<%=path %>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin"  prePath="<%=path %>/"/>
<!--框架必需end-->


<script language="javascript">
function ok(){
		//alert("--");
		//var name = document.form1.name.value;
		//var dept = document.form1.dept.value;
		this.form1.action = "add_user.action";
		this.form1.submit();
		//top.Dialog.close();
		return;
	}

</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="personId" value="<%=Integer.parseInt(request.getParameter("personid"))%>">
<input type="hidden" name="userId" value="<%=Integer.parseInt(request.getParameter("userid"))%>">
<div class="box1" panelWidth="500">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="2">登录帐号信息</th>
		</tr>
		<tr>
			<td>用户帐号：</td><td><input type="text"  name="username" value="<%=request.getParameter("name")%>"/></td>
		</tr>
		<tr>
			<td>登录密码：</td><td><input type="text"  name="password" value="<%=request.getParameter("password")%>"/></td>
		</tr>
		<tr>
			<td>失效时间：</td><td><input type="text" class="date"  name="expireTime" value="<%=request.getParameter("expireTime")%>"/></td>
		</tr>

		
		<tr>
			<td colspan="2">
				<input type="button"  onclick="ok()" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	</div>
</form>
</body>