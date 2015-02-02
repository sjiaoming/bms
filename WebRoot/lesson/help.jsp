<%@ page language="java" import="java.util.*" pageEncoding="utf-8"  isELIgnored="false"%>
<%@include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="<%=path %>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path %>/js/framework.js"></script>
<link href="<%=path %>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=path %>/"/>
<!--框架必需end-->
<!--平滑锚点start-->
<script type='text/javascript' src="<%=path %>/js/scroll/scrollTo.js"></script>
<script type='text/javascript' src="<%=path %>/js/scroll/localscroll.js"></script>
<script type="text/javascript" src="<%=path %>/js/pic/bubbleup.js"></script>
<script type="text/javascript" src="<%=path %>/js/attention/floatPanel.js"></script>
<script>
jQuery(function( $ ){
	$.localScroll.defaults.axis = 'xy';
	$.localScroll.hash({
		target: '#content',
		queue:true,
		duration:2500
	});
	$.localScroll({
		target: '#content', 
		queue:true,
		duration:1000,
		hash:true,
		onBefore:function( e, anchor, $target ){
		},
		onAfter:function( anchor, settings ){
		}
	});
});

$(document).ready(function(){
	$('.boxgrid.captionfull').each(function(){
		$(".cover", this).css({
			top:"230px"
		})
	})
	$('.boxgrid.captionfull').hover(function(){
		$(".cover", this).stop().animate({top:'200px'},{queue:false,duration:160});
	}, function() {
		$(".cover", this).stop().animate({top:'230px'},{queue:false,duration:160});
	});
})
$(document).ready(function(){
	$('.boxgrid1.caption').each(function(){
		$(".cover", this).css({
			top:"125px"
		})
	})
	$('.boxgrid1.caption').hover(function(){
		$(".cover", this).stop().animate({top:'80px'},{queue:false,duration:160});
	}, function() {
		$(".cover", this).stop().animate({top:'125px'},{queue:false,duration:160});
	});
	
	$("#floatPanel").floatPanel({
		width:300,
		direction:"br",
		init:"show",
		iframe:"loadAllMessage.action",
		beforeClickText:"message",
		afterClickText:"message"
	})	
})
$(function(){
    $(".imgBubble img").bubbleup({tooltip: true, scale:320});
});
</script>
<style>
#content{
	overflow:hidden;
	width:630px;
	background-color:white;
	position:relative;
	height:400px;
	float:left;
	border:solid 1px #ccc;
}
#navigation{
	float:left;
	width:150px;
}
.section{
	width:1900px;
	position:relative;
}

.section .sub{
	position:relative;
	float:left;
	padding:9px 21px 42px 45px;
	width:567px;
	height:352px;
}
.section .sub p{
	width:550px;
}
.section .next, .section .prev{
	font-size:18px;
	position:absolute;
	bottom:15px;
	letter-spacing:-2px;
}
.section .next{
	right:30px;
}
.section .prev{
	left:30px;
}

.boxgrid{ 
	width: 500px; 
	height: 250px; 
	margin:10px; 
	float:left; 
	background:#161613; 
	border: solid 2px #8399AF; 
	overflow: hidden; 
	position: relative; 
}
.boxgrid1{ 
	width: 200px; 
	height: 150px; 
	margin:10px; 
	float:left; 
	background:#161613; 
	border: solid 2px #8399AF; 
	overflow: hidden; 
	position: relative; 
}
.imgBubble{
	padding:30px 0 0 50px;
}  
.imgBubble li {
    padding: 0px;
    float: left;
    position: relative;
    margin-left: 5px;
    margin-right: 5px;
    width: 148px;
    height: 148px;
}
.imgBubble li a {
    position: absolute;
}
.imgBubble li img {
    position: absolute;
    width: 150px;
    top: 0px;
    left: 0px;
    padding: 0px;
    margin: 0 8px 0 0;
    border: none;
    overflow: hidden;
}
</style>
<!--平滑锚点end-->
<body>
<div id="scrollContent">
		
	<div id="navigation">
		<div class="list_menu2" showAll="true">
			<dt class="parent"><a href="#section1"><span>基本运行环境</span></a></dt>
			<ul class="child">
				<dt><a href="#section1b"><span>快捷方式创建</span></a></dt>
			</ul>
			<dt class="parent"><a href="#section2"><span>系统更新</span></a></dt>
			<ul class="child">
				<dt><a href="#section2b"><span>系统更新后的操作</span></a></dt>
			</ul>	
			<!-- <dt class="parent"><a href="#section3"><span>Flex特点2</span></a></dt>
			<ul class="child">
				<dt><a href="#section3b"><span>强大的数据展示</span></a></dt>
				<dt><a href="#section3c"><span>丰富的表现力</span></a></dt>
			</ul>
			<dt class="parent"><a href="#section4"><span>Flex特点3</span></a></dt>	
			<ul class="child">
				<dt><a href="#section4b"><span>破平面限制</span></a></dt>
				<dt><a href="#section4c"><span>良好数据传递和处理</span></a></dt>
			</ul> -->
		</div>
	</div>

	<div id="content">
		<div class="section">
			<ul>
				<li class="sub" id="section1">
					<h2>系统运行环境</h2>
					<p>为什么要使用IE9？微软很早就已经不修复IE6。IE8到目前为止的内核错误使得很多技术无法正常运行，IE9的推出基本上解决了之前所有IE版本的内核缺陷。
					<a href="?" title="点击进入IE9官网" onclick='javascript:window.open("http://windows.microsoft.com/zh-CN/internet-explorer/downloads/ie-9/worldwide-languages")' >
					<b><font color = "red">(IE9中文简体官网下载)</font></b></a>
					<br>台账管理系统是基于微软发布的IE9浏览器设计和研发的，系统中的报表及表格等特殊的功能需要在IE9中才能完美运行，所以使用此系统时如果出现不兼容问题，请检查是否是IE9浏览器</p>
					<div class="boxgrid captionfull">
						<img src="../images/ie9.png" onclick=""/>
						<div class="cover boxcaption">
							<div class="boxgrid_title">台账管理系统</div>
							<div class="boxgrid_con">采用最新的技术平台<br /></div>
						</div>		
					</div>
					
					<div class="clear"></div>
					
					
				</li>
				<li class="sub" id="section1b">
					<h2>创建快捷方式</h2>
					<p>通过系统地址访问到登陆界面后，通过鼠标右键菜单创建快捷方式。</p>
					<div class="boxgrid1 caption">
						<img src="../images/kjfs1.png"/>
						<div class="cover boxcaption">
							<div class="boxgrid_title">右键菜单</div>
							<div class="boxgrid_con"></div>
						</div>		
					</div>
					<div class="boxgrid1 caption">
						<img src="../images/kjfs3.png"/>
						<div class="cover boxcaption">
							<div class="boxgrid_title">桌面快捷方式</div>
							<div class="boxgrid_con"></div>
						</div>		
					</div>
					<div class="clear"></div>					
				</li>
			</ul>
		</div>
		<div class="section">
			<ul>
				<li class="sub" id="section2" >
					<h2>系统更新</h2>
					<p>为了更好的让您能更方便快捷的使用，台账系统会不断的改变现有功能并且加入新的功能，所以会阶段性的升级系统。</p>
					<a href="#section2b" class="next">&gt;&gt;</a>	
				</li>
				<li class="sub" id="section2b">
					<h2>系统更新后的操作</h2>
					<p>系统升级后，为了避免您浏览器的缓存中存有旧系统的文件，所以建议您在系统升级后第一步先删除浏览器缓存，以便您使用的是最新的台账系统</p>
					<ul class="imgBubble">
					    <li> <a href="javascript:;"><img src="../images/hc1.png" alt="IE选项"/></a></li>
					    <li> <a href="javascript:;"><img src="../images/hc3.png" alt="删除"/></a></li>
					    <li> <a href="javascript:;"><img src="../images/hc4.png" alt="删除"/></a></li>
					 </ul>
				 <div class="clear"></div>
				</li>
			</ul>
		</div>
		
	</div>
	<div id="floatPanel"></div>

</div>	
</body>
</html>
