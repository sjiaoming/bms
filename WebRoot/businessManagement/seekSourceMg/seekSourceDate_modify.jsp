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
	
	function ok(){
		var searchAnnouncedDate_date=document.getElementById("searchDate_date").value;
		if(searchAnnouncedDate_date==""){
			if(confirm("您确定要删除寻源结果审批通过日期吗？本次操作将同时删除寻源公布日期、寻源单价、寻源金额及合同执行方式，并且该操作将不可逆，是否继续？？？")){
				this.form1.action = "modifyPlanClearSeekDate.action";
				this.form1.submit();
				top.Dialog.close();
				return;
			}
		}else{
			if(confirm("您确定要提交吗?")){
			this.form1.action = "doSeekSourcePmodify.action";
			this.form1.submit();
			//top.Dialog.close();
			return;
			}
		}
	}

</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="tags" value="<%=request.getParameter("pids") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="350">
	<table class="tableStyle" >
		<tr>
			<th colspan="2">寻源信息</th>
		</tr>
		<tr>
			<td> 寻源签报日期：</td>
			<td><input  id="searchDate_date" name="searchDate"  value="${eDate }" class="date"/>
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