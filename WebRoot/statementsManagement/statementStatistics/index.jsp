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
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path %>/js/form/multiselect.js"></script>
<script>
	$(document).ready(function(){
	top.Dialog.close();
	})
	
	
	/* function myTest(){
		alert('1');
		setTimeout("search()",2000);
	}
	
	function pageForword(){
		
		this.form1.submit();
	}  */
	function search(){
		this.form1.action = "getSave.action";
		this.form1.submit();
		return;
	}
	function ok(){
		
		var access=false;
		var s = document.getElementById("sDate").value;
		var e = document.getElementById("eDate").value;
		if(s==null || s == "" || e==null || e==""){
			top.Dialog.alert("统计日期必填");
			access = false;
		}else{
			access = true;
		}
		//access=$('#form1').validationEngine({returnIsValid:true});
		//alert(access);
		return access;
	}
	function newWin(){
		if(ok()){
		var s = document.getElementById("sDate").value;
		var e = document.getElementById("eDate").value;
		var dd = document.getElementById("dd").value;
		window.open('getSave.action?sDate='+s+'&&eDate='+e+'&&flag=y&&orgId='+dd,'','toolbar=no, width=900,height=500,top=0,left=0, status=no, menubar=no, resizable=no, scrollbars=no');
		}
		}
</script>
<!--多功能表格end-->
<style>

body{
	line-height:150%;
}
h1 {font-size:15px;}
h2 {font-size:20px;}
p {font-size:14px;}
</style>

<body scroll=yes>
	<div class="box2" panelTitle="功能面板  "  roller="true">
	<form id="form1" name="form1" action="getSave.action"  method="post" onsubmit="return ok()">
	<input type="hidden" id="path" value="<%=path %>">
		<table useClick="false">
			<tr>
				<td>
                       <span class="star">*</span><input  id="sDate" name="sDate"  value="${sDate }" class="date" />
				</td>
				<td>到：</td>
				<td>
                       <span class="star">*</span> <input  id="eDate" name="eDate"  value="${eDate }" class="date"/>
				</td>
				<td>部门:</td>
				<td><select id="dd" name="orgId">
						<option value="0">请选择</option>
						<c:if test="${!empty sessionOrgs}">
						 	<c:forEach items="${sessionOrgs }" var="organization">  
								<option value="${organization.id}" <c:if test="${organization.id == orgId}">selected="selected"</c:if> >${organization.name }</option>					 
							</c:forEach>
						</c:if>
					</select></td>
				<%-- <td>计划提报单位：</td>
				<td>
					<select  name="useCompId" id="useCompId"  style="width:100px;" multiple >
						<c:if test="${empty useCompId }">
						<option value="" selected="selected" >请选择</option>
						</c:if>
						<c:if test="${!empty useCompId }">
						<option value="" >请选择</option>
						</c:if>
						<c:if test="${!empty sessionUseComps}">
						 	<c:forEach items="${sessionUseComps }" var="uc">
								<option value="${uc.id}" <c:if test="${uc.id == useCompId}">selected="selected"</c:if> rel="exclusive" >${uc.name }</option>					 
							</c:forEach>
						</c:if>
						<option value="0" rel="headernocb"></option>
					 </select>
				</td> --%>
				<td><input type="submit" value="统计"/></td>
				<td>
				<!-- <input type="button" value="打印预览" onclick="newWin()"></input> -->
				<button type="button"  onclick='top.Dialog.confirm("您确定要打印预览？",function(){newWin()});' ><span class="icon_print">打印预览</span></button>
				</td>
			</tr>
			
		</table>
		</form>
	</div>
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
		
	</table>
</body>
