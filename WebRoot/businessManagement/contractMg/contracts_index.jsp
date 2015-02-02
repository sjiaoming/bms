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
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=path %>/"/>
<!--框架必需end-->

<!--截取文字start-->
<script type="text/javascript" src="<%=path %>/js/text/text-overflow.js"></script>
<!--截取文字end-->

<!--多功能表格start-->
<link rel="stylesheet" type="text/css" href="<%=path %>/js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="<%=path %>/js/table/flexigrid/flexigrid.js"></script>
<!--多功能表格end-->
<script type="text/javascript" src="<%=path %>/js/form/multiselect.js"></script>

<script type="text/javascript">

	$(function(){
		top.Dialog.close();
		var s = document.getElementById("ccflag").value;
		//alert(s);
		if(s!=""&&s=="success"){
			alert("操作成功");
		}
			var parentMainHeight = window.parent.document.documentElement.clientHeight;
			var flexiGridHeight=parentMainHeight-parentTopHeight-parentBottomHeight-fixHeight-45;
			//alert(flexiGridHeight);
			//定义flexiGridHeight是为了让表格自适应页面高度，如果不需要可以将下面的height设为一个具体数值
			if(parentMainHeight == 0 || parentMainHeight == undefined){
				$('.flexiStyle').flexigrid({
					height:400,
					resizable: false,
					showToggleBtn: true,
					minColToggle: 1
					});	
			}else{
				$('.flexiStyle').flexigrid({
					height:parentMainHeight*0.65,
					resizable: false,
					showToggleBtn: true,
					minColToggle: 1
					});	
			}
		  if(this.form1.contractMsg.value!="" && this.form1.contractMsg.value=="addArrivalSuccess"){
				top.Dialog.alert("到货信息已保存");
				this.form1.contractMsg.value="";
		}else if(this.form1.contractMsg.value!="" && this.form1.contractMsg.value=="updateProcurementSuccess"){
				top.Dialog.alert("合同更新成功");
				this.form1.contractMsg.value="";
		}else if(this.form1.contractMsg.value!=""){
			top.Dialog.alert(this.form1.contractMsg.value);
			this.form1.contractMsg.value="";
		}
		  
		});
	/* function search(){

		this.form1.action = "procurementContracts.action";
		this.form1.submit();
		return;
	} */
	function search(offset){

		var url = "procurementContracts.action?pager.offset="+offset;
		this.form1.action = url;
		this.form1.submit();
		return;
		//getCheckBoxInformationnn(url);
	}
	function openWin(procurementContractId,flag,am){
		var diag = new top.Dialog();
		diag.Title = "详细合同信息";
		diag.URL = "getProcurementPlans.action?procurementContractId="+procurementContractId+"&b="+flag+"&am="+am;
		diag.Height=480;
		diag.Width = 1000;
		diag.MessageTitle="详细合同信息";
		diag.Message="计划合同明细";
		diag.CancelEvent = function(){
			document.getElementById("resetflag").value = "add";
			var inputValue = diag.innerFrame.contentWindow.document.getElementById('ccflag').value;
			if(inputValue!=""){
				search(document.getElementById("myOffset").value);
			}
			diag.close();
		};
		diag.show();
	}
	function openWin2(procurementContractId){
		var diag = new top.Dialog();
		diag.Title = "详细合同信息";
		diag.URL = "getProcurementPlans.action?procurementContractId="+procurementContractId+"&gn=modify";
		diag.Height=480;
		diag.Width = 1000;
		diag.MessageTitle="详细合同信息";
		diag.Message="计划合同明细";
		diag.CancelEvent = function(){
			document.getElementById("resetflag").value = "add";
			var inputValue = diag.innerFrame.contentWindow.document.getElementById('ccflag').value;
			if(inputValue!=""){
				search(document.getElementById("myOffset").value);
			}
			diag.close();
		};
		diag.show();
	}
	function del(procurementContractId){
		this.form1.procurementContractId.value=procurementContractId;
		this.form1.action = "del_procurementContract.action";
		this.form1.submit();
		top.Dialog.close();
		return;
	}
	
	function openWin1(pid){
		var diag = new top.Dialog();
		diag.Title = "到货管理";
		diag.URL = "toAddArrival.action?id="+pid;
		diag.Height=480;
		diag.Width = 1100;
		diag.MessageTitle="到货管理";
		diag.Message="到货管理";
		diag.CancelEvent = function(){
			document.getElementById("resetflag").value = "add";
			var inputValue = diag.innerFrame.contentWindow.document.getElementById('ccflag').value;
			if(inputValue!=""){
				search(document.getElementById("myOffset").value);
			}
			diag.close();
		};
		diag.show();
	}
	//导出
	function exportexcel(){
	     this.form1.action ="exportProcurementContract.action";
		this.form1.submit();
		return;
	}
	function toNext(url){
		this.form1.action = url;
		this.form1.submit();
		return;
	}
</script>

<style>
body{
	line-height:150%;
}
</style>
<body>	
<div class="box2" panelTitle="功能面板 " roller="false">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" id="path" value="<%=path %>">
	<input type="hidden" name="procurementContractId" value="">
	<input type="hidden" id="b" name="b" value="${param.b }">
	<input type="hidden" id="contractMsg" name="contractMsg" value="${contractMsg }">
	<input type="hidden" name="ccflag" id="ccflag" value="${ccflag }" />
	<input type="hidden" name="myOffset" id="myOffset" value="${myOffset }" />
	<input type="hidden" name="resetflag" id="resetflag" value="${resetflag }" />
		<table>
			<tr>
				<td >部门:</td>
				<td><select id="dd" name="orgId">
						<option value="0">请选择</option>
						<c:if test="${!empty sessionOrgs}">
						 	<c:forEach items="${sessionOrgs }" var="organization">  
								<option value="${organization.id}" <c:if test="${organization.id == params.id_orgId}">selected="selected"</c:if> >${organization.name }</option>					 
							</c:forEach>
						</c:if>
					</select></td>
				<td >合同编号：</td>
				<td><input id="contractNum" type="text" name="contractNum" value="${params.contractNum }"/></td>
				<td>合同签订日期 从:</td>
				<td>
                        <input  id="sDate" name="sDate"  value="${sDate }" class="date"/>
				</td>
				<td>到：</td>
				<td>
                         <input  id="eDate" name="eDate"  value="${eDate }" class="date"/>
				</td>
				
			</tr>
			<tr>
				<td style="width:100px">计划提报单位：</td>
				<td>
					<select  name="useCompId" id="useCompId"  style="width:100px;" multiple >
						<c:if test="${empty useCompId }">
						<option value="" selected="selected" >请选择</option>
						</c:if>
						<c:if test="${!empty useCompId }">
						<option value="" >请选择</option>
						</c:if>
						<c:if test="${!empty sessionUseComps}">
						 	<c:forEach items="${sessionUseComps }" var="uc">
								<option value="${uc.id}" <c:if test="${uc.id == useCompId}">selected="selected"</c:if> rel="exclusive" >${uc.name }</option>					 
							</c:forEach>
						</c:if>
						<option value="0" rel="headernocb"></option>
					 </select>
				</td>
				<td>操作人：</td>
				<td><input type="text" name="personName" value="${params.personName }"/></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>
				<c:if test="${my:method(login.id,'contractMgr',1)}">
				
				<button  onclick="showProgressBar();search(0);"><span class="icon_find">查询</span></button>
				</c:if>
				<c:if test="${param.b == 'y' }">
					<button onclick="exportexcel()" ><span class="icon_xls">导出</span></button>
				</c:if>
				</td>
			</tr>
		</table>
		</form>

	</div>
<table class="flexiStyle" >
	<thead>
		<tr>
		<th width="20">序号</th>
		<th width="110">合同名称</th>
		<th width="110">合同编号</th>
		<th width="70">合同签订日期</th>
		<th width="80">计划提报单位</th>
		<th width="60">部门名称</th>
		<th width="110">合同总额</th>
		<th width="110">预付金额</th>
		<th width="110">质保金额</th>
		<th width="130">供应商</th>
		<th width="70">合同到货日期</th>
		<th width="80">采购方案</th>
		<th width="110">已付总金额</th>
		<th width="110">挂账</th>
		<th width="110">应付金额</th>
		<th width="110">到货金额</th>
		<th width="110">验收金额</th>
		<th width="150">备注</th>
		<th width="100">操作人</th>
		<th width="100">操作</th>
		</tr>
		</thead>                           
	    <tbody>  
			<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="contract" varStatus="index">  
        <c:set var="pid" value="${contract.id }"></c:set>
		<tr>
			<td >${index.count }</td>
			<td >${contract.contractName }</td>
			<td >${contract.contractNum }</td>
			<td ><fmt:formatDate value="${contract.signingDate }" pattern="yyyy-MM-dd"/></td>
			<td >${contract.reportCompName }</td>
			<td >${contract.orgName }</td>
			<td ><fmt:formatNumber value="${contract.totalMoney}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.advance}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.qualityMoney}" type="currency"/></td>
			<td >${contract.suppliersName }</td>
			<td ><fmt:formatDate value="${contract.arrivalDate }" pattern="yyyy-MM-dd"/></td>
			<c:if test="${contract.procurementWay == '01'}">
			<td >公开招标</td>
			</c:if>
			<c:if test="${contract.procurementWay == '02'}">
			<td >邀请招标</td>
			</c:if>
			<c:if test="${contract.procurementWay == '03'}">
			<td >竞争性谈判</td>
			</c:if>
			<c:if test="${contract.procurementWay == '04'}">
			<td >单一来源</td>
			</c:if>
			<c:if test="${contract.procurementWay == '05'}">
			<td >询比价</td>
			</c:if>
			<td ><fmt:formatNumber value="${contract.payed}" type="currency"/></td>
			<td ><fmt:formatNumber value="${getcreditmoney[pid]}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.shouldPayment}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.arrivalMoney}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.acceptanceMoney}" type="currency"/></td>
			<td >${contract.remark}</td>
			<td >${contract.person.name}</td>
			<td>
			<c:if test="${param.b != 'y' }">
				<c:if test="${my:method(login.id,'contractMgr',0)}">
					<span  class="img_view hand" title="详细信息" onclick="openWin(${contract.id },'${param.b }',${contract.arrivalMoney==null?0:contract.arrivalMoney})"></span>
					<span  class="img_history hand" title="到货信息" onclick="openWin1(${contract.id })"></span>
				</c:if>
				<c:if test="${my:method(login.id,'contractMgr',2)}">
					<span  class="img_edit hand" title="修改" onclick="openWin2(${contract.id })"></span>
				</c:if>
				
				<c:if test="${my:method(login.id,'contractMgr',3)}">
					<span class="img_delete hand" title="删除" onclick='top.Dialog.confirm("删除该合同吗？",function(){del(${contract.id })});'></span>
				</c:if>
			</c:if>
			<c:if test="${param.b == 'y' }">
				<c:if test="${my:method(login.id,'contractMgr',1)}">
				<span  class="img_view hand" title="详细信息" onclick="openWin(${contract.id },'${param.b }',${contract.arrivalMoney})"></span>
				</c:if>
			</c:if>
			
			</td>
		</tr>
		</c:forEach>
		</c:if>
		<c:if test="${empty pm.list}">
			<tr><td>无数据</td></tr>
		</c:if>
		</tbody> 

</table>
<div style="height:35px;">
	<pg:pager items="${pm.total }" url="procurementContracts.action" export="currentPageNumber=pageNumber" maxPageItems="50" >

	<div class="float_right padding5 paging">
		<div class="float_left padding_top4">
		<c:choose>
			<c:when test="${ currentPageNumber == 1}">
				<span class="paging_disabled"><a href="javascript:;">首页</a></span>
			</c:when>
			<c:otherwise>
				<span ><pg:first><a href="#" onclick="toNext('${pageUrl}')">首页</a></pg:first></span>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ currentPageNumber > 1}">
				<span ><pg:prev><a href="#" onclick="toNext('${pageUrl}')">上一页</a></pg:prev></span>
			</c:when>
			<c:otherwise>
				<span class="paging_disabled"><a href="javascript:;">上一页</a></span>
			</c:otherwise>
		</c:choose>
		
			<pg:pages>
				<c:choose>
					<c:when test="${ currentPageNumber eq pageNumber}">
					<span class="paging_current"><a href="javascript:;">${pageNumber}</a></span>
					</c:when>
					<c:otherwise>
					<span><a href="#" onclick="toNext('${pageUrl}')">${pageNumber }</a></span>	
					</c:otherwise>
				</c:choose>
			</pg:pages>
			
		<span><pg:next><a href="#" onclick="toNext('${pageUrl}')">下页</a></pg:next></span>
		<span><pg:last><a href="#" onclick="toNext('${pageUrl}')">尾页</a></pg:last></span>
		<div class="clear"></div>
	</div>
	</pg:pager>
	<div class="clear"></div>
</div>
		
</body>
</html>
