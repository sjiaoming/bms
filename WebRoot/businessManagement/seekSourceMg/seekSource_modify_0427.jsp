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

<script type='text/javascript' src='<%=path %>/dwr/interface/planManager.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>

<script language="javascript"><!--
/*$(function(){
	//getSum();
	var s = document.getElementById("cflag").value;
	if(s!=""&&s=="addSuccess"){
		top.Dialog.close();
	}
});*/
function ok(){
		var access=false;
		access=$('#form1').validationEngine({returnIsValid:true});
		if(access){
			if(confirm("您确定要提交吗?")){
				this.form1.action = "doProcurementCheck.action";
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
	$("#row"+planId).parents("tr").remove();
		//return access;
	}

function addSupplier(name,sid){
	var ssid = "dis"+sid;
	//alert($("#countMaterial").find("tr").length);
	var tr = "<tr id='"+ssid+"'>";
	//alert(ssid);
	var s = "#"+ssid;
	///alert($(s).html()==null);
	if($(s).html()==null){
		var j=0;
		$("#countMaterial").find("tr").each(function(i){
			//alert(i);
			j = i+1;
			if(j==1){
				tr = tr + "<td>"+name+"</td>";
			}else{
				tr = tr + "<td style='text-align:center;'><input type='text' name='' style='width: 60px;'></td>";
			}
			//j++;
		  });
		tr = tr + "</tr>";
		//alert(tr);
		$("#selSupplier").append(tr);
	}
	/*
	$(ssid).live("click",function(){
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
	*/
}
</script>

<body>
<form action="?" id="form1" name="form1" >
<input type="hidden" name="tags" value="<%=request.getParameter("pids") %>" />
<input type="hidden" name="modifyFlag" value="<%=request.getParameter("modifyFlag") %>" />
<input type="hidden" name="cflag" id="cflag" value="${cflag }" />
<input type="hidden" name="pcId" id="id" value="${pc.id}" />
<div class="box1" panelWidth="828" panelheight="558">
	<table class="tableStyle" formMode="true" id="countMaterial">
		<tr>
			<th width="80">物料编码</th>
			<th width="180">物料描述</th>
			<th width="80">计划状态</th>
			<th width="130">审核状态</th>
			<th width="80">单价</th>
			<th width="80">数量</th>
			<th width="90">单位</th>
			<th width="80">预算金额</th>
			<th width="80">寻源单价</th>
			<th width="80">寻源金额</th>
		</tr>
	    <c:forEach items="${pc.plans }" varStatus="status" var="plan">
		<tr id="row${plan.id}">
			<td>${plan.itemsNum }</td>
			<td style="word-break:break-all">${plan.itemsName }</td>
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
				<c:if test="${plan.checkStatus==03 }">经理末审核</c:if>
				<c:if test="${plan.checkStatus==04 }">经理末通过审核</c:if>
				<c:if test="${plan.checkStatus==05}">经理已审核</c:if>
			</td>
			<td><fmt:formatNumber value="${plan.planPrice }" type="currency"/></td>
			<td>${plan.num }</td>
			<td>${plan.unit }</td>
			<td><fmt:formatNumber value="${plan.budget }" type="currency"/></td>
			<td><input type="text" name="seekSourcePrice" style="width: 60px;"/></td>
			<td><input type="text" name="seekSourceMoney" style="width: 60px;"/></td>
		</tr>
		</c:forEach>
	</table>
	<table class="tableStyle" formMode="true">
		<tr>
			<th>供应商:</th>
			<c:forEach items="${suppliers }" var="supplier">  
				<th>
					<input type="checkbox" value="${supplier.name}" id="${supplier.id}" onclick="addSupplier('${supplier.name}',${supplier.id})">${supplier.name }
				</th>
			</c:forEach>	
		</tr>
	</table>
	<table class="tableStyle" id="selSupplier" formMode="true">
		<tr>
			<th>供应商\物料名称</th>
			<c:forEach items="${materialNames }" var="name">  
			<td>
				${name}
			</td>
			</c:forEach>
		</tr>
	</table>
	<table class="tableStyle" formMode="true">
		<tr>
			<td>选择审核人
			<select id="personId" name="" class="validate[required]" autoWidth="true">
				<c:if test="${!empty persons}">
				 	<c:forEach items="${persons }" var="person">  
						<option value="${person.id}" >${person.name }</option>					 
					</c:forEach>
				</c:if>
			</select>
			</td>
			<td>
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