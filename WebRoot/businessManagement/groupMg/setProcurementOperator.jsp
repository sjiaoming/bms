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
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->

<script language="javascript">
	
	function setProcurementMoney(pid,num){
		
		document.getElementById("procurementMoney"+pid).value = (Number(document.getElementById("procurementPrice"+pid).value)).mul(num);
	}
	
	function ok(){
		var personId=document.getElementById("personId").value;
		if(personId=="" ||personId == '0' ){
			top.Dialog.alert("请选择业务员");
			return;
		}
		if(confirm("您确定要提交吗?")){
			this.form1.action = "modifyGroupProcurementsPerson.action";
			this.form1.submit();
			//top.Dialog.close();
			return;
		}
	}

</script>

<body>
<form action="?" id="form1" name="form1">
<input type="hidden" name="tags" value="<%=request.getParameter("tags") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="350">
	<table class="tableStyle" >
		<tr>
			<th colspan="2">业务员变更</th>
		</tr>
		<tr>
			<td>业务员:</td>
			<td><select id="personId" name="personId">
						<option value="0">请选择</option>
						<c:if test="${!empty persons}">
						 	<c:forEach items="${persons }" var="person">  
								<option value="${person.id}">${person.name }</option>					 
							</c:forEach>
						</c:if>
				</select>
			</td>
		</tr>
		
	</table>
	
	<table class="tableStyle" formMode="true">
	
		<tr>
			<td colspan="8">
				<input type="button"  onclick="ok()" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	
	
	</div>
</form>
</body>