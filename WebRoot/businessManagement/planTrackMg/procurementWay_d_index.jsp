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

<script type='text/javascript' src='<%=path %>/dwr/interface/procurementCheckManager.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
<!--多功能表格start-->
<link rel="stylesheet" type="text/css" href="<%=path %>/js/table/flexigrid/css/flexigrid/flexigrid.css">
<script type="text/javascript" src="<%=path %>/js/table/flexigrid/flexigrid.js"></script>
<!--多功能表格end-->


<script type="text/javascript">

	$(function(){
		var parentMainHeight = window.parent.document.documentElement.clientHeight;
		var flexiGridHeight=parentMainHeight-parentTopHeight-parentBottomHeight-fixHeight-35;
		//alert(parentMainHeight +'---'+flexiGridHeight);
		if(parentMainHeight == 0 || parentMainHeight == undefined){
			$('.flexiStyle').flexigrid({
				height:400,
				resizable: false,
				showToggleBtn: false,
				minColToggle: 1
				});	
		}else{
			$('.flexiStyle').flexigrid({
				height:parentMainHeight*0.6,
				resizable: false,
				showToggleBtn: false,
				minColToggle: 1
				});	
		}
	})
	$(document).ready(function(){
		top.Dialog.close();
		var s = document.getElementById("cflag").value;
		if(s!=""&&s=="success"){
			//action ServletActionContext.getRequest().setAttribute("cflag", "success");
			alert("操作成功");
		}
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
	function gettgvalue(tagss){
		if(tagss == 'add'){
			if(confirm("您确定要将这些计划执行采购方案吗？")){
				this.openWin();
			}
		}else if(tagss == 'mpw'){
			if(confirm("您确定要批量修改这些计划的采购方案吗？")){
				this.openWin3();
			}
		}else if(tagss == 'mpd'){
			if(confirm("您确定要批量修改这些计划的采购签报日期吗？")){
				this.openWin4();
			}
		}
	}

	function search(offset){
		var url = "procurementCheckDSearch.action?pager.offset="+offset;
		getCheckBoxInformationnn(url);
	}
	function resetmethod(){
		document.getElementById("all_select").value = "";
		this.form1.action = "planTrackSearch.action";
		this.form1.submit();
		return;
	}
	function selectValidationReturnTags(isAlert){
		var tags = getCheckBoxLine();
		var alls = document.getElementById("all_select").value;
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
	function backPlans(){
			var tags = selectValidationReturnTags(true);
			if(tags==""){
				top.Dialog.alert("请选择计划");
				return;
			}
				this.form1.action = "modifyPlansByIdsBack.action?tags="+tags;
				this.form1.submit();
				return;
	}
	function openWin(){
		
		var tags = selectValidationReturnTags(true);
		if(tags==""){
			top.Dialog.alert("请选择计划");
			return;
		}
		var diag = new top.Dialog();
		diag.Title = "添加方案";
		diag.URL = "businessManagement/planTrackMg/procurementWay_input.jsp?pids="+tags;
		diag.Height=480;
		diag.MessageTitle="添加采购方案";
		diag.Message="添加采购方案明细";
		diag.CancelEvent = function(){
			document.getElementById("resetflag").value = "add";
			var inputValue = diag.innerFrame.contentWindow.document.getElementById('cflag').value;
			if(inputValue!=""){
				search(document.getElementById("myOffset").value);
			}
			diag.close();
			};
		diag.show();
	}
	function checkPro(){
		var tags = selectValidationReturnTags(true);
		if(tags==""){
			top.Dialog.alert("请选择计划");
			return;
		}
		
		procurementCheckManager.checkPCStatus(tags,'09,01',function (data){
			//alert(data);
			if(data == 'no'){
				alert("请选择正确的审核状态");
				return false;
			}else{
				var diag = new top.Dialog();
				diag.Title = "审核采购方案";
				//diag.URL = "businessManagement/planTrackMg/procurementWay_d_modify.jsp?pids="+tags;
				diag.URL = "initProcurementWay.action?pids="+tags+"&initFlag=innerContral";
				diag.Height=258;
				diag.Width=508;
				diag.CancelEvent = function(){
					document.getElementById("resetflag").value = "add";
					var inputValue = diag.innerFrame.contentWindow.document.getElementById('cflag').value;
					if(inputValue!=""){
						search(document.getElementById("myOffset").value);
					}
					diag.close();
					};
				diag.show();
				}
		});
		//alert(errors+"  dddd");
		
	}
	function openWin4(){
		var tags = selectValidationReturnTags(true);
		if(tags==""){
			top.Dialog.alert("请选择计划");
			return;
		}
		var diag = new top.Dialog();
		diag.Title = "添加方案";
		diag.URL = "businessManagement/planTrackMg/procurementDate_modify.jsp?pids="+tags;
		diag.Height=258;
		diag.Width=508;
		diag.CancelEvent = function(){
			document.getElementById("resetflag").value = "add";
			var inputValue = diag.innerFrame.contentWindow.document.getElementById('cflag').value;
			if(inputValue!=""){
				search(document.getElementById("myOffset").value);
			}
			diag.close();
			};
		diag.show();
	}
	
		//修改窗口
	function openWin2(pcId){
		var diag = new top.Dialog();
		diag.Title = "查看采购方案";
		diag.URL = "getProcurementCheck.action?pc.Id="+pcId+"&pageType='d_index'";
		diag.Height=580;
		diag.Width=1320;
		diag.MessageTitle="查看采购方案";
		diag.Message="查看采购方案";
		diag.CancelEvent = function(){
			document.getElementById("resetflag").value = "add";
			var inputValue = diag.innerFrame.contentWindow.document.getElementById('cflag').value;
			if(inputValue!=""){
				search(document.getElementById("myOffset").value);
			}
			diag.close();
			};
		diag.show();
	}
	function openWin7(){
		var tags = selectValidationReturnTags(true);
		if(tags==""){
			top.Dialog.alert("请选择计划");
			return;
		}
		var tagsplit = tags.split(",");
		var diag = new top.Dialog();
		diag.Title = "添加方案";
		if(tagsplit.length > 1 ){
			diag.URL = "toModifySeekSource.action?modifyFlag=procurementRemark&tags="+tags;
		}else if(tagsplit.length == 1 ){
			diag.URL = "toModifySeekSource.action?modifyFlag=procurementRemark&id="+tagsplit[0];
		}
		diag.Height=258;
		diag.Width=508;
		diag.CancelEvent = function(){
			document.getElementById("resetflag").value = "add";
			var inputValue = diag.innerFrame.contentWindow.document.getElementById('cflag').value;
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
	function getSelectedCount(){
		//alert(status); //'undefined'
		var tags = selectValidationReturnTags(false);
		var tagsplit;
		if(tags != ""){
			tagsplit = tags.split(",");
			document.getElementById("selectCount").innerHTML = "已选择："+tagsplit.length+" 条";
		}else{
			document.getElementById("selectCount").innerHTML = "已选择：0 条";

		}
	}
		
	// 页面onload的时候计算当前页被选中项,并在页面表示
	function initPage() {
		if(document.getElementById("resetflag").value != ""){
			document.getElementById("all_select").value  = "";
			document.getElementById("resetflag").value = "";
		}
	getSelectedCount();
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
	//alert(url); showProgressBar();
	
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
<body onload="initPage()" >	
<div class="box2" panelTitle="功能面板 " roller="false">
	<form id="form1" name="form1" method="post">
	<input type="hidden" id="path" value="<%=path %>">
	<input type="hidden" id="all_select" name="all_select" value="${all }">
	<input type="hidden" name="cflag" id="cflag" value="${cflag }" />
	<input type="hidden" name="myOffset" id="myOffset" value="${myOffset }" />
	<input type="hidden" name="resetflag" id="resetflag" value="${resetflag }" />
		<table>
			<tr>
				<td>审核编号：</td>
				<td><input type="text" name="pc.checkNum" value="${pc.checkNum }" style="width:80px;" /></td>
				
				<td>提交日期 从:</td>
				<td>
                  	<input  style="width:100px;" id="sDate" name="pc.beginDate"  value="<fmt:formatDate value="${pc.beginDate }" pattern="yyyy-MM-dd" />" class="date"/>
				</td>
				<td>到：</td>
				<td>
                    <input  style="width:100px;" id="eDate" name="pc.endDate"  value="<fmt:formatDate value="${pc.endDate }" pattern="yyyy-MM-dd" />" class="date"/>
				</td>
				<td>采购方案状态:</td>
				<td><select id="checkStatus" name="pc.checkStatus">
						<option value="0">请选择</option>
						<option value="01" <c:if test="${pc.checkStatus == '01'}">selected="selected"</c:if> >主管未审核</option>	
						<option value="02" <c:if test="${pc.checkStatus == '02'}">selected="selected"</c:if> >主管未通过审核</option>	
						<option value="08" <c:if test="${pc.checkStatus == '08'}">selected="selected"</c:if> >主管己审核</option>		
						<option value="09" <c:if test="${pc.checkStatus == '09'}">selected="selected"</c:if> >内控未通过审核</option>	
						<option value="03" <c:if test="${pc.checkStatus == '03'}">selected="selected"</c:if> >内控己审核</option>	
						<option value="04" <c:if test="${pc.checkStatus == '04'}">selected="selected"</c:if> >经理未通过审核</option>		
						<option value="05" <c:if test="${pc.checkStatus == '05'}">selected="selected"</c:if> >经理己审核</option>	
						<option value="06" <c:if test="${pc.checkStatus == '06'}">selected="selected"</c:if> >董事长未通过审核</option>	
						<option value="07" <c:if test="${pc.checkStatus == '07'}">selected="selected"</c:if> >董事长己审核</option>			
								 
					</select>
				</td>
				<td>操作人：</td>
				<td><input type="text" name="personName" value="${params.personName }" style="width:80px;"/></td>
				<td>采购方案名称：</td>
				<td><input type="text" name="pc.procurementName" value="${pc.procurementName }" style="width:80px;"/></td>
				</tr>
				<tr>
				<td>方案号：</td>
				<td><input type="text" name="pc.caseNum" value="${pc.caseNum }" style="width:80px;" /></td>
				
				<td>包号:</td>
				<td>
                  <input type="text" name="pc.packageNum" value="${pc.packageNum }" style="width:80px;" />
				</td>
				<c:if test="${my:method(login.id,'procurementDWayMgr',1)}">
				<td><button  onclick='search(0);'><span class="icon_find">查询</span></button></td>
				</c:if>
				<td colspan="8">
				<c:if test="${my:method(login.id,'procurementDWayMgr',2)}">
				<button type="button"  onclick='top.Dialog.confirm("您确定批量审核采购方案？",function(){checkPro()});' ><span class="icon_page">批量审核采购方案</span></button>
				</c:if>
				</td>
				<td colspan="3"><font color="red"><b><div id="selectCount" ></div></b></font></td>
			</tr>
			
		</table>
		</form>

	</div>
<div id="scrollContent" >
<table class="flexiStyle" id="checkboxTable">
<thead>
	<tr>
		<th width="50">
		<a herf="#" onclick="test()">全</a>|
		<a herf="#" onclick="ftest()" >反</a>|
		<a herf="#" onclick="CancelAll()" >不</a>
		</th>
		<th width="30">序号</th>
		<th width="70">审核编号</th>
		<th width="100">采购方案名称</th>
		<th width="500">供应商</th>
		<th width="90">方案号</th>
		<th width="90">包号</th>
		<th width="100">状态</th>
		<th width="140">审核意见</th>
		<th width="60">提交日期</th>
		<th width="80">操作员</th>
		<th width="80">操作</th>
	</tr>
</thead>
	<tbody>	
	<c:if test="${!empty pm.list}">
        <c:forEach items="${pm.list }" var="pch" varStatus="status">  
			<tr >
			<td><input  id="tg"  name="checkbox" type="checkbox" value="${pch.id }" onclick='getSelectedCount()'/></td>
			<td >${status.index+1 }</td>
			<td >${pch.checkNum }</td>
			<td >${pch.procurementName }</td>
			<td >${pch.supplierNames }</td>
			<td >${pch.caseNum }</td>
			<td >${pch.packageNum }</td>
			<td>
			<c:if test="${pch.checkStatus==01 }">主管未审核</c:if>
			<c:if test="${pch.checkStatus==02 }">主管未通过审核</c:if>
			<c:if test="${pch.checkStatus==08 }">主管己审核</c:if>
			<c:if test="${pch.checkStatus==09 }">内控未通过审核</c:if>
			<c:if test="${pch.checkStatus==03 }">内控己审核</c:if>
			<c:if test="${pch.checkStatus==04 }">经理未通过审核</c:if>
			<c:if test="${pch.checkStatus==05 }">经理己审核</c:if>
			<c:if test="${pch.checkStatus==06 }">董事长未通过审核</c:if>
			<c:if test="${pch.checkStatus==07 }">董事长己审核</c:if>
			</td>
			<td >${pch.opinion }</td>
			<td ><fmt:formatDate value="${pch.submitDate }" pattern="yyyy-MM-dd"/></td>
			<td >${pch.person.name }</td>
			<td >
			<c:if test="${my:method(login.id,'procurementDWayMgr',1)}">
				<span class="img_edit hand" title="修改" onclick="openWin2(${pch.id})"></span>
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
</div>
<div style="height:35px;">
	<pg:pager items="${pm.total }" url="procurementCheckDSearch.action" export="currentPageNumber=pageNumber" maxPageItems="50" >

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
