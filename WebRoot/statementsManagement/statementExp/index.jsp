<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<!--框架必需start-->
	<script type="text/javascript" src="../../js/jquery-1.4.js"></script>
	<script type="text/javascript" src="../../js/framework.js"></script>
	<link href="../../css/import_basic.css" rel="stylesheet"
		type="text/css" />
	<link rel="stylesheet" type="text/css" id="skin" prePath="../../" />
	<!--框架必需end-->

	<!--截取文字start-->
	<script type="text/javascript" src="../../js/text/text-overflow.js"></script>
	<!--截取文字end-->
	<script type="text/javascript" src="../../js/form/lister.js"></script>
	<script language="javascript">
	$(document).ready(function(){
		$("#listA").lister("listB");		
	});
	
	function getSelValue(){
		var msg="";
		msg=$('#listA').lister('listB').getList();
		if(msg==""){
			msg="无选择";
			top.Dialog.alert(msg);
		}else{
		//top.Dialog.alert(msg);
		 var tmp = document.createElement("form"); 
		 alert(msg.length);
		 alert(msg);
	     var action = "/shtz/StatementExp?msg="+msg; 
	     tmp.action = action; 
	     tmp.method = "post"; 
	     document.body.appendChild(tmp); 
	     tmp.submit(); 
	     return tmp; 
		
		}
		//top.Dialog.alert(msg);
	}
	function selectAll(){
		$('#listA').lister('listB').sendAll(true);
	}
	function cancelAll(){
		$('#listA').lister('listB').sendAll(false);
	}
	</script>
	<style>
ul.lister {
	height: 250px;
}

div.listerLinksLeft,div.listerLinksRight {
	text-align: left;
	width: 240px;
	padding: 5px;
}

.listBtn {
	padding: 100px 0 0 0;
	float: left;
}
</style>

	<body>
	<form action="" name="form1">
		<div >
			<ul id="listA">
				<li el="0">
					包次
				</li>
				<li el="3">
					类别1
				</li>
				<li el="4">
					类别2
				</li>
				<li el="6">
					物资名称
				</li>
				<li el="14">
					数量
				</li>
				<li el="16">
					到货地点
				</li>
				<li el="17">
					预算金额（万元）
				</li>
				<li el="21">
					提报单位
				</li>
				<li el="25">
					技术标书编号
				</li>
				<li el="36">
					中标金额
				</li>
				<li el="38">
					合同金额
				</li>
				<li el="45">
					催货情况
				</li>
			</ul>
			<div class="listBtn">
				<input type="button" value="全选>>" onclick="selectAll()" />
				<br />
				<br />
				<input type="button" value="<<还原" onclick="cancelAll()" />
			</div>
			<ul id="listB"></ul>
		</div>
		<div class="clear"></div>
		
		<button onclick="getSelValue()" ><span class="icon_xls">导出</span></button>
		</form>
	</body>