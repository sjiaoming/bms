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
		var access=false;
		access=$('#form1').validationEngine({returnIsValid:true});
		if(access){
			if(confirm("您确定要提交吗?")){
				this.form1.action = "doSeekSourceI.action";
				this.form1.submit();
				//top.Dialog.close();
				return;
			}
		}
		//return access;
	}
</script>

<body>
<form action="?" id="form1" name="form1">
<input type="hidden" name="tags" value="<%=request.getParameter("ssids") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="1580" panelheight="558">
	<table class="tableStyle">
		<tr>
			<th colspan="18">寻源审核明细</th>
		</tr>
		<tr>
			<th width="80">物资类别</th>
			<th width="80">物资代码</th>
			<th width="80">物资名称</th>
			<th width="100">供应商</th>
			<th width="100">规格型号</th>
			<th width="100">材质</th>
			<th width="100">标准号</th>
			<th width="60">单价</th>
			<th width="60">数量</th>
			<th width="80">预算金额</th>
			<th width="80">要求到货日期</th>
			<th width="60">负责人</th>
			<th width="90">用途</th>
			<th width="80">寻源数量</th>
			<th width="80">寻源单价</th>
			<th width="80">寻源金额</th>
		</tr>
		<c:forEach items="${seekSource.details }" varStatus="status" var="detail">
		<tr>
			<td><span class="text_slice" style="width:80px;" title="${detail.plan.itemType }">${detail.plan.itemType }</span></td>
			<td>${detail.plan.itemsNum }</td>
			<td><span class="text_slice" style="width:80px;" title="${detail.plan.itemsName }">${detail.plan.itemsName }</span></td>
			<td><span class="text_slice" style="width:100px;" title="${detail.supplier.name }">${detail.supplier.name }</span></td>
			<td><span class="text_slice" style="width:100px;" title="${detail.plan.modelType }">${detail.plan.modelType }</span></td>
			<td><span class="text_slice" style="width:100px;" title="${detail.plan.material }">${detail.plan.material }</span></td>
			<td>${detail.plan.standardNum }</td>
			<td>${detail.plan.budgetPrice }</td>
			<td>${detail.plan.quantity }</td>
			<td width="80"><fmt:formatNumber value="${detail.plan.budgetMoney }" type="currency"/></td>
			<td><fmt:formatDate value="${detail.plan.arrivalDate }" pattern="yyyy-MM-dd"  /></td>
			<td>${detail.plan.person.name }</td>
			<td><span class="text_slice" style="width:100px;" title="${detail.plan.useFor }">${detail.plan.useFor }</span></td>
			<!-- 
			<td><span class="text_slice" style="width:80px;" title="${detail.plan.itemsName }">${detail.plan.itemsName }</span></td>
			<td><span class="text_slice" style="width:80px;" title="${detail.plan.reportComp }">${detail.plan.reportComp }</span></td>
			 -->
			<td>${detail.seekSourceQuantity}</td>
			<td>${detail.seekSourcePrice}</td>
			<td>${detail.seekSourceM}</td>
		</tr>
		</c:forEach>
		<tr><td colspan="14"></td><td colspan="2"><font color="red">合计：${totalMoney}</font></td></tr>
	</table>
	<table class="tableStyle">
		<tr>
			<th colspan="4">寻源审核记录</th>
		</tr>
		<tr>
			<th width="80">审核人</th>
			<th width="180">审核状态</th>
			<th width="180">审核意见</th>
			<th width="180">审核日期</th>
		</tr>
		<c:forEach items="${seekSource.checks }" varStatus="status" var="check">
			<tr>
				<td>${check.person.name }</td>
					<td>
						<c:if test="${check.checkStatus==01 }">主管末审核</c:if>
						<c:if test="${check.checkStatus==02 }">主管末通过审核</c:if>
						<c:if test="${check.checkStatus==08 }">主管己审核</c:if>
						<c:if test="${check.checkStatus==09 }">内控末通过审核</c:if>
						<c:if test="${check.checkStatus==03 }">内控己审核</c:if>
						<c:if test="${check.checkStatus==04 }">经理末通过审核</c:if>
						<c:if test="${check.checkStatus==05 }">经理己审核</c:if>
						<c:if test="${check.checkStatus==06 }">董事长末通过审核</c:if>
						<c:if test="${check.checkStatus==07 }">董事长己审核</c:if>
					</td>
					<td>${check.opinion }</td>
					<td>${check.checkDate }</td>
				</tr>
		</c:forEach>
	</table>
	</div>
</form>
</body>