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
<script type="text/javascript" src="<%=path%>/js/form/multiselect.js"></script>
<!--框架必需end-->
<!--截取文字start-->
<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
<!--截取文字end-->
<script type="text/javascript" src="<%=path %>/js/Calendar3.js"></script>
<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>
<head>
<style type="text/css">
	.tdAlign
        {
           width:90px;
        }
</style>
</head>
<script type='text/javascript' src='<%=path %>/dwr/interface/planManager.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>

<script language="javascript"><!--
$(function(){
	//getSum();
	var s = document.getElementById("cflag").value;
	if(s!=""&&s=="addSuccess"){
		top.Dialog.close();
	}
});
function ok(){
		var access=false;
		access=$('#form1').validationEngine({returnIsValid:true});
		var directorId = $("#personId").val();
		if(directorId != 0){
			if(checkQuantity()){
				if(access){
					if(confirm("您确定要提交吗?")){
						this.form1.action = "doModifySeekSource.action";
						this.form1.submit();
						//top.Dialog.close();
						return;
					}
				}
			//return access;
			}
		}else{
			alert("请选择主管！");
			return false;
		}
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
	$("#row"+planId).parents("tr").remove();
		//return access;
	}
function delOneSupplier(tableId){
	//alert(tableId);
	/*if(tableId != "countMaterial"){
		//alert($(tableId).attr("id"));
		//预防删除一条供应商后不能在选出来的问题
		$("#storeOrder").attr("value",0);
		$(tableId).remove();
	}else{
	//	$("#"+tableId).remove();$("#firstName").html()
		$("#firstSupplier").attr("value",0);
		$("#firstName").html("");
		$("#countMaterial").find("tr").each(function(i){
		//9为数量，10为单价，11为金额,
		$(this).children('td').eq(9).children('input').val("");
		$(this).children('td').eq(10).children('input').val("");
		//alert(price1+"    price1  price   " + price);
		$(this).children('td').eq(11).children('input').val("");
		//$(this).html(html);
		//alert(html);
		});
	}
	*/
	var detailIds = "";
	$(tableId).find("tr").each(function(i){
		//不要标题行和字段首行
		if(i>1){
			var temp = $(this).find('input[type=hidden]').val();
			detailIds = detailIds+","+ temp;
		}
	});
	if(detailIds != ""){
		if(confirm("您确定要删除吗？")){
			//alert(detailIds.substring(1));
			$("#tags").attr("value",detailIds.substring(1));
			//alert(33333);
			this.form1.action = "delSeekSourceDetail.action";
			this.form1.submit();
		}
	}
	//detailIds
}
function onChangeSupplier(){
	//alert("ddf  ");
	var sid = $("#suppName").val();
	var sname = $("#suppName").find("option:selected").text();
	var storeOrder = $("#storeOrder").val();
	var firstSupplier = $("#firstSupplier").val();
	//alert($("#table_"+sid).html());
	//alert(firstSupplier);
	//alert(sid+"   sid");
	if($("#table_"+sid).html() == null && firstSupplier != sid && firstSupplier != 0){
		//alert("xx  ");
		$("#storeOrder").attr("value",sid);
		var createTable = "<table  class='tableStyle' formMode='true' id=table_"+sid+"><tr id=tr_"+sid+"><th colspan='10'>"+sname+"</th>"+
						  
						  //"<th colspan='2'><input type='button' value='删除' class='button' style='width:60px' onclick='delOneSupplier(table_"+sid+")'></input></th></tr>";
						  "<th colspan='2'><input type='button' value='删除' class='button' style='width:60px' onclick='delOneSupplier(table_"+sid+")'></input></th></tr>";
		//for(var i = 0;i<length;i++){
		$("#countMaterial").find("tr").each(function(i){
			
			//alert($(this).attr("id"));
			//:eq("+j+")"). $("ul li:eq(1)")
			var pId = $(this).attr("id");
			if(i>0){
				var oneP = 0;
				var oneM = 0;
				var remain = 0;
				if(pId != ''){
					remain = calculateRemainQuantity(pId.substring(3));
					//alert(pId.substring(3)+"  XXX   "+pId);
					//oneP = $("input[name='seekSourcePrice']").length;
					if(remain*1>0){
						oneP = $("input[flag='seekSourcePrice_"+pId.substring(3)+"']:eq(0)").val();
						//oneM = $("input[flag='seekSourceMoney_"+pId.substring(3)+"']:eq(0)").val();
						//alert(oneP+"  money   "+oneM);
						oneM = (oneP*1)*(remain*1);
					}else{
						oneP = 0;
						oneM = 0;
						remain = 0;
					}
				}
				var html = $(this).html();
				var target="detailIds\" value=\"";
				//alert("html  remain "+remain); // id="detailIds" value="${d.id } "id='detailIds' value='0'
				
				var positionB = html.indexOf(target)+target.length;
				var temp1 = html.substring(positionB);
				var engTemp = temp1.substring(temp1.indexOf("\""));
				var beginTemp = html.substring(0,positionB);
				html = beginTemp+"0"+engTemp;
				
				//var positionE = html.indexOf("id='detailIds' value='");
				//html = html.replace(/id=\"detailIds\" value=\"\d{2}\"./g, "sssssssssssssssss");
				//加入剩余数量
				html = html.replace("\"seekSourceQuantity\"","\"seekSourceQuantity\" value='"+remain+"'");
				html = html.replace("\"seekSourcePrice\"","\"seekSourcePrice\" value='"+oneP+"'");
				html = html.replace("\"seekSourceMoney\"","\"seekSourceMoney\" value='"+oneM+"'");
				//alert("html  after "+html);
			createTable = createTable + "<tr>"+html + "</tr>";
			//剔除标题行的影响
			if(i>1)
				createTable = createTable + "<input type='hidden' name='supplierIds' value="+sid+"></input>";
			
			}
			//alert($(this).html());
		});
		//}
		createTable = createTable+"</table>";
		//alert(createTable);
		if(storeOrder*1==0){
			$("#countMaterial").after(createTable);
		}else{
			$("#table_"+storeOrder).after(createTable);
		}
	}else if(firstSupplier == 0){
		//alert(sname);
		if($("#table_"+sid).html() == null){
			$("#firstSupplier").attr("value",sid);
			$("#firstName").html(sname);
			//alert($(this).attr("id"));
			$("#countMaterial").find("tr").each(function(i){
			var pId = $(this).attr("id");
			if(i>0){
				var oneP = 0;
				var oneM = 0;
				var remain = 0;
				if(pId != ''){
					remain = calculateRemainQuantity(pId.substring(3));
					//alert(pId.substring(3)+"  XXX   "+pId);
					//oneP = $("input[name='seekSourcePrice']").length;
					//oneM = $("input[name='seekSourceMoney']").length;
					oneP = $("input[flag='seekSourcePrice_"+pId.substring(3)+"']:eq(0)").val();
					//oneM = $("input[flag='seekSourceMoney_"+pId.substring(3)+"']:eq(0)").val();
					//alert(oneP+"  money   "+oneM);
					oneM = (oneP*1)*(remain*1);
				}
				var html = $(this).html();
				//alert("html  before "+html);
				//加入剩余数量
				
			//9为数量，10为单价，11为金额,
			$(this).children('td').eq(9).children('input').val(remain);
			$(this).children('td').eq(10).children('input').val(oneP);
			//alert(price1+"    price1  price   " + price);
			$(this).children('td').eq(11).children('input').val(oneM);
			/*
				html = html.replace("\"seekSourceQuantity\"","\"seekSourceQuantity\" value='"+remain+"'");
				html = html.replace("\"seekSourcePrice\"","\"seekSourcePrice\" value='"+oneP+"'");
				html = html.replace("\"seekSourceMoney\"","\"seekSourceMoney\" value='"+oneM+"'");
				$(this).html(html);
				*/
			}
			});
		}
	}
}
function calculateRemainQuantity(planId){
	
	//var planIdLength = $("input[name='planId']").length;
	//var length = $("input[name='seekSourceQuantity']").length;
	var planQ = 0;
	var subtract = 0 ;
	//alert(planId+"  calculateRemainQuantity www ");
	$("input[name='planId']").each(function(i){
		var sum = 0;
		var pid = $(this).val();
		planQ = $("#planQuantity_"+pid).val();
		//alert(planQ+" planQ  vvv pid  "+pid);
		//alert(planId == pid);
		if(planId == pid){
			$("input[flag='seekSourceQuantity_"+pid+"']").each(function(j){
				sum = sum*1 + $(this).val()*1;
				//alert("seekSourceQuantity_"+pid+"  " +$(this).val());
			});
			//alert(sum+"  ddd  "+planQ);
			subtract = planQ-sum;
			//return planQ-sum;
			//break;
		}
	});
	return subtract;
}
function checkQuantity(){
	
	var isOk = true;
	$("input[name='planId']").each(function(i){
		var pid = $(this).val();
		var planQ = $("#planQuantity_"+pid).val();
		//alert("vvv    "+planQ);
		var sum = 0;
		$("input[flag='seekSourceQuantity_"+pid+"']").each(function(j){
			sum = sum*1 + $(this).val()*1;
			//alert("seekSourceQuantity_"+pid+"  " +$(this).val());
		});
		//alert("sum  "+sum+"  planQ  "+planQ);
		if(sum*1 != planQ*1){
			var name = $("#materialName_"+pid).val();
			var code = $("#materialCode_"+pid).val();
			alert(code+"  "+name+" 数量之和不等于计划数量!");
			isOk = false;
		}
		if(!isOk){
			return isOk;
		}
	//alert(planQ-sum);
	});
	return isOk;
}
function changePrice(planId,value){
	//alert(value);
	$("input[flag='seekSourcePrice_"+planId+"']").each(function(j){
		//alert("jdddd  "+j);
		var q = $("input[flag='seekSourceQuantity_"+planId+"']:eq("+j+")").val();
		$(this).val(value);
		$("input[flag='seekSourceMoney_"+planId+"']:eq("+j+")").val((q*1)*(value*1));
		//alert($("input[flag='seekSourceMoney_"+planId+"']:eq("+j+")").val());
	});
}
function changePriceByQuantity(planId,obj){
	//alert(obj.parentNode.parentNode.childNodes.length);
	var tr = obj.parentNode.parentNode;
	var quantity = obj.value;
	//alert($(tr).children('td').eq(10).html());
	//10为单价，11为金额
	//var price = $(tr).children('td').eq(10).children('input').value;
	var price = $(tr).children('td').eq(10).children('input').val();
	//alert(price1+"    price1  price   " + price);
	$(tr).children('td').eq(11).children('input').val((quantity*1)*(price*1));
	//alert($(tr).children('td').eq(10).val());
	/*$("input[flag='seekSourcePrice_"+planId+"']").each(function(j){
		//alert("jdddd  "+j);
		var q = $("input[flag='seekSourceQuantity_"+planId+"']:eq("+j+")").val();
		$(this).val(value);
		$("input[flag='seekSourceMoney_"+planId+"']:eq("+j+")").val((q*1)*(value*1));
		//alert($("input[flag='seekSourceMoney_"+planId+"']:eq("+j+")").val());
	});*/
}
</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" id="tags" name="tags" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="${cflag }" />
<input type="hidden" name="seekSource.pc.id" id="id" value="${seekSource.pc.id}" />
<input type="hidden" name="seekSource.id" id="id" value="${seekSource.id}" />
<input type="hidden" name="storeOrder" id="storeOrder" value="0"/>
<div class="box1" panelWidth="868" panelheight="558">
	<table class="tableStyle">
		<tr>
			<th>供应商:</th>
			<td>
				<select  name="supplier" id="suppName" onChange="onChangeSupplier();" class="default">
						<c:if test="${!empty suppliers}">
						 	<c:forEach items="${suppliers }" var="supplier">  
								<option value="${supplier.id}">${supplier.name }</option>	 
							</c:forEach>
						</c:if>
				 </select>
			 </td>
		</tr>
	</table>
	<c:forEach items="${detailMap }" var="detail" varStatus="status">
	<c:choose>
		<c:when test="${status.index == 0}">
	<input type="hidden" name="firstSupplier" id="firstSupplier" value="${detail.key }"/>
			<table class="tableStyle" id="countMaterial">
			<tr>
				<th colspan="10" id="firstName">${supplierMap[detail.key] }</th>
				<th colspan="2"><input type="button" value="删除" onclick='delOneSupplier(countMaterial);'></input></th>
			</tr>
		</c:when>
	<c:otherwise>
			<table class="tableStyle"" id="table_${detail.key }">
			<tr id="tr_${detail.key }">
				<th colspan="10">${supplierMap[detail.key] }</th>
				<th colspan="2"><input type="button" value="删除" onclick='delOneSupplier(table_${detail.key });'></input></th>
			</tr>
	</c:otherwise>
	</c:choose>
		<tr>
			<th width="80">物资类别</th>
			<th width="120">物资代码</th>
			<th width="120">物资名称</th>
			<th width="120">材质</th>
			<th width="120">标准号</th>
			<th width="60">单位</th>
			<th width="60">单价</th>
			<th width="60">数量</th>
			<th width="80">预算金额</th>
			<th width="80">寻源数量</th>
			<th width="80">寻源单价</th>
			<th width="80">寻源金额</th>
		</tr>
	 	<c:forEach items="${detailMap[detail.key] }" var="d" varStatus="status">
	 	<input type="hidden" name="supplierIds" value="${detail.key }"></input>
	    <input type="hidden" id="planQuantity_${d.plan.id }" value="${d.plan.quantity } "></input>
	    <input type="hidden" id="materialCode_${d.plan.id }" value="${d.plan.itemsNum } "></input>
	    <input type="hidden" id="materialName_${d.plan.id }" value="${d.plan.itemsName } "></input>
		<tr id="row${d.plan.id}">
	    <input type="hidden" id="detailIds" value="${d.id }" name="detailIds"></input>
	    <input type="hidden" name="planId" value="${d.plan.id }"></input>
		
			<td>${d.plan.itemType }</td>
			<td>${d.plan.itemsNum }</td>
			<td>${d.plan.itemsName }</td>
			<td>${d.plan.material }</td>
			<td>${d.plan.standardNum }</td>
			<td>${d.plan.unit }</td>
			<td>${d.plan.budgetPrice }</td>
			<td>${d.plan.quantity }</td>
			<td width="80"><fmt:formatNumber value="${d.plan.budgetMoney }" type="currency"/></td>
			<td width="80"><input type="text" name="seekSourceQuantity" flag="seekSourceQuantity_${d.plan.id }" value="${d.seekSourceQuantity }" style="width: 60px;" onblur="changePriceByQuantity(${d.plan.id },this)"/></td>
			<td width="80"><input type="text" name="seekSourcePrice" flag="seekSourcePrice_${d.plan.id }" value="${d.seekSourcePrice }" style="width: 60px;" onblur="changePrice(${d.plan.id },this.value)"/></td>
			<td width="80"><input type="text" name="seekSourceMoney" flag="seekSourceMoney_${d.plan.id }" value="${d.seekSourceM }" style="width: 60px;"/></td>
	   		<input type="hidden" name="materialName" value="${d.plan.itemsName }"></input>
		</tr>
		</c:forEach>
	</table>
	</c:forEach>
	<table class="tableStyle">
		<tr>
			<td colspan="14">审核主管
			<select id="personId" name="seekSource.director.id" class="default" style="width: 200px;">
						<option value="0">请选择</option>
						<c:if test="${!empty dpersons}">
						 	<c:forEach items="${dpersons }" var="dperson">  
								<option value="${dperson.id}" <c:if test="${seekSource.director.id == dperson.id }">selected="selected"</c:if> >${dperson.name }</option>					 
							</c:forEach>
						</c:if>
				</select>
			</td>
			<td colspan="2"><font color="red">合计：${totalMoney}</font></td>
		</tr>
	</table>
	<table class="tableStyle" formMode="true">
		<tr>
			<td>
			<c:if test="${seekSource.checkStatus==02 || seekSource.checkStatus==01}">
			<input type="button"  onclick="ok()" value=" 提 交 "/>
				
				<input type="reset" value=" 重 置 "/>
				</c:if>
			</td>
		</tr>
	</table>
	<table class="tableStyle">
		<tr>
			<th colspan="4">寻源审核记录</th>
		</tr>
		<tr>
			<th width="80">审核人</th>
			<th width="180">审核状态</th>
			<th width="180">审核意见</th>
			<th width="180">审核日期</th>
		</tr>
		<c:forEach items="${seekSource.checks }" varStatus="status" var="check">
			<tr>
				<td>${check.person.name }</td>
				<td>
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
				<td>${check.opinion }</td>
				<td>${check.checkDate }</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</form>
</body>

			<!-- 
			<td width="130">
				<c:if test="${plan.checkStatus==01 }">主管末审核</c:if>
				<c:if test="${plan.checkStatus==02 }">主管末通过审核</c:if>
				<c:if test="${plan.checkStatus==03 }">经理末审核</c:if>
				<c:if test="${plan.checkStatus==04 }">经理末通过审核</c:if>
				<c:if test="${plan.checkStatus==05}">经理已审核</c:if>
			</td>
			 -->