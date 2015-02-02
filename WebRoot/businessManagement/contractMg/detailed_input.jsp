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
<!--截取文字start-->
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>
<script language="javascript">

function oks(){
	
		var a = true;
	
		a = validationMoney(a);
		if(a){
			if(confirm("您确定要提交吗?")){
				this.form1.action = "doModifyProcurementContract.action";
				this.form1.submit();
				//top.Dialog.close();
				return;
			}
		}
		/*this.form1.action = "doModifyProcurementContract.action";
		this.form1.submit();*/
}
function checkArrival(){
	var am = document.getElementById("am").value;
	if(am <= 0){
		//top.Dialog.alert("没有到货信息");
		alert("没有到货信息,是否继续");
	}
	//return;
}
function checkArrival1(){
	var am = document.getElementById("am").value;
	var tempInput = document.getElementById("gz").getElementsByTagName("input");
	
	var totalCredit =0;
	for(var i=0;i<tempInput.length/3;i++){
		if(tempInput[i*3].value!=null && tempInput[i*3].value!=''){
			totalCredit +=Number(tempInput[i*3].value);
		}
	}
	if(totalCredit > am){
		//top.Dialog.alert("挂帐金额大于到货金额");
		alert("挂帐金额大于到货金额");
	}
	return;
}
function validationMoney(a){
		
		var tempInput = document.getElementById("gz").getElementsByTagName("input");
		var totalCredit =0;
		var aa = 0;
		var cc = 0;
		for(var i=0;i<tempInput.length/4;i++){
			if(tempInput[i*3].value!=null && tempInput[i*3].value!=''){
				totalCredit +=Number(tempInput[i*3].value);
				aa++;
			}
			if(tempInput[i*3+1].value!=null && tempInput[i*3+1].value!=''){
				cc++;
			}
		}
		var tempPayed = document.getElementsByName("payed");
		var totalPayed =0;
		for(var i=0;i<tempPayed.length;i++){
			if(tempPayed[i].value!=null && tempPayed[i].value!=''){
				totalPayed +=Number(tempPayed[i].value);
			}
		}
		var totalMoney = Number(document.getElementById("totalMoney").value);
		
		if(aa != cc){
			top.Dialog.alert("挂帐金额 与 挂帐日期必须对应");
			a = false;
		}else if(totalCredit > totalMoney){
			top.Dialog.alert("挂帐金额 不能大于 合同总额");
			a = false;
		}else if(totalPayed > totalMoney){
			top.Dialog.alert("已付金额 不能大于 合同总额");
			a = false;
		}
		
		return a;
}


function getSum(){
	
	var tempInput = document.getElementById("gz").getElementsByTagName("input");
	var totalcontractCreditMoney =0;
	for(var i=0;i<tempInput.length/3;i++){
		if(tempInput[i*3].value!=null && tempInput[i*3].value!=''){
			totalcontractCreditMoney +=Number(tempInput[i*3].value);
		}
	}
	//alert(totalcontractCreditMoney);
	
	var n = document.getElementsByName("payed");
	var o = 0;
	var tempo = 0;
		for(o=0;o<n.length;o++){
			if(n[o].value!=null && n[o].value!=''){
				tempo +=Number(n[o].value);
			}
			
		}
	//alert(tempo);
	var x = document.getElementById("advance");
	var qualityMoney = document.getElementById("qualityMoney");
	var sp = accSubtr(Number(totalcontractCreditMoney).add(Number(x.value)),tempo.add(Number(qualityMoney.value)));
	//alert(accSubtr(Number(totalcontractCreditMoney).add(Number(x.value)),tempo.add(Number(qualityMoney.value))));
	document.getElementById("shouldPayment").value = sp<0?0:sp;
	//this.form1.shouldPayment.value= accSubtr(temp,tempo.add(Number(qualityMoney.value)).add(Number(x.value)));
	var   txtRange   =   document.selection.createRange(); 
	txtRange.moveStart(   "character",   document.getElementById("shouldPayment").value.length); 
	txtRange.moveEnd(   "character",   0   ); 
	txtRange.select(); 
}

function setContractMoney(salesRatio){
	if(salesRatio<1){
		alert("当前对象销售比率小于1,请确认销售金额输入无误!");
	}
	var sum=document.getElementById("ContractMoney_Calculation");
	var inputStr=sum.getElementsByTagName("input");
	var sumContractMoney=0;
	for(var i=0;i<inputStr.length;i++){
		sumContractMoney=sumContractMoney+Number(inputStr[i].value);
	}
	document.form1.contractMoney.value=sumContractMoney;
}
function del(planId){
		this.form1.planId.value=planId;
		document.getElementById("form1").target = "";
		var arrivalnum = document.getElementById("arrivalnum_"+planId) == null ? 0 : document.getElementById("arrivalnum_"+planId).value;
		if(arrivalnum != 0){
			top.Dialog.alert("计划已执行，请先删除到货信息");
			return;
		}
		var procurementPrice = document.getElementById("procurementPrice_"+planId) == null ? 0 : document.getElementById("procurementPrice_"+planId).value ;
		var am = document.getElementById("am") == null ? 0 :document.getElementById("am").value;
		var arm = Number(arrivalnum).mul(Number(procurementPrice));
		var tp = Number(arm).subtr(Number(am));
		document.getElementById("am").value = tp > 0 ? tp : 0;
		this.form1.action = "del_plan_pid.action";
		this.form1.submit();
		return;
	}
function delcc(ccid){
		this.form1.ccid.value=ccid;
		this.form1.action = "del_contranctCredit.action";
		this.form1.submit();
		top.Dialog.close();
		return;
	}
function delp(payNum,procurementContractId){
		this.form1.payNum.value=payNum;
		this.form1.procurementContractId.value=procurementContractId;
		this.form1.action = "del_payed.action";
		
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


</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="ccid" value="" />
<input type="hidden" id="procurementContractId" name="procurementContractId" value="${procurementContract.id}" />
<input type="hidden" name="payNum" value="" />
<input type="hidden" name="planId" value="" />
<input type="hidden" id="am" name="am" value="${param.am }" />
<input type="hidden" id="b" name="b" value="${param.b }" />
<input type="hidden" id="cflag" name="cflag" value="${cflag }"/>
<input type="hidden" id="ccflag" name="ccflag" value=""/>
<div class="box1" panelWidth="990">
	<table class="tableStyle" id="ContractMoney_Calculation"  useClick="false"  >
		<tr>
			<th colspan="12">物资列表</th>
		</tr>
		<tr>
			<td> 计划编号</td>
			<td> 物资类别</td>
			<td> 物资代码</td>
			<td> 物资名称</td>
			<td> 规格型号</td>
			<td> 材质</td>
			<td> 使用单位</td>
			<td> 合同数量</td>
			<td> 采购单价(元)</td>
			<td> 采购金额(元)</td>
			<td> 到货数量</td>
			
			<td>操作</td>	
		</tr>
		<c:if test="${!empty planList}">
			<c:forEach items="${planList}" var="plan">  
				<tr>
				<td align="left"><span class="text_slice" style="width:80px;" title="${plan.planNum }">${plan.planNum }</span></td>
					<td align="left"><span class="text_slice" style="width:70px;" title="${plan.itemType }"> ${plan.itemType }</span></td>
					<td align="left"><span class="text_slice" style="width:80px;" title="${plan.itemsNum }"> ${plan.itemsNum }</span></td>
					<td align="left"><span class="text_slice" style="width:80px;" title="${plan.itemsName }">${plan.itemsName }</span></td>
					<td align="left"><span class="text_slice" style="width:80px;" title="${plan.modelType }"> ${plan.modelType }</span></td>
					<td align="left"><span class="text_slice" style="width:80px;" title="${plan.material }"> ${plan.material }</span></td>
			
					<td align="left"><span class="text_slice" style="width:50px;" title="${plan.reportComp }">${plan.reportComp }</span></td>
					<td align="left"> ${plan.contractNum }</td>
					<td align="left"> <fmt:formatNumber value="${plan.procurementPrice}" type="currency"/>
					<input type="hidden" id="procurementPrice_${plan.id }" value="${plan.procurementPrice}"/></td>
					<td align="left"> <fmt:formatNumber value="${plan.procurementMoney}" type="currency"/></td>
					<td>
					<c:forEach items="${m}" var="m">
						<c:if test="${m.key eq plan.id}">
						 <c:set var="mk" value="${m.value }"></c:set>
							${mk.arrivalNum }<input type="hidden" id="arrivalnum_${plan.id }" value="${mk.arrivalNum }"/>
						</c:if>
					</c:forEach>
					</td>
					
					<td align="left">
					<c:if test="${my:method(login.id,'planmgr',3)}">
						<span class="img_delete hand" title="删除" onclick='top.Dialog.confirm("移除该合同下的该计划吗？",function(){del(${plan.id})});'></span>
					</c:if>
					</td>
					
					
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty planList}">
				<tr >
					<td colspan="12"  align="left">该合同没有详细计划</td>
				</tr>
		</c:if>
	</table>
</div>
<div class="box2" panelWidth="990" panelTitle="采购合同台帐" showStatus="false" roller="true">
	<table class="tableStyle"  useClick="false"  >
		<tr>
			<td>合同名称：</td>
			<td >${procurementContract.contractName}</td>
			<td>签订日期：</td>
			<td><fmt:formatDate value="${procurementContract.signingDate}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<td>合同编号：</td>
			<td>${procurementContract.contractNum}</td>
			<td>合同总额：</td>
			<td><fmt:formatNumber value="${procurementContract.totalMoney}" type="currency"/><input type="hidden" id="totalMoney" name="totalMoney" value="${procurementContract.totalMoney}" />&nbsp;&nbsp;&nbsp;(元)</td>
		</tr>
		<tr>
			<td>供应商：</td>
			<td>${procurementContract.suppliersName}</td>
			
			<td>采购方案：</td>
			<td>
				  <c:if test="${procurementContract.procurementWay == '01' }">公开招标</c:if> 
				  <c:if test="${procurementContract.procurementWay == '02' }">邀请竞标</c:if> 
				  <c:if test="${procurementContract.procurementWay == '03' }">竞争性谈判</c:if>
				  <c:if test="${procurementContract.procurementWay == '04' }">单一来源</c:if> 
				  <c:if test="${procurementContract.procurementWay == '05' }">寻比价</c:if> 
			</td>
			 
		</tr>
		<tr>
			<td width="10%">业务部门：</td>
			<td>${procurementContract.orgName } </td>
			<td width="10%">合同应付金额：</td>
			<td><input id="shouldPayment" type="text" name="shouldPayment"  value="<fmt:formatNumber value="${procurementContract.shouldPayment}" pattern="#0.00"/>"  onfocus="getSum()" />&nbsp;&nbsp;&nbsp;(元)</td>
		</tr>
		<tr>
			<td>合同质保金额：</td>
			<td><fmt:formatNumber value="${procurementContract.qualityMoney}" type="currency"/><input id="qualityMoney" type="hidden" name="qualityMoney" value="${procurementContract.qualityMoney}"/>&nbsp;&nbsp;&nbsp;(元)</td>
			<td>预付款：</td>
			<td><fmt:formatNumber value="${procurementContract.advance}" type="currency"/><input id="advance" type="hidden" name="advance"  value="${procurementContract.advance}"/>&nbsp;&nbsp;&nbsp;(元)</td>
			
		</tr>
		<tr>
			<td><b>到货总额：</td>
			<td ><fmt:formatNumber value="${param.am }" type="currency"/></b></td>
			<td>质保日期：</td>
			<td><fmt:formatDate value="${procurementContract.qualityDate}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			
			<td>合同到货日期：</td>
			<td><fmt:formatDate value="${procurementContract.arrivalDate}" pattern="yyyy-MM-dd"/></td>
			<td></td>
			<td></td>
		</tr>
		

	</table>
	
	<table id="gz" class="tableStyle"  useClick="false"  >
	
	<c:if test="${!empty ccList}">
		<c:forEach items="${ccList}" var="cc">
			<tr>
			<td style="width:80px;">挂帐金额</td><td style="width:300px;"><input type="text" name="${cc.id}_contractCreditMoney"  value="<fmt:formatNumber value="${cc.contractCreditMoney }" pattern="#0.00"/>" onblur="getSum()"/>(元)</td>
			<td style="width:80px;">挂帐日期</td>
			<td style="width:150px;" >
			<input id="control_date" name="${cc.id}_contractCreditDate"  value="<fmt:formatDate value="${cc.contractCreditDate }" pattern="yyyy-MM-dd"/>" class="date"/>
			<!-- 
			<input name="${cc.id}_contractCreditDate" type="text" id="control_date" size="10"
                        maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" value="<fmt:formatDate value="${cc.contractCreditDate}" pattern="yyyy-MM-dd"/>"/>
              -->           
            </td>
			<td><c:if test="${empty param.b }">
			<input type="button" value="删除行" id="${cc.id}" onclick='top.Dialog.confirm("移除该合同下的该项吗？",function(){delcc(${cc.id})});'/></c:if></td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty param.b }">
	<tr>
			<td style="width:80px;">挂帐金额</td><td style="width:300px;"><input type="text" name="contractCreditMoney" onfocus="checkArrival()" onblur="checkArrival1();getSum()" />(元)</td>
			<td style="width:80px;">挂帐日期</td>
			<td style="width:150px;" >
			<input id="control_date" name="contractCreditDate" class="date"/>
			<!--  value="<fmt:formatDate value="${cc.contractCreditDate }" pattern="yyyy-MM-dd"/>" 
			<input name="contractCreditDate" type="text" id="control_date" size="10"  
                        maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />
             -->
                       </td>
			<td><!--<input type="button" value="追加" id="copyBtn"/>--></td>
	</tr>
	</c:if>
	</table>
	<table id="yf" class="tableStyle"  useClick="false"  >
		
		<c:if test="${!empty payedList}">
			<c:forEach items="${payedList}" var="p">
				<tr>
					<td style="width:80px;">已付金额</td>
					<td style="width:150px;">
						<input type="text" name="payed" value="<fmt:formatNumber value="${p.payed }" pattern="#0.00"/>" onblur="getSum()"/>(元)
					</td>
					<td style="width:80px;">
					付款日期
					</td>
					<td style="width:90px;">
					<input  id="payedDate" name="payedDate"  value="<fmt:formatDate value="${p.payedDate }" pattern="yyyy-MM-dd"/>" class="date"/>
					<!-- 
						<input name="payedDate" type="text" size="10"  
                        maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" value="${p.payedDate }"/>
                     -->
					</td>
					<td style="width:80px;">付款方式</td>
					<td style="width:150px;">
					<select id="payedStyle" name="payedStyle" >
							<option value="01" <c:if test="${p.payedStyle == '01' }">selected="selected"</c:if> >电汇</option>
							<option value="02" <c:if test="${p.payedStyle == '02' }">selected="selected"</c:if> >承兑</option>
					</select>
					</td>
					
					<td><c:if test="${empty param.b }"><input type="button" value="删除行" id="${p.payed}" onclick='top.Dialog.confirm("移除该已付金额吗？",function(){delp(${payed},${procurementContract.id})});'/></c:if></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty param.b }">
		<tr>
			<td style="width:80px;">已付金额</td><td style="width:150px;"><input type="text"  name="payed" onblur="getSum()"/>(元)</td>
			<td style="width:80px;">
				付款日期
			</td>
			<td style="width:90px;">
					<input  id="payedDate" name="payedDate" class="date"/>
					<!-- 
				<input name="payedDate" type="text" size="10"  
                      maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />
                     -->
			</td>
			<td style="width:80px;">付款方式</td>
			<td style="width:150px;">
				<select id="payedStyle" name="payedStyle" class="default">
					<option value="01">电汇</option>
					<option value="02">承兑</option>
				</select>
			</td>
			<td><input type="button" value="追加" id="copyBtn"/></td>
		</tr>
		</c:if>
	</table>
	
	<table class="tableStyle" transMode="true"  useClick="false"  >
		<tr>
			<td>备注：</td>
			<td colspan="3">
				<span class="float_left">
				<textarea style="width:400px;" name="procurementContract.remark" >${procurementContract.remark}</textarea>
				</span>
			</td>
		</tr>
		<c:if test="${empty param.b }">
		<tr>
			<td colspan="4">
				<input type="button"  onclick="oks()" value=" 提 交 "/>
				<!-- <input type="submit" value="提 交" /> -->
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
		</c:if>
	</table>
	
	</div>

</form>
</body>