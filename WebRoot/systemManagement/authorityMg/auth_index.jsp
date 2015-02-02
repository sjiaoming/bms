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
<script type="text/javascript" src="../../js/jquery-1.4.js"></script>
<script type="text/javascript" src="../../js/framework.js"></script>
<link href="../../css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="../../"/>
<!--框架必需end-->

<!--截取文字start-->
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->
<!--多功能表格start-->
<link rel="stylesheet" type="text/css" href="../../js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="../../js/table/flexigrid/flexigrid.js"></script>
<script>
	$(document).ready(function(){
	top.Dialog.close();
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
		this.form1.action = "user.action?flag=auth_search";
		this.form1.submit();
		return;
	}
	function openWin(personid){
		//alert(personid);
		if('undefined' == personid || personid == null){
			top.Dialog.alert("必须先分配登录帐号");
			return;
		}
		var diag = new top.Dialog();
		diag.Title = "授权窗口";
		diag.URL = "acl.action?principalType=User&principalSn="+personid;
		diag.Height=480;
		diag.MessageTitle="用户授权";
		diag.Message="用户授权明细";
		diag.CancelEvent = function(){
			diag.innerFrame.contentWindow.location.reload();
			diag.close();
			};
		diag.show();
	}
	function openWin1(userid,name){
		//alert(name);
		//alert(userid);
		var diag = new top.Dialog();
		diag.Title = "分配角色";
		diag.URL = "userRole.action?id="+userid;
		diag.Height=380;
		diag.MessageTitle="用户名称";
		diag.Message=name;
		diag.CancelEvent = function(){
			//diag.innerFrame.contentWindow.location.reload();
			diag.close();
			};
		diag.show();
	}
	function openWin2(personid,userid,name,password,expireTime){
		var diag = new top.Dialog();
		diag.Title = "分配帐号";
		diag.URL = "systemManagement/userMg/add_input.jsp?personid="+personid+"&name="+name+"&password="+password+"&expireTime="+expireTime+"&userid="+userid;
		diag.Height=380;
		diag.MessageTitle="帐号信息";
		diag.Message="帐号详细信息";
		diag.CancelEvent = function(){
			diag.innerFrame.contentWindow.location.reload();
			diag.close();
			};
		diag.show();
	}
	function openWin3(personid){
		var diag = new top.Dialog();
		diag.Title = "数据权限";
		//diag.URL = "dataAcl.action?personid="+personid;
		diag.URL = "systemManagement/authorityMg/data_index.jsp?personId="+personid;
		diag.Height=480;
		diag.MessageTitle="数据授权";
		diag.Message="数据授权明细";
		diag.CancelEvent = function(){
			//diag.innerFrame.contentWindow.location.reload();
			diag.close();
			};
		diag.show();
	}
	function del(userid){
		if('undefined' == userid || userid == null){
			top.Dialog.alert("此用户没有登录帐号");
			return;
		}
		this.form1.action = "del_user.action?id="+userid;
		this.form1.submit();
		//return;
		//window.location.reload();
	}
	function toNext(url){
		
		//var name = document.form1.name.value;
		this.form1.action = url+"&flag=auth_search";
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
		<table>
			<tr>
				<td>部门:</td>
				<td><select id="dd" name="orgId">
						<option value="0">请选择</option>
						<c:if test="${!empty sessionOrgs}">
						 	<c:forEach items="${sessionOrgs }" var="organization">  
								<option value="${organization.id}" <c:if test="${organization.id == params.id_org}">selected="selected"</c:if> >${organization.name }</option>					 
							</c:forEach>
						</c:if>
					</select></td>
				<td>姓名：</td>
				<td><input type="text" name="name" value="${params.name }"/></td>
				<td><button  onclick="search();"><span class="icon_find">查询</span></button></td>
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
			<th width="10%">所在部门</th>
			<th width="10%">登录帐号</th>
			<th width="10%">失效时间</th>
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
			<td >${person.org.name }</td>
			<td >${person.user.username }</td>
			<td ><fmt:formatDate value="${person.user.expireTime }" pattern="yyyy-MM-dd"/></td>
			<td >${person.description }</td>
			<td>
			<!-- 
			<span class="img_key hand" title="分配帐号" onclick="openWin2(${person.id },${person.user.id ==null ? 0 : person.user.id},'${person.user.username }','${person.user.password }','<fmt:formatDate value="${person.user.expireTime }" pattern="yyyy-MM-dd"/>');" ></span>
			 -->
			
			<span class="img_reg hand"  title="分配角色 " onclick="openWin1(${person.user.id },'${person.name }');"></span>
			<span class="img_view hand"  title="数据权限" onclick="openWin3(${person.id });"></span>
			</td>
		</tr>
		</c:forEach>
		</c:if>


	</table>
</div>

<div style="height:35px;">
	<pg:pager items="${pm.total }" url="user.action" export="currentPageNumber=pageNumber" maxPageItems="15">
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
