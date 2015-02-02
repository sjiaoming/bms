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


<script type="text/javascript">

	$(function(){
		var parentMainHeight = window.parent.document.documentElement.clientHeight;
		var flexiGridHeight=parentMainHeight-parentTopHeight-parentBottomHeight-fixHeight-45;
		//alert(parentMainHeight +'---'+flexiGridHeight);
		$("div#scrollContent").height(flexiGridHeight);
	});
	
	$(document).ready(function() {
		if(this.form1.all_select.value.split(",").length >1 ){
			/* temp = this.form1.all_select.value.split(",");
			temp = $.grep(temp, function(n) {return $.trim(n).length > 0;}); */
			
			var opn = this.form1.sopn.value.split(",");
			opn = $.grep(opn, function(n) {return $.trim(n).length > 0;});
			this.form1.tt.value = opn;
		}
    $("#Tags").tagEditor(
    {
       // items: temp,
        confirmRemoval: false,
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
	
	function addTag(feild,a,b){
		/* if(a!=b){
			top.Dialog.alert("请选择自己的计划");
			return;
		} */
		setfocus();
		var nowspid = feild.cells[0].childNodes[0].value;
		//alert(nowspid);
		var ids = feild.cells[0].childNodes[2].value;
		//alert(ids);
		//alert(ids+"===="+nowspid);
		if(!feild.getElementsByTagName("input")[0].checked){
			feild.getElementsByTagName("input")[0].checked = true;
		}else{
			feild.getElementsByTagName("input")[0].checked = false;
		} 
		if(feild.getElementsByTagName("input")[0].checked){
			//alert("if");
		 	$("#Tags").tagEditorAddTag(ids+'');
		 	
		 	var spidtemp = document.getElementById("spid").value;
		 	var sopntemp = document.getElementById("sopn").value;
			//alert(spidtemp+"  if  "+sopntemp);
		 	if(spidtemp!=null && spidtemp !=""){
		 		document.getElementById("spid").value = spidtemp +','+ nowspid;
		 		document.getElementById("sopn").value = sopntemp+','+ids;
		 	}else{
		 		document.getElementById("spid").value = nowspid;
		 		document.getElementById("sopn").value = ids;
		 	}
		 	//alert("if"+document.getElementById("sopn").value+'--'+document.getElementById("spid").value);
		}else{
			//alert("else");
			var tempTags = $("#Tags").tagEditorGetTags();
			var tagsplit = tempTags.split(",");
			var spidsplit = (document.getElementById("spid").value).split(",");
			var sopnsplit = (document.getElementById("sopn").value).split(",");
			document.getElementById("spid").value = "";
			document.getElementById("sopn").value = "";
			//alert(spidsplit+"  else  "+sopnsplit);
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

	
	function getCheckBoxLine(){
	var msg="";
	$("#checkboxTable input[type=checkbox]").each(function(){
		if($(this).attr("checked")){
			msg=msg+","+$(this).val()
		}
	})
	if(msg==""){
		msg="无选择"
	}
	top.Dialog.alert(msg);
	}
	
	function setblur()
	{
		//失去焦点
			this.form1.tt.blur();
	}
	
	function setfocus(){
		//获得焦点
		this.form1.tt.focus();
	}

	function search(offset){
		var pcid =  document.getElementById("pcid").value;
		var url = "planTrackSearch.action?pager.offset="+offset+"&method=planAdd&pc.id="+pcid;
		getCheckBoxInformationnn(url);
	}
	function resetmethod(){
		document.getElementById("spid").value = "";
 		document.getElementById("sopn").value = "";
		this.form1.action = "planTrackSearch.action?method=planAdd";
		this.form1.submit();
		return;
	}
	function ok(){
		setfocus();
		setblur();
		if(confirm("您确定要追加这些计划？")){
			var tags = document.getElementById("spid").value;
			var pcid =  document.getElementById("pcid").value;
			//alert(tags+"   sss   "+pcid);
			if(tags==""){
				top.Dialog.alert("请选择计划");
				return;
			}
			var tg = tags.split(",");
			this.form1.action = "paAddPlan.action?tags="+tags+"&pc.id="+pcid;
			this.form1.submit();
			return;
		}
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
<body onload="initPage()">	
<div class="box2" panelTitle="功能面板 " roller="false">
	<form id="form1" name="form1" action="#"  method="post">
	<input type="hidden" id="path" value="<%=path %>">

	<input type="hidden" id="all_select" name="all_select" value="<%=request.getAttribute("all") %>">
	<input type="hidden" id="spid" name="spid" value="${spid }">
	<input type="hidden" id="sopn" name="sopn" value="${sopn }">
	
	<input type="hidden" id="pcid" name="pc.id" value="${pc.id }" />

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
				<td>计划编号：</td>
				<td><input type="text" name="planNum" value="${params.planNum }"/></td>
				<td><button  onclick="search(0);"><span class="icon_find">查询</span></button></td>
			</tr>
			<tr>
				<td colspan="7">
				<input type="button" value="重置" onclick='resetmethod()'/>
				<input type="button" value="保存" onclick='ok()'/>
				<input id="Tags"  name="tt" type="text"  value="" style="width:0px;"/>
				</td>
			</tr>
			
		</table>
		</form>

	</div>
<div id="scrollContent" panelWidth="988">
<table class="tableStyle" id="checkboxTable" useMultColor="false">
	<tr>
		<th width="30"></th>
		
		<th width="80">计划编号</th>
		<th width="80">物资类别</th>
		<th width="80">物资代码</th>
		<th width="100">物资名称</th>
		<th width="80">规格型号</th>
		<th width="80">计划状态</th>
		<th width="80">审核状态</th>
		<th width="80">单位</th>
		<th width="80">数量</th>
		<th width="80">预算单价(元)</th>
		<th width="80">预算金额(元)</th>
	</tr>
	<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="plan" varStatus="index">  
		<!-- <tr  ${(empty plan.procurementWay)?"onclick=addTag(this) ":"" } >
			<td><c:if test="${empty plan.procurementWay}"><input  id="tg"  name="checkbox" type="checkbox" value="${plan.id }" /></c:if></td> -->
			<tr  onclick="addTag(this,${plan.person.id },${person.id })" >
			
			<td><input  id="tg"  name="checkbox" type="checkbox" value="${plan.id }" />
			<input  id="opn_${plan.id }"  name="opn" type="hidden" value="${plan.itemsNum }"/></td>
			<td>${plan.planNum }</td>
			<td>${plan.itemType }</td>
			<td style="word-wrap: break-word;word-break:break-all;">${plan.itemsNum }</td>
			<td style="word-wrap: break-word;word-break:break-all;">${plan.itemsName }</td>
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
				<c:if test="${plan.checkStatus==03 }">经理末审核</c:if>
				<c:if test="${plan.checkStatus==04 }">经理末通过审核</c:if>
				<c:if test="${plan.checkStatus==05}">经理已审核</c:if>
			</td>
			<td>${plan.unit }</td>
			<td>${plan.quantity }</td>
			<td><fmt:formatNumber value="${plan.budgetPrice }" type="currency"/></td>
			<td><fmt:formatNumber value="${plan.budgetMoney }" type="currency"/></td>
		</tr>
		</c:forEach>
		</c:if>
		<c:if test="${empty pm.list}">
			<tr><td>无数据</td></tr>
		</c:if>
</table>
</div>
<div style="height:35px;">
	<pg:pager items="${pm.total }" url="planTrackSearch.action" export="currentPageNumber=pageNumber" maxPageItems="50" >

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
