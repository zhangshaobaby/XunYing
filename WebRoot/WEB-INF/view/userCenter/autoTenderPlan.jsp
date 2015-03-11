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
		<title>自动投标</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/user_tixian.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/select_bank.js"></script>
		<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script src="<%=path%>/js/formValidator-4.1.3.js"
			type="text/javascript" charset="UTF-8"></script>
		<script src="<%=path%>/js/formValidatorRegex.js"
			type="text/javascript" charset="UTF-8"></script>
		<script type="text/javascript">
			$.formValidator.initConfig({formID:"form1",theme:"ArrowSolidBox",submitOnce:true,
          ajaxPrompt : '有数据正在异步验证，请稍等...'
	});     
         	 $("#transAmt").formValidator({onShow:"请输入取现金额",onFocus:"金额必须大于0，小数点后不超过2位。",onCorrect:"正确"}).inputValidator({min:1,onError:"请填写取现金额"}).regexValidator({regExp:"(?!^0\\d*$)^\\d{1,16}(\.\\d{1,2})?$",onError:"金额填写有误"});
			</script>

	</head>

	<body>
		<div style="background: #FFFFFF;">
			<div class="user_cont_r_1">
				<span>自动投标计划
				</span>
			</div>
			<!--提现到银行卡结束-->
			<form action="<%=path%>/huifu/autoTenderPlan" method="post"
				id="form1">
				<div class="user_cont_r_2tx1">
					<div class="user_cont_r_2tx1_l">
						投标计划类型
					</div>
					<div class="user_cont_r_2tx1_r">
						<select name="TenderPlanType">
							<option value="P">
								部分授权
							</option>
							<option value="W">
								完全授权
							</option>
						</select>
					</div>
				</div>


				<div class="user_cont_r_2tx1">
					<div class="user_cont_r_2tx1_l">
						提现金额
					</div>
					<div class="user_cont_r_2tx1_r1">
						<input type="text" id="transAmt" name="transAmt" />
						&nbsp;元
					</div>
					<div class="user_cont_r_2tx1_r2">
						<div id="transAmtTip"></div>
					</div>
				</div>
				<div class="chongzhi_button">
					<span> <input type="submit" name="submit1" value="确定"
							class="submit1" />&nbsp;&nbsp; <a
						href="javacript:window.close()"><input type="button"
								value="取消" class="submit2" /> </a> </span>
				</div>
			</form>
		</div>
	</body>
</html>
