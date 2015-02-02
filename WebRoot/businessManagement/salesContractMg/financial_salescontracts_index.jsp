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
<script type="text/javascript" src="<%=path %>/js/form/tageditor.js"></script>
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
		  if(this.form1.contractMsg.value!="" && this.form1.contractMsg.value=="salesUpdateSuccess"){
				top.Dialog.alert("销售合同已保存");
				this.form1.contractMsg.value="";
			}else if(this.form1.contractMsg.value!=""){
				top.Dialog.alert(this.form1.contractMsg.value);
				this.form1.contractMsg.value="";
			}
		});

	
	function search(offset){

		var url = "financialSalesContracts.action?pager.offset="+offset;
		this.form1.action = url;
		this.form1.submit();
		return;
	}
	function openWin(salesContractId,flag,am){
		
		var diag = new top.Dialog();
		diag.Title = "详细计划信息";
		diag.URL = "getFinancialPlans.action?salesContractId="+salesContractId+"&b="+flag+"&am="+am;
		diag.Height=480;
		diag.Width=900;
		diag.MessageTitle="详细计划信息";
		diag.Message="计划信息明细";
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
	function openWin1(salesContractId){
		var diag = new top.Dialog();
		diag.Title = "详细计划信息";
		diag.URL = "getPlans.action?salesContractId="+salesContractId+"&gn=modify";
		diag.Height=480;
		diag.Width=900;
		diag.MessageTitle="详细计划信息";
		diag.Message="计划信息明细";
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
	function del(salesContractId){
		this.form1.action = "del_salesContract.action?salesContractId="+salesContractId;
		this.form1.submit();
		top.Dialog.close();
		return;
	}
	//导出
	function exportexcel(){
	     this.form1.action = "exportSalesContract.action";
		this.form1.submit();
		return;
	}

</script>

<style>
body{
	line-height:150%;
}
</style>
<body >	
<div class="box2" panelTitle="功能面板 " roller="false">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" id="path" value="<%=path %>">
	<input type="hidden" id="b" name="b" value="${param.b }">
	<input type="hidden" id="all_select" name="all_select" value="<%=request.getAttribute("all") %>">
	<input type="hidden" id="contractMsg" name="contractMsg" value="${contractMsg }">
	<input type="hidden" name="ccflag" id="ccflag" value="${ccflag }" />
	<input type="hidden" name="myOffset" id="myOffset" value="${myOffset }" />
	<input type="hidden" name="resetflag" id="resetflag" value="${resetflag }" />
		<table>
			<tr>
				<td>部门:</td>
				<td><select id="dd" name="orgId">
						<option value="0">请选择</option>
						<c:if test="${!empty sessionOrgs}">
						 	<c:forEach items="${sessionOrgs }" var="organization">  
							<option value="${organization.id}"  <c:if test="${organization.id == params.id_orgId}">selected="selected"</c:if> >${organization.name }</option>					 
							</c:forEach>
						</c:if>
					</select></td>
				<td>销售合同号：</td>
				<td><input type="text" name="contractNum" value="${params.contractNum }"/></td>
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
				<c:if test="${my:method(login.id,'finanialSalescontractMgr',1)}">
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
		<th width="100">合同名称</th>
		<th width="80">合同签订日期</th>
		<th width="100">合同编号</th>
		<th width="160">计划提报单位</th>
		<th width="160">合同签订单位</th>
		<th width="160">合同到货地址</th>
		<th width="110">合同金额</th>
		<th width="110">合同质保金额</th>
		<th width="80">合同质保日期</th>
		<th width="110">合同开票金额</th>
		<th width="110">合同已收金额</th>
		<th width="110">合同应收金额</th>
		<th width="110">到货总金额</th>
		<th width="110">验收总金额</th>
		<th width="60">操作人</th>
		<th width="80">操作</th>
	</tr>
	</thead>
	 <tbody>  
	<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="contract" varStatus="index">  
		<tr>
			<td >${index.count }</td>
			<td >${contract.salesContractName }</td>
			<td ><fmt:formatDate value="${contract.signingDate }" pattern="yyyy-MM-dd"/></td>
			<td >${contract.contractNum }</td>
			<td >${contract.reportCompName}</td>
			<td >${contract.signComp}</td>
			<td >${contract.arrivalAddress}</td>
			<td ><fmt:formatNumber value="${contract.contractMoney}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.qualityMoney }" type="currency"/></td>
			<td ><fmt:formatDate value="${contract.qualityDate }" pattern="yyyy-MM-dd"/></td>
			<td ><fmt:formatNumber value="${contract.totalContractInvoiceMoney}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.totalcontractReceivedMoney}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.contractReceivableMoney}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.arrivalMoney}" type="currency"/></td>
			<td ><fmt:formatNumber value="${contract.acceptanceMoney}" type="currency"/></td>
			<td >${contract.person.name}</td>
			<td width="160">
			<c:if test="${param.b != 'y' }">
					<c:if test="${my:method(login.id,'finanialSalescontractMgr',0)}">
						<span class="img_view hand"  title="详细信息" onclick="openWin(${contract.id },'${param.b}',${contract.arrivalMoney})"></span>
					</c:if>
					<c:if test="${my:method(login.id,'finanialSalescontractMgr',2)}">
						<span  class="img_edit hand" title="修改" onclick="openWin1(${contract.id })"></span>
					</c:if>
					<c:if test="${my:method(login.id,'finanialSalescontractMgr',3)}">
						<span class="img_delete hand" title="删除" onclick='top.Dialog.confirm("删除该合同吗？",function(){del(${contract.id })});'></span>
					</c:if>
			</c:if>
			<c:if test="${param.b == 'y' }">
				<span class="img_view hand"  title="详细信息" onclick="openWin(${contract.id },'${param.b}',${contract.arrivalMoney})"></span>
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
	<pg:pager items="${pm.total }" url="financialSalesContracts.action" export="currentPageNumber=pageNumber" maxPageItems="50" >

	<div class="float_right padding5 paging">
		<div class="float_left padding_top4">
		<c:choose>
			<c:when test="${ currentPageNumber == 1}">
				<span class="paging_disabled"><a href="javascript:;">首页</a></span>
			</c:when>
			<c:otherwise>
				<span ><pg:first><a href="#" onclick="getCheckBoxInformationnn('${pageUrl }')" >首页</a></pg:first></span>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ currentPageNumber > 1}">
				<span ><pg:prev><a href="#"  onclick="getCheckBoxInformationnn('${pageUrl }')" >上一页</a></pg:prev></span>
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
					<span><a href="#"  onclick="getCheckBoxInformationnn('${pageUrl }')" >${pageNumber }</a></span>	
					</c:otherwise>
				</c:choose>
			</pg:pages>
			
		<span><pg:next><a href="#"  onclick="getCheckBoxInformationnn('${pageUrl }')" >下页</a></pg:next></span>
		<span><pg:last><a href="#"  onclick="getCheckBoxInformationnn('${pageUrl }')" >尾页</a></pg:last></span>
		<div class="clear"></div>
	</div>
	</pg:pager>
	<div class="clear"></div>
</div>
		
</body>
</html>
