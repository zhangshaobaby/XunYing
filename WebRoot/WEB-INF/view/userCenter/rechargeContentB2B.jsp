<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/user_chongzhi.css" rel="stylesheet" type="text/css" />
<script src="<%=path%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="<%=path%>/js/formValidator-4.1.3.js"
			type="text/javascript" charset="UTF-8"></script>
		<script src="<%=path%>/js/formValidatorRegex.js"
			type="text/javascript" charset="UTF-8"></script>
		<script type="text/javascript">
         $(document).ready(function(){
          $.formValidator.initConfig({formID:"form1",theme:"ArrowSolidBox",submitOnce:true,
          ajaxPrompt : '有数据正在异步验证，请稍等...'
	});     
         	 $("#amount").formValidator({onShow:"请输入充值金额",onFocus:"金额必须大于0，小数点后不超过2位。",onCorrect:"正确"}).inputValidator({min:1,onError:"请填写充值金额"}).regexValidator({regExp:"(?!^0\\d*$)^\\d{1,16}(\.\\d{1,2})?$",onError:"金额填写有误"});
         })
        </script>
		<title>1717tou.com</title>
	</head>

	<body>
		<!-- 信息开始 -->
		<div class="user_cont_rr">
		 <form name="form1" id="form1" method="post" action="<%=path%>/huifu/recharge">
                  <div class="user_cont_rrr">
					    <div class="user_cont_r_1"><span>充值</span></div>
						<div class="user_cont_r_2">选择充值网银</div>   
                         <table class="user_cont_r_3" cellspacing="0"> 
				<input type="hidden" name="huifuID" 
value="${huifuID}" />
				<input type="hidden" name="userid" 
value="${userid}" />
				<input type="hidden" name="gateBusiId" 
value="${gateBusiId}" />
                         	<tr>
                         		<td>
                         			    <input  value="8" type="radio" name="bankId.id" checked="checked"/><img src="<%=path%>/images/bank/CMB.gif"   /></td>
                         		<td>	<input  value="6" type="radio" name="bankId.id"/><img src="<%=path%>/images/bank/ICBC.gif"/></td>
                         		<td>	</td>
                         		<td>	
                         		</td>
                         	</tr>
                         	<tr>
                         		<td>
                         			    <input value="9"  type="radio" name="bankId.id"/><img src="<%=path%>/images/bank/CCB.gif"/></td>
                         		<td>	<input value="12"  type="radio" name="bankId.id"/><img src="<%=path%>/images/bank/BOC.gif"/></td>
                         		<td>	<input value="29"  type="radio" name="bankId.id"/><img src="<%=path%>/images/bank/SPDB.gif"/></td>
                         		<td>	
                         		</td>
                         	</tr>
                         	<tr>
                         		<td>
                         			    <input  value="7"  type="radio" name="bankId.id"/><img src="<%=path%>/images/bank/ABC.gif"/></td>
                         			<td><input  value="13"  type="radio" name="bankId.id"/><img src="<%=path%>/images/bank/BOCOM.gif"/></td>
                         			<td><input  value="17"  type="radio" name="bankId.id"/><img src="<%=path%>/images/bank/CEB.gif"/></td>
                         			<td></td>
                         	</tr>
                         </table>
                      
					</div>
					<div  class="user_cont_r_6">
						<span>输入充值金额</span><input id="amount" name="amount" value="" type="text" /><div id="amountTip"  style="width:480px;height:30px;float:left;margin-left:10px;font-size:12px;" ></div>
						
						
					</div>
					<div class="user_cont_r_7"><input type="submit" value="确认充值"/></div>
					<div class="user_cont_r_8">
						<h3>温馨提示：</h3>
						<div class="con">
							1.投资人充值过程全程免费，不收取任何手续费；<br/>
2.充值必须为银行借记卡，不支持存折、信用卡充值；<br/>
3.充值期间，请勿关闭浏览器，待充值成功并返回首页后，所充资金才能入账，如有疑问，请联系客服；<br/>
4.充值需要开通银行卡网上支付功能，如有疑问请咨询开户行客服；
						</div>
					</div>
									</form>
				</div>
		<!-- 信息结束 -->
	</body>
</html>
