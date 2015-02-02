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
<script src="../js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="../js/form/validationEngine.js" type="text/javascript"></script>
<script language="javascript">
function ok(){
		//var dept = document.form1.dept.value;
		this.form1.action = "addPerson.action";
		this.form1.submit();
		//top.Dialog.close();
		return;
	}

</script>

<body>
<form action="?" id="form1" name="form1" >
<div class="box1" panelWidth="500">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="2">人员信息</th>
		</tr>
		<tr>
			<td>姓名：</td><td><input type="text"  name="name"/></td>
		</tr>

		<tr>
			<td>性别：</td>
			<td><label for="radio-5">男</label><input type="radio" id="radio-5"  name="sex" value="1" />
				<label for="radio-6">女</label><input type="radio" id="radio-6"  name="sex" value="0"/>
			</td>
		</tr>
		<tr>
			<td>所在部门：</td>
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
		</tr>
		
		<tr>
			<td>职务：</td><td><input type="text"  name="duty"/></td>
		</tr>
		<tr>
			<td>电话：</td><td><input type="text" name="phone"/></td>
		</tr>
		<tr>
			<td>邮箱：</td><td><input type="text" name="email"/></td>
		</tr>
		<tr>
			<td>地址：</td><td><input type="text"  name="address"/></td>
		</tr>
		<tr>
			<td>计划业务组：</td>
			<td>
				<select id="group" name="planGroupId">
						<option value="0">请选择</option>
						<c:if test="${!empty groups}">
						 	<c:forEach items="${groups }" var="group">  
						 		<c:if test="${group.groupType == '01'}">
									<option value="${group.id}" <c:if test="${group.id == person.planGroup.id}">selected="selected"</c:if> >${group.groupName }</option>		
								</c:if>			 				 
							</c:forEach>
						</c:if>
					</select>
			</td>
		</tr>
		<tr>
			<td>寻源业务组：</td>
			<td>
				<select id="group" name="seekSourceGroupId">
						<option value="0">请选择</option>
						<c:if test="${!empty groups}">
						 	<c:forEach items="${groups }" var="group">  
						 		<c:if test="${group.groupType == '02'}">
									<option value="${group.id}" <c:if test="${group.id == person.seekSourceGroup.id}">selected="selected"</c:if> >${group.groupName }</option>		
								</c:if>			 
							</c:forEach>
						</c:if>
					</select>
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>
				<span class="float_left">
					<textarea name="description"> </textarea>
				</span>
				<span class="img_light" style="height:80px;" title="限制在200字以内"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button"  onclick="ok()" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	</div>
</form>
</body>