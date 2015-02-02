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
<script type="text/javascript" src="<%=path %>/js/form/vchecks.js"></script>

<script type='text/javascript' src='dwr/interface/aclManager.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<script type="text/javascript">
	//授权
	function addOrModifyPermission(field,resourceSn,permission) {
		
		//如果被选择上，则同时选择“继承”和启用
		if(field.checked) {
			document.getElementById(resourceSn+"_USE").checked = true;
			
			<c:if test="${type eq 'User'}">
				document.getElementById(resourceSn+"_EXT").checked = true;
				aclManager.addOrModifyUserExtends(
									${sn}, 
									resourceSn,
									field.checked
									);
			</c:if>
			
		}
		//alert(${sn}+"--"+resourceSn+"--"+permission+"--"+field.checked);
		aclManager.addOrModifyPermission(
									"${type}", 
									${sn}, 
									resourceSn, 
									permission, 
									field.checked
									);
	}
	
	//设置用户的继承特性
	function addOrModifyUserExtends(field,resourceSn) {
	//alert("继承");
		aclManager.addOrModifyUserExtends(
									${sn}, 
									resourceSn,
									field.checked
									);
	}
	
	//点击启用checkbox
	function usePermission(field,resourceSn){
		//如果checkbox被选中，意味着需要更新ACL的状态
		//alert("启用");
		//设置同步，依次执行
		dwr.engine.setAsync(false);
		
		if(field.checked) {
			//更新C/R/U/D状态
			addOrModifyPermission(document.getElementById(resourceSn+"_C"));
			addOrModifyPermission(document.getElementById(resourceSn+"_R"));
			addOrModifyPermission(document.getElementById(resourceSn+"_U"));
			addOrModifyPermission(document.getElementById(resourceSn+"_D"));
			
			//更新EXT状态
			<c:if test="${type eq 'User'}">
				addOrModifyUserExtends(document.getElementById(resourceSn+"_EXT"));
			</c:if>
		} else {
			aclManager.deletePermission(
									"${type}", 
									${sn},
									resourceSn
									);
			document.getElementById(resourceSn+"_C").checked = false;
			document.getElementById(resourceSn+"_R").checked = false;
			document.getElementById(resourceSn+"_U").checked = false;
			document.getElementById(resourceSn+"_D").checked = false;
			
			<c:if test="${type eq 'User' }">
				document.getElementById(resourceSn+"_EXT").checked = false;
			</c:if>
		}
	}
	
	//初始化表格
	function initTable() {
		aclManager.searchAclRecord(
									"${type}", 
									${sn}, 
									function(data){
										for(var i=0;i<data.length;i++){
											var resourceSn = data[i][0];
											var cState = data[i][1];
											var rState = data[i][2];
											var uState = data[i][3];
											var dState = data[i][4];
											var extState = data[i][5];
											
											document.getElementById(resourceSn+"_C").checked = cState == 0 ? false : true;
											document.getElementById(resourceSn+"_R").checked = rState == 0 ? false : true;
											document.getElementById(resourceSn+"_U").checked = uState == 0 ? false : true;
											document.getElementById(resourceSn+"_D").checked = dState == 0 ? false : true;
											
											//$(resourceSn+"_C").checked = cState == 0 ? false : true;
											//$(resourceSn+"_R").checked = rState == 0 ? false : true;
											//$(resourceSn+"_U").checked = uState == 0 ? false : true;
											//$(resourceSn+"_D").checked = dState == 0 ? false : true;
											//alert(resourceSn+'--'+cState+'--'+rState+'--'+uState+'--'+dState+'--'+extState);
											<c:if test="${type eq 'User' }">
												document.getElementById(resourceSn+"_EXT").checked = extState == 0 ? false : true;
											</c:if>
											
											document.getElementById(resourceSn+"_USE").checked = true;
										}
									});
	}
	
</script>

<body onload="initTable()">
<div id="scrollContent">
	
	<div class="box1" panelWidth="540">
	<fieldset>
	<c:forEach items="${modules}" var="module">
		<legend>
		<tr>
			<td width="10%">${module.name }</td>
			<td width="60%">
				<input type="checkbox" id="${module.id }_C" onclick="addOrModifyPermission(this,${module.id },'0')" resourceSn="${module.id }" permission="0">增加
				<input type="checkbox" id="${module.id }_R" onclick="addOrModifyPermission(this,${module.id },'1')" resourceSn="${module.id }" permission="1">查询
				<input type="checkbox" id="${module.id }_U" onclick="addOrModifyPermission(this,${module.id },'2')" resourceSn="${module.id }" permission="2">修改
				<input type="checkbox" id="${module.id }_D" onclick="addOrModifyPermission(this,${module.id },'3')" resourceSn="${module.id }" permission="3">删除
			</td>	
			<c:if test="${type eq 'User'}">
				<td ><input type="checkbox" id="${module.id }_EXT" onclick="addOrModifyUserExtends(this,${module.id })" resourceSn="${module.id }">继承</input></td>
			</c:if>
				<td ><input type="checkbox" id="${module.id }_USE" onclick="usePermission(this,${module.id })" resourceSn="${module.id }">启用</input></td>
		</tr>
		</legend> 
		<table class="tableStyle" transMode="true" footer="normal">
			<c:forEach items="${module.children}" var="child">
			<tr>
				<td width="10%">${child.name }</td>
				<td width="60%">
					<input type="checkbox" id="${child.id }_C" onclick="addOrModifyPermission(this,${child.id },'0')" resourceSn="${child.id }" permission="0">增加
					<input type="checkbox" id="${child.id }_R" onclick="addOrModifyPermission(this,${child.id },'1')" resourceSn="${child.id }" permission="1">查询
					<input type="checkbox" id="${child.id }_U" onclick="addOrModifyPermission(this,${child.id },'2')" resourceSn="${child.id }" permission="2">修改
					<input type="checkbox" id="${child.id }_D" onclick="addOrModifyPermission(this,${child.id },'3')" resourceSn="${child.id }" permission="3">删除
				</td>
				<c:if test="${type eq 'User'}">
				<td><input type="checkbox" id="${child.id }_EXT" onclick="addOrModifyUserExtends(this,${child.id })" resourceSn="${child.id }">继承</td>
				</c:if>
				<td><input type="checkbox" id="${child.id }_USE" onclick="usePermission(this,${child.id })" resourceSn="${child.id }">启用</td>
			</tr>
			</c:forEach>
		</table>

		
	</c:forEach>
	</fieldset> 
	</div>
	</div>				
</body>