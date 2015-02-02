<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@include file="/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--框架必需start-->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
	<link href="<%=path%>/css/import_basic.css" rel="stylesheet"
		type="text/css" />
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" />
	<!--框架必需end-->

	<!--截取文字start-->
	<script type="text/javascript"
		src="<%=path%>/js/text/text-overflow.js"></script>
	<!--截取文字end-->

	<!--多功能表格start-->
	<link rel="stylesheet" type="text/css"
		href="<%=path%>/js/table/flexigrid/css/flexigrid/flexigrid.css">
	<script type="text/javascript"
		src="<%=path%>/js/table/flexigrid/flexigrid.js"></script>
	<!--多功能表格end-->
	<script language="javascript">
	
</script>

	<body>
		<form action="add_plan.action" id="form1" name="form1">
			<div class="box1" panelWidth="900">
			<table class="tableStyle" formMode="true">
				<tr>
					<td>您已经成功导入了 ${numb}行数据</td>
				</tr>
			</table>
			
			</div>
		</form>
	</body>