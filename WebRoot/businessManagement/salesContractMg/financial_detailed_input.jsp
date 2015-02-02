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
<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>

<!--截取文字start-->
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->
<script type="text/javascript" src="<%=path %>/js/Calendar3.js"></script>
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>
<script language="javascript">
function ok(){
	var a = false;
	a = validationMoney();
	if(a){
		
		if(confirm("您确定要提交吗?")){
			this.form1.action = "doModifysales.action";
			this.form1.submit();
			//top.Dialog.close();
			return;
		}
	}
	//return a;
}
function getTotalContractReceivableMoney(){
	var tempInput = document.getElementById("kp").getElementsByTagName("input");
	var totalbillingMoney =0;
	for(var i=0;i<tempInput.length/4;i++){
		if(tempInput[i*4].value!=null && tempInput[i*4].value!=''){
			totalbillingMoney =Number(totalbillingMoney).add(Number(tempInput[i*4].value));
		}
	}
	var tempPayed = document.getElementsByName("contractReceivedMoney");
	var totalcontractReceivedMoney =0;
	for(var i=0;i<tempPayed.length;i++){
		if(tempPayed[i].value!=null && tempPayed[i].value!=''){
			totalcontractReceivedMoney = Number(totalcontractReceivedMoney).add(Number(tempPayed[i].value));
		}
	}
	var temp = Number(totalcontractReceivedMoney).subtr(Number(totalbillingMoney));
	
	document.getElementById("contractReceivableMoney").value= temp>0 ? temp : 0;
	var   txtRange   =   document.selection.createRange(); 
	txtRange.moveStart(   "character",   document.getElementById("contractReceivableMoney").value.length); 
	txtRange.moveEnd(   "character",   0   ); 
	txtRange.select(); 
}
function validationMoney(){
	var a = true;
	var tempInput = document.getElementById("kp").getElementsByTagName("input");
	var totalCredit =0;
	var aa = 0;
	var cc = 0;
	for(var i=0;i<tempInput.length/4;i++){
		if(tempInput[i*3].value!=null && tempInput[i*4].value!=''){
			totalCredit = Number(totalCredit).add(Number(tempInput[i*4].value));
			aa++;
		}
		if(tempInput[i*4+1].value!=null && tempInput[i*4+1].value!=''){
			cc++;
		}
	}
	var tempPayed = document.getElementsByName("contractReceivedMoney");
	var totalcontractReceivedMoney =0;
	for(var i=0;i<tempPayed.length;i++){
		if(tempPayed[i].value!=null && tempPayed[i].value!=''){
			totalcontractReceivedMoney = Number(totalcontractReceivedMoney).add(Number(tempPayed[i].value));
		}
	}
	var totalMoney = Number(document.getElementById("contractMoney").value);
	if(aa != cc){
		top.Dialog.alert("开票金额 与 开票日期必须对应");
		a = false;
	}else if(Number(totalCredit) > Number(totalMoney)){
		top.Dialog.alert("开票金额 不能大于 合同总额");
		a = false;
	}
	if(a)
		document.getElementById("totalContractInvoiceMoney").value = totalCredit;
	//alert(totalCredit);
	return a;
}

//没用
function setContractMoney(pid){
	
	var sales = document.getElementsByName("salesMoney");
	var sumContractMoney=0;
	for(var i=0;i<sales.length;i++){
		sumContractMoney=sumContractMoney+Number(sales[i].value);
	}
	document.form1.contractMoney.value=sumContractMoney;
	planManager.modifyPlanSalesMoney(pid,document.getElementById(pid+"_salesMoney").value);
	
	
}
function setSalesRatio(pid,procurementMoney){
	var	salesRatio=document.getElementById(pid+"_salesRatio");
	if(salesRatio.value<1){
		alert("当前对象销售比率小于1,请确认销售金额输入无误!");
	}
	document.getElementById(pid+"_salesMoney").value=salesRatio.value*procurementMoney;
	planManager.modifyPlan(pid,salesRatio.value,procurementMoney);
	
}
//移除合同下的计划
function del(planid,salesContractId){
		this.form1.planid.value=planid;
		this.form1.salesContractId.value=salesContractId;
		this.form1.action = "update_plan_sid.action";
		this.form1.submit();
		top.Dialog.close();
		return;
	}
	
$(function(){
	var s = document.getElementById("cflag").value;
	if(s!=""&&s=="addSuccess"){
		top.Dialog.close();
	}
	$("#copyBtn").live("click",function(){
		//克隆按钮所在的tr并添加到table的末尾
		$(this).parents("tr").clone(true).appendTo($(this).parents("table"));
		//找到table最后一个tr的最后一个td中的button按钮
		var $lastBtn=$(this).parents("table").find("tr").last().find("td").last().find("input[type='button']");
		//更改按钮的显示文字
		$lastBtn.val("删除行");
		//修正按钮在复制时产生的样式错误
		$lastBtn.removeClass("button_hover");
		$lastBtn.addClass("button");
		//对该按钮重新监听点击事件
		$lastBtn.click(function(e){
			//阻止默认行为，即复制行为
			e.stopPropagation();
			//将所在的行删除
			$(this).parents("tr").remove();
		});
	});
});
function delContractReceivedMoney(collect,salesContractId){
	this.form1.salesContractId.value=salesContractId;
	this.form1.collect.value=collect;
	this.form1.action = "del_contractReceivedMoney.action";
	this.form1.submit();
	top.Dialog.close();
	return;
}
function delBilling(billingId){
	
	this.form1.billingId.value=billingId;
	this.form1.action = "del_billing.action";
	//alert(document.getElementById("form1").target);
	document.getElementById("form1").target = "";
	//alert(document.getElementById("form1").target);
	this.form1.submit();
	return;
}
function saveBilling(){
	if(validationMoney()){
		var tempInput = document.getElementById("kp").getElementsByTagName("input");
		for(var i=0;i<tempInput.length/4;i++){
			if(tempInput[i*4].value==null || tempInput[i*4].value==''){
				top.Dialog.alert("开票金额不能为空");
				return;
			}
		}
		var salesContractId = document.getElementById("salesContractId").value;
		var billingMoney = document.getElementById("billingMoney").value;
		var billingDate = document.getElementById("billingDate").value;
		this.form1.action = "saveBilling.action?salesContractId="+salesContractId+"&billingMoney="+billingMoney+"&billingDate="+billingDate;
		this.form1.submit();
		return;
	}
}
function openWin1(billingId){
	var salesContractId = document.getElementById("salesContractId").value;
	var sbillingDate = document.getElementById(billingId+"_billingDate").value;
	var am = document.getElementById("am").value;
	var diag = new top.Dialog();
	diag.Title = "开票明细管理";
	diag.URL = "toAddBillingByArrival.action?scid="+salesContractId+"&id="+billingId+"&billingDate="+sbillingDate;
	//diag.URL = "toAddBillingByArrival.action?scid="+salesContractId;
	diag.Height=480;
	diag.Width = 1100;
	diag.MessageTitle="开票明细管理";
	diag.Message="开票明细管理";
	diag.CancelEvent = function(){
		window.location.href = "getPlans.action?salesContractId="+salesContractId+"&am="+am;
		diag.close();
	};
	diag.show();
}
function openWin2(){
	var salesContractId = document.getElementById("salesContractId").value;
	var billingDate = document.getElementById("billingDate").value;
	var am = document.getElementById("am").value;
	if(billingDate==null || billingDate == ""){
		top.Dialog.alert("开票日期必填");
		return;
	}
	var diag = new top.Dialog();
	diag.Title = "开票明细管理";
	//diag.URL = "toAddBillingByArrival.action?scid="+salesContractId+"&id="+billingId;
	diag.URL = "toAddBillingByArrival.action?scid="+salesContractId+"&billingDate="+billingDate;
	diag.Height=480;
	diag.Width = 1100;
	diag.MessageTitle="开票明细管理";
	diag.Message="开票明细管理";
	diag.CancelEvent = function(){
		window.location.href = "getFinancialPlans.action?salesContractId="+salesContractId+"&am="+am;
		diag.close();
	};
	diag.show();
}
function init(){
	getTotalContractReceivableMoney();
}

</script>

<body onload="init()">
<form action="?" id="form1" name="form1" >
<input type="hidden" name="planid" value="" />
<input type="hidden" id="salesContractId" name="salesContractId" value="${SalesContract.id }" />
<input type="hidden" name="collect" value="" />
<input type="hidden" id="totalContractInvoiceMoney" name="totalContractInvoiceMoney" value="" />
<input type="hidden" name="billingId" value="" />
<input type="hidden" id="cflag" name="cflag" value="${cflag }"/>
<input type="hidden" id="am" name="am" value="${param.am }"/>
<input type="hidden" name="ccflag" id="ccflag" value="" />
<div class="box1" panelWidth="890">
	<table class="tableStyle" id="ContractMoney_Calculation">
		<tr>
			<th colspan="13">物资列表</th>
		</tr>
		<tr>
			<td width="80"> 计划编号</td>
			<td width="70"> 物资类别</td>
			<td width="80"> 物资代码</td>
			<td width="80"> 物资名称</td>
			<td width="80"> 规格型号</td>
			<td width="80"> 材质</td>
			<td width="80"> 供应商</td>
			<td width="80"> 寻源数量</td>
			<td width="80"> 采购金额</td>
			<td width="80"> 销售比率</td>
			<td width="80"> 销售金额</td>
			<td width="80"> 到货数量</td>
		</tr>
		<c:if test="${!empty planList}">
			<c:forEach items="${planList}" var="plan">  
				<tr>
					<td align="left"> ${plan.planNum}</td>
					<td align="left" style="word-wrap: break-word;word-break:break-all;"> ${plan.itemType }</td>
					<td align="left"><span class="text_slice" style="width:80px;" title="${plan.itemsName }"> ${plan.itemsNum }</span></td>
					<td align="left"><span class="text_slice" style="width:80px;" title="${plan.itemsName }">${plan.itemsName }</span></td>
					<td align="left"><span class="text_slice" style="width:80px;" title="${plan.modelType }"> ${plan.modelType }</span></td>
					<td align="left"><span class="text_slice" style="width:80px;" title="${plan.material }"> ${plan.material }</span></td>
					<td align="left"><span class="text_slice" style="width:80px;" title="${plan.supplier.name }">${plan.supplier.name }</span></td>
					<td align="left"><span class="text_slice" style="width:50px;" title="${plan.contractNum }">${plan.contractNum }</span></td>
					<td align="left"><fmt:formatNumber value="${plan.procurementMoney}" type="currency"/><span class="text_slice" style="width:40px;" title="${plan.arrivalAddress }">${plan.arrivalAddress }</span></td>
					<td align="left">${plan.salesRatio}</td>
					<td align="left"><fmt:formatNumber value="${plan.salesMoney}" type="currency"/><input type="hidden" name="pid" value="${plan.id}"/></td>
					<td>
					<c:forEach items="${m}" var="m">
						<c:if test="${m.key eq plan.id}">
						 <c:set var="mk" value="${m.value }"></c:set>
							${mk.arrivalNum }
						</c:if>
					</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty planList}">
				<tr >
					<td colspan="9"  align="left">该合同没有详细计划</td>
				</tr>
		</c:if>
	</table>
</div>
<div class="box1" panelWidth="890">
	<table class="tableStyle"   useClick="false">
		<c:if test="${!empty SalesContract}">
		<tr>
			<td  style="width:187px;">合同属性</td><td>销售</td><td>合同名称：</td><td colspan="2">${SalesContract.salesContractName}</td>
		</tr>
		<tr>
			<td>部门:</td>
			<td>
				${SalesContract.orgName}
			</td>
			
			<td>合同签订日期:</td><td colspan="2"><fmt:formatDate  value="${SalesContract.signingDate}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<td>合同编号</td><td>${SalesContract.contractNum}</td>
			<td>提报单位</td>
			<td>${reportCompName}</td>
			
		</tr>
		
		<tr>
			<td>合同金额</td><td><fmt:formatNumber value="${SalesContract.contractMoney}" type="currency"/><input type="hidden" id="contractMoney" name="contractMoney" value="${SalesContract.contractMoney}"></input></td>
			<td>合同质保金额</td><td colspan="2"><fmt:formatNumber value="${SalesContract.qualityMoney}" type="currency"/></td>			
		</tr>
		<tr>
			<td>到货地址：</td>
			<td colspan="3">${SalesContract.arrivalAddress}</td>
		</tr>
		<tr>
			<td><b>到货总额：</td>
			<td><fmt:formatNumber value="${param.am }" type="currency"/></b></td>
			<td>供应商：</td><td colspan="2">${supplierNames}</td>			
		</tr>
		</c:if>
	</table>
	<table class="tableStyle"  id="kp"  useClick="false">
		<c:if test="${!empty billingList}">
			<c:forEach items="${billingList}" var="billing">
				<tr>
					<td style="width:187px;">合同开票金额<c:if test="${!empty billing.billingDetail}">【已绑定到货】</c:if></td>
					<td style="width:178px;">
					<fmt:formatNumber value="${billing.billingMoney}" pattern="#0.00"/>
					<input type="hidden"  name="${billing.id}_billingMoney" 
					value="<fmt:formatNumber value="${billing.billingMoney}" pattern="#0.00"/>"  
					onblur="getTotalContractReceivableMoney()" />
					</td>
					<td>开票日期</td>
					<td><input name="${billing.id}_billingDate" id="${billing.id}_billingDate" value="<fmt:formatDate value="${billing.billingDate}" pattern="yyyy-MM-dd"/>" type="text"  size="10" maxlength="10" onclick="new Calendar().show(this);" readonly="readonly"/></td>
					<td style="width:50px;"  >
					<input type="button" value="开票明细" onclick='openWin1(${billing.id})'/>
					</td>
					<td style="width:50px;" >
					<c:if test="${empty param.b }">
						<c:if test="${empty billing.billingDetail}">
							<input type="button" value="删除行" onclick='top.Dialog.confirm("移除该开票金额吗？此操作不可逆",function(){delBilling(${billing.id})});'/>
						</c:if>
						<c:if test="${!empty billing.billingDetail}">
							<input type="button" value="删除行" onclick='top.Dialog.alert("请先删除已绑定的到货信息");'/>
						</c:if>
					</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty param.b }">
		<tr>
			<td style="width:187px;">合同开票金额</td><td style="width:178px;"><input type="text" id="billingMoney" name="billingMoney" value="" onblur="getTotalContractReceivableMoney()"  disabled="disabled"/></td>
			<td>开票日期</td>
			<td>
			<input  id="billingDate" name="billingDate"  value="" class="date"/>
			</td>
			<td style="width:50px;" ></td>
			<td style="width:50px;" ><input type="button" value="添加开票明细" onclick='openWin2()'/></td>
			<input type="hidden" value="tempsss" />
			<!-- <td style="width:50px;" ><input type="button" value="追加" id="copyBtn"/></td> -->
		</tr>
		</c:if>
	</table>
	<table class="tableStyle"  useClick="false">
	
		<c:if test="${!empty collectList}">
			<c:forEach items="${collectList}" var="p">
				<tr >
				<td style="width:187px;">合同已收金额</td><td style="width:178px;"><input type="text" name="contractReceivedMoney"  value="<fmt:formatNumber value="${p.contractReceivedMoney}" pattern="#0.00"/>" onblur="getTotalContractReceivableMoney()"/></td>
				<td style="width:87px;">收款方式</td>
					<td>
					<select name="payedStyle" class="default">
							<option value="01" <c:if test="${p.payedStyle == '01' }">selected="selected"</c:if> >电汇</option>
							<option value="02" <c:if test="${p.payedStyle == '02' }">selected="selected"</c:if> >承兑</option>
					</select>
				</td>
				<td style="width:50px;"  ><c:if test="${empty param.b }"><input type="button" value="删除行" id="${collect}" onclick='top.Dialog.confirm("移除该已收金额吗？",function(){delContractReceivedMoney(${p.contractReceivedMoney},${SalesContract.id})});'/></c:if></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty param.b }">
			<c:if test="${!empty SalesContract}">
			<tr>
				<td  style="width:187px;">合同已收金额</td><td style="width:178px;"><input type="text"  name="contractReceivedMoney" value=""  onblur="getTotalContractReceivableMoney()"/></td>
				<td style="width:87px;">收款方式</td>
				<td>
						<select  name="payedStyle" class="default">
								<option value="01" <c:if test="${p.payedStyle == '01' }">selected="selected"</c:if> >电汇</option>
								<option value="02" <c:if test="${p.payedStyle == '02' }">selected="selected"</c:if> >承兑</option>
						</select>
				</td>
				<td  style="width:50px;"  ><input type="button" value="追加" id="copyBtn"/></td>
			</tr>
			</c:if>
		</c:if>
	</table>
	<table class="tableStyle"  useClick="false">
		<c:if test="${!empty SalesContract}">
		<tr>
			<td  style="width:187px;"><span class="float_left">合同应收金额</span>
			<span class="img_light" title="自动计算项" /></td>
			<td  style="width:548px;"><input type="text" id="contractReceivableMoney" title="自动计算项"  name="contractReceivableMoney" value="${SalesContract.contractReceivableMoney}" onfocus="getTotalContractReceivableMoney()"/>
			</td>
		</tr>
		<c:if test="${empty param.b }">
		<tr>
			<td colspan="4" align="center">
			<input type="button"  onclick="ok()" value=" 提 交 "/>
				<!-- <input type="submit" value="修 改"/> -->
			</td>
		</tr>
		</c:if>
		</c:if>
		
	</table>
</div>
</form>
</body>