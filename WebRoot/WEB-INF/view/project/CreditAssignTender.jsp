<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>债权购买</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<div class="container">
			<!-- start:Wall -->
			<div class="wall">
				<h3>
					眼镜产品销售公司
				</h3>
				<ul class="inline invest-info">
					<li>
						认购债权金额
						<span class="important">${credit.creditAmt}元</span>
					</li>
					<li>
						应付金额
						<span class="important">${credit.creditDealAmt} 元</span>
					</li>
					<li>
						实际收益率
						<span>12%</span>
					</li>

					<li>
						剩余期限
						<span>204 天</span>
					</li>
				</ul>

				<hr class="dashed" />
				<form action="<%=path%>/creditAssign/Initial" method="post"
					name="invest_form">
					<input data-val="true"
						data-val-number="The field CreditAssignID must be a number."
						data-val-required="请填写CreditAssignID。" id="CreditAssignID"
						name="creditAssignid" type="hidden" value="${credit.id}" />
					<div class="control-group">
						<div class="controls">
							<label class="checkbox">
								<input type="checkbox" id="Contract" name="Contract" />
								同意
								<a class="btn btn-mini btn-primary"
									href="/Contract/CreditAssignInvest?CreditAssignID=52223"
									target="_blank">债权转让投资协议</a>
							</label>
							<span class="help-block hide" for="Contract">请先同意债权转让投资协议</span>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary"
								id="act_invest_confirm">
								确认
							</button>
							<a class="btn" href="/CreditAssign/Index/52223"
								id="act_invest_cancel">取消</a>
						</div>
					</div>
				</form>
			</div>
	</body>
</html>
