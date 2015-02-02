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
	$('#chairManTR').hide();
});*/

function ok(){
		var access=false;
		access=$('#form1').validationEngine({returnIsValid:true});
		var status = $('#checkStatus').val();
		if(status == "05" ){
			var chairManId = $('#chairManId').val(); 
			
			if(chairManId == 0){
				alert("请选择董事长!");
				return;
			}
		}
		if(access){
			if(confirm("您确定要提交吗?")){
				this.form1.action = "doSeekSourceM.action";
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
	if(status == "04" ){
		$('#chairManTR').hide();
	}else{
		$('#chairManTR').show();
	}
}
</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="tags" value="<%=request.getParameter("ssids") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="500" panelheight="558">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="2">寻源审核</th>
		</tr>
		<tr>
			<td>是否通过:</td>
				<td>
				<select id="checkStatus" name="seekSource.checkStatus" onChange="displayManager()">
			 		<option value="05" >经理己审核</option>
					<option value="04" >经理末通过审核</option>					 
				</select>
			</td>
		</tr>
		<tr id="chairManTR">
		<td>审核董事长:</td>
			<td>
				<select id="chairManId" name="seekSource.chairMan.id" class="default">
					<option value="0">请选择</option>
					<c:if test="${!empty cpersons}">
					 	<c:forEach items="${cpersons }" var="cperson">  
							<option value="${cperson.id}" <c:if test="${seekSource.chairMan.id==cperson.id}"> selected="selected"</c:if>>${cperson.name }</option>					 
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