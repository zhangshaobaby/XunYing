<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="${ctx}/css/index.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/admincp4.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/js/jquery-1.7.js"></script>
		<script type="text/javascript" src="${ctx }/js/base.js"></script>
		<script type="text/javascript">
			function changeState(id,state,obj){
				$(obj).unbind("click");
				$(obj).removeAttr("onclick");
				$.post("${ctx}/auth/luckyMoney/verify",{id:id,state:state},function(data){
					window.location.href=window.location.href;
				});
			}
		</script>
		<title>1717tou.com</title>
	</head>
	 
	<body>
		<form action="${ctx}/auth/repaymentPlan/verifyList"></form>
		<div style="background:#FFFFFF;">
			<div class="admincp_r1"><span>资金审核</span></div>
			<div class="admincp_r_nav">
				<ul id="tabmenu">
					<li onclick="javascript:window.location.href='${ctx}/cash/auth/waitEnaminecashApplaylist'">提现</li>
					<li onclick="javascript:window.location.href='${ctx }/auth/repaymentPlan/verifyList'">还款</li>
					<li class="on">红包</li>
				</ul>
			</div>
			<div class="admincp_r2" id="content0">
				<div class="admincp_r2_c">
					<div class="admincp_r2_c1">产品名称</div>
					<div class="admincp_r2_c2">红包总计（￥）</div>
					<div class="admincp_r2_c3">时间</div>
					<div class="admincp_r2_c4">操作</div>
				</div>
				
				<!-- 循环开始 -->
				<c:forEach var="item" items="${page.result}">
				<div class="admincp_r2_d">
					<div class="admincp_r2_d1">
						${item[0] }<br />
					</div>
					<div class="admincp_r2_d2">${item[1] }</div>
					<div class="admincp_r2_d3">${item[2] }</div>
					<div class="admincp_r2_d4">
						<div class="admincp_r2_d4_l">
							<img src="images/button_tongguo.png" onclick="changeState(${item[3]},3,this)" />
						</div>
						<div class="admincp_r2_d4_r">
							<img src="images/button_butongguo.png" onclick="changeState(${item[3]},0,this)"/>
						</div>
					</div>
				</div>
				</c:forEach>
				
				<!-- 循环结束 -->
 					<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		</div>	
	</body>
</html>




