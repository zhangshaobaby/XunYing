<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/admincp4.css" rel="stylesheet" type="text/css" />
<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script type="text/javascript" src="<%=path %>/js/base.js"></script>
<script type="text/javascript">
		function   changestate(id,state,el) {
		//设置路径为空
		$(el).attr("href",";");
		jQuery.ajax( {
		type : "POST",
		url : "<%=path%>/cash/auth/changestate?id="+id+"&state.id="+state,
		success : function(result) {
			if(result != null && result != ""){
			 var message=eval(result);
			 if(message.msgCode!="success"){
			 alert(message.msgString);
			 }else{
			 alert("操作成功");
			 }
			 window.location.reload();
			}
		}
	});
		}
	function change(url){
	 window.location.href=url;
	}
 
	function descshow(id){
		var admincp_r2_b_action = document.getElementById('admincp_r2_b_action'+id);
		admincp_r2_b_action.style.display='block';
		
	
	}
	
	function deschide(id){
		var admincp_r2_b_action = document.getElementById('admincp_r2_b_action'+id);
		admincp_r2_b_action.style.display='none';
		
	
	}
	function descshow1(){
		var admincp_r2_d_action = document.getElementById('admincp_r2_d_action');
		admincp_r2_d_action.style.display='block';
		
	
	}
	
	function deschide1(){
		var admincp_r2_d_action = document.getElementById('admincp_r2_d_action');
		admincp_r2_d_action.style.display='none';
		
	
	}
 
  
  </script>
 
<title>1717tou.com</title>
</head>
 
<body>
<form action="<%=path%>/cash/auth/waitEnaminecashApplaylist"></form>
			<div style="background:#FFFFFF;">
				<div class="admincp_r1"><span>资金审核</span></div>
				<div class="admincp_r_nav">
					<ul id="tabmenu">
						<li class="on" onclick="change('<%=path%>/cash/auth/waitEnaminecashApplaylist')">提现</li>
						<li onclick="javascript:window.location.href='<%=path%>/auth/repaymentPlan/verifyList'">还款</li>
						<!-- <li onclick="javascript:window.location.href='<%=path%>/auth/luckyMoney/verifyList'">红包</li> -->
					</ul>
				</div>
				
					
				<div class="admincp_r2" id="content0">
					<div class="admincp_r2_a">
					    <div class="admincp_r2_a0" >受托方</div>
						<div class="admincp_r2_a1">提款账号</div>
						<div class="admincp_r2_a2">提款金额/可用余额</div>
						<div class="admincp_r2_a3">描述</div>
						<div class="admincp_r2_a4">时间</div>
						<div class="admincp_r2_a5">操作</div>	
					</div>
					
					<!-- 循环开始 -->
					<c:forEach var="item" items="${page.result}">
					<div class="admincp_r2_b">
					    <div class="admincp_r2_b0" title="${item.borrower.name}" >
					    <c:choose>
					    <c:when test="${item.borrower.name!=''&&fn:length(item.borrower.name)>5}">
							${fn:substring(item.borrower.name,0,5)}
							</c:when>
							<c:otherwise>
							${item.borrower.name}
							</c:otherwise>
							</c:choose>
					    
					    </div>
					  
						<div class="admincp_r2_b1" title="${item.openAcctId}">..${fn:substring(item.openAcctId,14,100)}</div>
						<div class="admincp_r2_b2">${item.transAmt}/<span style="color: green"><cc:selectBalance value="${item.usrCustId}"/> </span></div>
						<div class="admincp_r2_b3" title="${item.cashDesc}"><c:choose>
					  <c:when test="${item.cashDesc!=''}&&${fn:length(item.cashDesc)>5}">
							${fn:substring(item.cashDesc,0,5)}
							</c:when>
							<c:otherwise>
							${item.cashDesc}
							</c:otherwise>
							</c:choose></div>
						<div class="admincp_r2_b4">${item.createTime}</div>
						<div class="admincp_r2_b5">
							<div class="admincp_r2_b5_l">
								<img src="<%=path %>/images/button_tongguo1.png" style="position:absolute; left:0px;" onmousemove="descshow('${item.id}')"
								 onmouseout="deschide('${item.id}')"/>
								<a href="javascript:changestate('${item.id}','38',this)"><img src="<%=path %>/images/button_tongguo.png" /></a>
							</div>
							<a href="javascript:changestate('${item.id}','37',this)"><div class="admincp_r2_b5_r"><img src="<%=path %>/images/button_butongguo.png" /></div>
							</a>
						</div>
						
						<div class="admincp_r2_b_action" id="admincp_r2_b_action${item.id}" style="display:none;">${item.tipmes}</div>
							
					</div>
						</c:forEach>
					
					<!-- 循环结束 -->
   <jsp:include page="../common/page.jsp"></jsp:include>
			
<div class="yiyi" style="display:none;">
	<div class="yiyiwin">
		<div class="yiyiwin1">
			<div class="yiyiwin1_l">请输入意见：</div>
			<div class="yiyiwin1_r"><textarea name="cont" ></textarea></div>
		</div>
		<div class="yiyiwin2">
			<a href="#"><img src="images/icon_queding1.png" /></a>
		</div>
		
	</div>
</div>
	
</body>
</html>




