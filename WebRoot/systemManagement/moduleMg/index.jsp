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

<!--截取文字start-->
<script type="text/javascript" src="<%=path %>/js/text/text-overflow.js"></script>
<!--截取文字end-->
<!--多功能表格start-->
<link rel="stylesheet" type="text/css" href="<%=path %>./js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="<%=path %>/js/table/flexigrid/flexigrid.js"></script>


<script>
	$(document).ready(function(){
		top.Dialog.close();
		var s = document.getElementById("cflag").value;
		if(s!=""&&s=="success"){
			//action ServletActionContext.getRequest().setAttribute("cflag", "success");
			alert("操作成功");
		}
	})
	
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
	
	function myTest(){
		setTimeout("pageForword()",2000)
	}
	
	function pageForword(){		
		this.form1.submit();
	}
	function search(){
		this.form1.action = "module.action";
		this.form1.submit();
		return;
	}

	function openWin(moduleId,moduleName){
		var diag = new top.Dialog();
		diag.Title = "添加模块窗口";
		diag.URL = "systemManagement/moduleMg/add_input.jsp?moduleId="+moduleId+"&moduleName="+moduleName;
		diag.Height=480;
		diag.MessageTitle="添加模块";
		diag.Message="添加模块明细";
		diag.CancelEvent = function(){
			window.location.href = "module.action";
			diag.close();
			};
		diag.show();
	}
	function openWin1(moduleId){
		//alert(name);
		//alert(userid);
		var diag = new top.Dialog();
		diag.Title = "模块修改";
		diag.URL = "modify_module_input.action?moduleId="+moduleId;
		diag.Height=380;
		diag.MessageTitle="模块信息";
		diag.Message="模块详细信息";
		diag.CancelEvent = function(){
			window.location.href = "module.action";
			diag.close();
			};
		diag.show();
	}
	function openWin2(personid){
		//alert(personid);
		var diag = new top.Dialog();
		diag.Title = "分配帐号";
		diag.URL = "systemManagement/userMg/add_input.jsp?personid="+personid;
		diag.Height=380;
		diag.MessageTitle="帐号信息";
		diag.Message="帐号详细信息";
		diag.CancelEvent = function(){
			window.location.href = "module.action";
			diag.close();
			};
		diag.show();
	}
	function del(moduleId){
		//moduleManager.deleteModule(moduleid);
		this.form1.action = "del_module.action?id="+moduleId;
		this.form1.submit();
	}
function toNext(url){
		
		//var name = document.form1.name.value;
		//alert(name);
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
<!--onload="return findModules(${empty modules})"-->
	<div class="box2" panelTitle="功能面板  " roller="false">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" name="cflag" id="cflag" value="${cflag }" />
		<table>
			<tr>
				<td>模块:</td>
				<td>
					<select id="dd" name="moduleId">
						<option value="0">请选择</option>
						<c:if test="${!empty modules}">
						 	<c:forEach items="${modules }" var="module">  
							<option value="${module.id}" <c:if test="${module.id == params.id_id}">selected="selected"</c:if> >${module.name }</option>					 
							</c:forEach>
						</c:if>
					</select>
					
				</td>
				
				<td>模块名称：</td>
				<td><input type="text" name="name" value="${params.name }"/></td>
				<td><button  onclick="search();"><span class="icon_find">查询</span></button></td>
				<td><input type="button" onclick="openWin(0,'0')" value="添加模块" /></td>
			</tr>
			
		</table>
		</form>
	</div>



<div id="scrollContent" class="border_gray">
	<table class="tableStyle">
		<tr>
			<th width="5%">序号</th>
			<th width="16%">模块名称</th>
			<th width="16%">模块编号</th>
			<th width="16%">模块地址</th>
			<th width="16%">模块序号</th>
			<th width="16%">父模块</th>
			<th width="15%">操作</th>
		</tr>
		<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="module">  
		<tr bgcolor="">
			<td >${module.id }</td>
			<td >${module.name }</td>
			<td >${module.sn }</td>
			<td >${module.url }</td>
			<td >${module.orderNum }</td>
			<td >${module.parent.name }</td>
			<td>
			<c:if test="${empty module.parent}">
			<span class="img_view hand"  title="添加" onclick="openWin(${module.id},'${module.name}');"></span>
			</c:if>
			<span class="img_edit hand" title="修改" onclick="openWin1(${module.id});"></span>
			<span class="img_delete hand" title="删除帐号"  onclick='top.Dialog.confirm("您确定要删除登录帐号？",function(){del(${module.id })} );'></span>
			</td>
		</tr>
		</c:forEach>
		</c:if>


	</table>
</div>

<div style="height:35px;">
	<pg:pager items="${pm.total }" url="module.action" export="currentPageNumber=pageNumber" maxPageItems="15">
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
