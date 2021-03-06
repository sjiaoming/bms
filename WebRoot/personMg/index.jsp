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
<script type='text/javascript' src='dwr/interface/aclManager.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<script>
	$(document).ready(function(){
	top.Dialog.close();
	var s = document.getElementById("cflag").value;
	if(s!=""&&s=="addSuccess"){
		//action ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		alert("操作成功");
	}
	})
	
	
	function myTest(){
		setTimeout("pageForword()",2000)
	}
	
	function pageForword(){
		
		this.form1.submit();
	}
	function search(){
		this.form1.action = "person.action";
		this.form1.submit();
		return;
	}
function del(personid){
	
	this.form1.action = "delPerson.action?id="+personid;
	this.form1.submit();
	return;
}
function openWin(){
		var diag = new top.Dialog();
		diag.Title = "添加人员信息";
		diag.URL = "initAddPerson.action";//systemManagement/personMg/add_input.jsp";
		diag.Height=480;
		diag.MessageTitle="添加人员信息";
		diag.Message="添加人员信息明细";
		diag.CancelEvent = function(){
			window.location.href = "person.action";
			diag.close();
			};
		//diag.CancelEvent = function(){
		//window.location.reload();
		//window.location.href = "person.action";
		//diag.close();
		//};
		diag.show();
	}
	function openWin1(personId){
		var diag = new top.Dialog();
		diag.Title = "修改人员信息";
		diag.URL ="modify_person_input.action?personId="+personId;
		diag.Height=480;
		diag.MessageTitle="添加人员信息";
		diag.Message="添加人员信息明细";
		diag.CancelEvent = function(){
			window.location.href = "person.action";
			diag.close();
			};
		//diag.CancelEvent = function(){
		//window.location.reload();
		//window.location.href = "person.action";
		//diag.close();
		//};
		diag.show();
	}
	
function toNext(url){
		this.form1.action = url;
		this.form1.submit();
		return;
	}
</script>
<!--多功能表格end-->
<style>
body{
	line-height:150%;
}
</style>

<body>
	<div class="box2" panelTitle="功能面板  " roller="false">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" id="path" value="<%=path %>">
	<input type="hidden" name="cflag" id="cflag" value="${cflag }" />
		<table>
			<tr>
				<td>部门:</td>
				<td>
					<select id="dd" name="orgId">
						<option value="0">请选择</option>
						<c:if test="${!empty sessionOrgs}">
						 	<c:forEach items="${sessionOrgs }" var="organization">  
								<option value="${organization.id}" <c:if test="${organization.id == params.id_org}">selected="selected"</c:if> >${organization.name }</option>					 
							</c:forEach>
						</c:if>
					</select>
				</td>
				<td>姓名：</td>
				<td><input type="text" name="name"  value="${params.name }"/></td>
				<c:if test="${my:method(login.id,'personmgr',0)}">
					<td><input type="button" value="新建"   onclick="openWin();"/></td>
					</c:if>
				<c:if test="${my:method(login.id,'personmgr',1)}">
				<td><button  onclick="search();"><span class="icon_find">查询</span></button></td>
				</c:if>
			</tr>
		</table>
		</form>
	</div>



<div id="scrollContent" class="border_gray">
	<table class="tableStyle">
		<tr>
			<th width="5%">序号</th>
			<th width="10%">姓名</th>
			<th width="5%">性别</th>
			<th width="10%">职位</th>
			<th width="15%">职能组</th>
			<th width="15%">联系方式</th>
			<th width="15%">邮箱</th>
			<th width="10%">所在部门</th>
			<th width="15%">备注</th>
			<th width="10%">操作</th>
		</tr>
		<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="person">  
		<tr bgcolor="">
			<td >${person.id }</td>
			<td >${person.name }</td>
			<td >${person.sex }</td>
			<td >${person.duty }</td>
			<td >${person.planGroup.groupName }${person.seekSourceGroup.groupName }</td>
			<td >${person.phone }</td>
			<td >${person.email }</td>
			<td >${person.org.name }</td>
			<td >${person.description }</td>
			<td>
			<c:if test="${my:method(login.id,'personmgr',2)}">
			<span class="img_edit hand" title="修改" onclick="openWin1(${person.id });"></span>
			</c:if>
			<c:if test="${my:method(login.id,'personmgr',3)}">
				<span class="img_delete hand" title="删除" onclick='top.Dialog.confirm("您确定要删除人员？",function(){del(${person.id })});'></span>
			</c:if>
			</td>
		</tr>
		</c:forEach>
		</c:if>


	</table>
</div>

<div style="height:35px;">
	<pg:pager items="${pm.total }" url="person.action" export="currentPageNumber=pageNumber" maxPageItems="15">
	<div class="float_right padding5 paging">
		<div class="float_left padding_top4">
		<c:choose>
			<c:when test="${ currentPageNumber == 1}">
				<span class="paging_disabled"><a href="javascript:;">首页</a></span>
			</c:when>
			<c:otherwise>
				<span ><pg:first><a href="#" onclick="toNext('${pageUrl}')">首页</a></pg:first></span>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ currentPageNumber > 1}">
				<span ><pg:prev><a href="#" onclick="toNext('${pageUrl}')">上一页</a></pg:prev></span>
			</c:when>
			<c:otherwise>
				<span class="paging_disabled"><a href="javascript:;">上一页</a></span>
			</c:otherwise>
		</c:choose>
		
			<pg:pages>
				<c:choose>
					<c:when test="${ currentPageNumber eq pageNumber}">
					<span class="paging_current"><a href="javascript:;">${pageNumber}</a></span>
					</c:when>
					<c:otherwise>
					<span><a href="#" onclick="toNext('${pageUrl}')">${pageNumber }</a></span>	
					</c:otherwise>
				</c:choose>
			</pg:pages>
			
		<span><pg:next><a href="#" onclick="toNext('${pageUrl}')">下页</a></pg:next></span>
		<span><pg:last><a href="#" onclick="toNext('${pageUrl}')">尾页</a></pg:last></span>
		<div class="clear"></div>
	</div>
	</pg:pager>
	<div class="clear"></div>
</div>
		
</body>
