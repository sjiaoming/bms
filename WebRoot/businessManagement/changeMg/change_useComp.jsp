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

function ok(){
	//var status = $('#checkStatus').val();
//	if(status == "08" ){
	var reportCompId = $('#reportCompId').val(); 
	
		if(reportCompId == 0){
			alert("请选择计划提报单位!");
			return;
		}
//	}
	var access=false;
	access=$('#form1').validationEngine({returnIsValid:true});
	if(access){
		if(confirm("您确定要提交吗?")){
			this.form1.action = "doChangePlan.action";
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
<input type="hidden" name="tags" value="<%=request.getParameter("tags") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="flag" value="changeUseComp" />
<div class="box1" panelWidth="568" panelheight="558">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="2">变更供应商</th>
		</tr>
		<tr id="innerTR">
		<td>计划提报单位:</td>
		<td>
			<select id="reportCompId" name="plan.reportCompId">
						<c:if test="${!empty useComps}">
						 	<c:forEach items="${useComps }" var="uc">  
								<option value="${uc.id}">${uc.name }</option>					 
							</c:forEach>
						</c:if>
						<option value="0" rel="headernocb"></option>
			</select>
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