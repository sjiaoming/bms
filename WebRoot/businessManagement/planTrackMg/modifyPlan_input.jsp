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
<script type="text/javascript" src="<%=path %>/js/Calendar3.js"></script>

<script language="javascript">
function ok(){
		var access=true;
		var remark = document.getElementById("procurementRemark");
		if(remark.value==null || remark.value==""){
			if(document.getElementById("pw").value != document.getElementById("procurementWay").value){
				top.Dialog.alert("请填写修改理由");
				access = false;
			}
		}
		if(access){
			this.form1.action = "doModifyPlan.action";
			this.form1.submit();
			return;
		}
	}
</script>

<body>
<form action="?" id="form1" name="form1">
<input type="hidden" name="planId" value="<%=request.getParameter("planId")%>"/>
<input type="hidden" name="plan.id" value="${plan.id }"/>
<input type="hidden" id="pw" name="pw" value="${plan.procurementWay }"/>
<input type="hidden" id="pd" name="pd" value="<fmt:formatDate value="${plan.procurementDate }" pattern="yyyy-MM-dd"/>"/>
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="500">
	<table class="tableStyle" formMode="true">
		<tr>
			<td>序号:</td><td >${plan.id }</td>
		</tr>
		<tr>
			<td>购物车编号:</td><td >${plan.oldPlanNum }</td>
		</tr>
		<tr>
			<td>物料描述:</td><td >${plan.itemsName }</td>
		</tr>
		<tr>
			<td>提报单位:</td><td >${plan.reportComp }</td>
		</tr>
		
		<tr>
			<td>采购方案</td>
			<td >
			<select  name="plan.procurementWay" id="procurementWay"  >
				    <option value="1" <c:if test="${plan.procurementWay == '1' }">selected="selected"</c:if>>公开招标</option>
				    <option value="2" <c:if test="${plan.procurementWay == '2' }">selected="selected"</c:if>>邀请招标</option>
				    <option value="3" <c:if test="${plan.procurementWay == '3' }">selected="selected"</c:if>>竞争性谈判</option>
				    <option value="4" <c:if test="${plan.procurementWay == '4' }">selected="selected"</c:if>>单一来源</option>
				    <option value="5" <c:if test="${plan.procurementWay == '5' }">selected="selected"</c:if>>寻比价</option>
			</select>
			
			</td>
		</tr>
		<tr>
			<td>采购方案审批通过日期</td><td >
                         <input  id="procurementDate" name="plan.procurementDate"  value="<fmt:formatDate value="${plan.procurementDate }" pattern="yyyy-MM-dd"/>" class="date"/>
               </td>
		</tr>
		<tr>
			<td>采购方案备注</td><td ><textarea name="plan.procurementRemark" id="procurementRemark">${plan.procurementRemark }</textarea></td>
		</tr>	
		
		<tr>
			<td colspan="3">
				<input type="button"  onclick="ok()" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	</div>
</form>
</body>