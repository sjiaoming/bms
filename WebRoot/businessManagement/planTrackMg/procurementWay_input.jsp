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
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>

<script language="javascript">
function ok(){
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
		//return access;
	}

</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="tags" value="<%=request.getParameter("pids") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="500">
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="2">采购信息</th>
		</tr>
		<tr>
			<td> 采购方式：</td>
			<td>
				<select  name="procurementWay" id="procurementWay" class="validate[required]">
					<option value=""></option>
				    <option value="1">公开招标</option>
				    <option value="2">邀请招标</option>
				    <option value="3">竞争性谈判</option>
				    <option value="4">单一来源</option>
				    <option value="5">询比价</option>
				 </select>
			</td>
		</tr>
		<tr>
			<td>采购方案审批通过日期：</td><td><input  id="procurementDate" name="procurementDate"  value="" class="date"/></td>
                   
		</tr>
		<tr>
			<td>备注：</td><td><textarea name="procurementRemark"></textarea></td>
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