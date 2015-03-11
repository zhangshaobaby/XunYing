<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/user_chongzhi.css" rel="stylesheet" type="text/css" />
<script src="<%=path %>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<title>1717tou.com</title>
</head>
 
<body >
				<div class="user_cont_rr1">
                  <div class="user_cont_rrr">
					    <div class="user_cont_r_1"><span>提现</span></div>
						<div class="user_cont_r_10">您尚未绑定银行卡，请先绑定提现银行卡</div>   
					<div class="user_cont_r_9"><a href="<%=path %>/auth/huifu/enterpriseUserBindCard?usrCustId=${usrCustId}"><img src="<%=path %>/images/tixian1.png" alt=""/></a></div>
					<div class="user_cont_r_8">
						<h3>温馨提示：</h3>
						<div class="con">
							1.身份认证、账户托管开通、提现银行卡均设置成功后，才能进行提现操作；<br/>
							2.<span style="color:#e33340">费用为每笔1元</span>；从2014年5月1日起，每一位提现成功的用户，将获得一张提现补偿券，次月第一周发送到您账户中；<br/>
							3.预计到账时间：<br/>&nbsp;&nbsp;&nbsp;T日21:00之前申请的提现，当日审核完毕预计于T+1个工作日12:00-16:00到账；<br/>&nbsp;&nbsp;&nbsp;遇双休日法定节假日顺延，实际到账时间依据账户托管方（第三方支付平台）及提现银行而有所变异；<br/>
							4.提现必须为银行借记卡，不支持存折、信用卡提现。<br/>5.资金到达用户一起一起投账户的当日（包括双休日法定节假日）即可发起提现申请；<br/>6.因一起一起投无法触及用户资金账户，无法收取用户任何费用，为防止套现，所充资金必须经投标回款后才能提现。
						</div>
					</div>
				</div>
			
</body>
</html>


