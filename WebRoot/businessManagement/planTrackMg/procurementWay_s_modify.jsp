<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/common/common.jsp" %>
<%@ page import="java.util.*"%>
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

<script type='text/javascript' src='<%=path %>/dwr/interface/planManager.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>

<script language="javascript">
$(function(){
	//getSum();
	var s = document.getElementById("cflag").value;
	if(s!=""&&s=="addSuccess"){
		top.Dialog.close();
	}
	changeWay();
});
function ok(){
		var access=false;
		access=$('#form1').validationEngine({returnIsValid:true});
		/*alert($('#supplierIds').val());*/
		if(access){
			if(confirm("您确定要提交吗?")){
				this.form1.action = "modifyPc.action";
				this.form1.submit();
				//top.Dialog.close();
				return;
			}
		}
		//return access;
	}
function delRow(planId){
	//var zmid = $("input[name=contractMoney["+rowId+"].id]").attr("value");
	//$("#zmid").attr("value",zmid);
	//alert(planId +"   sadsdsd   rowid  ");
	//if(zmid != null && zmid != "" && zmid != "undefined"){ 	
	planManager.modifyRemovePlanPc(planId,
		function(){
		//alert(1);
		});
	//alert(2);
	$("#row"+planId).remove();
		//return access;
	}

function addPlan(){
	
	var pcid = document.getElementById("id").value;
	var diag = new top.Dialog();
	diag.Title = "合同追加管理";
	//diag.URL = "businessManagement/planTrackMg/planAdd_index.jsp?id="+pcid;//alert(diag.URL );
	diag.URL = "initPlanAdd.action?pc.id="+pcid;//alert(diag.URL );
	diag.Height=580;
	diag.Width = 988;
	diag.MessageTitle="合同追加管理";
	diag.Message="合同追加管理";
	diag.CancelEvent = function(){//alert(pcid);
		window.location.href =  "getProcurementCheck.action?pc.id="+pcid+"&pageType=s_index";
		diag.close();
	};
	diag.show();
}

function changeWay(){
	var v = $("#procurementWay").val();
//	alert(v);
	if(v=="01"){
		$("#supplierContent").hide();
		$("#supplierTitle").hide();
		$('#supplierIds').val("");
		//alert(2);
	}else{
		$("#supplierTitle").show();
		$("#supplierContent").show();
		//alert(3);
	}
}

</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="tags" value="<%=request.getParameter("pids") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="${cflag }" />
<input type="hidden" name="pc.id" id="id" value="${pc.id}" />
<div class="box1" panelWidth="1300">
	<table class="tableStyle">
		<tr>
			<th colspan="12">采购方案审核</th>
		</tr>
		<tr>
						<td>采购方式:</td>
						<td>
						<select  name="pc.procurementWay" id="procurementWay" class="validate[required]" onchange="changeWay()">
							<option value=""></option>
						    <option value="01" <c:if test="${pc.procurementWay==01 }">selected="selected"</c:if>>公开招标</option>
						    <option value="02" <c:if test="${pc.procurementWay==02 }">selected="selected"</c:if>>邀请招标</option>
						    <option value="03" <c:if test="${pc.procurementWay==03 }">selected="selected"</c:if>>竞争性谈判</option>
						    <option value="04" <c:if test="${pc.procurementWay==04 }">selected="selected"</c:if>>单一来源</option>
						    <option value="05" <c:if test="${pc.procurementWay==05 }">selected="selected"</c:if>>询比价</option>
						 </select>
						</td>
						<td>采购方案名称:</td>
						<td>
						<input type="text" name="pc.procurementName" value="${pc.procurementName }" style="width:100px;"/>
						</td>
						<td> 方案号：</td>
						<td>
							<input type="text" name="pc.caseNum" value="${pc.caseNum }" style="width:100px;"/>
						</td>
						<td> 包号：</td>
						<td>
							<input type="text" name="pc.packageNum" value="${pc.packageNum }" style="width:100px;"/>
						</td>
						<td id="supplierTitle">供应商:</td>
						<td id="supplierContent">
						<c:choose>
							<c:when test="${pageType=='s_index'}">
							<select  name="pc.supplierIds" id="supplierIds" 
							 autoWidth="true" style="width: 200px;" multiple>
								<c:if test="${!empty sessionSuppliers}">
								 	<c:forEach items="${sessionSuppliers }" var="supplier">  
										<option value="${supplier.id}" <c:if test="${ null != supplierMp[supplier.id]}">selected="selected"</c:if>>${supplier.name }</option>	 
									</c:forEach>
								</c:if>
								<option value="0" rel="headernocb"></option>
							 </select>
							</c:when>
							<c:otherwise>
							<% 
								Map map = (Map)request.getAttribute("supplierMp");
								String name = "";
								if(map.values().size()>0){
								for(Iterator it = map.values().iterator();it.hasNext();){
									name = name + (String)it.next()+"<br>";
								}
								name = name.substring(0,name.length()-1);
								}
							%>
							<span class="text_slice" style="width:170px;" title="<%=name %>"><%=name %></span>
							</c:otherwise>
						</c:choose>
						</td>
						<td>审核主管:</td>
						<td>
						<c:choose>
							<c:when test="${pageType=='s_index'}">
								<select id="managerId" name="pc.director.id">
									<option value="0">请选择</option>
									<c:if test="${!empty dpersons}">
									 	<c:forEach items="${dpersons }" var="dperson">  
											<option value="${dperson.id}" <c:if test="${pc.director.id==dperson.id}"> selected="selected"</c:if>>${dperson.name }</option>					 
										</c:forEach>
									</c:if>
								</select>
							</c:when>
							<c:otherwise>
								${pc.director.name}
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</table>
	<table class="tableStyle">
		<tr>
			<th colspan="12">计划详细:</th>
		</tr>
					<tr>
						<th width="80">计划编号</th>
						<th width="80">物资类别</th>
						<th width="80">物资代码</th>
						<th width="100">物资名称</th>
						<th width="80">规格型号</th>
						<th width="80">计划状态</th>
						<th width="80">审核状态</th>
						<th width="50">单位</th>
						<th width="50">数量</th>
						<th width="80">预算单价(元)</th>
						<th width="80">预算金额(元)</th>
						<th width="80">
						<c:if test="${pageType=='s_index'}">
							<input type="button" value="计划追加" id="planAdd" onclick="addPlan()"/>
						</c:if>
						</th>
					</tr>
       				 <c:forEach items="${pc.plans }" varStatus="status" var="plan">
					<tr id="row${plan.id}">
						
						<td>${plan.planNum }</td>
						<td><span class="text_slice" style="width:80px;" title="${plan.itemType }">${plan.itemType }</span></td>
						<td><span class="text_slice" style="width:80px;" title="${plan.itemsNum }">${plan.itemsNum }</span></td>
						<td><span class="text_slice" style="width:100px;" title="${plan.itemsName }">${plan.itemsName }</span></td>
						<td><span class="text_slice" style="width:80px;" title="${plan.modelType }">${plan.modelType }</span></td>
						<td>
							<c:if test="${plan.planStatus==01 }">未处理</c:if>
							<c:if test="${plan.planStatus==02 }">已采购方案</c:if>
							<c:if test="${plan.planStatus==03 }">已寻源</c:if>
							<c:if test="${plan.planStatus==04 }">已采购</c:if>
							<c:if test="${plan.planStatus==05 }">已销售</c:if>
							<c:if test="${plan.planStatus==06 }">已取消</c:if>
						</td>
						<td>
							<c:if test="${plan.checkStatus==01 }">主管末审核</c:if>
							<c:if test="${plan.checkStatus==02 }">主管末通过审核</c:if>
							<c:if test="${plan.checkStatus==08 }">主管己审核</c:if>
							<c:if test="${plan.checkStatus==09 }">内控末通过审核</c:if>
							<c:if test="${plan.checkStatus==03 }">内控己审核</c:if>
							<c:if test="${plan.checkStatus==04 }">经理末通过审核</c:if>
							<c:if test="${plan.checkStatus==05 }">经理己审核</c:if>
							<c:if test="${plan.checkStatus==06 }">董事长末通过审核</c:if>
							<c:if test="${plan.checkStatus==07 }">董事长己审核</c:if>
						</td>
						<td>${plan.unit }</td>
						<td>${plan.quantity }</td>
						<td><fmt:formatNumber value="${plan.budgetPrice }" type="currency"/></td>
						<td><fmt:formatNumber value="${plan.budgetMoney }" type="currency"/></td>
						<td>
							<c:if test="${pageType=='s_index'}">
								<img src="images/formEle/cross.png" id="delImg" onclick='top.Dialog.confirm("您确定要删除吗？",function(){delRow(${plan.id})});'></img>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
	<table class="tableStyle">
		<tr><th colspan="12">采购审批记录</th></tr>
					<tr>
						<th colspan="3" width="150">审核人</th>
						<th colspan="3" width="200">审核状态</th>
						<th colspan="3" width="150">审核日期</th>
						<th colspan="3" width="420">审核意见</th>
					</tr>
					<c:forEach items="${pc.checks }" varStatus="status" var="check">
					<tr>
						<td colspan="3">${check.person.name }</td>
						<td colspan="3">
							<c:if test="${check.checkStatus==01 }">主管末审核</c:if>
							<c:if test="${check.checkStatus==02 }">主管末通过审核</c:if>
							<c:if test="${check.checkStatus==08 }">主管己审核</c:if>
							<c:if test="${check.checkStatus==09 }">内控末通过审核</c:if>
							<c:if test="${check.checkStatus==03 }">内控己审核</c:if>
							<c:if test="${check.checkStatus==04 }">经理末通过审核</c:if>
							<c:if test="${check.checkStatus==05 }">经理己审核</c:if>
							<c:if test="${check.checkStatus==06 }">董事长末通过审核</c:if>
							<c:if test="${check.checkStatus==07 }">董事长己审核</c:if>
						</td>
						<td colspan="3">${check.checkDate }</td>
						<td colspan="3">${check.opinion }</td>
					</tr>
					</c:forEach>
				</table>
			</td>
	<table class="tableStyle" formMode="true">
			<tr>
			<td colspan="12">
				<c:if test="${pageType=='s_index'}">
					<c:if test="${pc.checkStatus==02 || pc.checkStatus==01}">
						<input type="button"  onclick="ok()" value=" 提 交 "/>
						<input type="reset" value=" 重 置 "/>
					</c:if>
				</c:if>
			</td>
		</tr>
	</table>
	</div>
</form>
</body>