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
			function changeState(id,state,el){
			$(el).attr("href",";");
				$.post("${ctx}/auth/repaymentPlan/verify",{id:id,state:state},function(data){
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
					<li class="on">还款</li>
					<!-- <li onclick="javascript:window.location.href='${ctx}/auth/luckyMoney/verifyList'">红包</li> -->
				</ul>
			</div>
			<div class="admincp_r2" id="content0">
				<div class="admincp_r2_c">
					<div class="admincp_r2_c1" style="width:220px;">产品描述</div>
					
					<div class="admincp_r2_c2" style="width:110px;">提款金额（￥）</div>
					<div class="admincp_r2_c3">申请时间</div>
					<div class="admincp_r2_c2" style="width:120px;">剩余金额</div>
					<div class="admincp_r2_c4">操作</div>
				</div>
				
				<!-- 循环开始 -->
				<c:forEach var="item" items="${page.result}">
				<div class="admincp_r2_d">
					<div class="admincp_r2_d1" style="width:220px;">
					<a target="_blank" href="${ctx }/project/view?id=${item[8]}&tag=1" title="${item[0] }"><cc:subLength var="${item[0] }" length="19"/></a><br />
						还款属性：
						<c:choose>
							<c:when test="${item[1]==0 }">
								本金
							</c:when>
							<c:otherwise>
								第${item[7]}批次利息
							</c:otherwise>
						</c:choose>
					</div>
					<div class="admincp_r2_d2" style="width:110px;">${item[2] }</div>
					<div class="admincp_r2_d3">${item[6] }</div>
					<div class="admincp_r2_d2" style="width:120px;">${item[9] }</div>
					<div class="admincp_r2_d4">
						<c:choose>
						<c:when test="${item[4]==0}">
							<div class="admincp_r2_d4_l">
								<img src="${ctx }/images/button_tongguo.png" onclick="changeState(${item[5]},1,this)" />
							</div>
							<div class="admincp_r2_d4_r">
								<img src="${ctx }/images/button_butongguo.png" onclick="changeState(${item[5]},-1,this)"/>
							</div>
						</c:when>
						<c:when test="${item[4]==1}">
							审核通过
						</c:when>
						<c:otherwise>
							审核拒绝
						</c:otherwise>
						</c:choose>
					</div>
				</div>
				</c:forEach>
				
				<!-- 循环结束 -->
 					<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		</div>	
	</body>
</html>




