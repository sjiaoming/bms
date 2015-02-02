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

<script src="<%=path %>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path %>/js/form/validationEngine.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path %>/js/Calendar3.js"></script>
<script src="<%=path %>/js/abc.js" type="text/javascript"></script>
<script language="javascript">
var flag = false;
$(function(){
	$("#copyBtn").live("click",function(){
		if(validationAdd()){
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
		}
	})
})
function getContractNum(feil,num){
	var total = Number(num);
	var ctemp=0;
	var temp = document.getElementsByName("arrivalNum");
	if(temp.length>0){
		for(var i =0;i<temp.length;i++){
			ctemp += Number(temp[i].value);
		}
	}
	feil.value = accSubtr(total,ctemp) >0 ?accSubtr(total,ctemp) :0;
	var   txtRange   =   document.selection.createRange(); 
    txtRange.moveStart(   "character",   feil.value.length); 
    txtRange.moveEnd(   "character",   0   ); 
    txtRange.select(); 
	
}
function validationNum(){
	var total = Number(${param.arrivalNum });
	var ctemp=0;
	var temp = document.getElementsByName("arrivalNum");
	if(temp.length<2){
		top.Dialog.alert("拆分不能为一条");
		return false;
	}
	if(temp.length>0){
		for(var i =0;i<temp.length;i++){
			ctemp = Number(ctemp).add(Number(temp[i].value));
		}
	}
	if(accSubtr(total,ctemp)<0 || accSubtr(total,ctemp)>0){
		top.Dialog.alert("请仔细核对拆分数量已保证与总数量相等");
		return false;
	}else{
		return true;
	}
	
}
function validationAdd(){
	var access=true;
	var total = Number(${param.arrivalNum });
	var ctemp=0;
	var temp = document.getElementsByName("arrivalNum");
	if(temp.length>0){
		for(var i =0;i<temp.length;i++){
			ctemp += Number(temp[i].value);
		}
	}
	if(accSubtr(total,ctemp)<=0){
		top.Dialog.alert("拆分数量总和已等于总数量，无法继续拆分");
		access =  false;
	}
	return access;
}
function ok(){
	var access=false;
	access=$('#form1').validationEngine({returnIsValid:true});
	access = validationNum();
		if(access){
			this.form1.action = "cfArrivalItems.action";
			this.form1.submit();
			//top.Dialog.close();
			return;
		}
	}

</script>

<body>
<form action="cfArrivalItems.action" id="form1" name="form1">
	<input type="hidden" name="aid" value="${param.aid }"/>
	<input type="hidden" name="scid" value="${param.scid }"/>
	<input type="hidden" name="bid" value="${param.id }"/>
	<input type="hidden" name="cflag" value="${param.cflag }"/>
	
	<div class="box2" panelWidth="250" panelTitle="拆分" showStatus="false" roller="true">
	<table id="tab1" class="tableStyle" >
		<tr>
			<td colspan="2"><b>总数量：${param.arrivalNum }</b></td>
		</tr>
		<tr >
			<td><input type="text" name="arrivalNum"  onclick="getContractNum(this,${param.arrivalNum })"/></td>
			<td><input type="button" value="追加" id="copyBtn"  /></td>
		</tr>
		</table>
	<table class="tableStyle" transMode="true">
		<tr>
			<td colspan="4">
				<input type="button"  onclick="ok()" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
	
	</div>

</form>
</body>