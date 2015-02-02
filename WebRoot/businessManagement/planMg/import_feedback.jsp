<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@include file="/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--框架必需start-->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
	<link href="<%=path%>/css/import_basic.css" rel="stylesheet"
		type="text/css" />
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" />
	<!--框架必需end-->

	<!--截取文字start-->
	<script type="text/javascript"
		src="<%=path%>/js/text/text-overflow.js"></script>
	<!--截取文字end-->

	<!--多功能表格start-->
	<link rel="stylesheet" type="text/css"
		href="<%=path%>/js/table/flexigrid/css/flexigrid/flexigrid.css">
	<script type="text/javascript"
		src="<%=path%>/js/table/flexigrid/flexigrid.js"></script>
	<!--多功能表格end-->
	<script language="javascript">
	
</script>

	<body>
		<form action="add_plan.action" id="form1" name="form1">
			<div class="box1" panelWidth="1370">
				<s:if test="Feedback==null">
						
				</s:if>

				<s:if test="Feedback!=null">
					<table class="tableStyle" >
						<tr>
							<th colspan="21" width=1370px><b><font color=red size=4 >${msg}</font></b></th>
						</tr>
						<tr>						
							
							<td>行号</td>
							<td>提报单位</td>
							<td>计划下达时间</td>
							<td>项目名称</td>
							<td>计划内容</td>
							<td>计划编号</td>
							<td>计划行号</td>
							<td>要求到货日期</td>
							<td>物资需求部门</td>
							<td>物资类别</td>
							<td>A物资类别</td>
							<td>物料编码</td>
							<td>物资名称</td>
							<td>规格型号</td>
							<td>计量单位</td>
							<td>计划数量</td>
							<td>项目负责人</td>
							<td>预算</td>
							<td>联系方式</td>
							<td>备注</td>
							<td>公司负责人</td>
						</tr>

						<s:iterator value="Feedback.keySet()" id="rowNumber">
							<tr>
								<td style="align: center; color: red;"><s:property value="#rowNumber" /></td>
								<s:iterator value="Feedback.get(#rowNumber).keySet()" id="cellNumber" >
								<s:if test="#cellNumber == 1"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 2"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 3"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 4"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 5"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 6"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 7"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 8"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 9"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 10"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 11"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 12"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 13"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 14"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 15"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 16"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 17"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 18"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 19"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								<s:if test="#cellNumber == 20"><td style="align: left; color: red;"><s:property value="Feedback.get(#rowNumber).get(#cellNumber)" /></td></s:if>
								</s:iterator>
								
							</tr>
						</s:iterator>


					</table>
				</s:if>
			</div>
		</form>
	</body>