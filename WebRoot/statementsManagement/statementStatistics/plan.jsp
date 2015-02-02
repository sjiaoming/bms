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
<script type="text/javascript" src="<%=path %>/js/form/multiselect.js"></script>
<!--截取文字start-->
<script type="text/javascript" src="<%=path %>/js/text/text-overflow.js"></script>
<!--截取文字end-->

<!--多功能表格start-->
<link rel="stylesheet" type="text/css" href="<%=path %>/js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="<%=path %>/js/table/flexigrid/flexigrid.js"></script>

<!--多功能表格end-->
<script type="text/javascript">
	$(function(){
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
				height:parentMainHeight*0.6,
				resizable: false,
				showToggleBtn: true,
				minColToggle: 1
				});	
		}
	})
	//导出
	function exportexcel(){
	     this.form1.action = "export.action";
	     //this.form1.action = "planSheet.action";
		this.form1.submit();
		return;
	}
	
	function toNext(url){
		
		//var name = document.form1.name.value;
		//alert(name);
		this.form1.action = url;
		this.form1.submit();
		return;
	}
	function getPlanStatusList(){
		this.form1.action = "getPlanStatusList.action";
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
<div class="box2" panelTitle="功能面板" roller="false" panelHeight="110">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" id="path" value="<%=path %>">
		<table>
			<tr>
				<td>部门:</td>
				<td><select id="dd" name="orgId">
						<option value="0">请选择</option>
						<c:if test="${!empty sessionOrgs}">
						 	<c:forEach items="${sessionOrgs }" var="organization">  
								<option value="${organization.id}" <c:if test="${organization.id == orgId}">selected="selected"</c:if> >${organization.name }</option>					 
							</c:forEach>
						</c:if>
					</select>
				</td>
				<td>计划编号：</td>
				<td><input type="text" name="planNum" value="${planNum }"/></td>
				<td>计划提报单位：</td>
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
				<td> 采购方式：</td>
				<td>
					<select  name="procurementWay" id="procurementWay" class="validate[required]">
						<option value="0">选择方式</option>
					    <option value="01" <c:if test="${procurementWay == '01' }">selected="selected"</c:if>>公开招标</option>
					    <option value="02" <c:if test="${procurementWay == '02' }">selected="selected"</c:if>>邀请招标</option>
					    <option value="03" <c:if test="${procurementWay == '03' }">selected="selected"</c:if>>竞争性谈判</option>
					    <option value="04" <c:if test="${procurementWay == '04' }">selected="selected"</c:if>>单一来源</option>
					    <option value="05" <c:if test="${procurementWay == '05' }">selected="selected"</c:if>>寻比价</option>
					 </select>
				</td>
				<td>操作人：</td>
				<td><input type="text" name="personName" value="${params.personName }"/></td>
			</tr>
			<tr>
				<%-- <td>合同执行方式:</td>
				<td>
				<select  name="contractExecutionWay" id="contractExecutionWay_option" >
					<option value="0">选择方式</option>
				    <option value="1" <c:if test="${contractExecutionWay == '1' }">selected="selected"</c:if> >统谈统签统付</option>
					<option value="2" <c:if test="${contractExecutionWay == '2' }">selected="selected"</c:if>>统谈统签分付</option>
					<option value="3" <c:if test="${contractExecutionWay == '3' }">selected="selected"</c:if>>统谈分签</option>
				 </select>
				</td>
			--%>
				<td>计划接收日期 从:</td>
				<td>
                        <input  id="sDate" name="sDate"  value="${sDate }" class="date"/>
				</td>
				<td>到：</td>
				<td>
                         <input  id="eDate" name="eDate"  value="${eDate }" class="date"/>
				</td>
				
				<td>供应商：</td>
				<td>
					<select  name="suppliersId" id="suppliersId" autoWidth="true" style="width:100px;" multiple>
						<c:if test="${empty suppliersId }">
						<option value="" selected="selected">请选择</option>
						</c:if>
						<c:if test="${!empty suppliersId }">
						<option value="" >请选择</option>
						</c:if>
						<c:if test="${!empty sessionSuppliers}">
						 	<c:forEach items="${sessionSuppliers }" var="supplier">  
								<option value="${supplier.id}" <c:if test="${supplier.id == suppliersId}">selected="selected"</c:if> rel="exclusive" >${supplier.name }</option>					 
							</c:forEach>
						</c:if>
						<option value="0" rel="headernocb"></option>
					 </select>
				</td>
				<td>采购方案名称：</td>
				<td><input type="text" name="procurementName" value="${params.procurementName }"/></td>
				<%-- <c:if test="${my:method(login.id,'planmgr',1)}"> --%>
				<td><button  onclick="getPlanStatusList()"><span class="icon_find">查询</span></button></td>
				<%-- </c:if> --%>
				
				<td><input  type="button" value="导出" onclick='exportexcel()'/></td>
			</tr>
			<tr>
				
				<td colspan="16">
				<b>
				总条数：<fmt:formatNumber value="${pm.total1 }"  pattern="#0.00"/>&nbsp;&nbsp;&nbsp;&nbsp;
				总预算金额：<fmt:formatNumber value="${pm.double1 }"  pattern="#0.00"/>&nbsp;&nbsp;&nbsp;&nbsp;
				总采购金额：<fmt:formatNumber value="${pm.double2 }"  pattern="#0.00"/>&nbsp;&nbsp;&nbsp;&nbsp;
				总到货数量：<fmt:formatNumber value="${pm.double3 }"  pattern="#0.00"/>&nbsp;&nbsp;&nbsp;&nbsp;
				总到货金额：<fmt:formatNumber value="${pm.double4 }"  pattern="#0.00"/>&nbsp;&nbsp;&nbsp;&nbsp;
				总验收数量：<fmt:formatNumber value="${pm.double5 }"  pattern="#0.00"/>&nbsp;&nbsp;&nbsp;&nbsp;
				总验收金额：<fmt:formatNumber value="${pm.double6 }"  pattern="#0.00"/>
				</b>
				</td>
				
			</tr>
		</table>
		</form>
	</div>

	<table class="flexiStyle" >
		<thead>
			<tr>
				
				<th width="80">计划编号</th>
				<th width="80">提报单位</th>
				<th width="80">物资类别</th>
				<th width="80">物资代码</th>
				<th width="80">设备名称及位号</th>
				<th width="80">物资名称</th>
				<th width="100">规格型号</th>
				<th width="80">计划状态</th>
				<th width="80">审核状态</th>
				<th width="80">材质</th>
				<th width="80">标准号</th>
				<th width="80">单位</th>
				<th width="80">数量</th>
				<th width="80">预算单价(元)</th>
				<th width="80">预算金额(元)</th>
				<th width="80">物资需求部门</th>
				<th width="80">要求到货日期</th>
				<th width="60">负责人</th>
				<th width="80">检修项目编号</th>
				<th width="80">用途</th>
				<th width="80">原厂商</th>
				<th width="80">备注</th>
				
				<th width="60">采购方案名称</th>
				<th width="60">采购方式</th>
				<th width="60">供应商</th>
				<th width="60">寻源单价</th>
				<th width="60">寻源金额</th>
				
				<th width="60">销售合同编号</th>
				<th width="60">销售合同名称</th>
				<th width="60">销售合同金额</th>
				<th width="60">采购合同编号</th>
				<th width="60">采购合同名称</th>
				
				<th width="60">采购合同金额</th>
				<th width="60">到货数量</th>
				<th width="60">到货金额</th>
				<th width="60">验收数量</th>
				<th width="60">验收金额</th>
			</tr>
		</thead> 
		                 
	    <tbody>  
	   
		<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="plan">  
		<tr>
			<td>${plan.planNum }</td>
			<td>${plan.reportCompName }</td>
			<td>${plan.itemType }</td>
			<td>${plan.itemsNum }</td>
			<td>${plan.facilityNameAndNum }</td>
			<td>${plan.itemsName }</td>
			<td>${plan.modelType }</td>
			<td>
				<c:if test="${plan.planStatus==01 }">未处理</c:if>
				<c:if test="${plan.planStatus==02 }">已采购方案</c:if>
				<c:if test="${plan.planStatus==03 }">已寻源</c:if>
				<c:if test="${plan.planStatus==04 }">已销售</c:if>
				<c:if test="${plan.planStatus==05 }">已采购</c:if>
				<c:if test="${plan.planStatus==06 }">已取消</c:if>
			</td>
			<td>
				<c:if test="${plan.checkStatus==01 }">主管末审核</c:if>
				<c:if test="${plan.checkStatus==02 }">主管末通过审核</c:if>
				<c:if test="${plan.checkStatus==03 }">经理末审核</c:if>
				<c:if test="${plan.checkStatus==04 }">经理末通过审核</c:if>
				<c:if test="${plan.checkStatus==05}">董事长末审核</c:if>
				<c:if test="${plan.checkStatus==06}">董事长末通过审核</c:if>
				<c:if test="${plan.checkStatus==07}">董事长己审核</c:if>
			</td>
			<td>${plan.material }</td>
			<td>${plan.standardNum }</td>
			<td>${plan.unit }</td>
			<td>${plan.quantity }</td>
			<td>${plan.budgetPrice }</td>
			<td>${plan.budgetMoney }</td>
			<td>${plan.demandDept }</td>
			<td><fmt:formatDate value="${plan.arrivalDate }" pattern="yyyy-MM-dd"  /></td>
			<td>${plan.personName }</td>
			<td>${plan.checkItemNum }</td>
			<td>${plan.useFor }</td>
			<td>${plan.originComp }</td>
			<td>${plan.remark }</td>
			
			<td>${plan.procurementName }</td>
			<td>${plan.procurementWay }</td>
			<td>${plan.suppliersName }</td>
			<td><fmt:formatNumber value="${plan.procurementPrice }" type="currency"/></td>
			<td><fmt:formatNumber value="${plan.procurementMoney }" type="currency"/></td>
			<td>${plan.saleContractNum }</td>
			<td>${plan.saleContractName }</td>
			<td><fmt:formatNumber value="${plan.saleContractMoney }" type="currency"/></td>
			<td>${plan.contractNum }</td>
			<td>${plan.contractName }</td>
			<td><fmt:formatNumber value="${plan.totalMoney }" type="currency"/></td>
			<td>${plan.arrivalNum }</td>
			<td><fmt:formatNumber value="${plan.arrivalMoney }" type="currency"/></td>
			<td>${plan.acceptanceNum }</td>
			<td><fmt:formatNumber value="${plan.acceptanceMoney }" type="currency"/></td>
		</tr>
		</c:forEach>
		</c:if>
		<c:if test="${empty pm.list}">
			<tr><td >无数据</td><td colspan="25">无数据</td><td colspan="25">无数据</td><td colspan="25">无数据</td></tr>
		</c:if>
		
		</tbody> 
		
	</table>

<div style="height:35px;">
	<pg:pager items="${pm.total }" url="getPlanStatusList.action" export="currentPageNumber=pageNumber" maxPageItems="50">
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
