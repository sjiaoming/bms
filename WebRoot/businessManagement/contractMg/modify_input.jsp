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
<!--多功能表格start-->
<link rel="stylesheet" type="text/css" href="../../js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="../../js/table/flexigrid/flexigrid.js"></script>
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path %>/js/Calendar3.js"></script>
<script type="text/javascript" src="<%=path %>/js/form/multiselect.js"></script>

<script type='text/javascript' src='dwr/interface/procurementContractManager.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<script language="javascript">
$(function(){
	//getSum();
	var s = document.getElementById("cflag").value;
	if(s!=""&&s=="addSuccess"){
		top.Dialog.close();
	}
	document.getElementById("cflag").value = "";
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
function ok(){
	var access=false;
	access=$('#form1').validationEngine({returnIsValid:true});
	if(access){
		if(confirm("您确定要提交吗?")){
			this.form1.action = "addContract.action";
			this.form1.method = "post";
			this.form1.submit();
			//top.Dialog.close();
			return;
		}
	}
	
	
	return access;
}

function ok_bak(){
	var access=false;
	access=$('#form1').validationEngine({returnIsValid:true});
	if(access){
		var advance = document.getElementById("advance");
		var totalMoney =  document.getElementById("totalMoney");
		var qualityMoney = document.getElementById("qualityMoney");
		var contractCreditMoney = document.getElementsByName("contractCreditMoney");
		
		var n = 0;
		var temp_contractCreditMoney = 0;
			for(n=0;n<contractCreditMoney.length;n++){
			
				temp_contractCreditMoney +=parseFloat(contractCreditMoney[n].value);
			}
		var su = parseFloat(advance.value)+parseFloat(qualityMoney.value)+temp_contractCreditMoney;
		
		if(su > parseFloat(totalMoney.value)){
			alert("预付＋质保＋挂帐 不能大于 合同额");
			access=false;
		}else{
			//getSum1();
			access = true;
		}
	}
	
	return access;
}
function setProcurementMoney(pid,feil){
	//alert((7.3).mul(8));
	document.getElementById("procurementMoney"+pid).value = (Number(document.getElementById("procurementPrice"+pid).value)).mul(Number(document.getElementById("planContractNum"+pid).value));
	
	var   txtRange   =   document.selection.createRange(); 
	txtRange.moveStart(   "character",   feil.value.length); 
	txtRange.moveEnd(   "character",   0   ); 
	txtRange.select(); 
	getSum();
}
function validation(){

	
}

function getSum(feil){
	var m = document.getElementsByName("procurementMoney");
	var i = 0;
	var temp = 0;
		for(i=0;i<m.length;i++){
			//temp +=parseFloat(m[i].value);
			temp = Number(temp).add(Number(m[i].value));
		}
	this.form1.totalMoney.value= temp;
	var   txtRange   =   document.selection.createRange(); 
	txtRange.moveStart(   "character",   feil.value.length); 
	txtRange.moveEnd(   "character",   0   ); 
	txtRange.select(); 
}

function getSum1(feil){

var m = document.getElementsByName("contractCreditMoney");
var n = document.getElementsByName("payed");
var x = document.getElementById("advance");
var qualityMoney = document.getElementById("qualityMoney");
var i = 0;
var temp = 0;
	for(i=0;i<m.length;i++){
		temp +=Number(m[i].value);
	}
var o = 0;
var tempo = 0;
	for(o=0;o<n.length;o++){
		tempo +=Number(n[o].value);
	}
this.form1.shouldPayment.value= accSubtr(temp.add(Number(x.value)),tempo.add(Number(qualityMoney.value)));
var   txtRange   =   document.selection.createRange(); 
txtRange.moveStart(   "character",   feil.value.length); 
txtRange.moveEnd(   "character",   0   ); 
txtRange.select(); 
//this.form1.noPayment.value= temp+parseFloat(x.value)-tempo;
}

function pm(){
	var cn = document.getElementById("contractNum").value;
	if(cn!=null && cn!=""){
		procurementContractManager.findProcurementContractByContractNum(
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
function checkArrival(){
	var cn = document.getElementById("contractNum").value;
	if(cn!=null && cn!=""){
		procurementContractManager.findProcurementContractByContractNum(
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
function openWin2(){
	
	var pcid = document.getElementById("id").value;
	var diag = new top.Dialog();
	diag.Title = "合同追加管理";
	diag.URL = "businessManagement/contractMg/planAdd_index.jsp?id="+pcid;
	diag.Height=480;
	diag.Width = 850;
	diag.MessageTitle="合同追加管理";
	diag.Message="合同追加管理";
	diag.CancelEvent = function(){
		window.location.href =  "getProcurementPlans.action?procurementContractId="+pcid+"&gn=modify";
		diag.close();
	};
	diag.show();
}

</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="orgId" value="${person.org.id }"/>
<input type="hidden" name="noPayment" value="0"/>
<input type="hidden" name="gn" value="modify"/>
<input type="hidden" id="id" name="id" value="${procurementContract.id}"/>
<input type="hidden" id="cflag" name="cflag" value="${cflag }"/>
<input type="hidden" name="ccflag" id="ccflag" value="" />
<div class="box1" panelWidth="990">
	<table class="tableStyle" >
		<tr>
			<th colspan="12">物资列表</th>
			<th><input type="button" value="合同追加" id="planAdd" onclick="openWin2()"/></th>
		</tr>
			<tr>
				<th>计划编号</th>
				<th>物料描述</th>
				<th>计划数量</th>
				<th>寻源数量</th>
				<th>使用单位</th>
				<th>预算单价</th>
				<th>预算金额(元)</th>
				<th>采购单价</th>
				<th>采购金额(元)</th>
			</tr>
		<c:if test="${!empty planList}">
			<c:forEach items="${planList }" var="p">
			<tr>
				<td>${p.id}</td>
				<td><span class="text_slice" style="width:80px;" title="${p.planNum}">${p.planNum}</span></td>
				<td>${p.quantity}</td>
				<td align="left"> ${p.contractNum }</td>
				<td><span class="text_slice" style="width:50px;" title="${p.reportComp}">${p.reportComp}</span></td>
				<td><fmt:formatNumber value="${p.budgetPrice}" type="currency"/></td>
				<td><fmt:formatNumber value="${p.budgetMoney}" /></td>
				<td><input type="text" id="procurementPrice${p.id}" name="procurementPrice" value="<fmt:formatNumber value="${p.procurementPrice}" pattern="#0.00"/>" onblur="setProcurementMoney('${p.id}',this);" /></td>
				<td><input type="text" id="procurementMoney${p.id}" name="procurementMoney" value="<fmt:formatNumber value="${p.procurementMoney}" pattern="#0.00"/>"  onfocus="setProcurementMoney('${p.id}',this);" style="width:100px;" />
				<input type="hidden" name="pid" value="${p.id}"/>
				<input type="hidden" id="pw" name="pw" value="${p.procurementWay}"/>
				<input type="hidden" id="reportCompId_${p.id}" name="reportCompId" value="${p.reportCompId}"/>
				<input type="hidden" id="reportComp_${p.id}" name="reportComp" value="${p.reportComp}"/>
				</td>
			</tr>
			</c:forEach>
		</c:if>
	</table>
	</div>
	<div class="box2" panelWidth="990" panelTitle="采购合同台帐" showStatus="false" roller="true" >
	<table class="tableStyle" useClick="false"  transMode="true" >
		<tr>
			<td>合同名称：<span class="star">*</span></td>
			<td colspan="2"><input type="text" name="contractName"  id="contractName" value="${procurementContract.contractName}" class="validate[required]" style="width:200px;" /></td>
		</tr>
		<tr>
			<td>合同编号：<span class="star">*</span></td>
			<td><input type="text" name="contractNum"  id="contractNum"  value="${procurementContract.contractNum}" class="validate[required]" onblur="pm()"/></td>
			<td>合同总额：<span class="star">*</span></td>
			<td><input id="totalMoney" type="text" name="totalMoney"  value="<fmt:formatNumber value="${procurementContract.totalMoney}"  pattern="#0.00"/>" onfocus="getSum(this);" value="0"  class="validate[required]"/>(元)</td>
		</tr>
		<tr>
			<td>供应商：<span class="star">*</span></td>
			<td>
				<select  name="suppliersId" id="suppliersId" class="validate[required]" autoWidth="true" style="width:200px;" multiple>
						<c:if test="${!empty sessionSuppliers}">
						 	<c:forEach items="${sessionSuppliers }" var="supplier">  
								<option value="${supplier.id}"  rel="exclusive"  <c:if test="${procurementContract.suppliersId == supplier.id}">selected="selected"</c:if> >${supplier.name }</option>					 
							</c:forEach>
						</c:if>
						<option value="0" rel="headernocb"></option>
				 </select>
			</td>
			<td>采购方案：</td>
			<td>
			<c:if test="${procurementContract.procurementWay == '01' }">公开招标</c:if>
			<c:if test="${procurementContract.procurementWay == '02' }">邀请招标</c:if>
			<c:if test="${procurementContract.procurementWay == '03' }">竞争性谈判</c:if>
			<c:if test="${procurementContract.procurementWay == '04' }">单一来源</c:if>
			<c:if test="${procurementContract.procurementWay == '05' }">寻比价</c:if>
			<input type="hidden" id="procurementWay" name="procurementWay" value="${procurementContract.procurementWay }" />
			</td>
		</tr>

		<tr>
			<td>合同质保金额：</td>
			<td><input id="qualityMoney" type="text" name="qualityMoney" value="<fmt:formatNumber value="${procurementContract.qualityMoney}"  pattern="#0.00"/>"   class="validate[required]"/>(元)</td>
			<td>预付款：<span class="star">*</span></td>
			<td><input type="text" name="advance" id="advance" value="<fmt:formatNumber value="${procurementContract.advance}"  pattern="#0.00"/>"   class="validate[required]"/>(元)</td>
		</tr>
		<tr>
			<td>签订日期：<span class="star">*</span></td>
			<td><input  id="signingDate" name="signingDate"  value="<fmt:formatDate value="${procurementContract.signingDate}" pattern="yyyy-MM-dd"/>" class="date"/></td>
			<td>到货日期：<span class="star">*</span></td>
			<td><input  id="arrivalDate" name="arrivalDate"  value="<fmt:formatDate value="${procurementContract.arrivalDate}" pattern="yyyy-MM-dd"/>" class="date"/></td>
		</tr>
		<tr>
			<td>质保日期：</td>
			<td>
                <input  id="qualityDate" name="qualityDate"  value="<fmt:formatDate value="${procurementContract.qualityDate}" pattern="yyyy-MM-dd"/>" class="date"/>       
            </td>
           	<td></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="4">
			<input type="button"  onclick="ok()" value=" 修改 "/>
				<!-- <input type="submit" value="提交"/> -->
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	
	
	</div>

</form>
</body>