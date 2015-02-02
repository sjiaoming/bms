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
				this.form1.action = "doDirectCheckGroupPlans.action";
				this.form1.submit();
				//top.Dialog.close();
				return;
			}
		}
		//return access;
	}
</script>

<body>
<form id="form1" name="form1">
<input type="hidden" name="tags" value="<%=request.getParameter("ssids") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<input type="hidden" name="group.id" value="${group.id }" />
<div class="box1" panelWidth="1700" panelheight="558">
	<table class="tableStyle">
		<tr>
			<th colspan="21">计划分组明细</th>
		</tr>
		<tr>
				<th width="80">提报单位</th>
				<th width="80">计划下达时间</th>
				<th width="80">项目名称</th>
				<th width="80">分组状态</th>
				<th width="120">计划内容</th>
				<th width="80">计划编号</th>
				<th width="60">计划行号</th>
				<th width="80">要求到货日期</th>
				<th width="80">物资需求部门</th>
				<th width="80">物资类别</th>
				<th width="80">A物资类别</th>
				<th width="80">物料编码</th>
				<th width="120">物资名称</th>
				<th width="80">规格型号</th>
				<th width="60">计量单位</th>
				<th width="60">计划数量</th>
				<th width="60">预算</th>
				<th width="80">项目负责人</th>
				<th width="60">联系方式</th>
				<th width="80">备注</th>
				<th width="60">公司负责人</th>
		</tr>
		<c:forEach items="${group.plans }" varStatus="status" var="detail">
		<tr>
			<td><span class="text_slice" style="width:80px;" title="${detail.plan.reportComp }">${detail.plan.reportComp }</span></td>
			<td><fmt:formatDate value="${detail.plan.planIssuedDate }" pattern="yyyy-MM-dd"  /></td></td>
			<td>${detail.plan.projectName }</td>
			<td>
				<c:if test="${detail.plan.groupStatus==01 }">未分组</c:if>
				<c:if test="${detail.plan.groupStatus==02 }">已退回</c:if>
				<c:if test="${detail.plan.groupStatus==03 }">已分组</c:if>
			</td>
			<td><span class="text_slice" style="width:100px;" title="${detail.plan.planContent }">${detail.plan.planContent }</span></td>
			<td><span class="text_slice" style="width:80px;" title="${detail.plan.planNum }">${detail.plan.planNum }</span></td>
			<td>${detail.plan.planRowNum }</td>
			<td><fmt:formatDate value="${detail.plan.arrivalDate }" pattern="yyyy-MM-dd"  /></td>
			<td>${detail.plan.demandDept }</td>
			<td>${detail.plan.itemType }</td>
			<td>${detail.plan.atype }</td>
			<td>${detail.plan.itemsNum }</td>
			<td><span class="text_slice" style="width:80px;" title="${detail.plan.itemsName }">${detail.plan.itemsName }</span></td>
			<td><span class="text_slice" style="width:80px;" title="${detail.plan.modelType }">${detail.plan.modelType }</span></td>
			<td>${detail.plan.unit }</td>
			<td>${detail.plan.quantity }</td>
			<td>${detail.plan.budgetMoney }</td>
			<td>${detail.plan.xmperson }</td>
			<td>${detail.plan.contact }</td>
			<td><span class="text_slice" style="width:80px;" title="${detail.plan.remark }">${detail.plan.remark }</span></td>
			<td>${detail.plan.gsperson }</td>
		</tr>
		</c:forEach>
		
	</table>
	
	<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="21">分组审核</th>
		</tr>
		<tr>
			<td colspan="4">是否通过:</td>
				<td colspan="17">
				<select id="checkStatus" name="group.status">
					<option value="04" >主管领导未通过</option>					 
			 		<option value="05" >主管领导己通过</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="4">审核意见:</td>
				<td colspan="17">
				<span class="float_left">
					<textarea name="group.remark"> ${group.remark}</textarea>
				</span>
			</td>
		</tr>
		<tr>
			<td colspan="21">
				<input type="button"  onclick="ok()" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	</div>
</form>
</body>