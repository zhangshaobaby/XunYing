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

			<div class="user_cont_r_1">
				<span><a href="user_tixian.html">提现记录</a>>账户提现</span>
			</div>
			<div class="user_cont_r_2">
				<form name="form1" action="<%=path%>/cash/auth/enchashmentApply" method="post"
					id="form1">
					<input type="hidden"  name="usrCustId" value="${usrCustId}" />
					<div class="user_cont_r_2tx">
						<div class="user_cont_r_2tx1">
							<div class="user_cont_r_2tx1_l">
								公司名称
							</div>
							<div class="user_cont_r_2tx1_r">
								<span>${borrowername}</span>
							</div>
						</div>
						<!--提现到银行卡信息开始 -->
						<div class="user_cont_r_2tx2">
							<div class="user_cont_r_2tx2_l">
								提现到银行卡
							</div>

							<div class="user_cont_r_2tx2_r1">
									<select name="openAcctId" id="bankcard">
										<c:forEach items="${buildbank}" var="item">
											<option value="${item.cardNumber}">
												${item.bankName}(...${fn:substring(item.cardNumber,14,100)})
											</option>
										</c:forEach>
									</select>

								</div>

							<div class="user_cont_r_2tx2_r2"><div id="cardTip"></div></div>
							<script type="text/javascript">
  										loadSelect(document.form1.bankcard);
									</script>
						</div>
						<div style="clear: both"></div>
						<!--提现到银行卡结束-->
						<div class="user_cont_r_2tx1">
							<div class="user_cont_r_2tx1_l">
								可用额度
							</div>
							<div class="user_cont_r_2tx1_r">
								<span>${AvlBal}</span>
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
							<div class="user_cont_r_2tx1_r2"  ><div id="transAmtTip" ></div></div>
						</div>
						<div class="user_cont_r_2tx1">
						<div class="user_cont_r_2tx1_l">
								提现说明:
							</div>
							<div><textarea name="cashDesc" rows="2" cols="40"></textarea> </div>
						</div>

						<div class="user_cont_r_2tx3">
							<div class="user_cont_r_2tx3_l"></div>
							<div class="user_cont_r_2tx3_r">
								<div class="user_cont_r_2tx3_r1">
									<span>警告：禁止信用卡套现，虚假交易行为，一经确认，将终止该用户的使用</span>
								</div>
								<div class="user_cont_r_2tx3_r2">
									<input type="submit" name="submit1" value="下一步" />
								</div>
								<div class="user_cont_r_2tx3_r3">
									<span>你的资金将从合作银行保管账号转入提现到银行卡</span>
								</div>
							</div>
						</div>

					</div>
				</form>
				<!--说明开始-->
				<div class="user_cont_r_2des">
					<div class="user_cont_r_2des_main">
					提示信息：	<br />
1.身份认证、开通汇付天下第三方资管账户、完成提现银行卡绑定后，才能进行提现操作；	<br />
2.汇付天下将收取1元/笔的提现手续费；	<br />
3.充值资金未投资直接提现，将收取提现金额的0.25%作为转账费用；	<br />
4.资金到达用户账户的当日即可申请提现，除偏远地区外的大部分银行卡，当日取现下一个工作日到账；	<br />
5.遇到问题请联系【在线客服】，或者致电客服热线：4000-57-3090。	<br />
						
					</div>
				</div>
				<!--说明结束-->
			</div>

		</div>
	</body>
</html>
