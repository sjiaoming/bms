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
	$(function(){
		top.Dialog.close();
		var s = document.getElementById("cflag").value;
		if(s!=""&&s=="success"){
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
		this.form1.action = "organization.action";
		this.form1.submit();
		return;
	}
	function findchildrenorgn(){
		//alert(0);
	 	var orgnId=document.getElementById('dd').value;
		orgnManager.findLevel4Organization(orgnId,function(data){
			var orgnId2=document.getElementById('dd2');
			orgnId2.innerHTML = '';
			for(var i=0;i<data.length;i++){
				var id = data[i][0];
				var name = data[i][1];
				orgnId2.options[0]=new Option('name','id');
				//alert(name);
				
			}
		
		});
	}
	
									
	function openWin(organizationId,organizationName){
	
		var diag = new top.Dialog();
		diag.Title = "添加窗口";
		diag.URL = "systemManagement/organizationMg/add_input.jsp?organizationId="+organizationId+"&organizatioName="+organizationName;
		diag.Height=480;
		diag.MessageTitle="添加部门";
		diag.Message="部门明细";
		diag.CancelEvent = function(){
			//diag.innerFrame.contentWindow.location.reload();
			diag.close();
			};
		diag.show();
	}
	function openWin4(){
		var diag = new top.Dialog();
		diag.Title = "新建窗口";
		diag.URL = "toNewOrg.action";
		diag.Height=480;
		diag.MessageTitle="新建部门";
		diag.Message="部门明细";
		diag.CancelEvent = function(){
			window.location.href = "organization.action";
			diag.close();
			};
			
		diag.show();
	}
	function openWin1(organizationId){
		//alert(name);
		//alert(userid);
		var diag = new top.Dialog();
		diag.Title = "部门修改";
		diag.URL = "modify_org_input.action?organizationId="+organizationId;
		diag.Height=380;
		diag.MessageTitle="部门信息";
		diag.Message="部门详细信息";
		diag.CancelEvent = function(){
			window.location.href = "organization.action";
			diag.close();
			};
		diag.show();
	}
	function del(organizationId){
		//moduleManager.deleteModule(moduleid);
		this.form1.action = "del_organization.action?organizationId="+organizationId;
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
	<div class="box2" panelTitle="功能面板" roller="false">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" id="cflag" name="cflag" value="${cflag }"/>
		<table>
			<tr>
				<td>部门:</td>
				<td>
					<select id="dd" name="parentId">
						<option value="0">请选择</option>
						<c:if test="${!empty sessionOrgs}">
						 	<c:forEach items="${sessionOrgs }" var="organization">  
								<option value="${organization.id}" <c:if test="${organization.id == parentId}">selected="selected"</c:if> >${organization.name }</option>					 
							</c:forEach>
						</c:if>
					</select>
				</td>
				<td>部门名称：</td>
				<td><input type="text" name="name" id="dd1" value="${params.name }"/></td>
				<c:if test="${my:method(login.id,'organizationMgr',1)}">
				<td><button  onclick="search();"><span class="icon_find">查询</span></button></td>
				</c:if>
				<c:if test="${my:method(login.id,'organizationMgr',0)}">
				<td><input type="button" value="新建"   onclick="openWin4();"/></td>
				<!-- <td><button  onclick='top.Dialog.open({URL:"systemManagement/organizationMg/new_input.jsp",Title:"普通窗口"});'><span class="icon_find">新建</span></button></td> -->
				</c:if>
			</tr>
			
		</table>
		</form>
	</div>
	<div id="scrollContent" class="border_gray">
	<table class="tableStyle">
		<tr>
			<th width="5%">序号</th>
			<th width="16%">部门名称</th>
			<th width="16%">部门编号</th>
			<th width="16%">部门描述</th>
			<th width="16%">从属部门</th>
			<th width="15%">操作</th>
		</tr>
		<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="organization">  
		<tr bgcolor="">
			<td >${organization.id }</td>
			<td >${organization.name }</td>
			<td >${organization.sn }</td>
			<td >${organization.description }</td>
			<td >${organization.parent.name }</td>
			<td>
			<c:if test="${empty organization.parent}">
			<c:if test="${my:method(login.id,'organizationMgr',0)}">
			<span class="img_view hand"  title="添加" onclick="openWin(${organization.id },'${organization.name}');"></span>
			</c:if>
			</c:if>
			<c:if test="${my:method(login.id,'organizationMgr',2)}">
			<span class="img_edit hand" title="修改" onclick="openWin1(${organization.id });"></span>
			</c:if>
			<c:if test="${my:method(login.id,'organizationMgr',3)}">
			<span class="img_delete hand" title="删除帐号"  onclick='top.Dialog.confirm("您确定要删除登录帐号？",function(){del(${organization.id})} );'></span>
			</c:if>
			</td>
		</tr>
		</c:forEach>
		</c:if>


	</table>
</div>
<div style="height:35px;">
	<pg:pager items="${pm.total }" url="organization.action" export="currentPageNumber=pageNumber" maxPageItems="15">
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

	

