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

<script type='text/javascript' src='<%=path %>/dwr/interface/planManager.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/interface/salesContractManager.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
<script type="text/javascript" src="<%=path %>/js/form/multiselect.js"></script>
<!--截取文字start-->
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>

<script language="javascript">
function ok(){
	var access=false;
	access=$('#form1').validationEngine({returnIsValid:true});
	if(access){
		if(confirm("您确定要提交吗?")){
			this.form1.action = "doModifyPlansales.action";
			this.form1.method = "post";
			this.form1.submit();
			//top.Dialog.close();
			return;
		}
	}
	//return access;
}
function validation(id){
	var obj1=document.getElementsByName(id);
	if(obj1.length>1){
		for(var i=0;i<obj1.length;i++){
			if(obj1[i].value!="")
			obj1[i].style.border="";
		}
	}
	if(obj1.length==1){
		var obj=document.getElementById(id);
		if(obj.value!=""){
		obj.style.border="";
		}
	}
}
function setContractMoney(){
	
	var sales = document.getElementsByName("salesMoney");
	var sumContractMoney=0;
	for(var i=0;i<sales.length;i++){
		sumContractMoney=sumContractMoney+Number(sales[i].value);
	}
	document.form1.contractMoney.value=sumContractMoney;
	var   txtRange   =   document.selection.createRange(); 
	txtRange.moveStart(   "character",   document.form1.contractMoney.value.length); 
	txtRange.moveEnd(   "character",   0   ); 
	txtRange.select(); 
	//planManager.modifyPlanSalesMoney(pid,document.getElementById(pid+"_salesMoney").value);
	 
}
function setSalesRatio(pid,procurementMoney){
	var	salesRatio=document.getElementById("salesRatioo");
	var	tags=document.getElementById("tags");
	alert(tags);
	/* if(salesRatio.value<1){
		alert("销售费率小于1％,请确认销售金额输入无误!");
	} */
	document.getElementById(pid+"_salesMoney").value=Number(procurementMoney).add((Number(salesRatio.value).div(100)).mul(Number(procurementMoney)));
}

function setSalesRatio1(){
	var	salesRatio=document.getElementById("salesRatioo");
	var	tags=document.getElementById("tags");
	
	
	var temp;	
	if(tags.value!=null && tags.value!=""){
		temp = tags.value.split(',');	
	} 
	if(temp.length > 0){
		for(var i=0; i<temp.length; i++){
			//var procurementMoney = document.getElementById(temp[i]+"_procurementMoney").value;
			//document.getElementById(temp[i]+"_salesMoney").value=Number(procurementMoney).add((Number(salesRatio.value).div(100)).mul(Number(procurementMoney)));
			var procurementPrice = document.getElementById(temp[i]+"_procurementPrice").value;
			var PriceRatio = Number(Number(procurementPrice).add((Number(salesRatio.value).div(100)).mul(Number(procurementPrice)))).toFixed(2);
			document.getElementById(temp[i]+"_salesMoney").value = Number(Number(PriceRatio).mul(Number(document.getElementById(temp[i]+"_contractNum").value))).toFixed(2);
		}
			
	}
	setContractMoney();
}

$(function(){
	$("#copyBtn").live("click",function(){
		//克隆按钮所在的tr并添加到table的末尾
		$(this).parents("tr").clone(true).appendTo($(this).parents("table"))
		//找到table最后一个tr的最后一个td中的button按钮
		var $lastBtn=$(this).parents("table").find("tr").last().find("td").last().find("input[type='button']");
		//更改按钮的显示文字
		$lastBtn.val("删除行");
		//修正按钮在复制时产生的样式错误
		$lastBtn.removeClass("button_hover");
		$lastBtn.addClass("button")
		//对该按钮重新监听点击事件
		$lastBtn.click(function(e){
			//阻止默认行为，即复制行为
			e.stopPropagation();
			//将所在的行删除
			$(this).parents("tr").remove()
		})
	})
})

function pm(){
	var cn = document.getElementById("contractNum").value;
	if(cn!=null && cn!=""){
		salesContractManager.findSalesContractListByContractNum(
				cn,
				function(data){
					if(data.length>0){
						top.Dialog.alert("合同编号已存在");
						document.getElementById("contractNum").value = "";
						return;
					}
		});
	}
}
function changeSaleRate(){
	var sumSaleMoney = 0,sumContractMoney = 0;
	$("input[name='salesMoney']").each(function(i){
		var oneSale = $(this).val();
		sumSaleMoney = sumSaleMoney*1 + oneSale*1;
	});
	$("input[name='sprocurementMoney']").each(function(i){
		var oneContract = $(this).val();
		sumContractMoney = sumContractMoney*1 + oneContract*1;
	});
	var rate = (sumSaleMoney*1-sumContractMoney*1)/sumContractMoney;
	rate = Math.round(rate*1000)/1000 ; 
	$("#salesRatioo").val(rate*100);
}
</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="tags" id="tags" value="<%=request.getParameter("tags") %>" />
<input type="hidden" name="salesContract.orgId" value="${person.org.id }" />
<input type="hidden" name="salesContract.signFlag" value="已签订" />
<input type="hidden" name="salesContract.reportCompId_sc" value="${reportCompId }" />
<input type="hidden" name="salesContract.reportCompName_sc" value="${reportCompName }" />
<input type="hidden" name="ccflag" id="ccflag" value="" />
<div class="box1" panelWidth="990">
	<table class="tableStyle"   id="ContractMoney_Calculation">
		<tr>
			<th colspan="13">物资列表</th>
		</tr>
		<tr>
			<!-- <td width="30">ID</td> -->
			<td width="80"> 计划编号</td>
			<td width="70"> 物资类别</td>
			<td width="80"> 物资代码</td>
			<td width="80"> 物资名称</td>
			<td width="80"> 规格型号</td>
			<td width="80"> 材质</td>
			<td width="80"> 标准号</td>
			<td width="80"> 供应商</td>
			<td width="80"> 寻源数量</td>
			<td width="50"> 采购单价</td>
			<td width="80"> 要求到货日期</td>
			<td width="80"> 采购金额(元)(含税)</td>
			<td width="80"> 销售金额(元)(含税)</td>	
		</tr>
		<c:if test="${!empty planList}">
			<c:forEach items="${planList}" var="plan">  
				<tr >
					<%-- <td align="left"> ${plan.id}</td> --%>
					<td align="left"><span class="text_slice" style="width:70px;" title="${plan.planNum }">${plan.planNum }</span></td>
					<td align="left"><span class="text_slice" style="width:70px;" title="${plan.itemType }">${plan.itemType }</span></td>
					<td align="left"> ${plan.itemsNum }
					<input type="hidden" id="${plan.id}_contractNum" name="scontractNum" value="${plan.contractNum}"/>
					</td>
					<td align="left"><span class="text_slice" style="width:50px;" title="${plan.itemsName}">${plan.itemsName}</span></td>
					<td align="left"><span class="text_slice" style="width:70px;" title="${plan.modelType }">${plan.modelType }</span></td>
					<td align="left"><span class="text_slice" style="width:70px;" title="${plan.material }">${plan.material }</span></td>
					<td>${plan.standardNum }</td>
					<td><span class="text_slice" style="width:70px;" title="${plan.supplier.name }">${plan.supplier.name }</span></td>
					<td>${plan.contractNum }</td>
					<td><fmt:formatNumber value="${plan.procurementPrice }" pattern="#0.00"/>
					<input type="hidden" id="${plan.id}_procurementPrice" name="sprocurementPrice" value="${plan.procurementPrice}"/>
					</td>
					<td><fmt:formatDate value="${plan.arrivalDate }" pattern="yyyy-MM-dd"  /></td>
					<td align="left"> <fmt:formatNumber value="${plan.procurementMoney}" type="currency"/>
					<input type="hidden" id="${plan.id}_procurementMoney" name="sprocurementMoney" value="${plan.procurementMoney}"/>
					</td>
					<td align="left" ><input type="text" id="${plan.id}_salesMoney" onblur="changeSaleRate()" class=" validate[required]" name="salesMoney"  style="width:70px;" value="<fmt:formatNumber value="${plan.procurementMoney*plan.salesRatio}" pattern="#0.00"/>"/>
					<input type="hidden" name="pid" value="${plan.id}"/></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</div>
<div class="box1" panelWidth="990">
	<table class="tableStyle"  useClick="false"  transMode="true" >
		<tr>
			<td>合同名称：<span class="star">*</span></td><td><input type="text" name="salesContract.salesContractName" id="contractName"  class=" validate[required]" style="width:200px;"/></td>
			<td>销售费率：<span class="star">*</span></td><td><input type="text" name="salesRatioo" id="salesRatioo"  class=" validate[required]" onblur="setSalesRatio1()"/>%</td>
		</tr>
		<tr>
			<td>合同编号：<span class="star">*</span></td><td><input type="text" name="salesContract.contractNum" id="contractNum"  class=" validate[required]" onblur="pm()"/>   </td>
			<td>合同签订日期:<span class="star">*</span></td><td>
			 <input  id="signingDate" name="salesContract.signingDate"  value="" class="date"/>
			</td>
		</tr>
		<tr>
			<td>合同金额(含税)：<span class="star">*</span></td>
			<td ><input type="text" name="contractMoney" id="contractMoney"  class=" validate[required]" onfocus="setContractMoney()"/>(元)</td>
		
			<td>合同签订单位：<span class="star">*</span></td>
			<td >${reportCompName }
				<input type="hidden" name="salesContract.signComp" value="${reportCompName }"></input>
			</td>
		</tr>
		<tr>
			<td>合同质保金额：</td><td><input type="text" name="salesContract.qualityMoney" id="qualityMoney" value="0" />(元)</td>	
			<td>合同质保日期：</td><td>
			<input  id="qualityDate" name="salesContract.qualityDate"  value="" class="date"/>
			</td>			
		</tr>
		<tr>
			<td>供应商：</td><td><span class="text_slice" style="width:350px;" title="${supplierNames}">${supplierNames}</span></td>		
			<td>到货地址：</td><td><textarea style="width:250px;height:50px;" name="salesContract.arrivalAddress"></textarea></td>	
		</tr>
		<tr>
			<td colspan="4" align="center">
			<input type="button"  onclick="ok()" value=" 提 交 "/>
				<!-- <input type="submit" value=" 提 交 "/> -->
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
		</table>
</div>
</form>
</body>