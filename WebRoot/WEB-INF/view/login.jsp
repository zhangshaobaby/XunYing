<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<title>1717tou.com</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/my_login.css" />
<script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx }/js/outerLogin.js" type="text/javascript"></script>
		        <style type="text/css">
		.STYLE2 {
			font-size: 10pt
		}
		</style>
		
		<script type="text/javascript">
			var basepath ='${ctx }';
	var msg='${msg}';
jQuery(document).ready(function () {
	jQuery("#msgValue").empty();
	jQuery("#msgValue").append("<p>${msg}</p>");
	var userid = "${username}";
	var cookieUser=getCookie("username");
	var cookieuserpwd=getCookie("password");
	if (userid != "") {
		document.getElementById("username").value = userid;
	}
	else if(cookieUser!=null){
	 document.getElementById("username").value = cookieUser;
	}
});
</script>
</head>

<body>
	<%@ include file="common/head.jsp" %>
	<div class="login">
		<div class="login1">首页><span>用户登录</span></div>
		<div class="login2">
			<div class="login2_1">
				<h3>用户登录</h3>
				<form id="loginForm" name="loginForm" action="dologin" method="post">
					<input type="hidden" value = "${refer }" name="refer"/>
				<div class="loginl">
					<div class="login2_11">
						<input type="text" name="username" id="username"  class="username" value="手机号/用户名" onfocus="this.value=''" />
					</div>
					<div class="login2_12">
						<input type="password" name="password" id="password"  class="pwd" value="" />
					</div>
					<div class="login2_13">
						<input onclick=" saveMe();" type="checkbox" class="check" />记住用户名
						<div style="float:right"><a href="${ctx }/user/toResetPwd">忘记密码？</a><a href="${ctx }/user/register">免费注册</a></div>
					</div>
					<div class="login2_14">
						<img onclick="submitForm()" src="${ctx }/images/login2_04.png" alt="" />
					</div>
					<div class="login2_15">
						<img src="${ctx }/images/icon_anquan.png" />你的信息通过256GSC加密保护，数据传输安全
				   </div>
				   <div id="msgValue" class="login2_15"style="margin-top:20px;color:#f00;width:100px;height:20px;">
					
				   </div>
				</div>
				</form>
			</div>
			<div class="login2_2">
				<div class="login2_21"><img src="${ctx }/images/login2_05.png" /></div>
				<div class="login2_22">
<span style="color:#e33340;font-size: 16px;">68</span>家信托刚兑机构，
				</div>
                <div class="login2_23">
<span style="color:#e33340;font-size: 16px;">30</span>倍的银行存款利息！
				</div>
                <a href="http://www.1717tou.com/huodong/newinfor" target="_blank">了解更多>></a>
			</div>

			<div class="login2_2">
				<div class="login2_21"><img src="${ctx }/images/login2_06.png" /></div>
				<div class="login2_22">
<span style="color:#e33340;font-size: 16px;">12</span>年的大客户理财经验，
				</div>
                <div class="login2_23">
<span style="color:#e33340;font-size: 16px;">9</span>道产品审核，担保本金！
				</div>
                <a href="http://www.1717tou.com/huodong/anquaninfor" target="_blank"">了解更多>></a>
			
		</div>
		</div>
	</div>
	<!-- 注册信息结束 -->
	<%@ include file="common/foot.jsp" %>
</body>
</html>
