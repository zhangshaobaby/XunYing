<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>更改密码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css">
	<link href="<%=path%>/css/user_anquan.css" rel="stylesheet" type="text/css" />
	<Script src="<%=path%>/js/jquery-1.7.js"></Script>	
	<script src="<%=path%>/js/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=path%>/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
    <Script src="<%=path%>/js/secritpwd.js"></Script>
	<Script src="<%=path%>/js/changePwd.js"></Script>
	<script type="text/javascript">
	var basepath="<%=path%>";
	</script>
  </head>  
  <body>
 <div style="background:#FFFFFF;">
					<div class="user_cont_r_1"><span>安全中心</span></div>
					<div class="user_cont_r_2">
						<div class="user_cont_r_2b">

					
					<div class="user_cont_r_2b1">
						<div class="user_cont_r_2b1a">
							<img src="<%=path%>/images/icon_false.png" />
						</div>
						<div class="user_cont_r_2b1b">
							支付密码
						</div>
						<div class="user_cont_r_2b1c">
							重设支付密码
						</div>
						<div class="user_cont_r_2b1d"></div>
					</div>
						<!-- 内容开始 -->
							<div class="user_cont_r_2b_pass">
								<div class="user_cont_r_2b_pass1">
								<form name="form_anquan3" method="post" action="<%=path%>/user/resetPayPassword" id="form_changePwd">
									<input type="hidden" name="id" value="${sessionScope.user.id}" />
								    <input type="hidden" id="username" name="username" value="${sessionScope.user.username}" />
									<div class="user_cont_r_2b_pass1_main">
										<div class="user_cont_r_2b_pass1_main1">
											<div class="user_cont_r_2b_pass1_main1_l">新密码</div>
											<div class="user_cont_r_2b_pass1_main1_c"><input type="password" name="paypwd" id="password1" /></div>
											<div class="user_cont_r_2b_pass1_main1_r"><div id="password1Tip"></div></div>
										</div>
										
										<div class="user_cont_r_2b_pass1_main1">
											<div class="user_cont_r_2b_pass1_main1_l">确认新密码</div>
											<div class="user_cont_r_2b_pass1_main1_c"><input type="password" name="password2" id="password2" /></div>
											<div class="user_cont_r_2b_pass1_main1_r"><div id="password2Tip"></div></div>
										</div>
										
										<div class="user_cont_r_2b_pass1_main3">
											<div class="user_cont_r_2b_pass1_main3_l"></div>
											<div class="user_cont_r_2b_pass1_main3_r">
												<input type="submit" name="button"  id="button" value="下一步" />
											</div>
										</div>
										
										
										
									</div>
								</form>
								</div>
						
								
							</div>
							<!-- 内容结束 -->
						</div>
					</div>
				</div>
  </body>
</html>
