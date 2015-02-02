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
<link rel="stylesheet" type="text/css" href="../../js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="../../js/table/flexigrid/flexigrid.js"></script>
<script language="javascript">

$(function(){
	
	var s = document.getElementById("cflag").value;
	//alert(s);
	if(s!=""&&s=="addSuccess"){
	//alert("addSuccess");
		top.Dialog.close();
	}
	
})

function open6(userid){
	var diag = new top.Dialog();
	diag.Title = "手动关闭窗口并刷新页面";
	diag.URL = "toUserRole.action?id="+userid;
	diag.ShowButtonRow=false;
	//diag.OkButtonText=" 确 定 ";
	//diag.OKEvent = function(){
	//	top.Dialog.alert("成功提交",function(){window.location.reload()})
	//	diag.close();
	//};
	diag.CancelEvent = function(){
	window.location.href = "userRole.action?id="+userid;
		//window.location.reload();
		//diag.innerFrame.contentWindow.location.reload();
		//top.Dialog.close();
		diag.close();
	};
	diag.show();
}
function del(userid,roleid){
	
	this.form1.action = "delxx.action?id="+userid+"&roleId="+roleid;
	this.form1.submit();
	return;
	//alert('1');
	//window.location.reload();
}
</script>
<body>
<form id="form1" name="form1" action="#"  method="post">
<div id="scrollContent">
	<input type="hidden" id="cflag" name="cflag" value="${cflag }"/>
	<div class="box1" panelWidth="540">
	<input type="button" value="添加角色" onclick='open6(${user.id});'/>
	<fieldset>
	<legend>已拥有的角色</legend>
	<table class="tableStyle" >
		<thead>
		<tr>
			<th width="10%">角色名称</th>
			<th width="10%">优先级</th>
			<th width="10%">操作</th>
		</tr>
		</thead>
		<tbody>
	 <c:if test="${!empty urs}">
        <c:forEach items="${urs }" var="ur">
		<tr>
			  <td>${ur.role.name }</td>
	          <td>${ur.orderNum }</td>
	          <td> <span class="img_delete hand" title="删除"  onclick='top.Dialog.confirm("您确定要删除角色？",function(){del(${ur.user.id },${ur.role.id})});'/></td> 
		</tr>
		</c:forEach>
	</c:if>
	 <c:if test="${empty urs}">
	    <tr>
	    	<td>
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </c:if>
	</tbody>
	</table>
	
	</fieldset>
	</div>
</div>
</form>
</body>