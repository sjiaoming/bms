<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/common/common.jsp" %>
<jsp:useBean id="now" class="java.util.Date" /> 
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
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>

<script language="javascript">
function ok(){
		//alert("--");
		//var name = document.form1.name.value;
		//var dept = document.form1.dept.value;
		this.form1.action = "doChange.action";
		this.form1.submit();
		//top.Dialog.close();
		return;
	}
function getsum(){
	var changenum = document.getElementById("changenum").value;
	var planPrice = document.getElementById("planPrice").value;
	document.getElementById("changecontractMoney").value = Number(changenum).mul(Number(planPrice));
	var   txtRange   =   document.selection.createRange(); 
	txtRange.moveStart(   "character",   document.getElementById("changecontractMoney").value.length); 
	txtRange.moveEnd(   "character",   0   ); 
	txtRange.select(); 
}
</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="planId" value="<%=request.getParameter("planId")%>"/>
<input type="hidden" name="cflag" id="cflag" value="${cflag }" />

<div class="box2" panelWidth="650" panelTitle="变更管理 <fmt:formatDate value="${now}" pattern="yyyy-MM-dd hh:mm:ss"/>" showStatus="false" roller="true">
	<table class="tableStyle" transMode="true">
		<tr>
			<td width="10%" >物资名称：</td><td>${param.itemsName }</td>
			<td width="10%" >计划编号：</td><td>${param.planNum }</td>
			<td></td>
		</tr>
		<tr>
			<td width="10%">原数量：</td><td width="40%">${param.quantity }</td>
			<td>数量变更为：</td>
			<td><input type="text" id="changenum" name="changenum" value="${param.quantity }" onblur="getsum()"/></td>
		</tr>
		<tr>
			<td width="10%">原预算单价：</td><td width="40%"><fmt:formatNumber value="${param.budgetPrice }" pattern="#0.00"/></td>
			<td>单价变更为：</td>
			<td><input type="text" id="planPrice" name="planPrice" value="<fmt:formatNumber value="${param.budgetPrice }" pattern="#0.00"/>" onblur="getsum()"/></td>
		</tr>
		<tr>
			<td width="10%">原预算金额：</td><td width="40%"><fmt:formatNumber value="${param.budgetMoney }" pattern="#0.00"/></td>
			<td>预算金额变更为：</td>
			<td><input type="text"  id="changecontractMoney" name="changecontractMoney" value="<fmt:formatNumber value="${param.budgetMoney }" pattern="#0.00"/>" onfocus="getsum()"/></td>
		</tr>
		<!-- 
		<tr>
			<td>变更原因及备注：</td>
			<td colspan="3">
				<span class="float_left">
					<textarea name="changereason">${param.reason }</textarea>
				</span>
				<span class="img_light" style="height:80px;" title="限制在200字以内"></span>
			</td>
		</tr>
 		-->
		<tr>
			<td colspan="4">
				<input type="button"  onclick="ok()" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	</div>

</form>
</body>