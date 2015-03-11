<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>认证列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css">
	<link href="<%=path%>/css/user_anquan.css" rel="stylesheet" type="text/css" />

  </head>
  
  <body>
   <div style="background:#FFFFFF;">
					<div class="user_cont_r_1"><span>安全中心</span></div>
					<div class="user_cont_r_2">
						<div class="user_cont_r_2b">
							
							
							<div class="user_cont_r_2b1">
								<div class="user_cont_r_2b1a"><c:choose>  <c:when test="${realNameAuthent==true}"><img src="images/icon_true.png" /></c:when><c:otherwise><img src="images/icon_false.png" /></c:otherwise></c:choose></div>
								<div class="user_cont_r_2b1b">实名认证</div>
								<div class="user_cont_r_2b1c">充值前，必须完成实名认证</div>
								<div class="user_cont_r_2b1d">已认证</div>
							</div>
							<div class="user_cont_r_2b1">
								<div class="user_cont_r_2b1a"><c:choose>  <c:when test="${huifuAccount==true}"><img src="images/icon_true.png" /></c:when><c:otherwise><img src="images/icon_false.png" /></c:otherwise></c:choose></div>
								<div class="user_cont_r_2b1b">第三方支付</div>
								<div class="user_cont_r_2b1c">投资前，必须开通第三方支付</div>
								<div class="user_cont_r_2b1d">　<c:choose>  <c:when test="${huifuAccount==true}">${sessionScope.user.huifuAccount}</c:when><c:otherwise><a  target="_blank" href="<%=path%>/huifu/regist?username=${sessionScope.user.username}">立即开通</a></c:otherwise></c:choose></div>
							</div>
							
							<div class="user_cont_r_2b1">
								<div class="user_cont_r_2b1a"><c:choose>  <c:when test="${phoneAuthent==true}"><img src="images/icon_true.png" /></c:when><c:otherwise><img src="images/icon_false.png" /></c:otherwise></c:choose></div>
								<div class="user_cont_r_2b1b">手机认证</div>
								<div class="user_cont_r_2b1c">绑定手机号码，账户资金变动实时通知</div>
								<div class="user_cont_r_2b1d"><c:choose>  <c:when test="${phoneAuthent==true}">${sessionScope.user.phone}</c:when><c:otherwise><a href="<%=path%>/forwordAction/forwordToView?path=saveCenter/phoneAuthent">认证</a></c:otherwise></c:choose></div>
							</div>
							
							<div class="user_cont_r_2b1">
								<div class="user_cont_r_2b1a"><img src="images/icon_true.png" /></div>
								<div class="user_cont_r_2b1b">登陆密码</div>
								<div class="user_cont_r_2b1c">如果忘记登录密码，可使用手机找回</div>
								<div class="user_cont_r_2b1d"><a href="<%=path%>/forwordAction/forwordToView?path=saveCenter/changePwd">修改</a></div>
							</div>
							
							<div class="user_cont_r_2b1">
								<div class="user_cont_r_2b1a"><img src="images/icon_true.png" /></div>
								<div class="user_cont_r_2b1b">支付密码</div>
								<div class="user_cont_r_2b1c">支付密码已启用，默认与初始登陆密码相同，请及时修改</div>
								<div class="user_cont_r_2b1d"><a href="<%=path%>/forwordAction/forwordToView?path=saveCenter/paypwd">修改</a>/<a href="<%=path%>/forwordAction/forwordToView?path=saveCenter/askResetPwd">找回</a></div>
							</div>
							
							<div class="user_cont_r_2b1">
								<div class="user_cont_r_2b1a"><c:choose>  <c:when test="${question==true}"><img src="images/icon_true.png" /></c:when><c:otherwise><img src="images/icon_false.png" /></c:otherwise></c:choose></div>
								<div class="user_cont_r_2b1b">密保问题</div>
								<div class="user_cont_r_2b1c">通过密码问题联系一起一起投平台进行个人投诉建议</div>
								<div class="user_cont_r_2b1d"><a href="<%=path%>/user/pwdsafe">设置</a></div>
							</div>
						</div>
					</div>
			</div>
  </body>
</html>
