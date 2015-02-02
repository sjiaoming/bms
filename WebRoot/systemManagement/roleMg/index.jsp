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
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=path %>/"/>
<!--框架必需end-->

<!--截取文字start-->
<script type="text/javascript" src="<%=path %>/js/text/text-overflow.js"></script>
<!--截取文字end-->
<script>
	$(document).ready(function(){
		var s = document.getElementById("cflag").value;
		if(s!=""&&s=="addSuccess"){
			top.Dialog.close();
			alert("操作成功");
		}
	})
	
	$(function(){
		var parentMainHeight = window.parent.document.documentElement.clientHeight;
		var flexiGridHeight=parentMainHeight-parentTopHeight-parentBottomHeight-fixHeight-45;
		//定义flexiGridHeight是为了让表格自适应页面高度，如果不需要可以将下面的height设为一个具体数值
		/* $('.flexiStyle').flexigrid({
			height:parentMainHeight * 0.65,
			resizable: false,
			showToggleBtn: false
			});	 */
		//$('.tableStyle').height = parentMainHeight * 0.65;
	})
	
	function myTest(){
		setTimeout("pageForword()",2000)
	}
	
	function pageForword(){
		
		this.form1.submit();
	}
	function search(){
		
		//var name = document.form1.name.value;
		//alert(name);
		this.form1.action = "role.action";
		this.form1.submit();
		return;
	}
	function toNext(url){
		
		//var name = document.form1.name.value;
		//alert(name);
		this.form1.action = url;
		this.form1.submit();
		return;
	}
	function openWin(roleid){
		//alert(roleid);
		var diag = new top.Dialog();
		diag.Title = "授权窗口";
		diag.URL = "acl.action?principalType=Role&principalSn="+roleid;
		diag.Height=480;
		diag.MessageTitle="角色授权";
		diag.Message="角色授权明细";
		diag.CancelEvent = function(){
			diag.innerFrame.contentWindow.location.reload();
			diag.close();
			};
		diag.show();
	}
	function del(roleid){
	
	this.form1.action = "del_role.action?id="+roleid;
	this.form1.submit();
	return;
	//window.opener.location.reload();
	}
	function openWin1(){
		//alert('--')
		var diag = new top.Dialog();
		diag.Title = "添加角色信息";
		diag.URL = "systemManagement/roleMg/add_input.jsp";
		diag.Height=140;
		diag.MessageTitle="添加角色信息";
		diag.Message="添加角色信息明细";
		diag.CancelEvent = function(){
			//diag.innerFrame.contentWindow.location.reload();
			window.location.href = "role.action";
			diag.close();
			};
		diag.show();
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
	<input type="hidden" name="cflag" id="cflag" value="${cflag }" />
		<table>
			<tr>
				
				<td>角色名称：</td>
				<td><input type="text" name="name" value="${params.name }"/></td>
				<td><input type="button" value="新建"  onclick="openWin1();"/></td>
				<td><button  onclick="search();"><span class="icon_find">查询</span></button></td>
			</tr>
		</table>
		</form>
	</div>



<div id="scrollContent" class="border_gray">
	<table class="tableStyle" >
		<tr>
			<th width="10%">序号</th>
			<th width="10%">角色名称</th>
			<th width="10%">操作</th>
		</tr>
		<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="role">  
		<tr bgcolor="">
			<td >${role.id }</td>
			<td >${role.name }</td>
			<td><span class="img_view hand" title="授权" onclick="openWin(${role.id });"></span>
			<!-- <span class="img_edit hand" title="修改"></span> -->
			<span class="img_delete hand" title="删除"  onclick='top.Dialog.confirm("您确定要删除角色？",function(){del(${role.id })});'></span></td>
		</tr>
		</c:forEach>
		</c:if>


	</table>
</div>

<div style="height:35px;">
	<pg:pager items="${pm.total }" url="role.action" export="currentPageNumber=pageNumber" maxPageItems="15">
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
