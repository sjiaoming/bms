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
<script type="text/javascript" src="<%=path %>/js/framework.js" ></script>
<link href="<%=path %>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=path %>/"/>
</script>
<!--框架必需end-->
<!--截取文字start-->
<script type="text/javascript" src="<%=path %>/js/text/text-overflow.js"></script>
<!--截取文字end-->
<script type="text/javascript" src="<%=path %>/js/form/multiselect.js"></script>
<!--多功能表格start-->
<link rel="stylesheet" type="text/css" href="<%=path %>/js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="<%=path %>/js/table/flexigrid/flexigrid.js"></script>
<!--多功能表格end-->
<script type="text/javascript">
	
</script>
<style>
body{
	line-height:150%;
}
</style>
<body id="bodyone">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" id="path" value="<%=path %>">
		<table>
			<c:if test="${PlanDirectCheckGroup!=null }">
				<tr>
					<td><span><font color="red">计划主管</font></span></td>
				</tr>
				<tr>
					<td>计划分组审批:<span><font color="red">${uncheckPlanCount }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${PlanOperatorSetGroup!=null }">
				<tr>
					<td><span><font color="red">计划业务员</font></span></td>
				</tr>
				<tr>
					<td>未分组计划:<span><font color="red">${unDistributionPlanCount }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${BusinessOperatorGroup!=null  }">
				<tr>
					<td><span><font color="red">采购业务员</font></span></td>
				</tr>
				<tr>
					<td>采购方案:<span><font color="red">${pcCount }</font></span>条</td>
				</tr>
				<tr>
					<td>采购合同:<span><font color="red">${pccCount }</font></span>条</td>
				</tr>
				<tr>
					<td>销售合同:<span><font color="red">${scCount }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${PcDCheck!=null}">
				<tr>
					<td><span><font color="red">采购主管</font></span></td>
				</tr>
				<tr>
					<td>未审核采购方案:<span><font color="red">${PcDCheckPcCount }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${PcDirectorSetOperator!=null }">
				<tr>
					<td>采购主管设置业务员:<span><font color="red">${setOperatorForPlanCount }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${PcICheck!=null  }">
				<tr>
					<td><span><font color="red">主管领导</font></span></td>
				</tr>
				<tr>
					<td>未审核采购方案:<span><font color="red">${PcICheckPc }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${PlanInternalGroup!=null }">
				<tr>
					<td>计划分组审批:<span><font color="red">${uncheckPlanGroupICount }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${PcMCheck!=null  }">
				<tr>
					<td><span><font color="red">经理</font></span></td>
				</tr>
				<tr>
					<td>未审核采购方案:<span><font color="red">${PcMCheckPc }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${PlanManagerGroup!=null  }">
				<tr>
					<td>计划分组审批:<span><font color="red">${uncheckPlanGroupMCount }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${PcCCheck!=null  }">
				<tr>
					<td><span><font color="red">董事长</font></span></td>
				</tr>
				<tr>
					<td>未审核采购方案:<span><font color="red">${PcCCheckPc }</font></span>条</td>
				</tr>
			</c:if>
			
			<c:if test="${SeekSourceOperator!=null  }">
				<tr>
					<td><span><font color="red">寻源业务员</font></span></td>
				</tr>
				<tr>
					<td>未寻源:<span><font color="red">${ssOperatorCount }</font></span>条</td>
				</tr>
			</c:if>
			<c:if test="${SeekSourceDirector!=null }">
				<tr>
					<td><span><font color="red">寻源主管</font></span></td>
				</tr>
				<tr>
					<td>寻源分组:<span><font color="red">${unGroupCount }</font></span>条</td>
				</tr>
				<tr>
					<td>寻源设置业务员:<span><font color="red">${dsetSeekSourceOperator }</font></span>条</td>
				</tr>
				<tr>
					<td>寻源审批:<span><font color="red">${dSeekSourceCheckCount }</font></span>条</td>
				</tr>
			</c:if>
		</table>
	</form>
</body>
