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
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>
<script language="javascript">
	
	function setProcurementMoney(pid,num){
		document.getElementById("procurementPrice"+pid).value = trim(document.getElementById("procurementPrice"+pid).value);
			document.getElementById("procurementMoney"+pid).value = (Number(document.getElementById("procurementPrice"+pid).value)).mul(num);
	}
	function setprocurementPrice(pid,num){
		document.getElementById("procurementMoney"+pid).value = trim(document.getElementById("procurementMoney"+pid).value);
			document.getElementById("procurementPrice"+pid).value = (Number(document.getElementById("procurementMoney"+pid).value)).div(num);
	}
	function trim(str){ //删除左右两端的空格  
		return str.replace(/(^\s*)|(\s*$)/g, "");  
	}  
	function ltrim(str){ //删除左边的空格  
	return str.replace(/(^\s*)/g,"");  
	}  
	function rtrim(str){ //删除右边的空格  
	return str.replace(/(\s*$)/g,"");  
	}
	function ok(){
		
			var access=true;
			access=$('#form1').validationEngine({returnIsValid:true});
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
				//$("input:text[name='procurementPrice']").addClass("validate[required,custom[numberandfolt]]");
				//$("input:text[name='procurementMoney']").addClass("validate[required,custom[numberandfolt]]");
			}
			if(tempPrice.length>0){
				for(var i=0;i<tempPrice.length;i++){
					//var re = /^-?[1-9]*(\.\d*)?$|^-?0(\.\d*)?$/;
					//alert(!isNaN(tempPrice[i].value));
					if(isNaN(tempPrice[i].value)){
						top.Dialog.alert("第"+(i+1)+"行 寻源单价不是有效的数字");
						return;
					}else if(isNaN(tempMoney[i].value)){
						top.Dialog.alert("第"+(i+1)+"行 寻源金额不是有效的数字");
						return;
					}
					if(tempPrice[i].value > 0 || tempMoney[i].value > 0){
						if(tempDate.value ==null || tempDate.value==""){
							top.Dialog.alert("寻源结果未签报，不能填写单价。");
							return;
						}
					}
				}
			}
					
		
		if(access==true){
			if(confirm("您确定要提交吗?")){
				this.form1.action = "doSeekSource.action";
				this.form1.submit();
				//top.Dialog.close();
				return;
			}
		}
	}

</script>

<body>
<form action="?" id="form1" name="form1">
<input type="hidden" name="tags" value="<%=request.getParameter("pids") %>" />
<input type="hidden" name="cflag" id="cflag" value="" />
<div class="box1" panelWidth="750">
	<table class="tableStyle" >
		<tr>
			<th colspan="4">寻源信息</th>
		</tr>
		<tr>
			<td> 寻源公布日期：</td><td>
			<input  id="searchAnnouncedDate_date" name="searchAnnouncedDate"  value="" class="date validate[required]"/>
				<span class="star">*</span></td>
			<td>寻源结果审批通过日期：</td><td>
			<input  id="searchDate_date" name="searchDate"  value=""  class="date"/>
			</td>
		</tr>
		
		<tr>
			<td >合同执行方式:</td><td colspan="3">
				<select  name="contractExecutionWay" id="contractExecutionWay_option" >
					<option value="">选择方式</option>
				    <option value="1">统谈统签统付</option>
				    <option value="2">统谈统签分付</option>
				    <option value="3">统谈分签</option>
				 </select>
			</td>
		</tr>
		
	</table>
</div>
<div class="box1" panelWidth="750">	
	<table class="tableStyle" formMode="true">
			<tr>
				<th>序号</th>
				<th>购物车编号</th>
				<th>行号</th>
				<th>物料描述</th>
				<th>计划数量</th>
				<th>计划单价(元)</th>
				<th>预算金额(元)</th>
				<th>寻源单价(元)</th>
				<th>寻源金额(元)</th>
			</tr>
		<c:if test="${!empty plist}">
			<c:forEach items="${plist }" var="p" varStatus="index">  
			<tr>
				<td >${index.count }</td>
				<td>${p.oldPlanNum}</td>
				<td>${p.planNum}</td>
				<td><span class="text_slice" style="width:100px;" title="${p.itemsName}">${p.itemsName }</span></td>
				<td>${p.num}</td>
				<td><fmt:formatNumber value="${p.planPrice}" pattern="#0.00"/></td>
				<td><fmt:formatNumber value="${p.budget}" pattern="#0.00"/></td>
				<td><input type="text" id="procurementPrice${p.id}" name="procurementPrice" value="" onblur="setProcurementMoney('${p.id}',${p.num});"  style="width:100px;" /></td>
				<td><input type="text" id="procurementMoney${p.id}" name="procurementMoney" value="" onblur="setprocurementPrice('${p.id}',${p.num});"  style="width:100px;" />
				<input type="hidden" name="pid" value="${p.id}"/>
				</td>
				
			</tr>
			</c:forEach>
		</c:if>
		
		<tr>
			<td colspan="9">
				<input type="button"  onclick="ok()" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	
	
	</div>
</form>
</body>