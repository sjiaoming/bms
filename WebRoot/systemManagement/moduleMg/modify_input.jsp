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
<script type="text/javascript" src="<%=path %>/js/text/text-overflow.js"></script>
<!--截取文字end-->
<!--多功能表格start-->
<link rel="stylesheet" type="text/css" href="<%=path %>/js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="<%=path %>/js/table/flexigrid/flexigrid.js"></script>
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>
<script language="javascript">

$(document).ready(function(){
	var selectObject=document.getElementById('select_parent_name');
	selectObject.value=document.from1.parentId.value;
	})
	
function ok(){
		//alert("--");
		//var name = document.form1.name.value;
		//var dept = document.form1.dept.value;
		this.form1.action = "update_module.action";
		this.form1.submit();
		//top.Dialog.close();
		return;
	}

</script>

<body>
<form action="?" id="form1" name="form1">
<input type="hidden" name="parentId" value="${module.parent.id}">
<input type="hidden" name="moduleId1" value="${module.id}">
<div class="box1" panelWidth="500">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="2">修改模块信息</th>
		</tr>
		<tr>
			<td>模块名称:</td><td><input type="text"  name="name1" value="${module.name }"/></td>
		</tr>
		<tr>
			<td>模块编号：</td><td><input type="text"  name="sn" value="${module.sn }"/></td>
		</tr>
		<c:if test="${!empty module.parent}">
		<tr>
			<td>模块地址：</td><td><input type="text"  name="url"  value="${module.url }"/></td>
		</tr>
		<tr>
			<td>父模块：</td>
			<td>
				<select id="select_parent_name" name="modify_parentId" >
					<c:if test="${!empty parentModule}">
					<c:forEach items="${parentModule}" var="modules">
					<option value="${modules.id}"  <c:if test="${module.parent.id == modules.id}">selected="selected"</c:if> >${modules.name}</option>
					</c:forEach>
					</c:if>
				</select>
			
			</td>
		</tr>
		</c:if>
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