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
<script type="text/javascript" src="<%=path%>/js/form/multiselect.js"></script>
<!--框架必需end-->
<!--截取文字start-->
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->
<script type="text/javascript" src="<%=path %>/js/Calendar3.js"></script>
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>

<script language="javascript">
function ok(){
		var access=false;
		access=$('#form1').validationEngine({returnIsValid:true});
		if(access){
			if(confirm("您确定要提交吗?")){
				this.form1.action = "doSeekSourceD.action";
				this.form1.submit();
				//top.Dialog.close();
				return;
			}
		}
		//return access;
	}

</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="tags" value="<%=request.getParameter("ssids") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="868" panelheight="558">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="5">寻源审核明细</th>
		</tr>
		<tr>
			<th width="180">物料描述</th>
			<th width="180">供应商</th>
			<th width="80">寻源数量</th>
			<th width="80">寻源单价</th>
			<th width="80">寻源金额</th>
		</tr>
		<c:forEach items="${seekSource.details }" varStatus="status" var="detail">
		<tr>
			<td style="word-wrap: break-word;word-break:break-all;">${detail.materialName }</td>
			<td width="80">${detail.supplier.name }</td>
			<td width="80">${detail.seekSourceQuantity }</td>
			<td width="80">${detail.seekSourcePrice }</td>
			<td width="80">${detail.seekSourceM }</td>
		</tr>
		</c:forEach>
	</table>
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="3">寻源审核记录</th>
		</tr>
		<tr>
			<th width="80">审核人</th>
			<th width="180">审核状态</th>
			<th width="180">审核日期</th>
		</tr>
		<c:forEach items="${seekSource.checks }" varStatus="status" var="check">
			<tr>
				<td>${check.person.name }</td>
					<td>
						<c:if test="${check.checkStatus==01 }">主管末审核</c:if>
						<c:if test="${check.checkStatus==02 }">主管末通过审核</c:if>
						<c:if test="${check.checkStatus==03 }">经理末审核</c:if>
						<c:if test="${check.checkStatus==04 }">经理末通过审核</c:if>
						<c:if test="${check.checkStatus==05}">经理已审核</c:if>
					</td>
					<td>${check.checkDate }</td>
				</tr>
		</c:forEach>
	</table>
	</div>
</form>
</body>