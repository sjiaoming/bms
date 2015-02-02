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
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>
<script language="javascript">

	function setProcurementMoney(pid,num,feild){
	
		document.getElementById("procurementMoney"+pid).value = (Number(document.getElementById("procurementPrice"+pid).value)).mul(num);
	}
	function setprocurementPrice(pid,num){
		
		document.getElementById("procurementPrice"+pid).value = (Number(document.getElementById("procurementMoney"+pid).value)).div(num);
	}
	function ok(){
		var searchAnnouncedDate_date=document.getElementById("searchAnnouncedDate_date").value;
		
		if(searchAnnouncedDate_date==""){
			top.Dialog.alert("寻源公布日期不能为空!");
			return;
		}
		var tempDate = document.getElementById("searchDate_date");
		var tempPrice = document.getElementsByName("procurementPrice");
		var tempMoney = document.getElementsByName("procurementMoney");
		if(tempDate.value!=null && tempDate.value!=""){
			var tempcew = document.getElementById("contractExecutionWay_option");
			if(tempcew.value == 0){
				top.Dialog.alert("合同执行方式不能为空");
				return;
			}
			
			if(tempPrice.length>0){
				for(var i=0;i<tempPrice.length;i++){
					if(tempPrice[i].value <= 0 || tempMoney[i].value <=0){
						top.Dialog.alert("寻源单价与寻源金额不能为 0");
						return;
					}				
				}
			}
		}
		if(tempPrice.length>0){
			for(var i=0;i<tempPrice.length;i++){
				if(tempPrice[i].value > 0 || tempMoney[i].value > 0){
					if(tempDate.value ==null || tempDate.value==""){
						top.Dialog.alert("寻源签报日期不能为空!");
					return;
					}
				}
			}
		}
		if(confirm("您确定要修改此计划吗?")){
			this.form1.action = "modifySeekSource.action";
			this.form1.submit();
			//top.Dialog.close();
			return;
		}
		
	}
	

</script>

<body>
<form action="?" id="form1" name="form1" >
<div class="box1" panelWidth="700">
<input type="hidden" name="cflag" id="cflag" value="" />
<table class="tableStyle" >
		<tr>
			<th colspan="4">寻源信息</th>
		</tr>
		<tr>
			<td> 寻源公布日期：</td>
			<td>
			<input  id="searchAnnouncedDate_date" name="searchAnnouncedDate"  value="<fmt:formatDate value="${plan.searchAnnouncedDate }" pattern="yyyy-MM-dd"/>" class="date"/>
				<span class="star">*</span></td>
			<td>寻源结果审批通过日期：</td><td>
			<input  id="searchDate_date" name="searchDate"  value="<fmt:formatDate value="${plan.searchDate }" pattern="yyyy-MM-dd"/>" class="date"/>
			</td>
		</tr>
		
		<tr>
			
				<td>合同执行方式</td>
					<td colspan="3"><select  name="contractExecutionWay" id="contractExecutionWay_option">
						<option value="0">选择方式</option>
					    <option value="1" <c:if test="${plan.contractExecutionWay == '1' }">selected="selected"</c:if> >统谈统签统付</option>
					    <option value="2" <c:if test="${plan.contractExecutionWay == '2' }">selected="selected"</c:if>>统谈统签分付</option>
					    <option value="3" <c:if test="${plan.contractExecutionWay == '3' }">selected="selected"</c:if>>统谈分签</option>
					 </select>
				</td>
		</tr>
		
	</table>
	<table class="tableStyle" formMode="true">
			<tr>
				<th>ID</th>
				<th>购物车编号</th>
				<th>物料描述</th>
				<th>计划数量</th>
				<th>计划单价(元)</th>
				<th>预算金额(元)</th>
				<th>寻源单价(元)</th>
				<th>寻源金额(元)</th>
			</tr>
		<c:if test="${!empty plan}">
			<c:set value="${plan }" var="p"></c:set>
			<tr>
				<td>${p.id}</td>
				<td>${p.oldPlanNum}</td>
				<td><span class="text_slice" style="width:100px;" title="${p.itemsName}">${p.itemsName }</span></td>
				<td>${p.num}</td>
				<td>${p.planPrice}</td>
				<td>${p.budget}</td>
				<td><input type="text" id="procurementPrice${p.id}" name="procurementPrice" value="<fmt:formatNumber value="${p.procurementPrice }" pattern="#0.00"/>" onblur="setProcurementMoney('${p.id}',${p.num});"   class="validate[required]"  style="width:60px;"/></td>
				<td><input type="text" id="procurementMoney${p.id}" name="procurementMoney" value="<fmt:formatNumber value="${p.procurementMoney }" pattern="#0.00"/>"  onblur="setprocurementPrice('${p.id}',${p.num});"  style="width:60px;" />
				<input type="hidden" name="pid" value="${p.id}"/>
				</td>
				
			</tr>
		</c:if>
		
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