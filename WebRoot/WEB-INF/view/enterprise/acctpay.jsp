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
		<link href="<%=path%>/css/user_tixian.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/select_bank.js"></script>
		<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script src="<%=path%>/js/formValidator-4.1.3.js"
			type="text/javascript" charset="UTF-8"></script>
		<script src="<%=path%>/js/formValidatorRegex.js"
			type="text/javascript" charset="UTF-8"></script>
		<script type="text/javascript">
        var basepath='<%=path%>';
         $(document).ready(function(){
          $.formValidator.initConfig({formID:"form1",theme:"ArrowSolidBox",submitOnce:true,
          ajaxPrompt : '有数据正在异步验证，请稍等...'
	});     
         	 $("#transAmt").formValidator({onShow:"请输入取现金额",onFocus:"金额必须大于0，小数点后不超过2位。",onCorrect:"正确"}).inputValidator({min:1,onError:"请填写取现金额"}).regexValidator({regExp:"(?!^0\\d*$)^\\d{1,16}(\.\\d{1,2})?$",onError:"金额填写有误"});
         
         })
        </script>
		<title>1717tou.com</title>
	</head>

	<body>

		<div style="background: #FFFFFF;">

			<div class="user_cont_r_1">用户支付</div>
			<div class="user_cont_r_2">
				<form name="form1" action="<%=path%>/huifu/usracctpay" method="post"
					id="form1">
						<input type="hidden" name="usrCustId" value="${usrCustId}" /><br />
						
					<div class="user_cont_r_2tx">
						<div class="user_cont_r_2tx1">
							<div class="user_cont_r_2tx1_l">
								您的名称
							</div>
							<div class="user_cont_r_2tx1_r">
								<span>${borrowername}</span>
							</div>
						</div>
	
						<div class="user_cont_r_2tx1">
							<div class="user_cont_r_2tx1_l">
								可支付额度
							</div>
							<div class="user_cont_r_2tx1_r">
								<span>${AvlBal}</span>
							</div>
						</div>


						<div class="user_cont_r_2tx1">
							<div class="user_cont_r_2tx1_l">
								支付金额
							</div>
							<div class="user_cont_r_2tx1_r1">
								<input type="text" id="transAmt" name="transAmt" />
								&nbsp;元
							</div>
							<div class="user_cont_r_2tx1_r2"  ><div id="transAmtTip" ></div></div>
						</div>

						<div class="user_cont_r_2tx3">
							<div class="user_cont_r_2tx3_l"></div>
							<div class="user_cont_r_2tx3_r">
								<div class="user_cont_r_2tx3_r2">
									<input type="submit" name="submit1" value="下一步" />
								</div>
							</div>
						</div>

					</div>
				</form>

			</div>

		</div>
	</body>
</html>
