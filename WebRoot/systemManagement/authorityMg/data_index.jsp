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
<script type="text/javascript" src="<%=path%>/js/form/vchecks.js"></script>

<script type='text/javascript' src='<%=path %>/dwr/interface/aclManager.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>

<script type="text/javascript">
	function search(){
		this.form1.action = "user.action?flag=data_search";
		this.form1.submit();
		return;
	}

	
	//授权
	function addOrModifyDataPermission(field,pid) {
	//	alert(this.form1.personId.value+"--"+field.resourceSn+"--"+pid+"--"+field.checked);
		aclManager.addOrModifyDataPermission(this.form1.personId.value,pid,field.checked);
	}
	
	//初始化表格
	function initTable() {
	
		aclManager.searchDataAclRecord(
									this.form1.personId.value, 
									this.form1.orgId.value, 
									this.form1.name.value, 
									function(data){
										
									   	if(data==null || data[0]==""){
									   		
									   		return;
									  	 }
										for(var i=0;i<data.length;i++){
										if(${orgId!=null}){
											document.getElementById(data[i]+"_person").checked = true;
											}
										}
									});
	}


</script>

<body  onload="initTable()">

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
				<input type="hidden" name="personId" value="<%=Integer.parseInt(request.getParameter("personId"))%>">
			</tr>
			
		</table>
		</form>
	</div>



<div id="scrollContent" class="border_gray">
	<table class="tableStyle">
		<tr>
			<th width="5%">操作</th>
			<th width="10%">姓名</th>
			<th width="5%">性别</th>
			<th width="10%">职位</th>
			<th width="10%">所在部门</th>
			<th width="10%">登录帐号</th>
			<th width="10%">失效时间</th>
			<th width="15%">备注</th>
		</tr>
		<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="person">  
		<tr bgcolor="">
			<td ><input type="checkbox" id="${person.id}_person" onclick="addOrModifyDataPermission(this,${person.id })" resourceSn="${person.id }" ></td>
			<td >${person.name }</td>
			<td >${person.sex }</td>
			<td >${person.duty }</td>
			<td >${person.org.name }</td>
			<td >${person.user.username }</td>
			<td ><fmt:formatDate value="${person.user.expireTime }" pattern="yyyy-MM-dd"/></td>
			<td >${person.description }</td>
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