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
		var way = $("#procurementWay").val();
		var supplier = $("#supplierIds").val();
		//alert(way+"  ssss  "+supplier);
		if(way != '01' && supplier == null){
			alert("请选择供应商");
			return false ;
		}else{
			var access=false;
			access=$('#form1').validationEngine({returnIsValid:true});
			if(access){
				if(confirm("您确定要提交吗?")){
					this.form1.action = "doProcurementWay.action";
					this.form1.submit();
					//top.Dialog.close();
					return;
				}
			}
		}
		//return access;
	}
function changeWay(){
	var v = $("#procurementWay").val();
//	alert(v);
	if(v=="01"){
		$("#supplierRow").hide();
		//alert(2);
	}else{
		$("#supplierRow").show();
		//alert(3);
	}
}
</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="tags" value="<%=request.getParameter("pids") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<input type="hidden" name="pageType" value="director" />
<div class="box1" panelWidth="500" panelHeight="450">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="2">采购信息</th>
		</tr>
		<tr>
			<td> 采购方案名称：</td>
			<td>
				<input type="text" name="procurementName" />
			</td>
		</tr>
		<tr>
			<td> 方案号：</td>
			<td>
				<input type="hidden" name="caseNum" value="${maxCaseNum }"/>
				<span>${maxCaseNum }</span>
			</td>
		</tr>
		<tr>
			<td> 包号：</td>
			<td>
				<input type="hidden" name="packageNum" value="${maxCaseNum }"/>
				<span>${maxCaseNum }</span>
			</td>
		</tr>
		<tr>
			<td> 采购方式：</td>
			<td>
				<select  name="procurementWay" id="procurementWay" class="validate[required]" onchange="changeWay()">
					<option value=""></option>
				    <option value="01">公开招标</option>
				    <option value="02">邀请招标</option>
				    <option value="03">竞争性谈判</option>
				    <option value="04">单一来源</option>
				    <option value="05">询比价</option>
				 </select>
			</td>
		</tr>
		<tr id="supplierRow">
			<td> 选择供应商：</td>
			<td><!--  class="validate[required]"  -->
				<select  name="supplierIds" id="supplierIds"
				 autoWidth="true" style="width: 200px;" multiple>
					<c:if test="${!empty sessionSuppliers}">
					 	<c:forEach items="${sessionSuppliers }" var="supplier">  
							<option value="${supplier.id}" >${supplier.name }</option>					 
						</c:forEach>
					</c:if>
					<option value="0" rel="headernocb"></option>
				 </select>
			</td>
		</tr>
		<tr>
			<td>审核主管:</td>
				<td><select id="managerId" name="pc.director.id">
						<option value="0">请选择</option>
						<c:if test="${!empty dpersons}">
						 	<c:forEach items="${dpersons }" var="dperson">  
								<option value="${dperson.id}" >${dperson.name }</option>					 
							</c:forEach>
						</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td>采购方案备注:</td>
				<td>
				<span class="float_left">
					<textarea  name="memo" id="memo"> </textarea>
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