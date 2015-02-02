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

<script>
	$(function(){
		var parentMainHeight = window.parent.document.documentElement.clientHeight;
		var flexiGridHeight=parentMainHeight-parentTopHeight-parentBottomHeight-fixHeight-45;
		//定义flexiGridHeight是为了让表格自适应页面高度，如果不需要可以将下面的height设为一个具体数值
		$('.flexiStyle').flexigrid({
			height:flexiGridHeight,
			resizable: false,
			showToggleBtn: false
			});	
	})
	function preview(oper) { 
	 if (oper < 10){ 
	 bdhtml=window.document.body.innerHTML;//获取当前页的html代码 
	 
	 sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域 
	 eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域 
	 prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html 
	 
	 prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html 
	 window.document.body.innerHTML=prnhtml; 
	 window.print(); 
	 window.document.body.innerHTML=bdhtml; 
	 
	 }
	  else{ 
	 window.print(); 
	 } 
 
	 }
	 
	
</script>
<style>
body{
	line-height:50%;
}
h1 {font-size:15px;}
h2 {font-size:20px;}
p {font-size:14px;}
</style>

<body>
<!--startprint1-->
	<table class="tableStyle" useClick="false" >
		<tr>
		<td colspan="4" align="center">
		<h2>统计日期：${sDate } ~ ${eDate }</h2>
		</td>
		</tr>
		<tr>
			<td width="5%"><h1>已收到计划条数：</h1></td>
			<td width="35%"><h2>${ybPlanCount}</h2></td>
			<td width="5%"><h1>已收到计划金额：</h1></td>
			<td width="55%"><h2><fmt:formatNumber value='${(empty ybPlanMoney)?"0":ybPlanMoney  }' type="currency"/>  （元）</h2></td>
		</tr>
		<tr>
			<td><h1>已寻源条数：</h1></td>
			<td><h2>${xYCount }</h2></td>
			<td><h1>已寻源金额：</h1></td>
			<td><h2><fmt:formatNumber value='${(empty xYMoney)?"0":xYMoney }' type="currency"/>   （元）</h2></td>
		</tr>
		<tr>
			<td><h1>待寻源条数：</h1></td>
			<td><h2>${dXyCount }</h2></td>
			<td><h1>待寻源金额：</h1></td>
			<td><h2><fmt:formatNumber value='${(empty dXyMoney)?"0":dXyMoney }' type="currency"/>   （元）</h2></td>
		</tr>
		<tr>
			<td><h1>采购合同份数：</h1></td>
			<td><h2>${yqProcurementContract }</h2></td>
			<td><h1>采购合同金额：</h1></td>
			<td><h2><fmt:formatNumber value='${(empty yqProcurementMoney)?"0":yqProcurementMoney }' type="currency"/>   （元）</h2></td>
		</tr>
		<tr>
			<td><h1>挂帐金额：</h1></td>
			<td><h2><fmt:formatNumber value='${(empty creditMoney)?"0":creditMoney }' type="currency"/>   （元）</h2></td>
			<td><h1>已付金额：</h1></td>
			<td><h2><fmt:formatNumber value='${yfMoney}' type="currency"/>   （元）</h2></td>
		</tr>
		<tr>
			<td><h1>到货金额：</h1></td>
			<td colspan="3"><h2><fmt:formatNumber value='${(empty arrivalMoney)?"0":arrivalMoney }' type="currency"/>   （元）</h2></td>
		</tr>
		<tr>
			<td><h1>销售合同份数：</h1></td>
			<td><h2>${salesContractCount }</h2></td>
			<td><h1>销售合同金额：</h1></td>
			<td><h2><fmt:formatNumber value='${(empty SalesContractMoney)?"0":SalesContractMoney }' type="currency"/>   （元）</h2></td>
		</tr>
		<tr>
			<td><h1>开票金额：</h1></td>
			<td><h2><fmt:formatNumber value='${(empty billingMoney)?"0":billingMoney }' type="currency"/>   （元）</h2></td>
			<td><h1>已收金额：</h1></td>
			<td><h2><fmt:formatNumber value='${ysMoney }' type="currency"/>   （元）</h2></td>
		</tr>
		<tr>
			<td><h1>节资：</h1></td>
			<td><h2><fmt:formatNumber value='${(jz<0)?0:jz }' type="currency"/>   （元）</h2></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="4">
			</td>
		</tr>
	</table>
<!--endprint1-->
	<table>
		<tr><td><input type="button" value="打印" onclick="preview(1)" ></input></td></tr>
	</table>
	
</body>
