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
<script language="javascript">
$(function(){
	var s = document.getElementById("cflag").value;
	if(s!=""&&s=="addSuccess"){
	top.Dialog.close();
	}
});
function getCheckBoxLine(){
	var msg="";
	$("#checkboxTable input[type=checkbox]").each(function(){
		if($(this).attr("checked")){
			msg=msg+","+$(this).val();
		}
	})
	/* if(msg==""){
		msg="无选择"
	}
	top.Dialog.alert(msg); */
	return msg;
}
function ok(){
	var aid = getCheckBoxLine();
	if(aid==""){
		top.Dialog.alert("请选择到货批次");
		return;
	}
	if(confirm("确定开票？")){
		document.getElementById("baid").value = aid;
		this.form1.action = "addBillingDetail.action";
		this.form1.submit();
		//top.Dialog.close();
		return;
	}
	
	
}
function delbill(aid){
	if(confirm("确定要删除开票信息？")){
	document.getElementById("aid").value = aid;
	this.form1.action = "delBillingDetail.action";
	this.form1.submit();
	return;
	}
}
function openWin1(aid,arrivalNum){
	var salesContractId = document.getElementById("salesContractId").value;
	var billingId = document.getElementById("id").value;
	var diag = new top.Dialog();
	diag.Title = "到货信息拆分";
	diag.URL = "businessManagement/salesContractMg/cf_arrivalItems.jsp?aid="+aid+"&id="+billingId+"&arrivalNum="+arrivalNum+"&scid="+salesContractId;
	diag.Height=300;
	diag.Width = 260;
	diag.MessageTitle="到货信息拆分";
	diag.Message="到货信息拆分";
	diag.CancelEvent = function(){
		window.location.href = "toAddBillingByArrival.action?scid="+salesContractId+"&id="+billingId;
		diag.close();
	};
	diag.show();
}
</script>

<body>
<form action="addBillingDetail.action" id="form1" name="form1" >
	<input type="hidden" name="id" id="id" value="${id }" />
	<input type="hidden" name="baid" id="baid" value="" />
	<input type="hidden" name="aid" id="aid" value="" />
	<input type="hidden" name="cflag" id="cflag" value="${cflag }" />
	<input type="hidden" name="sbillingDate" id="sbillingDate" value="<fmt:formatDate value="${sbillingDate }" pattern="yyyyMMdd"/>" />
	<input type="hidden" id="salesContractId" name="salesContractId" value="${salesContract.id }" />
	<div class="box2"  panelTitle="当前开票信息" showStatus="false" roller="true">
	<table id="tab0" class="tableStyle" >
		<tr>	
				<th style="width:40px">计划编码</th>
				<th style="width:205px">物资名称</th>
				<th style="width:150px">到货数量</th>
				<th style="width:150px">到货时间</th>
				<th style="width:150px">验收数量</th>
				<th style="width:150px">验收时间</th>
				<th style="width:150px">开票数量</th>
				<th style="width:150px">开票金额</th>
				<th style="width:150px">开票日期</th>
				<th style="width:150px">操作</th>
		</tr>
		
		<c:if test="${!empty na}">
			
			<c:forEach items="${na }" var="na">
			<tr>
				<td>${na.plan.planNum }</td>
				<td><span class="text_slice" style="width:150px;" title="${na.plan.itemsName }">${na.plan.itemsName }</span></td>
				<td>${na.arrivalNum }</td>
				<td><fmt:formatDate value="${na.arrivalDate }" pattern="yyyy-MM-dd"/></td>
				<td>${na.acceptanceNum }</td>
				<td><fmt:formatDate value="${na.acceptanceDate }" pattern="yyyy-MM-dd"/></td>
				<td>${na.billingNum }</td>
				<td><fmt:formatNumber value="${na.billingMoney }" type="currency"/></td>
				<td><fmt:formatDate value="${na.billingDate }" pattern="yyyy-MM-dd"/></td>
				<td><span class="img_delete hand" title="移除" onclick='delbill(${na.id})'></span></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	</div>
	<div class="box2"  panelTitle="未开票信息" showStatus="false" roller="true">
	<table class="tableStyle" id="checkboxTable">
		<tr>	
				<th style="width:70px"></th>
				<th style="width:70px">计划编码</th>
				<th style="width:205px">物资名称</th>
				<th style="width:150px">到货数量</th>
				<th style="width:150px">到货金额</th>
				<th style="width:150px">到货时间</th>
				<th style="width:150px">验收数量</th>
				<th style="width:150px">验收金额</th>
				<th style="width:150px">验收时间</th>
				<th style="width:150px">已开票数量</th>
				<th style="width:150px">开票数量</th>
		</tr>
		
		<c:if test="${!empty al}">
		
			<c:forEach items="${al }" var="l">
			<tr>
				<td><input type="checkbox"  value="${l.id }"/></td>
				<td>${l.plan.planNum }</td>
				<td><span class="text_slice" style="width:150px;" title="${l.plan.itemsName }">${l.plan.itemsName }</span></td>
				<td>${l.arrivalNum }</td>
				<td><fmt:formatNumber value="${l.arrivalNum * l.plan.salesPrice }" type="currency"/></td>
				<td><fmt:formatDate value="${l.arrivalDate }" pattern="yyyy-MM-dd"/></td>
				<td>${l.acceptanceNum }</td>
				<td><fmt:formatNumber value="${l.acceptanceNum * l.plan.salesPrice }" type="currency"/></td>
				<td><fmt:formatDate value="${l.acceptanceDate }" pattern="yyyy-MM-dd"/></td>
				<td>${l.billingNum }</td>
				<td><input id="billingNum_${l.id }" name="billingNum_${l.id }"  value="${l.arrivalNum - l.billingNum }"  ></input></td>
				</tr>
			</c:forEach>
		
		</c:if>
	</table>
	<table class="tableStyle" transMode="true">
		<tr>
			<td colspan="4">
			<input type="button" value="保存" onclick="ok()"/>
				<!-- <input type="submit" value="提交" /> -->
			</td>
		</tr>
	</table>
	
	</div>

</form>
</body>