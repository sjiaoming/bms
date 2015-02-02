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
<!--截取文字start-->
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->
<!--多功能表格start-->
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>

<script type='text/javascript' src='<%=path %>/dwr/interface/loginparam.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>

<script language="javascript">
function ok(){
	var access=false;
	access=$('#form1').validationEngine({returnIsValid:true});
	
		var password = document.getElementById("password").value;
		var repassword = document.getElementById("repassword").value;
		if(password != repassword){
			top.Dialog.alert("两次密码不一致，请重新输入");
			access = false;
		}
	if(access){
		this.form1.action = "modify_myPwd.action";
		this.form1.submit();
		top.Dialog.close();
		return;
	}
}

</script>

<body >
<form action="" id="form1" name="form1" >

<div class="box1" panelWidth="500">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="2">密码修改</th>
		</tr>
		<tr>
			<td>新密码：</td><td><input type="text"  name="password" id="password" value=""  class="validate[required]" /></td>
		</tr>
		<tr>
			<td>再次输入密码：</td><td><input type="text"  name="repassword"  id="repassword" value=""  class="validate[required]" /></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button"  onclick="ok()" value=" 提 交 "/>
				<!-- <input type="submit" value="submit"/> -->
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	</div>
</form>
</body>