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
function ok(){
		

		document.form2.action = "add_userRole.action";
		document.form2.submit();
		//close();
		//alert("--");
		//return;
	}
function close(){
	top.Dialog.close();
}
function ook(){
	
	var self=this;
    setTimeout(function(){
    	this.form1.action = "add_userRole.action";
		this.form1.submit();
		//alert("--");
		top.Dialog.close();},1000);

}

</script>

<body>
<form action="＃" id="form2" name="form2" >
<input type="hidden" name="id" value="${id}">
<div id="scrollContent">
	
	<div class="box1" panelWidth="540">
	<fieldset>
	<legend>选择角色</legend>
	<table class="tableStyle" >
		<thead>
		<tr>
			<th width="10%">选择</th>
			<th width="10%">角色名称</th>
		</tr>
		</thead>
		<tbody>
	 <c:if test="${!empty pm.list}">
          <c:forEach items="${pm.list }" var="role">
		<tr>
			  <td><input type="radio" name="roleId" value="${role.id }"></td>
	          <td>${role.name }</td>
		</tr>
		</c:forEach>
	</c:if>
	  <c:if test="${empty pm.list}">
	    <tr>
	    	<td>
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </c:if>
		</tbody>
		<thead>
		<tr>
			<th width="10%">优先级</th>
			<th width="10%">--</th>
		</tr>
		</thead>
	</table>
	<tr>
			<td colspan="2">
			<button  onclick="ok()">提交</button>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</fieldset>
	</div>
</div>


</form>
</body>