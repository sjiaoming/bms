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
<script src="<%=path %>/js/jquery.validate.min.js" type="text/javascript"></script>
<link href="<%=path %>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin"  prePath="<%=path %>/"/>
<!--框架必需end-->
<!--截取文字start-->
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->
<!--多选框脚本start-->
<script type="text/javascript" src="../../js/form/multiselect.js"></script>
<!--多选框脚本end-->

<script type="text/javascript" src="<%=path %>/js/Calendar3.js"></script>
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>
<script language="javascript">
	
	function setProcurementMoney(pid,num){
		
		document.getElementById("procurementMoney"+pid).value = (Number(document.getElementById("procurementPrice"+pid).value)).mul(num);
	}
	
	function ok(){
		var searchAnnouncedDate_date=document.getElementById("contractExecutionWay_option").value;
		if(searchAnnouncedDate_date==""){
			top.Dialog.alert("合同执行方式不能为空!");
			return;
		}
		
		
		if(confirm("您确定要提交吗?")){
			this.form1.action = "doSeekSourcePmodify.action";
			this.form1.submit();
			//top.Dialog.close();
			return;
		}
	}

</script>

<body>
<form action="?" id="form1" name="form1">
<input type="hidden" name="tags" value="<%=request.getParameter("pids") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="350">
	<table class="tableStyle" >
		<tr>
			<th colspan="2">寻源信息</th>
		</tr>
		<tr>
			<td>合同执行方式:</td>
				<td><select  name="contractExecutionWay" id="contractExecutionWay_option" >
					<option value="0">选择方式</option>
				    <option value="1">统谈统签统付</option>
				    <option value="2">统谈统签分付</option>
				    <option value="3">统谈分签</option>
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