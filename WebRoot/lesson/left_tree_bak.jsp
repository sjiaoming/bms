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
<script type="text/javascript" src="<%=path %>/js/tree/dtree/dtree.js"></script>
<link href="<%=path %>/js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path %>/js/nav/tab.js"></script>
<script>
 var tab;
 var index=1;
$( function() {
	 tab= new TabView( {
		containerId :'tab_menu',
		pageid :'page',
		cid :'tab1',
		position :"top"
	});
	tab.add( {
		id :'index0',
		title :"新闻管理",
		url :"chapter9-3-2-content2.html",
		isClosed :true
	});
});
function addTab(id,name,url){
	alert('111');
	tab.add( {
		id :id,
		title :name,
		url :url
	});
	index++;
}
function a(){
	alert('ssss');
}
</script>
</head>
<body leftFrame="true">
	<div style="text-align:center;" >
	<br />
	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	</div>
	<div id="scrollContent">
	<script type="text/javascript">
		

		d = new dTree('d');
		d.add(0,-1,'台帐管理');
		<c:forEach items="${modules}" var="m">
			<c:if test="${empty m.parent}">
				//var p${m.id} = new createPanel('id_${m.id}','${m.name}');
				//o.addPanel(p${m.id});
				d.add(${m.id },0,'${m.name}',null,'','')
			</c:if>
		</c:forEach>
		<c:forEach items="${modules}" var="s">
			<c:if test="${!empty s.parent}">
				if('undefined' != ${s.parent.id} && ${s.parent.id} != null){
					//p${s.parent.id}.addButton('netm.gif','${s.name}','parent.main.location="${s.url}"');
					
					if('${s.url}'.indexOf('?') < 0){
						d.add(${s.id },${s.parent.id},'${s.name}','<%=path %>/${s.url}?snid=${s.id }','','frmright');
					}else{
						d.add(${s.id },${s.parent.id},'${s.name}','<%=path %>/${s.url}&snid=${s.id }','','frmright');
					}
				}
			</c:if>
		</c:forEach>

		//d.add(300,0,'系统管理',null,'','');
		//d.add(2,300,'用户管理','../systemManagement/userMg/index.jsp','','frmright');
		//d.add(3,300,'权限管理','#','','frmright');
		//d.add(4,300,'角色管理','../systemManagement/roleMg/index.jsp','','frmright');
		
		d.add(301,0,'基础信息管理',null,'','');
		//d.add(10,301,'组织管理','#','','frmright');
		//d.add(11,301,'部门管理','#','','frmright');
		//d.add(12,301,'板块管理','#','','frmright');
		//d.add(13,301,'物资管理','#','','frmright');
		//d.add(14,301,'供应商管理','#','','frmright');
		//d.add(19,301,'使用单位管理','#','','frmright');
		d.add(22,301,'人员管理',"javascript:a()",'','frmright');
		
		//d.add(302,0,'业务管理',null,'','');
		//d.add(15,302,'计划管理','../businessManagement/planManagement/index.jsp','','frmright');
		//d.add(16,302,'合同管理','#','','frmright');
		//d.add(17,302,'台帐查询','#','','frmright');
		//d.add(18,302,'付款跟踪','../businessManagement/payTracking/index.jsp','','frmright');

		//d.add(303,0,'报表管理',null,'','');
		//d.add(20,303,'报表导出','../statementsManagement/statementExp/index.jsp','','frmright');
		//d.add(21,303,'台帐统计','../statementsManagement/statementStatistics/index.jsp','','frmright');
		
		
		
		document.write(d);

		
	</script>
</div>

</body>
</html>
