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
<script type="text/javascript" src="<%=path %>/js/Calendar3.js"></script>
<script language="javascript">
var flag = false;
$(function(){
	$("#copyBtn").live("click",function(){
		//克隆按钮所在的tr并添加到table的末尾
		$(this).parents("tr").clone(true).appendTo($(this).parents("table"))
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
			$(this).parents("tr").remove()
		})
	
	})
})
function getArrivalValue(field){
	if(flag){
	var pid = field.cells[0].childNodes[1].value;
	var nowNum = field.cells[1].childNodes[0].value;
	var total_arrivalNums = Number(document.getElementById("arrivalNums_"+pid).value);
	var sum_total_arrivalNum = 0;
	var total_arrivalNum = document.getElementsByName("arrivalNum_"+pid);
	
	if(total_arrivalNum.length>0){
		for(var i =0;i<total_arrivalNum.length;i++){
			sum_total_arrivalNum += Number(total_arrivalNum[i].value);
		}
	}
	var temp2 = 0;
	var temp = document.getElementById("tab1").getElementsByTagName("select");
	var temp1 = document.getElementById("tab1").getElementsByTagName("input");
	if(temp.length>0){
		for(var i =0;i<temp.length;i++){
			
			if(temp[i].value == pid ){
					
					if(i ==0){
						if(temp1[i].value!=null){
							sum_total_arrivalNum += Number(temp1[i].value);
						}
					}else{
						if(temp1[i*5].value!=null){
							sum_total_arrivalNum += Number(temp1[i*5].value);
						}
					}
			}
			
		}
	}
	field.cells[1].childNodes[0].value = total_arrivalNums - sum_total_arrivalNum >= 0 ? total_arrivalNums - sum_total_arrivalNum : 0;
	//document.getElementById("sss").value = temp.length+"&"+temp1.length+"&"+sum_total_arrivalNum+"&flag:"+flag;
	var   txtRange   =   document.selection.createRange(); 
	txtRange.moveStart(   "character",   field.cells[1].childNodes[0].value.length); 
	txtRange.moveEnd(   "character",   0   ); 
	txtRange.select();
	}
	flag = false;
}

function ok(){
	
	this.form1.action = "addArrival.action";
	this.form1.submit();
	/*
	var access=false;
	//var validate=true;
		access=$('#form1').validationEngine({returnIsValid:true});
		if(access){
			 var temp1 = document.getElementById("tab2").getElementsByTagName("input");
			if(temp1.length>0){
				for(var i =0;i<temp1.length;i++){
					if(temp1[i].name.substring(0,11) == "arrivalNums"){
						
						if(temp1[i].value<getSumArrivalNums(temp1[i].name.substring(12))){
							top.Dialog.alert("到货数量不能大于总数量");
							access = false;
							break;
						}else{
							access = true;
						}
						
					}
				}
			}
			if(access){
				if(confirm("您确定要提交吗?")){
					this.form1.action = "addArrival.action";
					this.form1.submit();
					//top.Dialog.close();
					return;
				}
			}
		}
		*/
	}
function getSumArrivalNums(pid){
	//alert(pid);
	//var total_arrivalNums = Number(document.getElementById("arrivalNums_"+pid).value);
	var sum_total_arrivalNum = 0;
	var total_arrivalNum = document.getElementsByName("arrivalNum_"+pid);
	if(total_arrivalNum.length>0){
		for(var i =0;i<total_arrivalNum.length;i++){
			sum_total_arrivalNum += Number(total_arrivalNum[i].value);
		}
	}
	//var temp2 = 0;
	var temp = document.getElementById("tab1").getElementsByTagName("select");
	var temp1 = document.getElementById("tab1").getElementsByTagName("input");
	if(temp.length>0){
		for(var i =0;i<temp.length;i++){
			
			if(temp[i].value == pid ){
					
					if(i ==0){
						if(temp1[i].value!=null){
							sum_total_arrivalNum += Number(temp1[i].value);
						}
					}else{
						if(temp1[i*5].value!=null){
							sum_total_arrivalNum += Number(temp1[i*5].value);
						}
					}
			}
			
		}
	}
	//alert(pid);
	return sum_total_arrivalNum;
}
function del(pid,aid){
	document.getElementById("id").value = pid;
	document.getElementById("aid").value = aid;
	this.form1.action = "deleteArrivalItems.action";
	this.form1.submit();
	return;
}
</script>

<body>
<form action="?" id="form1" name="form1" >
	<input type="hidden" name=id id="id" value="" />
	<input type="hidden" name="aid" id="aid"  value="" />
	<input type="hidden" id="ccflag" name="ccflag" value=""/>
	<table  id="tab2" class="tableStyle" >
			<tr>
				<th>ID</th>
				<th>计划编号</th>
				<th>物料名称</th>
				<th>规格型号</th>
				<th> 供应商</th>
				<th>合同数量</th>
				<th>到货数量</th>
				<th>验收数量</th>
				<!-- <th>操作</th> -->
			</tr>
		<c:if test="${!empty pc.plan}">
			<c:forEach items="${pc.plan }" var="p">
			
			<tr>
				<td>${p.id}</td>
				<td>${p.planNum}</td>
				<td><span class="text_slice" style="width:50px;" title="${p.itemsName}">${p.itemsName}</span></td>
				<td><span class="text_slice" style="width:50px;" title="${p.modelType}">${p.modelType}</span></td>
				<td><span class="text_slice" style="width:80px;" title="${p.supplier.name}">${p.supplier.name}</span></td>
				<td>${p.contractNum}
					<input type="hidden" id="arrivalNums_${p.id }" name="arrivalNums_${p.id }" value="${p.contractNum}"  />
				</td>
				
				<td>
				<c:forEach items="${m}" var="m">
					<c:if test="${m.key eq p.id}">
					 <c:set var="mk" value="${m.value }"></c:set>
						${mk.arrivalNum }
					</c:if>
				</c:forEach>
				</td>
				<td>
				<c:forEach items="${m}" var="m">
				<c:if test="${m.key eq p.id}">
					<c:set var="mv" value="${m.value }"></c:set>
					${mv.acceptanceNum }
					<input type="hidden" id="acceptanceNums_${p.id }" name="acceptanceNums_${p.id }" value="${mv.acceptanceNum }"  />
				</c:if>
				</c:forEach>
				</td>
				
				<!-- <td><button onclick="open2();"></button></td> -->
			</tr>
			</c:forEach>
		</c:if>
	</table>
	<div class="box2"  panelTitle="表单标题" showStatus="false" roller="true">
	<table id="tab0" class="tableStyle" >
		<tr>
				
				<th style="width:70px">计划编码</th>
				<th style="width:205px">物资名称</th>
				<th style="width:150px">供应商</th>
				<th style="width:150px">到货数量</th>
				<th style="width:150px">到货时间</th>
				<th style="width:150px">验收数量</th>
				<th style="width:150px">验收时间</th>
				<th style="width:150px">操作</th>
		</tr>
		
		<c:if test="${!empty al}">
		
			<c:forEach items="${al }" var="l">
			<tr>
				<td>${l.plan.planNum }</td>
				<td><span class="text_slice" style="width:150px;" title="${l.plan.itemsName }">${l.plan.itemsName }</span></td>
				<td><span class="text_slice" style="width:150px;" title="${l.plan.supplier.name}">${l.plan.supplier.name}</span></td>
				<td>${l.arrivalNum }
					<input type="hidden" id="arrivalNum_${l.plan.id }" name="arrivalNum_${l.plan.id }" value="${l.arrivalNum }"  /></td>
				<td>${l.arrivalDate }</td>
				<td>${l.acceptanceNum }
					<input type="hidden" id="acceptanceNum_${l.plan.id }" name="acceptanceNum_${l.plan.id }" value="${l.acceptanceNum }"  /></td>
				<td>${l.acceptanceDate }</td>
				<td><span class="img_delete hand" title="删除" onclick='top.Dialog.confirm("删除该条到货纪录吗？",function(){del(${pc.id },${l.id})});'></span></td>
				</tr>
			</c:forEach>
		
		</c:if>
	</table>
	<table id="tab1" class="tableStyle" >	
		<tr onclick="getArrivalValue(this)" id="tr1">
		
			<td style="width:210px">
			<c:if test="${!empty pc.plan}">
			
			<select id="itemsName" name="pid" class="default" style="width:315px">
			<c:forEach items="${pc.plan }" var="p">
				    <option value="${p.id}">[${p.planNum} ][ ${p.itemsName}]</option>
			</c:forEach>
			</select>
			</c:if>
			</td>
			<td style="width:145px"><input type="text" name="arrivalNum" class=" validate[required]" onclick="javascript:flag=true;"/></td>
			<td style="width:145px"><input  id="arrivalDate" name="arrivalDate"  value="" class="date"/></td>
			<td style="width:145px"><input type="text" name="acceptanceNum" class=" validate[required]" onclick="getAcceptanceValue()"/></td>
			<td style="width:145px"><input  id="acceptanceDate" name="acceptanceDate"  value="" class="date"/></td>
			<td style="width:150px"><input type="button" value="追加" id="copyBtn"/></td>
		</tr>
		</table>
	<table class="tableStyle" transMode="true">
		<tr>
			<td colspan="4">
			<input type="button" value="提交" onclick="ok()"/>
				<!-- <input type="submit" value="提交" /> -->
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	
	</div>

</form>
</body>