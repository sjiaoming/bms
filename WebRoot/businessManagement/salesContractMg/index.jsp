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
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=path %>/dwr/interface/planManager.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>

<script type="text/javascript">
	$(function(){
		top.Dialog.close();
		var s = document.getElementById("ccflag").value;
		if(s!=""&&s=="success"){
			alert("操作成功");
		}
		var parentMainHeight = window.parent.document.documentElement.clientHeight;
		var flexiGridHeight=parentMainHeight-parentTopHeight-parentBottomHeight-fixHeight-35;
		if(parentMainHeight == 0 || parentMainHeight == undefined){
			$('.flexiStyle').flexigrid({
				height:400,
				resizable: false,
				showToggleBtn: false,
				minColToggle: 1
				});	
		}else{
			$('.flexiStyle').flexigrid({
				height:parentMainHeight*0.65,
				resizable: false,
				showToggleBtn: false,
				minColToggle: 1
				});	
		}
		if(this.form1.contractMsg.value!="" && this.form1.contractMsg.value=="success"){
			top.Dialog.alert("销售台帐已保存");
		}
		this.form1.contractMsg.value="";
	})
	//全选 
	function test(){
		tempc = document.getElementsByName("checkbox");
		for(var i=0;i<tempc.length;i++) {    
		f =tempc[i];    
		if(f.checked==false){      
			f.checked=true;     
			}
		}
		getSelectedCount();
	} 
	//反选 
	function ftest() { 
		tempc = document.getElementsByName("checkbox");
		for(var i=0;i<tempc.length;i++) {    
			f =tempc[i];    
			if(f.checked==false){      
				f.checked=true;
				}else{    
					f.checked=false;     
				} 
			}
		getSelectedCount();
		} 
	//取消全部 
	function CancelAll() { 
		tempc = document.getElementsByName("checkbox");
		for(var i=0;i<tempc.length;i++) {
		f =tempc[i];    
		if(f.checked==true)     {      
			f.checked=false;     
			} 
		}
		getSelectedCount();
	}
	function getCheckBoxLine(){
		var msg="";
		$("#checkboxTable input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg+=$(this).val()+",";
			}
		})
		return msg;
	}
	function resetmethod(){
		document.getElementById("all_select").value = "";
		this.form1.action = "salesContractSearch.action";
		this.form1.submit();
		return;
	}
	function search(offset){
		var url = "salesContractSearch.action?pager.offset="+offset;
		getCheckBoxInformationnn(url);
	}
	function selectValidationReturnTags(isAlert){
		var tags = getCheckBoxLine();
		var alls = document.getElementById("all_select").value;
		//alert("tags"+tags+"alls"+alls);
		var tagsf = "";
		if(tags !=""){
			var tempOpn = ","+alls+",";
			var ttags = tags.split(",");
			for(var i=0;i<ttags.length;i++){
				var tempTag = ","+ttags[i]+",";
				if(tempOpn.indexOf(tempTag) < 0){
					tagsf += ttags[i]+",";
				}
			}
		}
		tags = tagsf+alls;
		var opn ="";
		if(tags!=""){
			opn= tags.split(",");
			opn = $.grep(opn, function(n) {return $.trim(n).length > 0;});
		}
		/* if(isAlert){
			if(opn==""){
				top.Dialog.alert("请选择计划");
				return;
			}
		} */
		return opn+"";
	}
	function openWin(){
		var tags = selectValidationReturnTags(true);
		if(tags==""){
			top.Dialog.alert("请选择计划");
			return;
		}
		/*
		var diag = new top.Dialog();
		diag.Title = "增加销售合同";
		diag.URL = "addSalesContract_input.action?tags="+tags;
		diag.Height=480;
		diag.Width = 1000;
		diag.MessageTitle="增加销售合同";
		diag.Message="添加销售合同明细";
		diag.CancelEvent = function(){
			document.getElementById("resetflag").value = "add";
			var inputValue = diag.innerFrame.contentWindow.document.getElementById('ccflag').value;
			if(inputValue!=""){
				search(document.getElementById("myOffset").value);
			}
			diag.close();
		};
		diag.show();
		*/
		//先注销，等确定后在写
		if(tags != undefined){
		planManager.validationToAddSalesContract(
				tags,
				function(data){
					if(data!=null && data!=""){
						top.Dialog.alert(data);
						return;
					}else{
						var diag = new top.Dialog();
						diag.Title = "增加销售合同";
						diag.URL = "addSalesContract_input.action?tags="+tags;
						diag.Height=580;
						diag.Width = 1000;
						diag.MessageTitle="增加销售合同";
						diag.Message="添加销售合同明细";
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
					
		});
		}
	}
	function getSelectedCount(){
		var tags = selectValidationReturnTags(false);
		var tagsplit;
		if(tags != ""){
			tagsplit = tags.split(",");
			document.getElementById("selectCount").innerHTML = "已选择："+tagsplit.length+" 条";
		}else{
			document.getElementById("selectCount").innerHTML = "已选择：0 条";
		}
		
	}
	function cf(pid,num){
		var diag = new top.Dialog();
		diag.Title = "拆分计划";
		diag.Width=260;
		diag.Height=300;
		diag.URL = "businessManagement/salesContractMg/cf.jsp?id="+pid+"&num="+num;
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
	$(function(){
		$("#checkboxTable input[type=checkbox]").click(function(){
			if($(this).attr("checked") == false){
				var alls = document.getElementById("all_select").value;
				var opn ="";
				if(alls!=""){
					opn= alls.split(",");
					opn = $.grep(opn, function(n) {return $.trim(n).length > 0;});
			
					var tempAll="";
					var tempOpn = ","+opn+",";
					var tempTag = ","+$(this).val()+",";
					if(tempOpn.indexOf(tempTag) > -1){
						var allsplit = tempOpn.split(",");
						for(var i=0;i<allsplit.length;i++){
							if($(this).val() != allsplit[i]){
								tempAll +=allsplit[i]+",";
							}
						}
						document.getElementById("all_select").value = tempAll;
					}
					getSelectedCount();
				}
			}
		})
	})
	// 页面onload的时候计算当前页被选中项,并在页面表示
	function initPage() {
	//alert("initPage");
		if(document.getElementById("resetflag").value != ""){
			document.getElementById("all_select").value  = "";
			document.getElementById("resetflag").value = "";
		}
	getSelectedCount();
	//var all_selected = document.forms[0].all_select.value;
	var all_selected = document.form1.all_select.value;
	//alert(all_selected);
		if(all_selected != "" && all_selected!= null) {
		var arrall_select = all_selected.split(",");
		//alert("arrall_select.length:"+arrall_select.length);
			if(arrall_select.length > 0) {
			//alert("arrall_select for");
				for(k = 0; k < arrall_select.length; k++) {
				//alert("arrall_select[k]:"+arrall_select[k]);
					for(i = 0; i < document.getElementsByName("checkbox").length; i++) {
					//alert(document.getElementsByName("checkbox")[i].value+"=========="+arrall_select[k]);
						if(document.getElementsByName("checkbox")[i].value == arrall_select[k]) {
						//alert("checked");
						document.getElementsByName("checkbox")[i].checked = true; 
						} 
					}
				} 
			} 
		} 
	}
	//每次翻页的时候调用getCheckBoxInformation()方法,页面加载的时候调用initPage()方法.
	function getCheckBoxInformationnn(url) {
	//alert(document.all.checkbox.length);
	//alert(url);
	var checkboxes = document.getElementsByName("checkbox");
	//checkboxes[1].checked = true;
	//alert(checkboxes.length);
	
	var checkedStr = "";
	var uncheckedStr = "";
	var all_selected = "";
	//alert(this.form1.all_select.value);
	if(this.form1.all_select.value.split(",").length >1 ){
		all_selected = this.form1.all_select.value;
	}
	for(i = 0; i < checkboxes.length; i++) {
	var checkbox = checkboxes[i];
	if(checkbox.checked) {
	//alert("checked");
	checkedStr = checkedStr + "," + checkbox.value;
	}else {
	uncheckedStr = uncheckedStr + "," + checkbox.value;
	}
	}
	url = url+"&now_selected="+checkedStr+"&no_selected="+uncheckedStr+"&all_selected="+all_selected+"&flag=CHECKED";
	//alert(url);
	
	this.form1.action=url;
	this.form1.submit();
	return;
	}

	//<td >${plan.reportComp }
	//<input type="hidden" id="reportCompId_${plan.id }" name="reportCompId_${plan.id }" value="${plan.reportCompId }" />
	//</td>
</script>

<style>
body{
	line-height:150%;
}
</style>
<body onload="initPage()">	
<div class="box2" panelTitle="功能面板 " roller="false">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" id="path" value="<%=path %>">
	<input type="hidden" id="contractMsg" name="contractMsg" value="${contractMsg }">
	<input type="hidden" id="all_select" name="all_select" value="${all }">
	<input type="hidden" name="ccflag" id="ccflag" value="${ccflag }" />
	<input type="hidden" name="add" id="add" value="${add }" />
	<input type="hidden" name="myOffset" id="myOffset" value="${myOffset }" />
	<input type="hidden" name="resetflag" id="resetflag" value="${resetflag }" />
		<table>
			<tr>
				<td>部门:</td>
				<td><select id="dd" name="orgId">
						<option value="0">请选择</option>
						<c:if test="${!empty sessionOrgs}">
						 	<c:forEach items="${sessionOrgs }" var="organization">  
							<option value="${organization.id}" <c:if test="${organization.id == params.id_org}">selected="selected"</c:if> >${organization.name }</option>					 
							</c:forEach>
						</c:if>
					</select></td>
				<td>采购方案名称：</td>
				<td><input type="text" name="procurementName" value="${params.procurementName }" style="width:80px;" /></td>
				<td>计划编号：</td>
				<td><input type="text" name="planNum" value="${params.planNum }" style="width:80px;" /></td>
				<td>搜备注：</td>
				<td><input type="text" name="remark" value="${params.remark }" style="width:80px;" /></td>
				<td>操作人：</td>
				<td><input type="text" name="personName" value="${params.personName }" style="width:80px;" /></td>
				<td>方案号：</td>
				<td><input type="text" name="caseNum" value="${params.caseNum }" style="width:80px;" /></td>
				<td>包号:</td>
				<td>
                  <input type="text" name="packageNum" value="${params.packageNum }" style="width:80px;" />
				</td>
				<c:if test="${my:method(login.id,'salescontractMg',1)}">
				<td><button  onclick='showProgressBar();search(0);'><span class="icon_find">查询</span></button></td>
				</c:if>
			</tr>
			<c:if test="${my:method(login.id,'salescontractMg',0)}">
			<tr>
				<td colspan="7">
				<!-- <input type="button" value="重新选择" onclick='resetmethod()'/> -->
				<button type="button"  onclick='top.Dialog.confirm("您确定要重新选择？",function(){resetmethod()});' ><span class="icon_reload">重新选择</span></button>
				<!-- <input type="button" value="生成销售合同" onclick='gettgvalue()'/> -->
				<button type="button"  onclick='top.Dialog.confirm("您确定要生成销售合同？",function(){openWin()});' ><span class="icon_ok">生成销售合同</span></button>
				</td>
				<td colspan="4"><font color="red"><b><div id="selectCount" ></div></b></font></td>
			</tr>
			</c:if>
		</table>
		</form>

	</div>
<table class="flexiStyle" id="checkboxTable">
<thead>
	<tr>
		<th width="50">
		<a herf="#" onclick="test()">全</a>|
		<a herf="#" onclick="ftest()" >反</a>|
		<a herf="#" onclick="CancelAll()" >不</a>
		</th>
		<th width="20">ID</th>
		<th width="120">计划编号</th>
		<th width="80">物资类别</th>
		<th width="120">物资代码</th>
		<th width="120">采购方案名称</th>
		<th width="80">方案号</th>
		<th width="80">包号</th>
		<th width="120">设备名称及位号</th>
		<th width="120">提报单位</th>
		<th width="120">物资名称</th>
		<th width="120">供应商</th>
		<th width="100">规格型号</th>
		<th width="120">计划状态</th>
		<th width="120">审核状态</th>
		<th width="120">审核意见</th>
		<th width="120">材质</th>
		<th width="120">标准号</th>
		<th width="120">单位</th>
		<th width="120">数量</th>
		<th width="120">物资需求部门</th>
		<th width="100">要求到货日期</th>
		<th width="120">用途</th>
		<th width="120">原厂商</th>
		<th width="120">备注</th>
		<th width="60">操作人</th>
		<th width="50">操作</th>
	</tr>
</thead>
	<tbody>
	<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="plan" varStatus="index">  
		<tr>
			<td><input  id="tg_${plan.id }"  name="checkbox" type="checkbox" value="${plan.id }"  onclick='getSelectedCount()'/></td>
			<td >${index.count }</td>
			
			<td>${plan.planNum }</td>
			<td>${plan.itemType }</td>
			<td>${plan.itemsNum }</td>
			<td>${plan.pc.procurementName }</td>
			<td>${plan.pc.caseNum }</td>
			<td>${plan.pc.packageNum }</td>
			<td>${plan.facilityNameAndNum }</td>
			<td>${plan.reportComp }</td>
			<td>${plan.itemsName }</td>
			<td>${plan.supplier.name }</td>
			<td>${plan.modelType }</td>
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
				<c:if test="${plan.checkStatus==08}">主管己审核</c:if>
				<c:if test="${plan.checkStatus==09}">内控末通过审核</c:if>
				<c:if test="${plan.checkStatus==03 }">内控己审核</c:if>
				<c:if test="${plan.checkStatus==04 }">经理末通过审核</c:if>
				<c:if test="${plan.checkStatus==05}">经理己审核</c:if>
				<c:if test="${plan.checkStatus==06}">董事长末通过审核</c:if>
				<c:if test="${plan.checkStatus==07}">董事长己审核</c:if>
			</td>
			<td>${plan.opinion }</td>
			<td>${plan.material }</td>
			<td>${plan.standardNum }</td>
			<td>${plan.unit }</td>
			<td>${plan.quantity }</td>
			<td>${plan.demandDept }</td>
			<td><fmt:formatDate value="${plan.arrivalDate }" pattern="yyyy-MM-dd"  /></td>
			<td>${plan.useFor }</td>
			<td>${plan.originComp }</td>
			<td>${plan.remark }</td>
			<td>${plan.person.name }</td>
			<td></td>
		</tr>
		</c:forEach>
		</c:if>
		<c:if test="${empty pm.list}">
			<tr><td>无数据</td></tr>
		</c:if>
		</tbody>
</table>
<div style="height:35px;">
	<pg:pager items="${pm.total }" url="salesContractSearch.action" export="currentPageNumber=pageNumber" maxPageItems="50" >

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
