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
<script type="text/javascript" src="<%=path%>/js/form/multiselect.js"></script>
<!--框架必需end-->
<!--截取文字start-->
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->
<script type="text/javascript" src="<%=path %>/js/Calendar3.js"></script>
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>

<script language="javascript">

/*$(function(){
	$('#innerTR').hide();
});*/

function ok(){
	var status = $('#checkStatus').val();
	if(status == "08" ){
	var innerId = $('#innerId').val(); 
	
		if(innerId == 0){
			alert("请选择内控!");
			return;
		}
	}
	var access=false;
	access=$('#form1').validationEngine({returnIsValid:true});
	if(access){
		if(confirm("您确定要提交吗?")){
			this.form1.action = "doSeekSourceD.action";
			this.form1.submit();
			//top.Dialog.close();
			return;
		}
	}
		//return access;
	}

function displayManager(){
	var status = $('#checkStatus').val();
	//alert(status);
	if(status == "02" ){
		$('#innerTR').hide();
	}else{
		$('#innerTR').show();
	}
}
</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="tags" value="<%=request.getParameter("ssids") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="568" panelheight="558">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="2">寻源审核</th>
		</tr>
		<tr>
			<td>是否通过:</td>
			<td>
			<select id="checkStatus" name="seekSource.checkStatus" onChange="displayManager()">
		 		<option value="08" >主管己审核</option>
				<option value="02" >主管末通过审核</option>					 
			</select>
			</td>
		</tr>
		<tr id="innerTR">
		<td>审核内控:</td>
		<td>
			<select id="innerId" name="seekSource.innerContral.id" class="default">
				<option value="0">请选择</option>
				<c:if test="${!empty ipersons}">
				 	<c:forEach items="${ipersons }" var="iperson">  
						<option value="${iperson.id}">${iperson.name }</option>					 
					</c:forEach>
				</c:if>
			</select>
		</td>
		</tr>
		<tr>
			<td>审核意见:</td>
				<td>
				<span class="float_left">
					<textarea name="seekSource.opinion"> ${seekSource.opinion}</textarea>
				</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="button"  onclick="ok()" value=" 提 交 "/>
				<!-- <input type="submit" value="提交" /> -->
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	</div>
</form>
</body>