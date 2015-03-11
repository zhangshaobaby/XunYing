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
		<link href="<%=path%>/css/user_jilu.css" rel="stylesheet"
			type="text/css" />
			<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<Script src="<%=path%>/js/dateTimePicker/WdatePicker.js"></Script>
		<script type="text/javascript" src="<%=path %>/js/base.js"></script>
		<title>1717tou.com</title>
	</head>

	<body>

		<div style="background: #FFFFFF;">

			<div class="user_cont_r_1">
				<span>提现记录</span>
			</div>
			<div class="user_cont_r_2">

				<div class="user_cont_r_2h">
					<div class="user_cont_r_2h_main">
						<div class="user_cont_r_2h_main1">
							<font>账户余额</font>
							<br />
							可用余额
						</div>
						<div class="user_cont_r_2h_main2">
							<font>${AcctBal}</font>
							<br />
							${AvlBal}
						</div>
						<div class="user_cont_r_2h_main3">
							<a target="_blank" href="<%=path%>/cash/enchashment"><img
									src="<%=path%>/images/icon_tixian.png" />
							</a>
						</div>
					</div>
				</div>

				<div class="user_cont_r_2a">
					<form name="form1" action="<%=path%>/cash/cashlist" method="post">
						<span>起止日期： <input  onClick='WdatePicker()' type="text" name="start_time" class="input_1" value="${start_time }" />
							- <input onClick='WdatePicker()'  type="text" name="end_time" class="input_1" value="${end_time }" /> &nbsp;&nbsp;<input
								type="submit" name="sousuo" value="搜索" class="input_2" /> </span>
					</form>
				</div>

				<div class="user_cont_r_2b">

					<div class="user_cont_r_2b1">
						交易类型|流水号
					</div>
					<div class="user_cont_r_2b2">
						取款账户
					</div>
					<div class="user_cont_r_2b3">
						金额（￥）|明细
					</div>
					<div class="user_cont_r_2b4">
						时间
					</div>
				</div>
				<!-- 交易记录循环开始-->
				<c:forEach var="item" items="${page.result}">
					<div class="user_cont_r_2c">
						<div class="user_cont_r_2c1">
							${item.id}
						</div>
						<div class="user_cont_r_2c2">
							${item.openAcctId}
						</div>
						<div class="user_cont_r_2c3">
							${item.transAmt}
						</div>
						<div class="user_cont_r_2c2">
							${item.createTime}
						</div>


					</div>
				</c:forEach>


				<!-- 交易记录循环结束-->
					<jsp:include page="../common/page.jsp"></jsp:include>

			</div>

		</div>
	</body>
</html>


