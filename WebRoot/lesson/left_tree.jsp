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
<script type="text/javascript" src="<%=path %>/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/nav/jquery.layout.js"></script>
<script type="text/javascript" src="<%=path %>/js/nav/layout.js"></script>
<script type="text/javascript" src="<%=path %>/js/nav/tab.js"></script>
<script type="text/javascript" src="<%=path %>/js/nav/ddaccordion.js"></script>
<script>
 var tab;
 var flag = true;
$( function() {
	 tab= new TabView( {
		containerId :'tab_menu',
		pageid :'page',
		cid :'tab1',
		position :"top"
	});
	tab.add( {
		id :'index9999',
		title :"使用帮助",
		url :"lesson/help.jsp",
		isClosed :true
	});
});
function addTab(id,name,url){
	if($(".tab_item").length  > 7){
		flag = false;
	}else{
		flag = true;
	}
	if(flag){
		tab.add( {
			id :id,
			title :name,
			url :url
		});
	}else{
		alert("界面开启太多，请关闭一些再尝试");
	}
	/* if($(".tab_item").length  > 8){
		flag = false;
	}else{
		flag = true;
	} */
	
}
</script>
</head>
<style>
a {
	behavior:url(../js/method/focus.htc)
}
</style>
<body leftFrame="true" rel="layout">
	 <div class="ui-layout-west">
        <div style="width:200px;">
			<div class="arrowlistmenu">
			<c:forEach items="${modules}" var="m">
				<c:if test="${empty m.parent}">
					<div class="menuheader expandable">${m.name}</div>
					<ul class="categoryitems">
						<c:forEach items="${modules}" var="s">
							<c:if test="${!empty s.parent && s.parent.id == m.id }">
								<li><a href="javascript:addTab('index${s.id}','${s.name}','<%=path %>/${s.url}')" target="frmright"><span>${s.name}</span></a></li>
							</c:if>
						</c:forEach>
					</ul>
				</c:if>
			</c:forEach>
				<div class="menuheader expandable">帮助中心</div>
				<ul class="categoryitems">
				<li><a href="javascript:addTab('index9999','使用帮助','<%=path %>/lesson/help.jsp')" target="frmright"><span>使用帮助</span></a></li>
				</ul>
			</div>
		</div>
    </div>

    <div class="ui-layout-center">
		 <div class="subhead" style="height:30px;">
			<div id="tab_menu"></div>
		</div>
        <div class="content">
			<div id="page" style="width:100%;height:100%;"></div>	
        </div>
		
    </div>
	
			
</body>

</html>
