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
<script type="text/javascript" src="<%=path %>/js/framework.js" ></script>
<link href="<%=path %>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=path %>/"/>
</script>
<!--框架必需end-->
<!--截取文字start-->
<script type="text/javascript" src="<%=path %>/js/text/text-overflow.js"></script>
<!--截取文字end-->
<script type="text/javascript" src="<%=path %>/js/form/multiselect.js"></script>
<!--多功能表格start-->
<link rel="stylesheet" type="text/css" href="<%=path %>/js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="<%=path %>/js/table/flexigrid/flexigrid.js"></script>
<!--多功能表格end-->
<script type="text/javascript" src="<%=path %>/js/form/tageditor.js"></script>
<script type="text/javascript">
$(function(){
	top.Dialog.close();
	var parentMainHeight = window.parent.document.documentElement.clientHeight;
	var flexiGridHeight=parentMainHeight-parentTopHeight-parentBottomHeight-fixHeight-35;
	//alert(parentMainHeight +'---'+flexiGridHeight);
	$('.flexiStyle').flexigrid({
		height:flexiGridHeight,
		resizable: false,
		showToggleBtn: false
		});	
})

$(document).ready(function() {
	if(this.form1.all_select.value.split(",").length >1 ){
		var opn = this.form1.sopn.value.split(",");
		opn = $.grep(opn, function(n) {return $.trim(n).length > 0;});
		this.form1.tt.value = opn;
	}
$("#Tags").tagEditor(
{
   // items: temp,
    confirmRemoval: true,
    completeOnBlur: true
});

$("#getTagsButton").click(function() {
   alert($("#Tags").tagEditorGetTags());
    
});
$("#addTagButton").click(function() {
    $("#Tags").tagEditorAddTag("12121");
	
});
$("#resetTagsButton").click(function() {
    $("#Tags").tagEditorResetTags();
});
});

function addTag(id){
	setfocus();
	var ids = document.getElementById("opn_"+id).value;
	var nowspid = id;
	if(document.getElementById("tg_"+id).checked){
		
	 	$("#Tags").tagEditorAddTag(ids+'');
	 	
	 	var spidtemp = document.getElementById("spid").value;
	 	var sopntemp = document.getElementById("sopn").value;
	 	if(spidtemp!=null && spidtemp !=""){
	 		document.getElementById("spid").value = spidtemp +','+ nowspid;
	 		document.getElementById("sopn").value = sopntemp+','+ids;
	 	}else{
	 		document.getElementById("spid").value = nowspid;
	 		document.getElementById("sopn").value = ids;
	 	}
	}else{
		var tempTags = document.getElementById("sopn").value;
		var tagsplit = tempTags.split(",");
		var spidsplit = (document.getElementById("spid").value).split(",");
		var sopnsplit = (document.getElementById("sopn").value).split(",");
		document.getElementById("spid").value = "";
		document.getElementById("sopn").value = "";
		$("#Tags").tagEditorResetTags();
		if(tagsplit.length >1){
			for(var i=0;i<tagsplit.length;i++){
				if(tagsplit[i]!=ids){
					$("#Tags").tagEditorAddTag(tagsplit[i]+'');
					var spidtemp = document.getElementById("spid").value ;
					var sopntemp = document.getElementById("sopn").value ;
				 	if(spidtemp!=null && spidtemp !=""){
				 		document.getElementById("spid").value = spidsplit[i] +','+ spidtemp;
				 		document.getElementById("sopn").value = tagsplit[i] +','+ sopntemp;
				 	}else{
				 		document.getElementById("spid").value = spidsplit[i];
				 		document.getElementById("sopn").value = tagsplit[i];
				 	}
				}
					
			}
		}
		
	 }
}


	function search(){
		var url = "plan.action?pager.offset=0";
		getCheckBoxInformationnn(url);
	}
	function resetmethod(){
		document.getElementById("spid").value = "";
 		document.getElementById("sopn").value = "";
		this.form1.action = "plan.action";
		this.form1.submit();
		return;
	}
	function del(planid){
		
		this.form1.action = "del_plan.action?id="+planid;
		this.form1.submit();
		return;
		//window.location.reload();
	}
	function openWin(){
		//alert('--')
		var diag = new top.Dialog();
		diag.Title = "添加计划信息";
		diag.URL = "addOrModify.action";
		diag.Height=480;
		diag.MessageTitle="添加计划信息";
		diag.Message="添加计划信息明细";
		diag.CancelEvent = function(){
			diag.innerFrame.contentWindow.location.reload();
			diag.close();
			};
		//diag.CancelEvent = function(){
		//window.location.reload();
		//window.location.href = "person.action";
		//diag.close();
		//};
		diag.show();
	}
	function openWin1(){
		//alert('--')
		var diag = new top.Dialog();
		diag.Title = "添加采购方案";
		diag.URL = "addOrModify.action";
		diag.Height=480;
		diag.MessageTitle="添加采购方案";
		diag.Message="添加采购方案明细";
		diag.CancelEvent = function(){
			diag.innerFrame.contentWindow.location.reload();
			diag.close();
			};
		//diag.CancelEvent = function(){
		//window.location.reload();
		//window.location.href = "person.action";
		//diag.close();
		//};
		diag.show();
	}
	//修改窗口
	function openWin2(planId){
		var diag = new top.Dialog();
		diag.Title = "修改管理";
		diag.URL = "modifyPlanMg.action?planId="+planId;
		diag.Height=480;
		diag.MessageTitle="修改管理";
		diag.Message="修改管理明细";
		diag.CancelEvent = function(){
			diag.innerFrame.contentWindow.location.reload();
			diag.close();
			};
		//diag.CancelEvent = function(){
		//window.location.reload();
		//window.location.href = "person.action";
		//diag.close();
		//};
		diag.show();
	}
	//导入窗口
	function openWin3(){
		
		var diag = new top.Dialog();
		diag.Title = "导入窗口";
		diag.CancelEvent = function(){
			diag.close();
			};
		diag.URL = "businessManagement/planMg/import_input.jsp";
		diag.Height=480;
		diag.Width=930;
		diag.MessageTitle="导入excel文件";
		diag.Message="选择您的导入文件";
	
		diag.show();
	}
	
	//导出
	function exportexcel(){
	     this.form1.action = "export.action";
		this.form1.submit();
		return;
	}
	function openWin4(){
		var tags = document.getElementById("spid").value;
		if(tags==""){
			top.Dialog.alert("请选择计划");
			return;
		}
		var diag = new top.Dialog();
		diag.Title = "批量更新";
		diag.URL = "businessManagement/planMg/planOrg_modify.jsp?pids="+tags;
		diag.Height=258;
		diag.Width=368;
		diag.show();
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
	function setblur()
	{
		//失去焦点
			this.form1.tt.blur();
	}

	function gettgvalue(){
		var tags = document.getElementById("spid").value;
		if(tags==null || tags.trim() == ""){
			top.Dialog.alert("请选择计划");
			return;
		}
		//if(confirm("您确定要删除这些计划吗？")){
			this.form1.action = "deletePlansByIds.action?tags="+tags;
			this.form1.submit();
			return;
		//}
	}
	
	function setfocus(){
		//获得焦点
		this.form1.tt.focus();
	}
	

	// 页面onload的时候计算当前页被选中项,并在页面表示
	function initPage() {
	//alert("initPage");
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
</script>
<style>
body{
	line-height:150%;
}
</style>
<body onload="initPage()" id="bodyone">
<div class="box2" panelTitle="功能面板" roller="false">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" id="path" value="<%=path %>">
	<input type="hidden" id="b" name="b" value="${param.b }">
	<input type="hidden" id="all_select" name="all_select" value="<%=request.getAttribute("all") %>">
	<input type="hidden" id="spid" name="spid" value="${spid }">
	<input type="hidden" id="sopn" name="sopn" value="${sopn }">
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
				<td>购物车编号：</td>
				<td><input type="text" name="oldPlanNum" value="${params.oldPlanNum }"/></td>
				<td>提报单位：</td>
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
				<td>接收日期 从:</td>
				<td>
                        <input  id="sDate" name="sDate"  value="${sDate }" class="date"/>
				</td>
				<td>到：</td>
				<td>
                         <input  id="eDate" name="eDate"  value="${eDate }" class="date"/>
				</td>
				<c:if test="${my:method(login.id,'planmgr',1)}">
				<td><button  onclick="search();"><span class="icon_find">查询</span></button></td>
				</c:if>
				<!-- 
				<c:if test="${my:method(login.id,'planmgr',0)}">
					<button  onclick="openWin();"><span class="icon_page">新建</span></button>
				</c:if>
				 -->
				 
				<c:if test="${my:method(login.id,'planmgr',0)}">
					<c:if test="${param.b != 'y' }">
					<td><input type="button" value="导入" onclick='openWin3()'/></td>
					</c:if>
					<c:if test="${param.b == 'y' }">
						<td><input  type="button" value="导出" onclick='exportexcel()'/></td>
					</c:if>
				</c:if>
			</tr>
		</table>
		<table>
			<tr>
				<td colspan="10">
				<c:if test="${my:method(login.id,'planmgr',0)}">
				<!-- <input type="button" value="重新选择" onclick='resetmethod()'/> -->
				<button type="button"  onclick='top.Dialog.confirm("您确定要重新选择？",function(){resetmethod()});' ><span class="icon_reload">重新选择</span></button>
				<c:if test="${my:method(login.id,'planmgr',3)}">
				<!-- <input type="button" value="删除" onclick='gettgvalue()'/> -->
				<button type="button"  onclick='top.Dialog.confirm("您确定要删除？",function(){gettgvalue()});' ><span class="icon_delete">删除</span></button>
				</c:if>
				</c:if>
				<c:if test="${my:method(login.id,'planmgr',2)}">
				<!-- <input type="button" value="批量修改部门" onclick='openWin4()'/> -->
				<button type="button"  onclick='top.Dialog.confirm("您确定要批量修改部门？",function(){openWin4()});' ><span class="icon_pencil">批量修改部门</span></button>
				</c:if>
				<input id="Tags"  name="tt" type="hidden"  value="" style="width:0px;"/>
			</td>
			</tr>
		</table>
		</form>
	</div>
<div id="scrollContent">
<table class="flexiStyle" >
<thead>
		<tr>
				<th width="20"></th>
				<th width="20">ID</th>
				<th width="60">接收日期</th>
				<th width="120">购物车编号</th>
				<th width="20">行号</th>
				<th width="120">物料编码</th>
				<th width="120">物料描述</th>
				<th width="120">单价</th>
				<th width="120">数量</th>
				<th width="40">单位</th>
				<th width="120">预算金额</th>
				<th width="100">部门</th>
				<th width="60">负责人</th>
				<th width="60">交货日期</th>
				<th width="120">提报单位</th>
				<th width="120">到货地址</th>
				<th width="60">操作</th>
				<th width="20"></th>
			</tr>
</thead>
	<tbody>	
		<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="plan" varStatus="index">
		<tr >
			<td ><input  id="tg_${plan.id }"  name="checkbox" type="checkbox" value="${plan.id }"  onclick='addTag(${plan.id })'/>
			<input  id="opn_${plan.id }"  name="opn" type="hidden" value="${plan.oldPlanNum } - ${plan.planNum }" /></td>
			<td>${index.count }</td>
			<td><fmt:formatDate value="${plan.planReceiptDate }" pattern="yyyy-MM-dd" /></td>
			<td>${plan.oldPlanNum }</td>
			<td>${plan.planNum }</td>
			<td>${plan.itemsNum }</td>
			<td>${plan.itemsName }</td>
			<td><fmt:formatNumber value="${plan.planPrice }" type="currency"/></td>
			<td>${plan.num }</td>
			<td>${plan.unit }</td>
			<td><fmt:formatNumber value="${plan.budget }" type="currency"/></td>
			<td>${plan.org.name }</td>
			<td>${plan.person.name }</td>
			<td><fmt:formatDate value="${plan.arrivalDate }" pattern="yyyy-MM-dd"  /></td>
			<td>${plan.reportComp }</td>
			<td>${plan.arrivalAddress }</td>
			<td>
			<c:if test="${my:method(login.id,'planmgr',2)}">
				<span class="img_edit hand" title="修改"  onclick="openWin2(${plan.id })"></span>
			</c:if>
			<%-- <c:if test="${my:method(login.id,'planmgr',3)}">
				<span class="img_delete hand" title="删除" onclick='top.Dialog.confirm("您确定要删除此计划吗？",function(){del(${plan.id})});'></span>
			</c:if> --%>
			</td>
		</tr>
		</c:forEach>
		</c:if>
		<c:if test="${empty pm.list}">
			<tr><td>无数据</td></tr>
		</c:if>
		</tbody>
	</table>
</div>

<div style="height:35px;">
	<pg:pager items="${pm.total }" url="plan.action" export="currentPageNumber=pageNumber" maxPageItems="50">
	<div class="float_right padding5 paging">
		<div class="float_left padding_top4">
		<c:choose>
			<c:when test="${ currentPageNumber == 1}">
				<span class="paging_disabled"><a href="javascript:;">首页</a></span>
			</c:when>
			<c:otherwise>
				<span ><pg:first><a href="#" onclick="getCheckBoxInformationnn('${pageUrl}')">首页</a></pg:first></span>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ currentPageNumber > 1}">
				<span ><pg:prev><a href="#" onclick="getCheckBoxInformationnn('${pageUrl}')">上一页</a></pg:prev></span>
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
					<span><a href="#" onclick="getCheckBoxInformationnn('${pageUrl}')">${pageNumber }</a></span>	
					</c:otherwise>
				</c:choose>
			</pg:pages>
			
		<span><pg:next><a href="#" onclick="getCheckBoxInformationnn('${pageUrl}')">下页</a></pg:next></span>
		<span><pg:last><a href="#" onclick="getCheckBoxInformationnn('${pageUrl}')">尾页</a></pg:last></span>
		<div class="clear"></div>
	</div>
	</pg:pager>
	<div class="clear"></div>
</div>
		
</body>
