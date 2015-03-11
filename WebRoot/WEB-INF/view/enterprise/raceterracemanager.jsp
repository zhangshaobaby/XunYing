<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<link href="<%=path%>/css/admincp13.css" rel="stylesheet"
			type="text/css" />


		<title>1717tou.com</title>
	</head>

	<body>
<div>昨日注册 经纪人：${y2Times}人，其中开通第三方支付${y2hfTimes}人<div>
<div>昨日注册 一般人：${y1Times}人，其中开通第三方支付${y1hfTimes}人<div>
<div>今日注册 经纪人：${today2Times}人，其中开通第三方支付${today2hfTimes}人<div>
<div>今日注册 一般人：${today1Times}人，其中开通第三方支付${today1hfTimes}人<div>
<div>总计注册 经纪人：${all2Times}人，其中开通第三方支付${all2hfTimes}人<div>
<div>总计注册 一般人：${all1Times}人，其中开通第三方支付${all1hfTimes}人<div>
		<div style="background: #FFFFFF;">
			<div class="admincp_r1">
				<span>平台账户</span>
			</div>

			<div class="admincp_r2">

				<div class="admincp_r2_h">
					<div class="admincp_r2_h_main">
						<div class="admincp_r2_h_main1">
							<font>账户余额</font>
							<br />
							可用余额
						</div>
						<div class="admincp_r2_h_main2">
							<font>${AcctBal}</font>
							<br />
							${AvlBal}
						</div>
						<div class="admincp_r2_h_main3">
							<a href="<%=path%>/auth/operator/recharge?gateBusiId=B2B" target="_blank"><img
									src="<%=path%>/images/icon_chongzhi.png" />
							</a>
							<a href="<%=path%>/auth/operator/recharge?gateBusiId=B2C" target="_blank">
							<img
									src="<%=path%>/images/icon_chongzhi_2.png" />
								</a>		
							

						</div>
					</div>
				</div>

				<div class="admincp_r2_a">
					<div class="admincp_r2_a1">
						账户类型
					</div>
					<div class="admincp_r2_a2">
						子账户
					</div>
					<div class="admincp_r2_a3">
						可用余额
					</div>
					<div class="admincp_r2_a4">
						账户余额
					</div>
					<div class="admincp_r2_a5">
						冻结余额
					</div>
				</div>
				<!-- 交易记录循环开始-->
				<c:forEach var="item" items="${list}">
					<div class="admincp_r2_b">
						<div class="admincp_r2_b1">
							<c:choose>
								<c:when test="${item.AcctType=='BASEDT'}">基本借记户</c:when>
								<c:when test="${item.AcctType=='MERDT'}">专属借记帐户</c:when>
								<c:when test="${item.AcctType=='DEP'}">保证金账户</c:when>
								<c:otherwise>${item.AcctType}</c:otherwise>
							</c:choose>
						</div>
						<div class="admincp_r2_b2">
							${item.SubAcctId}
						</div>
						<div class="admincp_r2_b3">
							${item.AvlBal}
						</div>
						<div class="admincp_r2_b4">
							${item.AcctBal}
						</div>
						<div class="admincp_r2_b5">
							${item.FrzBal}
						</div>
					</div>
				</c:forEach>
				<!-- 交易记录循环结束-->


			</div>


		</div>

	</body>
</html>


