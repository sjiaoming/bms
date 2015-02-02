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
	   var firstSID = $("#firstsid").attr("value");
	  // alert(firstSID);
	   if(firstSID>0){
			var access=false;
			access=$('#form1').validationEngine({returnIsValid:true});
			var directorId = $("#personId").val();
			if(directorId != 0){
				if(checkQuantity()){
					if(access){
						if(confirm("您确定要提交吗?")){
							this.form1.action = "addSeekSource.action";
							this.form1.submit();
							this.form1.method = "post";
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
	   }else{
		   alert("请选择至少一个供应商！");
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
	calculateTotalMoney();
	}
function delOneSupplier(tableId){
	//alert(tableId);
	if(tableId != "countMaterial"){
		//alert($(tableId).attr("id"));
		//预防删除一条供应商后不能在选出来的问题
		$("#storeOrder").attr("value",0);
		$(tableId).remove();
	}else{
	//	$("#"+tableId).remove();$("#firstName").html()
		$("#firstSupplier").attr("value",0);
		$("#firstsid").attr("value",0);
		$("#firstName").html("");
		$("#countMaterial").find("tr").each(function(i){
			if(i>1){
				var pId = $(this).attr("id");
				//alert(pId);
				$("input[flag='seekSourcePrice_"+pId.substring(3)+"']:eq(0)").val("");
				$("input[flag='seekSourceQuantity_"+pId.substring(3)+"']:eq(0)").val("");
				$("input[flag='seekSourceMoney_"+pId.substring(3)+"']:eq(0)").val("");
			}
			/*var html = $(this).html();alert(html);
			html = html.replace("\"seekSourceQuantity\"","\"seekSourceQuantity\" value=\"\"");
			html = html.replace("\"seekSourcePrice\"","\"seekSourcePrice\" value=\"\"");
			html = html.replace("\"seekSourceMoney\"","\"seekSourceMoney\" value=\"\"");
			$(this).html(html);alert("22   "+html);*/
		});
	}
	calculateTotalMoney();
}
function onChangeSupplier(){
	//alert("ddf  ");
	var sid = $("#suppName").val();
	var sname = $("#suppName").find("option:selected").text();
	//alert("deeeed  ");
	var storeOrder = $("#storeOrder").val();
	var firstSupplier = $("#firstSupplier").val();
	if($("#table_"+sid).html() == null && firstSupplier != sid && firstSupplier != 0){
		//alert("xx  ");
		$("#storeOrder").attr("value",sid);
		var createTable = "<table  class='tableStyle' id=table_"+sid+"><tr id=tr_"+sid+"><th colspan='13'>"+sname+"</th>"+
						  
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
					//oneM = $("input[name='seekSourceMoney']").length;
					if(remain*1>0){
						oneP = $("input[flag='seekSourcePrice_"+pId.substring(3)+"']:eq(0)").val();
						//oneM = $("input[flag='seekSourceMoney_"+pId.substring(3)+"']:eq(0)").val();
						//alert(oneP+"  money   "+oneM);
						oneM = (oneP*1)*(remain*1);
						oneM = Math.round(oneM*100)/100;
					}else{
						oneP = 0;
						oneM = 0;
						remain = 0;
					}
				}
				var html = $(this).html();
				//alert("html  before "+html);
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
		//$("#firstsid").attr("value",sid);
		$("input[name='supplierIds']").each(function(i){
			$(this).val(sid);
		});
		$("#firstSupplier").attr("value",sid);
		$("#firstName").html(sname);
	}
	calculateTotalMoney();
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
		//alert(planId+"   vvv   "+pid);
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
			return false;
		}
	//alert(planQ-sum);
	});
	return isOk;
}
function changePrice(planId,value){
	//alert(value);
	$("input[flag='seekSourcePrice_"+planId+"']").each(function(j){
		//alert("jdddd  "+j);
		var oneValue = $("input[flag='seekSourcePrice_"+planId+"']:eq("+j+")").val();
		if(value == oneValue){
			var q = $("input[flag='seekSourceQuantity_"+planId+"']:eq("+j+")").val();
			$(this).val(value);
			var result = (q*1)*(value*1);
			//alert(result);
			result = Math.round(result*100)/100 ;
			//alert("Math.round    "+result);
			$("input[flag='seekSourceMoney_"+planId+"']:eq("+j+")").val(result);
			calculateTotalMoney();
		}
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
function calculateTotalMoney(){
	var totalMoney = 0.0 ;
	$("input[name='seekSourceMoney']").each(function(j){
		//alert("jdddd  "+j);
		var m = $("input[name='seekSourceMoney']:eq("+j+")").val();
		totalMoney = totalMoney*1 + m*1 ;
		//alert($("input[flag='seekSourceMoney_"+planId+"']:eq("+j+")").val());
	});
	//alert(totalMoney);
	$("#totalMoney").text(totalMoney);
}
</script>

<body>
<form action="?" id="form1" name="form1" method="post">
<input type="hidden" name="tags" value="<%=request.getParameter("pids") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="${cflag }" />
<input type="hidden" name="seekSource.pc.id" id="id" value="${pc.id}" />
<input type="hidden" name="storeOrder" id="storeOrder" value="0"/>
<input type="hidden" name="firstSupplier" id="firstSupplier" value="0"/>
<div class="box1" panelWidth="1580" panelheight="558">
	<table class="tableStyle">
		<tr>
			<th>供应商:</th>
			<td>
				<select  name="supplier" id="suppName" onChange="onChangeSupplier();" class="default">
						<option value="">请选择供应商</option>
						<c:if test="${!empty suppliers}">
						 	<c:forEach items="${suppliers }" var="supplier">  
								<option value="${supplier.id}">${supplier.name }</option>	 
							</c:forEach>
						</c:if>
				 </select>
			 </td>
		</tr>
	</table>
	<table class="tableStyle" id="countMaterial">
		<tr>
			<th colspan="14" id="firstName"></th>
			<th colspan="2"><input type="button" value="删除" onclick="delOneSupplier('countMaterial')"></input></th>
		</tr>
		<tr>
			<th width="80">计划编号</th>
			<th width="80">物资类别</th>
			<th width="80">物资代码</th>
			<th width="80">物资名称</th>
			<th width="100">规格型号</th>
			<th width="100">材质</th>
			<th width="100">标准号</th>
			<th width="60">单价</th>
			<th width="60">数量</th>
			<th width="80">预算金额</th>
			<th width="80">要求到货日期</th>
			<th width="60">负责人</th>
			<th width="90">用途</th>
			<th width="80">寻源数量</th>
			<th width="80">寻源单价</th>
			<th width="80">寻源金额</th>
		</tr>
	    <c:forEach items="${pc.plans }" varStatus="status" var="plan">
	    <input type="hidden" id="planQuantity_${plan.id }" value="${plan.quantity } "></input>
	    <input type="hidden" id="materialCode_${plan.id }" value="${plan.itemsNum } "></input>
	    <input type="hidden" id="materialName_${plan.id }" value="${plan.itemsName } "></input>
	 	<input type="hidden" id="firstsid" name="supplierIds" value=""></input>
		<tr id="row${plan.id}">
	    <input type="hidden" name="planId" value="${plan.id }"></input>
			<td>${plan.planNum }</td>
			<td>${plan.itemType }</td>
			<td><span class="text_slice" style="width:80px;" title="${plan.itemsNum }">${plan.itemsNum }</span></td>
			<td><span class="text_slice" style="width:80px;" title="${plan.itemsName }">${plan.itemsName }</span></td>
			<td><span class="text_slice" style="width:100px;" title="${plan.modelType }">${plan.modelType }</span></td>
			<td><span class="text_slice" style="width:100px;" title="${plan.material }">${plan.material }</span></td>
			<td>${plan.standardNum }</td>
			<td>${plan.budgetPrice }</td>
			<td>${plan.quantity }</td>
			<td width="80"><fmt:formatNumber value="${plan.budgetMoney }" type="currency"/></td>
			<td><fmt:formatDate value="${plan.arrivalDate }" pattern="yyyy-MM-dd"  /></td>
			<td>${plan.person.name }</td>
			<td><span class="text_slice" style="width:100px;" title="${plan.useFor }">${plan.useFor }</span></td>
			<td width="80"><input type="text" name="seekSourceQuantity" flag="seekSourceQuantity_${plan.id }" style="width: 60px;" onblur="changePriceByQuantity(${plan.id },this)" value="${plan.quantity }"/><font color="black">${plan.unit }</font></td>
			<td width="80"><input type="text" name="seekSourcePrice" flag="seekSourcePrice_${plan.id }" style="width: 60px;" onblur="changePrice(${plan.id },this.value)"/></td>
			<td width="80"><input type="text" name="seekSourceMoney" flag="seekSourceMoney_${plan.id }" style="width: 60px;"/></td>
	   		<input type="hidden" name="materialName" value="${plan.itemsName }"></input>
		</tr>
		</c:forEach>
	</table>
	<table class="tableStyle">
		<tr>
			<td>审核主管
			<select id="personId" name="seekSource.director.id" class="default" style="width: 200px;">
						<option value="0">请选择</option>
						<c:if test="${!empty dpersons}">
						 	<c:forEach items="${dpersons }" var="dperson">  
								<option value="${dperson.id}" >${dperson.name }</option>					 
							</c:forEach>
						</c:if>
				</select>
			</td>
			<td>寻源合计:<font color="red"><span id="totalMoney" class="msg"></span></font>
			</td>
		</tr>
	</table>
	<table class="tableStyle" formMode="true">
		<tr>
			<td>
			<input type="button"  onclick="ok()" value=" 提 交 "/>
				
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	</div>
</form>
</body>

			<!-- 
			<td width="130">
				<c:if test="${plan.checkStatus==01 }">主管未审核</c:if>
				<c:if test="${plan.checkStatus==02 }">主管未通过审核</c:if>
				<c:if test="${plan.checkStatus==03 }">经理未审核</c:if>
				<c:if test="${plan.checkStatus==04 }">经理未通过审核</c:if>
				<c:if test="${plan.checkStatus==05}">经理已审核</c:if>
			</td>
			 -->